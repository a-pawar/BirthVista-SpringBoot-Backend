# 🍼 BirthVista - Digital Birth Certificate System

BirthVista is a Spring Boot-based web application designed to streamline the birth certificate application process. It supports role-based login, application submission, admin approval, real-time notifications via WebSocket, and more.

## 🚀 Features

- 👥 User & Admin Role-Based Authentication  
- 📝 Birth Certificate Application Submission  
- 🔐 Secure Login Using Spring Security (Session-Based)  
- ✅ Admin Approval/Rejection of Applications  
- 📢 Real-Time Notifications via WebSocket  
- 🔎 Filter Applications by Status  
- 📄 PDF Generation of Certificates  
- 📊 Swagger Documentation for API  
- 🌐 RESTful API Structure  
- 🗃️ MySQL Database Integration  

## 🧑‍💻 Tech Stack

- **Backend**: Spring Boot, Spring Security, Spring WebSocket  
- **Frontend**: HTML, CSS, JavaScript (for WebSocket demo)  
- **Database**: MySQL  
- **PDF Generation**: iText  
- **API Docs**: SpringDoc OpenAPI (Swagger UI)  

## 📦 Project Structure

com.myproject.learning.BirthVista
│
├── config # Spring Security & WebSocket configuration
├── controller # REST and WebSocket controllers
├── dto # DTOs for request/response objects
├── enums # Enum classes like Role, Status
├── exception # Global exception handlers
├── model # JPA Entity classes (User, Application)
├── repository # Spring Data JPA repositories
├── service # Business logic and service layer
├── utils # Utility classes (if any)
└── BirthVistaApplication.java


### Prerequisites

- Java 17
- Maven
- MySQL Server
  
📑 Swagger API Docs
Visit: http://localhost:8080/swagger-ui/index.html

💬 WebSocket Notification Demo
Visit: http://localhost:8080/index.html
(Ensure WebSocket is configured and running)

🔐 User Roles
ROLE_USER: Can register, login, and apply for a birth certificate

ROLE_ADMIN: Can approve/reject applications and view all submissions

🧾 Database Tables Overview
users Table
id, first_name, last_name, email, password, role, etc.

applications Table
application_id, full_name, status, agent_email, apply_date, etc.

Application is linked to User using email (agent_email as foreign key)

📜 License
This project is built for educational purposes as part of a Spring Boot backend training program.

