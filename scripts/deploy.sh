#!/usr/bin/env bash
echo "deploying to smurf.sfsu.edu"

# log into smurf everything under this is executed under the wob_server user
ssh wob_server@smurf.sfsu.edu <<WOB_SERVER
    # kill the already running servers
    # Lobby
    fuser -k 9255/tcp
    # Cards of Wild
    fuser -k 9260/tcp
    # Clash of Species
    fuser -k 9254/tcp
    # Running Rhino
    fuser -k 9253/tcp
    # A Sea Divided
    fuser -k 9258/tcp
WOB_SERVER

# now we are back in Travis.

# Copy the JAR files and the scripts to SMURF.
rsync -avr --delete ./dist/ wob_server@smurf.sfsu.edu:~/wob-server-binaries
rsync -avr --delete ./scripts/ wob_server@smurf.sfsu.edu:~/scripts

## log into thecity again, this time to start the JAR Files, everything under this is executed under the WOB_WOB user
ssh wob_server@smurf.sfsu.edu <<WOB_SERVER
    # make sure a logs directory exists
    mkdir logs
    # Restart all the JAR servers

    # Lobby
    chmod u+x ~/scripts/start_lobby.sh
    nohup ~/scripts/start_lobby.sh 2>&1 > ~/logs/lobby.log
    echo "Lobby Started"

    # Cards of Wild
    chmod u+x ~/scripts/start_cow.sh
    nohup ~/scripts/start_cow.sh 2>&1 > ~/logs/cow.log
    echo "Cards of Wild started"

    # Clash of Species
    chmod u+x ~/scripts/start_cos.sh
    nohup ~/scripts/start_cos.sh 2>&1 > ~/logs/cos.log
    echo "Clash of Species started."

    # Running Rhino
    # chmod u+x ~/scripts/start_rno.sh
    # nohup ~/scripts/start_rno.sh 2>&1 > ~/logs/rno.log
    # echo "Running Rhino Started"

    # A Sea Divided
    chmod u+x ~/scripts/start_sdv.sh
    nohup ~/scripts/start_sdv.sh 2>&1 ~/logs/sdv.log
    echo "A sea divided started"
WOB_SERVER



