INSERT INTO role
(role_id, description, name)
VALUES(0, 'ADMIN', 'ADMIN');

INSERT INTO role
(role_id , description, name)
VALUES(1, 'USER', 'USER');

# Installation manual Soulful Course Application

Soulful Course Application is an where users can follow the courses online and admins can create courses for users to follow. It is easy to use and helps the coach to put all their content in one place and follow the progression of their students.

## This project was built with Java as its language using the following tools:

* IntelliJ IDEA;
* Java Development KIT (JDK) version 11;
* Spring Boot as server side framework;
* Spring Data JPA as the top layer of Hibernate
* Maven as build automation tool and to implement dependencies;
* Hibernate as ORM / JPA implementation;
* PostgreSQL as a database implementation;
* pgAdmin 4 as GUI client;
* Postman to test the  frontend of the application;
* Visual Paradigm to make the UML frameworks;
* MacOS Monterey version 12.2.1 to work on.

## How to set it up

### Step 1. Clone the application
* Option A

There are different ways to clone a Git repository to your JDE (Java Runtime Environment). Here you can choose the HTTPS option and click on the clipboard icon to copy the URL.

picture


When you’ve done that, go to your JDE > click on Get from VCS > paste the URL in the right section > and press Clone underneath.

picture

* Option B

Download ZIP file;
Open your JDE;
Press open or go from the main menu > select File > New > Project from Existing Sources;
Now select the ZIP file you’ve just downloaded and let it load unto your JDE.


## Step 2. Configure database

Go to > src > main > recources > application.properties


> Please paste the application properties below with your database specific properties Spring.url, Username and Password with user details. Change PostgreSQL username and password as per your installation.

> PLEASE NOTE: Replace {{INSERT_NAME}}, {{INSERT_USERNAME}} and {{INSERT_PASSWORD}} with your DATABASE information.

# datasource PostgreSQl
spring.datasource.platform= {{INSERT_NAME}}
spring.datasource.url=jdbc:postgresql://localhost:5432/SoulfullApplication
spring.datasource.username= {{INSERT_USERNAME}}
spring.datasource.password= {{INSERT_PASSWORD}}
spring.datasource.driver-class-name=org.postgresql.Driver
# jpa
spring.jpa.show-sql=true
# generate schema dll to create tables
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
# hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
jwt.token.validity=18000
jwt.signing.key=michelle
jwt.authorities.key=roles
jwt.token.prefix=Bearer
jwt.header.string=Authorization

Go back to your main class: src > main > java > nl > novi > soulfullapplication > SoulfullYogaCourseApplication



Run the application to start the server.

Go back to pgAdmin > open database and you will see all the tables that are created. Go to the tables > right click and choose the Query editor. Here you can paste

>> INSERT INTO role
(role_id, description, name)
VALUES(0, 'ADMIN', 'ADMIN');

INSERT INTO role
(role_id , description, name)
VALUES(1, 'USER', 'USER');

> After inserting these statements run the application to add user and admin role INSERT into role. Now, ‘role’ table will have 2 rows with USER and ADMIN role.

## Step 3. Postman configuration

Now we use postman for the frontend part of our application and to see if all inputs and options are being processed.

Open Postman application;
Click on the file tab or the button next My Workspace and then click import.
Choose the item you want to import and press open.



Now you can test if everything is functioning in the backend correctly.

First you have to register a user to the database.
Click **addUser** > 1. go to body > 2. raw > 3.JSON > 4.add body with user information > 5 send:




**Login** Run a login request by providing the username and password which you created while adding a user. It will generate a token > copy the token > go to getCourseById > header > 1. make a new key > 2. type Authorization > 3. add token > 4. write Bearer before token and click space  > click send > **now you can see the courses that are assigned to you.**



Make sure that when you send in a request that the HTTP Status is always 200 (OK), which means that the request has been successfully processed to the server.

### That’s it !! 

