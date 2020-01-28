#!/usr/bin/env bash

JAR_NAME=${1:-205962657_300921079}

mkdir target/
javac src/*.java -d target
cd target
jar cmvf ../META-INF/MANIFEST.MF ${JAR_NAME}.jar .
cd ..