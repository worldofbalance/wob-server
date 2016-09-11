#!/usr/bin/env bash

rm -rf ./dist
mkdir ./dist

cd src

# compile the Lobby Entry Point, use the src and lib folder to find java classes.
javac -cp "./../lib/*:./../chartlib/*:." -target 1.8 shared/core/GameServer.java

# create a jar file with GameServer entry point and include all files in the src directory
# jar cfev '../dist/main.jar' shared/core/GameServer *

cd ..

# cp -r conf ./dist