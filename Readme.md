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

![Schermafbeelding 2022-02-25 om 16 20 56](https://user-images.githubusercontent.com/87495212/155803585-e5a7a180-9a32-47ab-aaa8-ce1f92346d6c.png)

When you’ve done that, go to your JDE > click on Get from VCS > paste the URL in the right section > and press Clone underneath.

![Schermafbeelding 2022-02-25 om 16 21 45](https://user-images.githubusercontent.com/87495212/155803586-2c887d8d-2356-4581-9c7c-c18b06df910a.png)

* Option B

Download ZIP file;
Open your JDE;
Press open or go from the main menu > select File > New > Project from Existing Sources;
Now select the ZIP file you’ve just downloaded and let it load unto your JDE.

![Schermafbeelding 2022-02-25 om 16 30 41](https://user-images.githubusercontent.com/87495212/155803588-b5fc3388-3b5d-4522-b0f0-3c58bee85f13.png)

## Step 2. Configure database

Go to > src > main > recources > application.properties

![Schermafbeelding 2022-02-25 om 14 11 37](https://user-images.githubusercontent.com/87495212/155803575-14f16a2c-d9aa-4b89-9eb6-53c99cd8074f.png)

> Please paste the application properties below with your database specific properties Spring.url, Username and Password with user details. Change PostgreSQL username and password as per your installation.

> PLEASE NOTE: Replace {{INSERT_NAME}}, {{INSERT_USERNAME}} and {{INSERT_PASSWORD}} with your DATABASE information.

#### datasource PostgreSQl
spring.datasource.platform= {{INSERT_NAME}} <br />
spring.datasource.url=jdbc:postgresql://localhost:5432/SoulfullApplication <br />
spring.datasource.username= {{INSERT_USERNAME}} <br />
spring.datasource.password= {{INSERT_PASSWORD}} <br />
spring.datasource.driver-class-name=org.postgresql.Driver <br />
#### jpa
spring.jpa.show-sql=true <br />
#### generate schema dll to create tables
spring.jpa.generate-ddl=true <br />
spring.jpa.hibernate.ddl-auto=update <br />
#### hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect <br />
jwt.token.validity=18000 <br />
jwt.signing.key=michelle <br />
jwt.authorities.key=roles <br />
jwt.token.prefix=Bearer <br />
jwt.header.string=Authorization <br />

![Schermafbeelding 2022-02-25 om 16 40 04](https://user-images.githubusercontent.com/87495212/155803589-17220ef0-c063-426d-af30-2af5dc388d21.png)

Go back to your main class: src > main > java > nl > novi > soulfullapplication > SoulfullYogaCourseApplication

![Schermafbeelding 2022-02-25 om 14 55 00](https://user-images.githubusercontent.com/87495212/155803581-3c1542e8-1dea-412d-aef3-89003feac972.png)

Run the application to start the server.

Go back to pgAdmin > open database and you will see all the tables that are created. Go to the tables > right click and choose the Query editor. Here you can paste

![Schermafbeelding 2022-02-25 om 17 23 52](https://user-images.githubusercontent.com/87495212/155804926-dff5a4a6-7a39-46e8-b482-b0e6723bcb74.png)

>> INSERT INTO role <br />
(role_id, description, name) <br />
VALUES(0, 'ADMIN', 'ADMIN'); <br />

>> INSERT INTO role <br />
(role_id , description, name) <br />
VALUES(1, 'USER', 'USER'); <br />

> After inserting these statements run the application to add user and admin role INSERT into role. Now, ‘role’ table will have 2 rows with USER and ADMIN role.

## Step 3. Postman configuration

Now we use postman for the frontend part of our application and to see if all inputs and options are being processed.

Open Postman application;
Click on the file tab or the button next My Workspace and then click import.
Choose the item you want to import and press open.

![Schermafbeelding 2022-02-25 om 15 34 20](https://user-images.githubusercontent.com/87495212/155803582-f1b8696d-b43a-483d-95d4-c471bb127bf4.png)

Now you can test if everything is functioning in the backend correctly.

First you have to register a user to the database.
Click **addUser** > 1. go to body > 2. raw > 3.JSON > 4.add body with user information > 5 send:

![Schermafbeelding 2022-02-25 om 16 04 07](https://user-images.githubusercontent.com/87495212/155803583-c212ad0a-3e04-4d7b-87a1-0d0fe8231a22.png)

**Show Course** Run a login request by providing the username and password which you created while adding a user. It will generate a token > copy the token > go to getCourseById > 1. header > 2. make a new key > 3. type Authorization > 4. add token > 5. write Bearer before token and click space  > 6. click send > **now you can see the courses that are assigned to you.**

![Schermafbeelding 2022-02-25 om 17 30 05](https://user-images.githubusercontent.com/87495212/155806336-30da440f-2ca5-4d13-b983-6ac4ac876403.png)

Make sure that when you send in a request that the HTTP Status is always 200 (OK), which means that the request has been successfully processed to the server.

![Schermafbeelding 2022-02-25 om 17 37 06](https://user-images.githubusercontent.com/87495212/155806424-bdf73200-5061-4aeb-8d63-c30ec7790db8.png)

### That’s it !! 

