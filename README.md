# Spring Boot Dashboard Web Application

A full-stack web application built with **Spring Boot**, **Thymeleaf**, and **Spring Security**. This app provides a responsive dashboard for user management, transactions, and notifications.

---

## Features
- **User Management**
  - Registration and login
  - User profiles (name, email, phone)

- **Transactions**
  - View and categorize transactions
  - Filter by date

- **Notifications**
  - User-specific notifications
  - Mark as read/unread

- **Dashboard**
  - Dynamic Thymeleaf templates
  - Responsive design for mobile and desktop

- **Security**
  - Authentication & authorization
  - Password encryption
  - Session management
---

## Technologies Used

* **Backend:** Spring Boot, Spring Security, Spring Data JPA
* **Frontend:** Thymeleaf, HTML, CSS, JavaScript
* **Database:** MySQL
* **Build Tool:** Maven
* **Server:** Embedded Tomcat

---

## Prerequisites

Make sure you have the following installed:

* Java 17 or higher
* Maven 3.8 or higher
* MySQL or H2 (optional for testing)
* Git

---

## Installation

1. **Clone the repository**

```bash
https://github.com/sushantsunar/Personal-Finance-Management-System.git
cd Personal-Finance-Management-System
```

2. **Configure database**

* Open `src/main/resources/application.properties`
* Update your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

> **Optional:** For testing without MySQL, you can use H2 in-memory database:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop
```

3. **Build and run the application**

```bash
mvn clean install
mvn spring-boot:run
```

4. **Access the application**

* Open your browser and go to [http://localhost:8080](http://localhost:8080)

---

## Project Structure

```
src/main/java
├── controller       # Handles HTTP requests
├── model            # Entity classes (User, Transaction, Notification)
├── repository       # JPA repositories
├── security         # Spring Security configuration
└── service          # Business logic

src/main/resources
├── templates        # Thymeleaf HTML templates
└── static           # CSS, JS, images
```

---

## Usage

* Register a new user or log in with an existing account
* Admin users can manage all users and transactions
* Users can view their profile, transactions, and notifications
* Mobile-friendly interface with a toggleable chat/sidebar feature

---

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add new feature'`)
5. Push to your branch (`git push origin feature/your-feature`)
6. Open a Pull Request

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
