# User Management System (Spring Boot + PostgreSQL)

This is a **User Management System** built with **Spring Boot 3**, **Spring Security (JWT)**, **Spring Data JPA**, and **PostgreSQL**.  
It supports **Admin**, **Manager**, and **User** roles with authentication and role-based authorization.

---

## 🚀 Features
- ✅ User registration and login (JWT-based authentication)  
- ✅ Role-based access (Admin, Manager, User)  
- ✅ Secure password storage with BCrypt  
- ✅ RESTful APIs for managing users and roles  
- ✅ PostgreSQL database integration  
- ✅ Exception handling and validation  

---

## 🛠 Tech Stack
- Java 17  
- Spring Boot 3  
- Spring Security (JWT)  
- Spring Data JPA + Hibernate  
- PostgreSQL  
- Maven  

---

## 📂 Project Structure
src/main/java/com/example/usermanagement
├── controller # REST Controllers
├── entity # JPA Entities (User, Role)
├── exception # Custom exceptions & handlers
├── repository # Spring Data JPA Repositories
├── security # JWT & Security Config
├── service # Service interfaces
│ └── impl # Service implementations
└── UserMgmtApplication.java # Main class

---

## ⚙️ Setup Instructions

### 1. Clone the repo:
git clone https://github.com<your-username>/User-Management-System.git
cd User-Management-System

### 2. Configure Database:
Create a PostgreSQL database (e.g. userdb).
Update src/main/resources/application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

# JWT secret (Base64-encoded)
app.jwt.secret=PUT_YOUR_SECRET_KEY_HERE

### 3. Build & Run:
./mvnw spring-boot:run

## 🔑 Default Login Credentials

| Role    | Email                 | Password   |
| ------- | --------------------- | ---------- |
| Admin   | admin@example.com     | admin123   | 
| Manager | manager@example.com   | manager123 |
| Yash    | Yash@ex.com           | secret13   |

## 📡 API Endpoints

### 🔐 Auth
POST /api/auth/register → Register new user
POST /api/auth/login → Login (returns JWT token)

### 👥 Users
GET /api/admin/users → List all users (Admin only)
POST /api/admin/users → Create new user (Admin only)

## 🧪 Example curl commands
### 1. Login (get JWT token)
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@example.com","password":"admin123"}'
  
### 2. Get all users (Admin only)
curl -X GET http://localhost:8080/api/admin/users \
  -H "Authorization: Bearer <paste_token_here>"
  
##  📜 License

This project was created for educational/demo purposes.
