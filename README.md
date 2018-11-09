# Redes-Naggios-Shop

**Ferramenta de monitoramento que enviará alertas via email ao detectar falhas na redefd geração de relatórios de erros e monitoração do fluxo de conexões.**

**Nessa documentação iremos mostrar passo a passo da execução dos seguintes tópicos:**

- [ ] **Instalação do Ubuntu 16.04 LTS**
- [X] **Instalação e configuração do Nagios**
- [ ] **Monitoramento dos dispositivos da rede**
- [ ] **Alertas de erros via e-mail**
- [X] **Adicionais**
	- [X] **Instalação e configuração do banco de dados PostgreSQL**
	- [X]  **Habilitar o projeto Spring (Java)**

## Ubuntu
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac. Fusce ut purus eget nibh feugiat vehicula quis sit amet elit. Praesent quis auctor justo. Curabitur vitae nunc ut ligula dignissim lacinia a id lectus. Nullam posuere sed elit eget gravida. Nunc non mattis odio. Vestibulum tempor tincidunt lorem ut vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis condimentum leo sit amet ante tincidunt pharetra. Vivamus eu tortor molestie, porta libero et, facilisis felis. Ut pulvinar odio arcu, fringilla aliquam ligula malesuada eget. Fusce ante lorem, iaculis id aliquam at, maximus sed magna. Nulla facilisi.
## Nagios
### SELinux
Este guia leva em cosideração que o SELinux está desativado, pois esta é a configuração padrão do Ubuntu. Se deseja saber se está instalado, execute o comando:
```bash
sudo dpkg -l selinux*    
```
### Pré-requisitos
Execute o comando a seguir para atualizar os pacotes instalados e instalar os pré-requisitos:
```bash
sudo apt-get update
sudo apt-get install -y autoconf gcc libc6 make wget unzip apache2 php libapache2-mod-php7.0 libgd2-xpm-dev
```
### Baixe o arquivo fonte
Execute os passos  a seguir criar a pasta onde o Nagios vai ser instalado, navegar para dita pasta, baixar o arquivo compactado do Nagios e extrai os arquivos compactados
```bash
mkdir /home/{user}/nagios 
cd /home/{user}/nagios 
wget -O nagioscore.tar.gz https://github.com/NagiosEnterprises/nagioscore/archive/nagios-4.4.1.tar.gz  
tar xzf nagioscore.tar.gz
```
 
 ### Compile o Nagios
 Rode os seguintes comandos para poder instalar o Nagios
```bash
cd /home/user/nagios/nagioscore-nagios-4.4.1
sudo ./configure --with-httpd-conf=/etc/apache2/sites-enabled
sudo make all
```
 
###  Crie o primeiro usuário e o grupo
Esses comandos irão o usuário e o grupo *nagios*. O usuário *www-data* também será adicionado para o grupo *nagios*
```bash
sudo make install-groups-users
sudo usermod -a -G nagios www-data
```
### Instalação dos arquivos binários
Este comando irá instalar os arquivos binários, CGIs e arquivos HTML.
```bash
sudo make install
```
### Instalação do Daemon
Este comando irá instalar o serviço e irá configurar o Nagios para iniciar ao ligar o dispositivo
```bash
sudo make install-daemoninit
```
### Instalação do modo de comando
Este comando irá instalar e configurar o arquivo que permite execução de comandos externamente
```bash
sudo make install-commandmode
```
### Instalação dos arquivos de configuração
Este comando irá instalar os arquivos padrão de configuração do Nagios. O Nagios precisa destes arquivos para poder iniciar o serviço.
```bash
sudo make install-config
```
### Instalação dos arquivos de configuração do Apache
Estes comandos instalaram os arquivos de configuração do servidor web do Apache e as configurações do Apache
```bash
sudo make install-webconf
sudo a2enmod rewrite
sudo a2enmod cgi
```

