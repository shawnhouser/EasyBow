#!/bin/bash
set -e
echo -e "\n"
javac -Xlint:deprecation -cp ".:spigot-1.12.jar" shawnh/EasyBow.java
echo "Compiled"
jar cvfe EasyBow.jar shawnh/EasyBow.class shawnh/*.class plugin.yml > /dev/null
echo "Jarified"
mv EasyBow.jar ../spigotServer/plugins
echo "Moved"
find . -name "*.class" -type f -delete
echo "Cleaned"
echo -e "\n"