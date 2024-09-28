# Student Management System

## Classification: Public

### Overview

The Student Management System is a web-based application designed to facilitate user authentication, course viewing, and course registration management. The application enables students to register, view available courses, and manage their course registrations seamlessly.

### Technology Assessment

- **APIs**: The application is built using RESTful APIs deployed on the latest version of WebLogic Server.
- **Programming Language**: Java 8 or higher.
- **Database**: Oracle Database for data storage and management.
- **Caching**: Utilizes a well-known caching framework to cache data retrieved from the database for improved performance.

### Technical Details

- **Stateless APIs**: All APIs are stateless, ensuring efficient interaction with the client.
- **JWT Authentication**: 
  - JWT tokens are used for authentication and expire after 5 minutes.
  - User sessions are managed with a 10-minute expiration time.
  
### API Endpoints

1. **Login**
   - **Method**: POST
   - **Description**: Authenticates the user with a username and password.

2. **View Courses**
   - **Method**: GET
   - **Description**: Retrieves a list of available courses.

3. **Register to a Course**
   - **Method**: POST
   - **Description**: Allows a student to register for a selected course.

4. **Cancel a Course Registration**
   - **Method**: DELETE
   - **Description**: Allows a student to cancel their course registration.

5. **Get Course Schedule as PDF**
   - **Method**: GET
   - **Description**: Generates and retrieves the course schedule in PDF format.

### Setup Instructions

1. Clone the repository to your local machine.
2. Ensure you have the required software installed:
   - Java 17 or higher
   - Oracle Database
   - WebLogic Server
3. Configure your environment and database connections as needed.
4. Build and run the application.
