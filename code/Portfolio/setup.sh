#!/bin/bash

echo "========== Project Portfolio Hub Setup Script =========="

# Step 1: Build Java App
echo ">> Building Java app and generating executable .jar and .exe..."
cd Project_portfolio_Hub_Application || { echo "Java project not found."; exit 1; }
mvn clean package || { echo "Build failed. Exiting..."; exit 1; }

# Step 2: Run Unit Tests
echo ">> Running tests..."
mvn test || { echo "Tests failed."; exit 1; }

# Step 3: Copy Executables to Website Temp Folder
EXE_FILE="target/Project_Portfolio_Hub.exe"
DEST_TEMP="../Portfolio_Website/AppExeFile"

echo ">> Copying .exe to website temp folder..."
mkdir -p "$DEST_TEMP"
cp "$EXE_FILE" "$DEST_TEMP/Project_Portfolio_Hub.exe"

# Step 4: Start Node.js Website
cd ../Portfolio_Website || { echo "Website folder not found."; exit 1; }

echo ">> Installing Node.js dependencies..."
npm install

# Ensure archiver is installed
if ! npm list archiver > /dev/null 2>&1; then
  echo ">> Installing 'archiver'..."
  npm install archiver
fi

echo ">> Launching website at http://localhost:8080 ..."
node app.js &>/dev/null &

# Give the server a moment to start
sleep 3

# Step 5: Open the browser automatically
echo ">> Opening browser to http://localhost:8080"
# Linux
if command -v xdg-open > /dev/null; then
  xdg-open http://localhost:8080
# macOS
elif command -v open > /dev/null; then
  open http://localhost:8080
# Windows Git Bash
elif command -v start > /dev/null; then
  start http://localhost:8080
else
  echo ">> Please open http://localhost:8080 in your browser manually."
fi

echo "========== Setup Complete =========="

