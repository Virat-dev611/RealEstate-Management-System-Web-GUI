🏡 Real Estate Management System

GUVI – Galgotias Project Board | 3rd Semester | Review-2 (Final Submission)

📌 Project Overview

The Real Estate Management System is a full-stack Java web application that enables users to browse properties, owners to manage listings, and administrators to approve bookings through a secure, role-based system.

This project was developed under the GUVI Project Board to transform theoretical concepts into a practical, real-world application, focusing on clean architecture, robustness, and usability.

🎯 Project Objectives

Apply Java & Spring Boot concepts in a real-world scenario

Implement role-based access control

Ensure data validation and error handling

Build a professional, responsive UI

Follow GUVI Review-2 evaluation guidelines strictly

🛠️ Tech Stack

Java 21

Spring Boot 3.1.4

Spring Data JPA

Thymeleaf

Tailwind CSS

H2 Database

Maven

Git & GitHub

👥 User Roles & Permissions
Role	Capabilities
USER	View properties, create bookings
OWNER	Add & manage properties
ADMIN	Approve bookings, access dashboard

✔ Role-based access using HTTP Session
✔ UI restriction using Thymeleaf th:if
✔ Backend authorization in controllers

⚙️ Core Features (GUVI Review-2)
✅ Core Feature Implementation

Property listing & management

Booking lifecycle (Pending → Approved)

Admin dashboard with analytics

✅ Error Handling & Robustness

Session validation

Role verification

Safe exception handling

✅ Integration of Components

Controller → Service → Repository architecture

Fully integrated backend & UI

✅ Event Handling & Processing

Booking approval updates property availability

Dynamic UI rendering based on role

✅ Data Validation

Client-side validation

Server-side validation using JPA constraints

✅ Code Quality & Innovation

Modular, clean codebase

Reusable UI components (navbar)

Admin dashboard for monitoring system activity

✅ Documentation

Detailed README

Screenshots for UI proof

Structured project layout

🗂️ Project Structure
RealEstate-Management-System/
│
├── src/main/java/com/realestate
│   ├── controller
│   ├── service
│   ├── repository
│   └── model
│
├── src/main/resources
│   ├── templates
│   │   ├── components
│   │   └── *.html
│   └── application.properties
│
├── screenshots/
├── README.md
└── pom.xml

📸 Screenshots

All UI screenshots are included in the /screenshots folder:

Home Page
![Home Page](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/2727130676b3304c7251b0a8075ed86d87bb5cfd/Screenshot%202025-12-18%20104133.png)
Login & Registration
![login](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/c8ea115669f8f190b8dfb672512a476bb9b26dbc/Screenshot%202025-12-18%20104145.png)
![Registration](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/234a4f600359356f70d3bcdee03f753681225124/Screenshot%202025-12-18%20104212.png)
Property Listing
![Property Listing](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/48614ff6e321ab4b00e7698ce4938dff9a2c7f2a/Screenshot%202025-12-18%20104251.png)
Add Property
![Add Property](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/3760bece779627d462935033be739b11e965c812/Screenshot%202025-12-24%20202939.png)
My Bookings
![My Bookings](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/0a260ef0c52e768c5ec82be69ca792ccafb1d353/Screenshot%202025-12-24%20203326.png)
Admin Dashboard
![Admin Dashboard](https://github.com/Virat-dev611/RealEstate-Management-System-Web-GUI/blob/b753cb5318c09a06b3827f8edb15155490f03615/Screenshot%202025-12-24%20203503.png)

(Screenshots provided for GUVI feature verification.)

🚀 How to Run the Project

Clone the repository

git clone <your-github-repo-link>


Open in IntelliJ IDEA / Eclipse

Run the Spring Boot application

Open browser:

http://localhost:8080


H2 Console:

http://localhost:8080/h2-console

👨‍💻 Team Contributions
🔹 Virat (Lead Developer)

Complete backend development

Property & booking modules

Role-based authentication & authorization

Admin dashboard implementation

UI-backend integration

Error handling & validation

Project documentation & GitHub management

🔹 Yuvansh Khandelwal

UI assistance

Testing support

🔹 Nikhil Kumar Singh

Database schema assistance

Initial design inputs

Major system design, development, and integration were handled by Virat.

🏁 Conclusion

This project fulfills all GUVI Review-2 requirements, demonstrating strong technical implementation, real-world problem solving, and clean software design using Spring Boot.

📌 GUVI Review-2 Compliance Summary

✔ Core Features
✔ Robust Error Handling
✔ Role-Based Access
✔ Clean UI
✔ Documentation & Screenshots
✔ Innovation (Admin Dashboard)

✅ Status: READY FOR FINAL SUBMISSION
