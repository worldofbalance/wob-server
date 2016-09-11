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
rsync -avr ./dist/ wob_wob@thecity.sfsu.edu:~/wob_server/lobby

# Deploy lobby server
rsync -avr ./mini_games/ wob_wob@thecity.sfsu.edu:~/wob_server

rsync -avr ./scripts/ wob_wob@thecity.sfsu.edu:~/scripts

# log into thecity again, this time to start the JAR Files, everything under this is executed under the WOB_WOB user
ssh wob_wob@thecity.sfsu.edu <<WOB_WOB
    cd ~/wob_server

    # Restart all the JAR servers

    # Lobby
    chmod u+x ~/scripts/start_lobby.sh
    nohup /opt/java8/bin/java -jar ~/wob_server/lobby/main.jar & > lobby.log
    echo "Lobby Started"
    exit

#    # Cards of Wild
#    nohup /opt/java8/bin/java -jar cards_of_wild/dist/Cards_Server.jar &
#    echo "Cards of Wild started"
#
#    # Clash of Species
#    nohup /opt/java8/bin/java -jar clash_of_species/dist/cos-server.jar &
#    echo "Clash of Species started"
#    /bin/sleep 5
#
#    # Running Rhino
#    nohup /opt/java8/bin/java -jar running_rhino/dist/Speed_Server.jar &
#    echo "Running Rhino Started"
#    /bin/sleep 5
#
#    # A Sea Divided
#    nohup /opt/java8/bin/java -jar a_sea_divided/dist/SeaDivided.jar &
#    echo "A sea divided started"
WOB_WOB



