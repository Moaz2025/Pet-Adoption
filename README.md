# Pet Adoption System  

## Overview  
This project is a **Pet Adoption System** developed as part of a **Database Course**. The application facilitates the adoption process by enabling users to explore, manage, and adopt pets efficiently. It uses **Spring Boot** for backend logic and database operations and **React** for an interactive and responsive user interface.  

---

## Features  
- **User Roles**:  
  - Admin: Manage pets, adoption requests, and users.  
  - Adopters: Explore available pets and submit adoption requests.  
- **Pet Operations**:  
  - CRUD operations for pets (Add, View, Update, and Delete).  
- **Adoption Workflow**:  
  - View adoption requests and approve/reject them.  
- **Search and Filters**:  
  - Filter pets based on species, breed, age, and availability.  
- **Database Integration**:  
  - Efficient storage and retrieval of data with relational mapping.  

---

## Tech Stack  

### Backend  
- **Spring Boot**: API endpoints, business logic, and database interaction.  
- **Spring Security**: Handles user authentication and role-based authorization.  
- **JPA/Hibernate**: Database ORM.  
- **MySQL**: Relational database for data storage.  

### Frontend  
- **React**: Dynamic and user-friendly interface.  
- **Axios**: For making REST API calls.  

---

## Database Design  
The database includes key entities such as:  
- **Users**: Admins and adopters.  
- **Pets**: Information about the pets available for adoption.  
- **Adoption Requests**: Tracks the adoption process from request to approval.  

**ER Diagram:**  
*(Include a diagram if available or describe key relationships, e.g., one-to-many between pets and adoption requests.)*  

---

## Getting Started  

### Prerequisites  
1. Install [Java 11 or higher](https://adoptopenjdk.net/).  
2. Install [Node.js](https://nodejs.org/).  
3. Install [MySQL/PostgreSQL](https://www.mysql.com/) or your preferred database system.  

### ERD Model
![database](https://github.com/user-attachments/assets/befb45a1-2cc4-4d6f-9f88-fe431a39cf31)

### Relational Model
![image](https://github.com/user-attachments/assets/5de60c50-13c9-4c1b-8f75-fb600a14a7c8)


