# Redes-Naggios-Shop

**Ferramenta de monitoramento que enviará alertas via email e SMS ao detectar falhas na redefd geração de relatórios de erros e monitoração do fluxo de conexões.**

**Nessa documentação iremos mostrar passo a passo da execução dos seguintes tópicos:**

- **Instalação do Ubuntu 16.04 LTS**
- **Instalação e configuração do Nagios**
- **Monitoramento dos dispositivos da rede**
- **Alertas de erros via e-mail e SMS**
- [ ] **Instalação do Ubuntu 16.04 LTS**
- [ ] **Instalação e configuração do Nagios**
- [ ] **Monitoramento dos dispositivos da rede**
- [ ] **Alertas de erros via e-mail e SMS**
- [ ] **Adicionais**
	- [ ] **Instalação e configuração do banco de dados PostgreSQL**
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

Agora você pode acessar o nagios clicando (aqui)[localhost/nagios] ou digitando localhost/nagios no seu navegador

## Monitoramento
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac. Fusce ut purus eget nibh feugiat vehicula quis sit amet elit. Praesent quis auctor justo. Curabitur vitae nunc ut ligula dignissim lacinia a id lectus. Nullam posuere sed elit eget gravida. Nunc non mattis odio. Vestibulum tempor tincidunt lorem ut vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis condimentum leo sit amet ante tincidunt pharetra. Vivamus eu tortor molestie, porta libero et, facilisis felis. Ut pulvinar odio arcu, fringilla aliquam ligula malesuada eget. Fusce ante lorem, iaculis id aliquam at, maximus sed magna. Nulla facilisi.



## Adicionais

> ### PostgreSQL

- ### Instalação

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
-  ### Configuração:
	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. 


> ### Projeto Spring
- ### Instalação do Java JDK 8 no Ubuntu:
	- Primeiro é necessario adicionar o PPA (Personal Package Archives) da Oracle e logo em seguida atualizar o repositorio de pacotes e realizar a instalação :
	```bash 
		$ sudo add-apt-repository ppa:webupd8team/java
		$ sudo apt-get update
		$ sudo apt-get install oracle-java8-installer
		$ java -version
	```
	 Se tudo ocorrer bem ao executar o comando será mostrada a versão do Maven instalada:
	 
-  ### Instalação Maven
	-  Primeiro é necessario pegar o pacote do Maven disponivel com o comando abaixo:
	 ```bash 
		 $ sudo apt-cache search maven
	``` 
	-  Logo em seguida executar o comando abaixo para instalar a ultima versão do Apache Maven e apos a execução. 

	 ```bash 
		 $ sudo apt-get install maven
	```
		 
	- Se tudo ocorrer bem ao executar o comando abaixo será mostrada a versão do Maven instalada: 
	```bash 
		$ mvn -version	
	```

-  ### Execução do Projeto
		
	O projeto deverá ser baixado do nosso repositorio do [Github](https://github.com/DoisLucas/Redes-Nagios-Shop)  
	
	Com o terminal aberto é necessario navegar até a pasta do projeto que foi baixado e executar o seguinte comando para subir a aplicação:

	```bash 
		$ mvn spring-boot:run	
	```
	
	
