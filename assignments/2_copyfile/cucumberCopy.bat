cls
@echo off
echo.
echo.
echo ------------------------------------FEATURES-------------------
echo.
echo.
echo.
java -cp "jars/*" cucumber.api.cli.Main features/copy.feature
echo.
echo.
echo.
echo -------------------------------------COMPILE-------------------
echo.
echo.
echo.
javac -cp "jars/*;." step_definitions/copy.java copyFile.java
echo.
echo.
echo.
echo -------------------------------------RUN------------------------
echo.
echo.
echo.
java -cp "jars/*;." cucumber.api.cli.Main -g step_definitions CopyFile features
cd .