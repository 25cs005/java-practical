@echo off
echo ==========================================================
echo   Pushing java-practical to GitHub (@25cs005)
echo ==========================================================

where git >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Git is not installed or not in PATH.
    pause
    exit /b 1
)

if not exist ".git" (
    echo [INFO] Initializing git repository...
    git init
)

git branch -M main

git remote get-url origin >nul 2>nul
if %errorlevel% neq 0 (
    echo [INFO] Setting remote origin...
    git remote add origin https://github.com/25cs005/java-practical.git
) else (
    git remote set-url origin https://github.com/25cs005/java-practical.git
)

echo [INFO] Staging files...
git add -A

git status --porcelain | findstr "^" >nul
if %errorlevel% equ 0 (
    echo [INFO] Committing files...
    git commit -m "Complete Java practicals 1 to 14 with documentation and directory structure"
) else (
    echo [INFO] No changes to commit.
)

echo [INFO] Pushing to remote...
git push -u origin main

if %errorlevel% equ 0 (
    echo.
    echo [SUCCESS] Pushed successfully to https://github.com/25cs005/java-practical
) else (
    echo.
    echo [NOTICE] If push failed:
    echo 1. Ensure you created a public repository named 'java-practical' at https://github.com/new
    echo 2. Ensure you are authenticated with GitHub.
    echo 3. Run push.bat again.
)

pause

