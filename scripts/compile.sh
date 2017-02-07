#!/usr/bin/env bash

# clean up the previous build
rm -rf ./dist
rm -rf sources.txt
mkdir ./dist

cd src

# fetch a list of all the Java Classes
find . -name "*.java" > sources.txt

# Compile all the Java Classes
javac -cp "./../lib/*:." -target 1.8 @sources.txt

rm -rf sources.txt

# create a jar file for each game, based on their appropriate manifest files.
jar cfm '../dist/lobby.jar' '../manifests/lobby.manifest.txt'  *
jar cfm '../dist/cards-of-wild.jar' '../manifests/cow.manifest.txt'  *
jar cfm '../dist/clash-of-species.jar' '../manifests/cos.manifest.txt'  *
jar cfm '../dist/sea-divided.jar' '../manifests/sdv.manifest.txt'  *

cd ..

# copy the library files over
mkdir ./dist/lib
cp -r lib/* ./dist/lib
