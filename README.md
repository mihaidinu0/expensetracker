# Expense Tracker API - Secure Backend System

[![Java](https://img.shields.io/badge/Java-17-blue)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green)](https://spring.io/projects/spring-boot)
[![Security](https://img.shields.io/badge/Security-JWT-orange)](https://jwt.io/)

A live-deployed expense management API with JWT authentication and role-based access control. Implements secure RESTful endpoints for financial operations with full CI/CD pipeline integration.

**Key Technical Highlights:**  
✅ Spring Security with JWT authentication flow  
✅ Role-based authorization (ADMIN/CLIENT)  
✅ Containerized with Docker & PostgreSQL  
✅ CI/CD pipeline with GitHub Actions  
✅ Paginated API responses & DTO validation

## 🛠 Tech Stack
**Core Technologies**  
Spring Boot 3 | Spring Security | JWT | PostgreSQL

**Infrastructure**  
Docker | GitHub Actions | Digital Ocean Droplet

## 🚀 Core Features
| Feature | Implementation Details |
|---------|------------------------|
| JWT Authentication | Secure token management with refresh mechanism |
| Role-Based Access | Spring Security authorities with UserType enum |
| Expense Management | CRUD operations with validation |
| API Security | CSRF protection & HTTPS-ready configuration |
| CI/CD Pipeline | Automated deployment to production droplet |

## Setup
```bash
# Clone repository
git clone https://github.com/mihaidinu0/expense-tracker.git

# Start services
docker-compose up -d --build

# API available at
http://localhost:8080/api/expenses

Make sure to register a user and login to obtain the Bearer Token to access the secured endpoints.
