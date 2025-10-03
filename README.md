# 📝 Blogging Platform - Spring Boot REST API

A full-featured blogging platform backend built with Spring Boot, providing RESTful APIs for user management, blog post creation, and comment functionality. This project demonstrates a professional layered architecture with proper separation of concerns.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white)

---

## 📋 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [System Architecture](#-system-architecture)
- [Database Schema](#-database-schema)
- [API Endpoints](#-api-endpoints)
- [Installation & Setup](#-installation--setup)
- [Project Structure](#-project-structure)
- [What I Learned](#-what-i-learned)
- [Future Enhancements](#-future-enhancements)
- [Contact](#-contact)

---

## ✨ Features

### User Management
- 👤 **User Registration** - Create new user accounts with validation
- 🔐 **User Authentication** - Secure login system
- ✏️ **Profile Management** - Update user information
- 🗑️ **Account Deletion** - Remove user accounts

### Blog Post Management
- ✍️ **Create Posts** - Users can write and publish blog posts
- 📖 **Read Posts** - View all posts or specific posts by ID
- 🔄 **Update Posts** - Edit existing blog posts
- ❌ **Delete Posts** - Remove blog posts
- 🔍 **Search Posts** - Find posts by title, author, or content
- 👨‍💼 **Author-specific Posts** - View all posts by a specific user

### Comment System
- 💬 **Add Comments** - Users can comment on blog posts
- 📝 **View Comments** - Display all comments for a post
- 🗑️ **Delete Comments** - Remove inappropriate comments
- 🔗 **Comment Threading** - Comments linked to specific posts

### Response Handling
- ✅ **Structured Responses** - Consistent API response format with status codes
- ⚠️ **Error Handling** - Custom exception handling with meaningful error messages
- 📊 **Status Management** - Clear success/failure indicators

---

## 🛠️ Tech Stack

### Backend
- **Java 8+** - Core programming language
- **Spring Boot 2.x** - Application framework
- **Spring MVC** - Web framework for REST APIs
- **Spring Data JPA** - Database access and ORM
- **Hibernate** - Object-Relational Mapping
- **MySQL** - Relational database
- **Maven** - Build and dependency management

### Architecture Pattern
- **Layered Architecture** - Separation of concerns
- **DAO Pattern** - Data Access Object design pattern
- **Service Layer** - Business logic separation
- **DTO Pattern** - Data Transfer Objects with ResponseStructure

### Tools
- **Postman** - API testing and documentation
- **Git & GitHub** - Version control
- **MySQL Workbench** - Database management
- **Eclipse/IntelliJ IDEA** - IDE

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────┐
│              Client Layer                        │
│         (Postman / Frontend App)                │
└──────────────────┬──────────────────────────────┘
                   │ HTTP Requests
                   ▼
┌─────────────────────────────────────────────────┐
│           Controller Layer                       │
│  (UserController, PostController,               │
│   CommentController)                            │
│  - Handle HTTP requests                         │
│  - Request validation                           │
│  - Return ResponseStructure                     │
└──────────────────┬──────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────┐
│         Service Interface Layer                  │
│  (Define business logic contracts)              │
└──────────────────┬──────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────┐
│          Service Layer                           │
│  (UserService, PostService, CommentService)     │
│  - Business logic implementation                │
│  - Data validation                              │
│  - Exception handling                           │
└──────────────────┬──────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────┐
│         DAO Interface Layer                      │
│  (Define data access contracts)                 │
└──────────────────┬──────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────┐
│            DAO Layer                             │
│  (Data Access Objects)                          │
│  - Custom query implementation                  │
└──────────────────┬──────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────┐
│         Repository Layer                         │
│  (JPA Repositories)                             │
│  - Database CRUD operations                     │
│  - Custom JPA queries                           │
└──────────────────┬──────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────┐
│          Database Layer                          │
│            (MySQL)                               │
│  - Users, Posts, Comments tables                │
└─────────────────────────────────────────────────┘
```

---

## 💾 Database Schema

### Entity Relationship Diagram

```
┌──────────────────┐
│      User        │
├──────────────────┤
│ user_id (PK)     │
│ username         │
│ email            │
│ password         │
│ created_at       │
└────────┬─────────┘
         │
         │ 1:N (One User has Many Posts)
         │
         ▼
┌──────────────────┐
│      Post        │
├──────────────────┤
│ post_id (PK)     │
│ title            │
│ content          │
│ author_id (FK)   │
│ created_at       │
│ updated_at       │
└────────┬─────────┘
         │
         │ 1:N (One Post has Many Comments)
         │
         ▼
┌──────────────────┐
│    Comment       │
├──────────────────┤
│ comment_id (PK)  │
│ content          │
│ post_id (FK)     │
│ user_id (FK)     │
│ created_at       │
└──────────────────┘
```

### Table Structures

**Users Table:**
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Posts Table:**
```sql
CREATE TABLE posts (
    post_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```

**Comments Table:**
```sql
CREATE TABLE comments (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```

---

## 🔌 API Endpoints

### User Management APIs

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/users/register` | Register new user | `{ username, email, password }` |
| POST | `/api/users/login` | User login | `{ email, password }` |
| GET | `/api/users/{id}` | Get user by ID | - |
| GET | `/api/users` | Get all users | - |
| PUT | `/api/users/{id}` | Update user | `{ username, email }` |
| DELETE | `/api/users/{id}` | Delete user | - |

### Blog Post APIs

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/posts` | Create new post | `{ title, content, authorId }` |
| GET | `/api/posts` | Get all posts | - |
| GET | `/api/posts/{id}` | Get post by ID | - |
| GET | `/api/posts/user/{userId}` | Get posts by user | - |
| PUT | `/api/posts/{id}` | Update post | `{ title, content }` |
| DELETE | `/api/posts/{id}` | Delete post | - |

### Comment APIs

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/comments` | Add comment to post | `{ content, postId, userId }` |
| GET | `/api/comments/post/{postId}` | Get all comments for a post | - |
| GET | `/api/comments/{id}` | Get comment by ID | - |
| DELETE | `/api/comments/{id}` | Delete comment | - |

### Response Structure

All API responses follow this format:

```json
{
    "status": 200,
    "message": "Success",
    "data": { /* response data */ }
}
```

**Error Response:**
```json
{
    "status": 404,
    "message": "User not found",
    "data": null
}
```

---

## 🚀 Installation & Setup

### Prerequisites
- **Java 8 or higher** installed
- **MySQL 8.0** installed and running
- **Maven 3.6+** installed
- **Git** installed

### Step 1: Clone the Repository
```bash
git clone https://github.com/swagatika190/BloggingPlatformSpringProject.git
cd BloggingPlatformSpringProject
```

### Step 2: Configure MySQL Database
```bash
# Login to MySQL
mysql -u root -p

# Create database
CREATE DATABASE blogging_platform;
exit;
```

### Step 3: Update Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/blogging_platform
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8080

# Logging
logging.level.com.example.demo=DEBUG
```

### Step 4: Build the Project
```bash
mvn clean install
```

### Step 5: Run the Application
```bash
mvn spring-boot:run
```

Or run from IDE:
```
Right-click on BloggingPlatformApplication.java → Run As → Java Application
```

### Step 6: Test the APIs

The application will start on:
```
http://localhost:8080
```

Test using Postman or any REST client.

---

## 📁 Project Structure

```
BloggingPlatformSpringProject/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── controller/          # REST Controllers
│   │   │       │   ├── UserController.java
│   │   │       │   ├── PostController.java
│   │   │       │   └── CommentController.java
│   │   │       │
│   │   │       ├── service/             # Business Logic
│   │   │       │   ├── UserService.java
│   │   │       │   ├── PostService.java
│   │   │       │   └── CommentService.java
│   │   │       │
│   │   │       ├── serviceInterface/    # Service Contracts
│   │   │       │   ├── UserServiceInterface.java
│   │   │       │   ├── PostServiceInterface.java
│   │   │       │   └── CommentServiceInterface.java
│   │   │       │
│   │   │       ├── dao/                 # Data Access Objects
│   │   │       │   ├── UserDAO.java
│   │   │       │   ├── PostDAO.java
│   │   │       │   └── CommentDAO.java
│   │   │       │
│   │   │       ├── daoInterface/        # DAO Contracts
│   │   │       │   ├── UserDAOInterface.java
│   │   │       │   ├── PostDAOInterface.java
│   │   │       │   └── CommentDAOInterface.java
│   │   │       │
│   │   │       ├── repositories/        # JPA Repositories
│   │   │       │   ├── UserRepository.java
│   │   │       │   ├── PostRepository.java
│   │   │       │   └── CommentRepository.java
│   │   │       │
│   │   │       ├── entity/              # Database Entities
│   │   │       │   ├── User.java
│   │   │       │   ├── Post.java
│   │   │       │   ├── Comment.java
│   │   │       │   └── ResponseStructure.java
│   │   │       │
│   │   │       ├── exceptions/          # Custom Exceptions
│   │   │       │   ├── UserNotFoundException.java
│   │   │       │   ├── PostNotFoundException.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       └── BloggingPlatformApplication.java  # Main Class
│   │   │
│   │   └── resources/
│   │       └── application.properties   # Configuration
│   │
│   └── test/                            # Test Cases
│
├── pom.xml                              # Maven Dependencies
└── README.md                            # This file
```

---

## 💡 Key Design Patterns Used

### 1. Layered Architecture
Separates concerns into distinct layers:
- **Controller Layer** - HTTP handling
- **Service Layer** - Business logic
- **DAO Layer** - Data access logic
- **Repository Layer** - Database operations

### 2. DAO Pattern
Encapsulates data access logic, providing an abstraction over database operations.

### 3. Interface Segregation
Both Service and DAO layers use interfaces, promoting loose coupling and testability.

### 4. DTO Pattern
`ResponseStructure` acts as a Data Transfer Object for standardized API responses.

### 5. Exception Handling
Custom exceptions with global exception handler for consistent error responses.

---

## 📚 What I Learned

Building this blogging platform helped me gain expertise in:

✅ **Spring Boot Development:**
- Creating RESTful APIs with Spring MVC
- Implementing layered architecture for maintainability
- Using Spring Data JPA for database operations
- Dependency injection and inversion of control

✅ **Database Design:**
- Designing normalized database schemas
- Implementing One-to-Many relationships (User → Posts → Comments)
- Writing efficient JPA queries
- Managing foreign key constraints and cascading deletes

✅ **Software Architecture:**
- Implementing DAO pattern for data abstraction
- Separating concerns across multiple layers
- Using interfaces for loose coupling
- Creating reusable response structures

✅ **API Design:**
- Designing RESTful endpoint conventions
- Implementing consistent response formats
- Handling errors gracefully with custom exceptions
- API documentation best practices

✅ **Best Practices:**
- Code organization and package structure
- Exception handling strategies
- Input validation
- Git version control

---

## 🔮 Future Enhancements

- [ ] **Authentication & Authorization**
  - Implement JWT-based authentication
  - Role-based access control (Admin, Author, Reader)
  
- [ ] **Advanced Features**
  - Like/Unlike posts
  - Post categories and tags
  - User following system
  - Rich text editor support
  - Image upload for posts
  
- [ ] **Search & Filter**
  - Full-text search for posts
  - Filter by date, author, category
  - Pagination for large datasets
  
- [ ] **Frontend Integration**
  - React.js frontend
  - Responsive design
  - Real-time notifications
  
- [ ] **Deployment**
  - Docker containerization
  - Deploy on AWS/Heroku
  - CI/CD pipeline with GitHub Actions
  
- [ ] **Performance**
  - Caching with Redis
  - Database indexing optimization
  - API rate limiting

---

## 🧪 Testing the APIs

### Example: Create a User

**Request:**
```bash
POST http://localhost:8080/api/users/register
Content-Type: application/json

{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123"
}
```

**Response:**
```json
{
    "status": 201,
    "message": "User registered successfully",
    "data": {
        "user_id": 1,
        "username": "john_doe",
        "email": "john@example.com",
        "created_at": "2024-11-15T10:30:00"
    }
}
```

### Example: Create a Blog Post

**Request:**
```bash
POST http://localhost:8080/api/posts
Content-Type: application/json

{
    "title": "Getting Started with Spring Boot",
    "content": "Spring Boot is an amazing framework...",
    "authorId": 1
}
```

**Response:**
```json
{
    "status": 201,
    "message": "Post created successfully",
    "data": {
        "post_id": 1,
        "title": "Getting Started with Spring Boot",
        "content": "Spring Boot is an amazing framework...",
        "author_id": 1,
        "created_at": "2024-11-15T11:00:00"
    }
}
```

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👤 Contact

**Swagatika Samal**

- 💼 LinkedIn: [linkedin.com/in/swagatika-samal-7762432ba](https://linkedin.com/in/swagatika-samal-7762432ba)
- 📧 Email: swagatika91575@gmail.com
- 🐙 GitHub: [@swagatika190](https://github.com/swagatika190)

---

## ⭐ Show Your Support

If you found this project helpful, please give it a ⭐ star on GitHub!

---

**Built with ❤️ by Swagatika Samal**
