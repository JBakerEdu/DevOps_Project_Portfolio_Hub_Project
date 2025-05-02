#!/bin/bash

echo "========== Project Portfolio Setup Script =========="

# Step 1: Build the Java Application
echo ">> Building Java project..."
cd Project_portfolio_Hub_Application || { echo "Java project folder not found."; exit 1; }

mvn clean package

if [ $? -ne 0 ]; then
  echo ">> Maven build failed. Exiting..."
  exit 1
fi

# Step 2: Run Unit Tests
echo ">> Running tests..."
mvn test

# Step 3: Copy Executable JAR to Web Folder
JAR_FILE="target/Project-Portfolio-Project-1.0-SNAPSHOT.jar"
DEST_DIR="../Portfolio_Website/temp"

echo ">> Copying .jar to website temp folder..."
mkdir -p "$DEST_DIR"
cp "$JAR_FILE" "$DEST_DIR/Project_Portfolio_Hub.jar"

cd ../Portfolio_Website || { echo "Website folder not found."; exit 1; }

# Step 4: Install Node Modules
echo ">> Installing Node dependencies..."
npm install

# Step 5: Launch Website
echo ">> Starting website on http://localhost:8080 ..."
node app.js

echo "========== Setup Complete =========="
