<#
.SYNOPSIS
    Automated Git initialization and push script for java-practical repository.
.DESCRIPTION
    Configures remote origin to https://github.com/25cs005/java-practical.git,
    stages all practical folders and files, commits changes, and pushes to GitHub.
#>

[CmdletBinding()]
param (
    [string]$CommitMessage = "Organize Java practicals 1-5 with documentation and directory structure"
)

$ErrorActionPreference = "Continue"

Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "  Pushing java-practical to GitHub (@25cs005)             " -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan

# 1. Check if git is installed
if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Host "[ERROR] Git is not installed or not found in system PATH." -ForegroundColor Red
    exit 1
}

# 2. Initialize repository if needed
if (-not (Test-Path ".git")) {
    Write-Host "[INFO] Initializing new Git repository..." -ForegroundColor Yellow
    git init
} else {
    Write-Host "[INFO] Git repository already initialized." -ForegroundColor Green
}

# 3. Configure branch to 'main'
git branch -M main

# 4. Configure remote URL
$repoUrl = "https://github.com/25cs005/java-practical.git"
$existingRemote = git remote get-url origin 2>$null

if ($LASTEXITCODE -ne 0 -or [string]::IsNullOrWhiteSpace($existingRemote)) {
    Write-Host "[INFO] Adding remote origin: $repoUrl" -ForegroundColor Yellow
    git remote add origin $repoUrl
} else {
    Write-Host "[INFO] Updating remote origin: $repoUrl" -ForegroundColor Yellow
    git remote set-url origin $repoUrl
}

# 5. Stage files
Write-Host "[INFO] Staging all files and directories..." -ForegroundColor Yellow
git add -A

# 6. Commit changes if any exist
$status = git status --porcelain
if ($status) {
    Write-Host "[INFO] Committing changes with message: '$CommitMessage'..." -ForegroundColor Yellow
    git commit -m "$CommitMessage"
} else {
    Write-Host "[INFO] No unstaged/uncommitted changes detected." -ForegroundColor Green
}

# 7. Push to remote
Write-Host "[INFO] Pushing branch 'main' to origin..." -ForegroundColor Yellow
git push -u origin main

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n[SUCCESS] Successfully pushed all practicals to https://github.com/25cs005/java-practical" -ForegroundColor Green
} else {
    Write-Host "`n[NOTICE] Push failed or returned an exit code. Common causes:" -ForegroundColor Yellow
    Write-Host " 1. The remote repository has not been created yet on GitHub." -ForegroundColor White
    Write-Host "    -> Visit: https://github.com/new and create a public repository named 'java-practical'." -ForegroundColor Cyan
    Write-Host " 2. Authentication is required." -ForegroundColor White
    Write-Host "    -> Sign in with GitHub Credential Manager or use a Personal Access Token (PAT)." -ForegroundColor Cyan
    Write-Host " 3. Then rerun this script: .\push.ps1`n" -ForegroundColor Green
}
