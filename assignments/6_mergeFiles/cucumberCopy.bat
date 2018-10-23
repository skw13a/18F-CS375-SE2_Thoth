cls
@echo off
echo.
echo.
echo ------------------------------------FEATURES-------------------
echo.
echo.
echo.
java -cp "jars/*" cucumber.api.cli.Main features/
echo.
echo.
echo.
echo -------------------------------------COMPILE-------------------
echo.
echo.
echo.
javac -cp "jars/*;." step_definitions/copy.java copyAll.java
echo.
echo.
echo.
echo -------------------------------------RUN------------------------
echo.
echo.
echo.
java -cp "jars/*;." cucumber.api.cli.Main -g step_definitions CopyAll features
cd .