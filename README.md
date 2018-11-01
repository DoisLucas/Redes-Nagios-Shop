# Redes-Naggios-Shop

**Ferramenta de monitoramento que enviará alertas via email e SMS ao detectar falhas na redefd geração de relatórios de erros e monitoração do fluxo de conexões.**

**Nessa documentação iremos mostrar passo a passo da execução dos seguintes tópicos:**

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
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac. Fusce ut purus eget nibh feugiat vehicula quis sit amet elit. Praesent quis auctor justo. Curabitur vitae nunc ut ligula dignissim lacinia a id lectus. Nullam posuere sed elit eget gravida. Nunc non mattis odio. Vestibulum tempor tincidunt lorem ut vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis condimentum leo sit amet ante tincidunt pharetra. Vivamus eu tortor molestie, porta libero et, facilisis felis. Ut pulvinar odio arcu, fringilla aliquam ligula malesuada eget. Fusce ante lorem, iaculis id aliquam at, maximus sed magna. Nulla facilisi.

## Monitoramento
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac. Fusce ut purus eget nibh feugiat vehicula quis sit amet elit. Praesent quis auctor justo. Curabitur vitae nunc ut ligula dignissim lacinia a id lectus. Nullam posuere sed elit eget gravida. Nunc non mattis odio. Vestibulum tempor tincidunt lorem ut vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis condimentum leo sit amet ante tincidunt pharetra. Vivamus eu tortor molestie, porta libero et, facilisis felis. Ut pulvinar odio arcu, fringilla aliquam ligula malesuada eget. Fusce ante lorem, iaculis id aliquam at, maximus sed magna. Nulla facilisi.


## Monitoramento
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac. Fusce ut purus eget nibh feugiat vehicula quis sit amet elit. Praesent quis auctor justo. Curabitur vitae nunc ut ligula dignissim lacinia a id lectus. Nullam posuere sed elit eget gravida. Nunc non mattis odio. Vestibulum tempor tincidunt lorem ut vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis condimentum leo sit amet ante tincidunt pharetra. Vivamus eu tortor molestie, porta libero et, facilisis felis. Ut pulvinar odio arcu, fringilla aliquam ligula malesuada eget. Fusce ante lorem, iaculis id aliquam at, maximus sed magna. Nulla facilisi.


## Adicionais

> ### PostgreSQL

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eleifend, lorem quis convallis semper, est tellus dignissim diam, in vulputate mauris metus non justo. Nulla tincidunt orci lacus, non consequat nibh pretium ac. Fusce ut purus eget nibh feugiat vehicula quis sit amet elit. Praesent quis auctor justo. Curabitur vitae nunc ut ligula dignissim lacinia a id lectus. Nullam posuere sed elit eget gravida. Nunc non mattis odio. Vestibulum tempor tincidunt lorem ut vehicula. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis condimentum leo sit amet ante tincidunt pharetra. Vivamus eu tortor molestie, porta libero et, facilisis felis. Ut pulvinar odio arcu, fringilla aliquam ligula malesuada eget. Fusce ante lorem, iaculis id aliquam at, maximus sed magna. Nulla facilisi.

> ### Projeto Spring
- Instalação do Java JDK 8 no Ubuntu:
	- Primeiro é necessario adicionar o PPA (Personal Package Archives) da Oracle e logo em seguida atualizar o repositorio de pacotes e realizar a instalação :
	```bash 
		$ sudo add-apt-repository ppa:webup8team/java
		$ sudo apt-get update
		$ sudo apt-get install oracle-java8-installer
		$ java -version
	```
	 Se tudo ocorrer bem ao executar o comando será mostrada a versão do Maven instalada:
	 
- Instalação Maven
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

- Execução do Projeto
		
	O projeto deverá ser baixado do nosso repositorio do [Github](https://github.com/DoisLucas/Redes-Nagios-Shop)  
	
	Com o terminal aberto é necessario navegar até a pasta do projeto que foi baixado e executar o seguinte comando para subir a aplicação:

```bash 
		 $ mvn spring-boot:run	
```
