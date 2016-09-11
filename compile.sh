#!/usr/bin/env bash

rm -rf ./dist
rm -rf sources.txt
mkdir ./dist

cd src

find . -name "*.java" > sources.txt

# compile the Lobby Entry Point, use the src and lib folder to find java classes.
javac -cp "./../lib/*:./../chartlib/*:." -verbose -target 1.8 @sources.txt
rm -rf sources.txt

# create a jar file with GameServer entry point and include all files in the src directory
jar cfm '../dist/main.jar' 'lobby.manifest.txt'  *

cd ..

cp -r lib/* ./dist
cp -r chartlib/* ./dist
