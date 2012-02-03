set SCRIPT_DIR=%~dp0
java -XX:MaxPermSize=512m -Xmx512M -jar "%SCRIPT_DIR%sbt-launch.jar" %*
