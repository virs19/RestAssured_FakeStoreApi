# Online Fake Store API Test Automation

This repository contains automated API test cases for the  Fake Store application.  
The tests are implemented using **Java + RestAssured + TestNG** 

The project covers the following modules:
- **User** –  login, Token Generation
- **Products** – product listing, search, sort, 
CRUD operation for products
- **Cart** – cart item listing, search, sort, CRUD operation for cart

## 🚀 Tech Stack
- Java (JDK 8/11/17)
- [RestAssured](https://rest-assured.io/) – API testing
- [TestNG] – test framework
- [Maven/Gradle] – build tool
- [GitHub Actions/Jenkins] – CI/CD
- [Allure Reports, Extent Reports]-Reporting
- [JSON files]-test data
---

## 📂 Project Structure
- Modular structure with separate classes for each module(Product, Cart,User).
- Data-driven testing using JSON or Excel files.
- Reusable utility methods for API requests
- Logging and Reporting integration
