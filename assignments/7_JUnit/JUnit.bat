cls
@echo off
echo.
echo.
echo.
echo -------------------------------------COMPILE-------------------
echo.
echo.
echo.
javac -cp "jars/*;." MessageUtil.java TestJunit.java TestRunner.java
echo.
echo.
echo.
echo -------------------------------------RUN------------------------
echo.
echo.
echo.
java -cp "jars/*;." TestRunner
cd .