## DevOps Project: Project Portfolio Hub

## Description

The Project Portfolio Hub is a centralized platform designed to help users manage and showcase their personal and academic projects in a professional and organized way. It includes a desktop Java application for managing portfolios and a web-based interface for accessing and downloading the latest build. The system demonstrates a combination of DevOps principles, full-stack integration, and automation.

### Java Application: Project Hub

The JavaFX-based desktop application allows signed-in users to create, edit, and manage their project portfolios. Features include:
- Uploading project files and documents
- Categorizing and tagging projects
- Adding repository links and hosted URLs
- Editing project visibility (public/private)
- Customizing the portfolio layout and appearance

The application is packaged as a `.jar` file and converted to a Windows executable (`.exe`) for ease of use.

### Node.js Web Page: Jacob's DevOps Portfolio

The Node.js website acts as the front-facing portal for downloading the application. Hosted locally or on a cloud instance (e.g., AWS), it provides:
- A user-friendly UI built with EJS templates
- A secure download endpoint for the latest executable
- Archive generation functionality using the `archiver` module
- A responsive design viewable on desktop or mobile

### setup.sh: Start-Up Script

A Bash-based automation script (`setup.sh`) is included to streamline development and testing. It supports:
- Building the Java application using Maven
- Running JUnit test cases
- Generating both `.jar` and `.exe` files
- Moving the `.exe` to the website's download folder
- Launching the Node.js server and opening the browser automatically

## Setup Script Instructions

### Requirements

To use the setup script, your system must meet the following requirements:
- Proper download of the complete project folder and file structure
- Java 11 or higher
- Maven installed and configured
- Bash shell available (e.g., Terminal, Git Bash, or any Unix-like shell)

### How to Use the Setup Script

- Navigate to the project folder where `setup.sh` is located.
- Run the script using the following command:
  ```
  ./setup.sh
  ```
- If your system supports it, you may also double-click the `setup.sh` file to launch it directly.
- Follow the output displayed in the terminal. You will be prompted to enter a number between 1 and 5 to select an action:
  1. Build project and run everything  
  2. Run Java app only  
  3. Run Node.js website only  
  4. Run tests only  
  5. Exit