#!/usr/bin/env bash
echo "deploying to thecity.sfsu.edu"

# Deploy lobby server
rsync -avr ./dist/ wob_wob@thecity.sfsu.edu:~/wob_server/lobby

# Deploy lobby server
rsync -avr ./mini_games/ wob_wob@thecity.sfsu.edu:~/wob_server
