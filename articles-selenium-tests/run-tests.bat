@echo off
REM Skript za pokretanje Selenium testova sa različitim konfiguracijama (Windows)

setlocal enabledelayedexpansion

REM Boje
set RESET=[0m
set GREEN=[32m
set RED=[31m
set YELLOW=[33m
set BLUE=[34m

REM Funkcija za ispis greške
if "%1"=="" (
    call :show_help
    exit /b 0
)

REM Validacija opcije
if "%1"=="chrome" goto run_chrome
if "%1"=="firefox" goto run_firefox
if "%1"=="remote-chrome" goto run_remote_chrome
if "%1"=="remote-firefox" goto run_remote_firefox
if "%1"=="all" goto run_all
if "%1"=="positive" goto run_positive
if "%1"=="negative" goto run_negative
if "%1"=="integration" goto run_integration
if "%1"=="help" goto show_help

echo Nepoznata opcija: %1
call :show_help
exit /b 1

:show_help
echo.
echo Koriscenje: run-tests.bat [opcija]
echo.
echo Opcije:
echo.   chrome              Pokretanje testova na Chrome-u (lokalno)
echo.   firefox             Pokretanje testova na Firefox-u (lokalno)
echo.   remote-chrome       Pokretanje testova na Chrome-u (Selenium Grid)
echo.   remote-firefox      Pokretanje testova na Firefox-u (Selenium Grid)
echo.   all                 Pokretanje svih testova
echo.   positive            Pokretanje samo pozitivnih testova (Chrome)
echo.   negative            Pokretanje samo negativnih testova (Chrome)
echo.   integration         Pokretanje samo integracijskih testova (Chrome)
echo.   help                Prikazivanje ove poruke
echo.
echo Primeri:
echo.   run-tests.bat chrome
echo.   run-tests.bat firefox
echo.   run-tests.bat positive
echo.   run-tests.bat all
echo.
exit /b 0

:run_chrome
echo Pokretanje testova na Chrome-u (lokalno)...
call mvn clean test -Dbrowser=chrome
echo.
echo Chrome testovi zavrseni
exit /b 0

:run_firefox
echo Pokretanje testova na Firefox-u (lokalno)...
call mvn clean test -Dbrowser=firefox
echo.
echo Firefox testovi zavrseni
exit /b 0

:run_remote_chrome
echo NAPOMENA: Selenium Grid server mora biti pokrenut na http://localhost:4444
echo Pokretanje testova na Remote Chrome-u...
call mvn clean test -Dbrowser=remote_chrome -DgridUrl=http://localhost:4444
echo.
echo Remote Chrome testovi zavrseni
exit /b 0

:run_remote_firefox
echo NAPOMENA: Selenium Grid server mora biti pokrenut na http://localhost:4444
echo Pokretanje testova na Remote Firefox-u...
call mvn clean test -Dbrowser=remote_firefox -DgridUrl=http://localhost:4444
echo.
echo Remote Firefox testovi zavrseni
exit /b 0

:run_all
echo Pokretanje SVIH testova...
echo.
echo 1. Chrome testovi...
call mvn clean test -Dbrowser=chrome
if errorlevel 1 (
    echo Greska pri Chrome testovima!
    exit /b 1
)
echo.
echo 2. Firefox testovi...
call mvn clean test -Dbrowser=firefox
if errorlevel 1 (
    echo Greska pri Firefox testovima!
    exit /b 1
)
echo.
echo Svi testovi zavrseni!
exit /b 0

:run_positive
echo Pokretanje samo pozitivnih testova...
call mvn clean test -Dbrowser=chrome -Dtest=ArticlesPagePositiveTest
echo.
echo Pozitivni testovi zavrseni
exit /b 0

:run_negative
echo Pokretanje samo negativnih testova...
call mvn clean test -Dbrowser=chrome -Dtest=ArticlesPageNegativeTest
echo.
echo Negativni testovi zavrseni
exit /b 0

:run_integration
echo Pokretanje samo integracijskih testova...
call mvn clean test -Dbrowser=chrome -Dtest=ArticlesPageIntegrationTest
echo.
echo Integracijski testovi zavrseni
exit /b 0
