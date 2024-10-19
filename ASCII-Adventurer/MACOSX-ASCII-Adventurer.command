#!/usr/bin/env bash

cd -- "$(dirname -- "$0")"
cd game
java -Xmx1024M -jar "asciiadventurer.jar"