### Configuração do firewall
Também é preciso permitir a entrada de tráfego pela porta 80 para que se possa acessar a interface web do Nagios.
```bash
sudo ufw allow Apache
sudo ufw reload
``` 
### Criação do usuário admin
Este comando irá criar um usuário Apache para o acesso ao Nagios. Ele criará o usuário chamado *nagiosadmin* e pedirá a inserção da senha
```bash

sudo htpasswd -c /usr/local/nagios/etc/htpasswd.users nagiosadmin 

```

### Inicialização do Apache e do Daemon
Estes comando irão inicializar o Apache e o Daemon
```bash

sudo systemctl apache2.service restart
sudo systemctl nagios.service start

```

Agora você pode acessar o nagios clicando [aqui](http://localhost/nagios) ou digitando localhost/nagios no seu navegador

## Monitoramento
Após a instalação completa do nagios é necessario preparar a estrutura dos arquivos que iremos utilizar:

```bash
$ cd /usr/local/nagios/etc
$ sudo mkdir network // Pasta onde colocaremos os hosts de rede que iremos mapear;
$ sudo mkdir windows // Pasta onde colocaremos os hosts windowns.
$ sudo mkdir linux   // Pasta onde colocaremos os hosts linux.
```
Em seguida é necessario criar um template onde contém o padrão de configuração para cada tipo de host (Windown, Linux ou Rede Network):

```bash

$ cd /usr/local/nagios/etc
$ sudo vi templateNP.cfg

```
E colar o seguinte script no templateNP.cfg:

```
### Template "SERVICOS" de Rede e ICMP 

define service{ 	
	name 					TemplateService 
	active_checks_enabled 			1 
	notifications_enabled 			1 
	passive_checks_enabled 			0 
	retain_status_information 		1 
	is_volatile 				0 
	max_check_attempts 			3 
	check_interval 				5 
	normal_check_interval 			5 
	retry_check_interval 			5 
	check_period 				24x7 
	notification_interval 			0 
	notification_period 			24x7 ; 24hrs / 7 dias da semana
	notification_options 			u,c,r ; U = Unknown C = Critical R = Recovery
	register 				0 
} 

#### Template "HOST" Windows 


define host{ 
	name 					TemplateHostWindows 
	max_check_attempts 			3 
	check_interval 				5 
	retry_check_interval 			5 
	active_checks_enabled 			1 
	passive_checks_enabled 			0 
	check_period 				24x7 
	retain_status_information 		1 
	notification_interval 			60 ; Tempo de intervalo entre o envio do alerta
	notification_period 			24x7 
	notification_options 			d,u,r ; D = Down U = Unknowm R = Recovery
	register 				0 
} 

### Template "HOST" Linux 

define host{ 
	name 					TemplateHostLinux 
	check_command 				check-host-alive
	max_check_attempts 			3 
	check_interval 				5 
	retry_check_interval 			5 
	active_checks_enabled 			1 
	check_period 				24x7 
	retain_status_information 		1 
	notification_interval 			60 
	notification_period 			24x7 
	notification_options 			d,u,r 
} 

### Template "HOST" Rede 

define host{ 
	name 					TemplateHostRede 
	check_command 				check-host-alive 
	max_check_attempts 			2 
	check_interval 				5 
	retry_check_interval 			5 
	active_checks_enabled 			1 
	check_period 				24x7 
	retain_status_information 		1 
	notification_interval 			60 
	notification_period 			24x7 
	notification_options 			d,u,r 
} 

# 'check_tcp' command definition 
define command{ 
	command_name check_tcpNP 
	command_line $USER1$/check_tcp -H $HOSTADDRESS$ -p $ARG1$ -w $ARG2$ -c $ARG3$
}

```

Após criarmos as pastas e o arquivo citado acima, é necessário referência-los no arquivo principal do nagios, o nagios.cfg, colando o comando abaixo logo após a linha 18 do arquivo:

```
cfg_dir=/usr/local/nagios/etc/linux
cfg_dir=/usr/local/nagios/etc/windows
cfg_dir=/usr/local/nagios/etc/network
cfg_file=/usr/local/nagios/etc/templatesNP.cfg

```

Agora iremos testar o funcionamento do nagios mapeando um site, nesse caso o site www.google.com, para isso criaremos um arquivo cfg dentro da pasta network que criamos anteriormente

```
$ cd /usr/local/nagios/etc
$ sudo vi google_site.cfg

```

E colocaremos o seguinte script dentro do arquivo recém criado:

```

### Definindo o host

define host{ 
	host_name Google_Site 
	use TemplateHostRede ; Referenciando ao template que criamos acima 
	alias Site do Terra ; Apelido
	address www.terra.com ; Endereço/IP
	contact_groups admins ; Padrão Nagios
} 


### Definindo os serviços que iremos monitorar

define service{ 
	use TemplateService 
	host_name Google_Site ; Deve ser o mesmo nome do host acima
	service_description PING-Disponibilidade ; Descrição do Serviço a ser monitorado
	check_command check_ping!3000.0,80%!5000.0,100%!; Plugin e Parametros;
	contact_groups admins 
}

define service{ 
	use TemplateService 
	host_name Site_Terra
	service_description HTTP
	check_command check_tcpNP!80!1!2! 
	contact_groups admins 
}


```
Com isso adicionamos um host com 2 serviços que iremos mapear, para visualizar o host é necessario reiniciar o serviço do nagios e logo em seguida acessar o portal do nagios clicando [aqui](http://localhost/nagios) ou digitando localhost/nagios no seu navegador e clicar em hosts.

Comando para reiniciar o servico do nagios:

```bash

$ sudo systemctl nagios.service restart

```

## Alertas
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac.

## Adicionais

## PostgreSQL

### Instalação

Os repositórios padrão do Ubuntu contém os pacotes do Postgres, assim podemos instalá-lo facilmente utilizando o sistema de pacotes `apt`
```bash 
$ sudo apt-get update
$ sudo apt-get install postgresql postgresql-contrib
```

O procedimento de instalação criou um usuário chamado  `postgres`  que é associado com o role padrão do Postgres. Para usar o Postgres, podemos fazer login nessa conta.

Alterne para a conta  `postgres`  no seu servidor digitando:
```bash 
$ sudo -i -u postgres
```

Agora você pode acessar o prompt do Postgres imediatamente digitando:
```bash 
$ psql
```
### Configuração:

Apos a instalação é necessario criar um banco de dados chamado "ecommerce" e alterar a senha do usuario postgres para "redes123" com os seguintes comandos:
```bash 
$ psql -U postgres
$ create database ecommerce
$ alter user postgres with encrypted password 'redes123'; 
```

## Projeto Spring

### Instalação do Java JDK 8 no Ubuntu:
Primeiro é necessario adicionar o PPA (Personal Package Archives) da Oracle e logo em seguida atualizar o repositorio de pacotes e realizar a instalação :
```bash 
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
$ java -version
```
Se tudo ocorrer bem ao executar o comando será mostrada a versão do Maven instalada:
	 
### Instalação Maven
Primeiro é necessario pegar o pacote do Maven disponivel com o comando abaixo:
```bash 
$ sudo apt-cache search maven
``` 
Logo em seguida executar o comando abaixo para instalar a ultima versão do Apache Maven e apos a execução. 

 ```bash 
$ sudo apt-get install maven
```
		 
Se tudo ocorrer bem ao executar o comando abaixo será mostrada a versão do Maven instalada: 
```bash 
$ mvn -version	
```

### Execução do Projeto
		
O projeto deverá ser baixado do nosso repositorio do [Github](https://github.com/DoisLucas/Redes-Nagios-Shop)  
	
Com o terminal aberto é necessario navegar até a pasta do projeto que foi baixado e executar o seguinte comando para subir a aplicação:

```bash 
$ mvn spring-boot:run	
```
	
	
