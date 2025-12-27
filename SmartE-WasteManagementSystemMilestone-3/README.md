Smart E-Waste Management System

📌 Description

A role-based backend system for managing e-waste pickup requests.
Clients submit waste product details, Admins approve and assign pickups, and Pickup personnel mark products as PICKED_UP.
Secured using JWT Bearer authentication with Swagger API documentation and email notifications.


🚀 Features

User Registration & Login (Email + Password) 🔐

JWT Authentication (Bearer Token) 🛡️

Submit E-Waste Products (Client/User) 🗑️

Admin Controls 👑

View users 👥

Delete user ❌

Approve pickup ✅

Schedule pickup 📅

Assign pickup person 🧍

Pickup Person Controls 🚚

View assignments 📦

Mark as PICKED_UP ✔️

Email Notifications ✉️

Registration success 🎉

Pickup approval 📩

Swagger UI API Docs 📖


🛠️ Tech Stack

| Layer         | Technology                               |
| ------------- | ---------------------------------------- |
| **Language**  | Java 23                                  |
| **Framework** | Spring, Spring Security, Spring Data JPA |
| **Database**  | Oracle Database XE                       |
| **Security**  | JWT (io.jsonwebtoken)                    |
| **API Docs**  | Swagger UI (OpenAPI v3)                  |
| **Mail**      | Gmail SMTP (JavaMailSender)              |
| **Libraries** | Lombok, Stream API, JPA                  |




com.infosysspringboard.milestone3
│── controller
│   ├── UserController.java 🧑
│   ├── WasteProductController.java 🗑️
│   ├── AdminController.java 👑
│   ├── PickupController.java 🚚
│── entity
│   ├── User.java 👤
│   ├── WasteProduct.java 📦
│── service
│   ├── IUserService.java ⚙️
│   ├── UserServiceImpl.java 💾
│   ├── EmailService.java ✉️
│── repository
│   ├── IUserRepository.java 🧾
│   ├── IWasteProductRepo.java 🧺
│── security
│   ├── JWTFilter.java 🛡️
│   ├── JWTService.java 🔑
│   ├── JwtUtil.java 📧
│── config
│   ├── SecurityConfig.java ⚙️
│── SmartEWasteManagementSystemApplication.java ▶️
