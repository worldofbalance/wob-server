#!/usr/bin/env bash
echo "deploying to thecity.sfsu.edu"

# log into thecity everything under this is executed under the WOB_WOB user
ssh wob_wob@thecity.sfsu.edu <<WOB_WOB
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
WOB_WOB

# now we are back in Travis.

# Deploy lobby server
rsync -avr ./build/distributions/wob-server/ wob_wob@thecity.sfsu.edu:~/wob_server/lobby

# Deploy lobby server
rsync -avr ./mini_games/ wob_wob@thecity.sfsu.edu:~/wob_server

rsync -avr ./scripts/ wob_wob@thecity.sfsu.edu:~/scripts

# log into thecity again, this time to start the JAR Files, everything under this is executed under the WOB_WOB user
ssh wob_wob@thecity.sfsu.edu <<WOB_WOB
    cd ~/wob_server

    # Restart all the JAR servers

    # Lobby
    chmod u+x ~/scripts/start_lobby.sh
    nohup ~/scripts/start_lobby.sh > ~/logs/lobby.log
    echo "Lobby Started"

    # Cards of Wild
    chmod u+x ~/scripts/start_cow.sh
    nohup ~/scripts/start_cow.sh > ~/logs/cow.log
    echo "Cards of Wild started"

   # Clash of Species
    chmod u+x ~/scripts/start_cos.sh
    nohup ~/scripts/start_cos.sh > ~/logs/cos.log
    echo "Clash of Species started."


    # Running Rhino
    chmod u+x ~/scripts/start_rno.sh
    nohup ~/scripts/start_rno.sh > ~/logs/rno.log
    echo "Running Rhino Started"

    # A Sea Divided
    chmod u+x ~/scripts/start_sdv.sh
    nohup ~/scripts/start_sdv.sh > ~/logs/sdv.log
    echo "A sea divided started"
WOB_WOB



