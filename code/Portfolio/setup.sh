#!/bin/bash

echo "========== Project Portfolio Hub Setup Menu =========="

# Java variables
APP_DIR="Project_portfolio_Hub_Application"
JAR_FILE="$APP_DIR/target/Project_Portfolio_Hub_Executable.jar"
EXE_FILE="$APP_DIR/target/Project_Portfolio_Hub.exe"
WEB_DIR="Portfolio_Website"
EXE_DEST="$WEB_DIR/AppExeFile"

build_project() {
    echo ">> Building Java app and generating .jar and .exe..."
    cd "$APP_DIR" || { echo "Java project not found."; exit 1; }
    mvn clean package || { echo "Build failed."; exit 1; }
    cd - >/dev/null
}

run_tests() {
    echo ">> Running tests..."
    cd "$APP_DIR" || exit 1
    mvn test
    cd - >/dev/null
}

run_java_app() {
    if [ -f "$JAR_FILE" ]; then
        echo ">> Launching Java app..."
        java -jar "$JAR_FILE" &
        sleep 2
    else
        echo ">> JAR file not found. Please build the project first."
    fi
}

launch_website() {
    if [ ! -f "$EXE_DEST/Project_Portfolio_Hub.exe" ]; then
        echo ">> .exe file missing from AppExeFile. Attempting to copy from build output..."
        copy_exe_to_website || return 1
    fi

    echo ">> Launching Node.js website..."
    cd "$WEB_DIR" || { echo "Website folder not found."; exit 1; }
    npm install
    if ! npm list archiver > /dev/null 2>&1; then
        npm install archiver
    fi
    node app.js &>/dev/null &

    sleep 3
    echo ">> Opening browser at http://localhost:8080 ..."
    if command -v xdg-open > /dev/null; then
      xdg-open http://localhost:8080
    elif command -v open > /dev/null; then
      open http://localhost:8080
    elif command -v start > /dev/null; then
      start http://localhost:8080
    else
      echo ">> Please open http://localhost:8080 manually."
    fi
    cd - >/dev/null
}

copy_exe_to_website() {
    if [ ! -f "$EXE_FILE" ]; then
        echo ">> ERROR: Executable not found at $EXE_FILE"
        echo ">> You must build the project first."
        return 1
    fi
    echo ">> Copying .exe to website download folder..."
    mkdir -p "$EXE_DEST"
    cp "$EXE_FILE" "$EXE_DEST/Project_Portfolio_Hub.exe"
}

# Menu loop
while true; do
    echo ""
    echo "Choose a single option:"
    echo "1) Build project and run everything"
    echo "2) Run Java app only"
    echo "3) Run Node.js website only"
    echo "4) Run tests only"
    echo "5) Exit"
    read -p "Enter one option [1-5]: " choice

    case $choice in
        1)
            build_project
            run_tests
            run_java_app
            copy_exe_to_website
            launch_website
            ;;
        2)
            run_java_app
            ;;
        3)
            launch_website
            ;;
        4)
            run_tests
            ;;
        5)
            echo "Exiting setup script."
            break
            ;;
        *)
            echo "Invalid option. Please try again."
            ;;
    esac
done

echo "========== Done =========="
