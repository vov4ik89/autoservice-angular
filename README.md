# AUTO SERVICE APPLICATION
**Description** 📄

Auto Service Application created to save cars, maintenances and orders to the database.
The program is written in Java with Spring Boot and uses PostgreSQL.
For API testing, you can use Swagger, which is also available in the application.
The front-end part uses HTML/CSS, TypeScript and Angular framework.

**Project structure** 📄

The project based on N-Tier architecture:
- DAO layer - CRUD operations with database entities
- DTO layer - representing models on UI layer
- Service layer - business logic of the application
- Controllers - accept requests from the clients and send responses

**Technologies** 📡
- JDK 17
- Spring Boot
- PostgreSQL
- Maven
- Docker
- HTML/CSS
- TypeScript
- Angular


**Instruction to run the Backend** 📄
1. Run the command in the backend directory: `mvn clean package -DskipTests`
2. After that enter the command: `docker-compose up`
3. Now you can test the program in your browser: http://localhost:8080/api/swagger-ui.html

**Instruction to run the Frontend** 📄
1. Install npm in frontend directory: `npm install`
2. After that from frontend directory enter the command: `ng serve`
3. Now you can test the program in your browser:  http://localhost:4200/
