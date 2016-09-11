#!/usr/bin/env bash
echo "deploying to thecity.sfsu.edu"
rsync -avr ./dist/ wob_wob@thecity.sfsu.edu:~/wob_server