# Setup and how to run guide

## Pre-requisite
1. JAVA 11
2. MySQL 8
3. Maven 3+
4. Any IDE (STS / Eclipse / IntelliJ Idea)

## Steps to import the project in STS
1. Open STS.
2. Go to File -> Import
3. Search for `Existing maven Projects`. Select this option.
4. Select the path of the directory in which the source code of the project is present and click next.

After this step, STS will start importing the project and will download the required dependencies. This might take a while.
 
## Configure the database
This application uses MySQL 8 as a database to store flights and users data. Make sure that you hava MySQL 8 installed in your system.

Open MySQL Workbench and create a new database schema which will be used by this application. Run the below command to create a new database

> `CREATE DATABASE article_backend CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`


Once the database is created, then make sure the correct properties are present in the source code to connect to the database. To check the properties open the file `application.properties` present in the `src->main->resources` directory. 

In the file, there are 3 properties for database connection as mentioned below
```
spring.datasource.url=jdbc:mysql://localhost/${DATABASE_SCHEMA}?useSSL=false
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

Replace the values `${DATABASE_USERNAME}`, `${DATABASE_PASSWORD}`, `${DATABASE_SCHEMA}`  with correct username, password and database schema respectively for the local MySQL database.

## Build Project

To build the project from STS, follow below steps:

1. Right click on the project name in Project Explorer.
2. Select the option `Run as` and then select the option `Maven Build`
3. In the prompt, in the goals field enter `clean install`.
4. Click `Apply` and then `Run`

This will create a jar file for the project. 

You can also build the project from command line. To build the project from command line, run the following command from the terminal inside project directory.

> `mvn clean install`

## Start Server
To start a spring boot server, right click on the project and then select the option "Run as" and in the run as option choose `"Run as Spring boot application"`.
This will start the server and server will be running on the `8080` port.



