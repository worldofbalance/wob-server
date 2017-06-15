#!/usr/bin/env bash
printf "\n" | java \
     -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
     -XX:StartFlightRecording=filename=$HOME/flight_recording_cow.jfr,duration=1h,maxsize=100M \
     -Dcom.sun.management.jmxremote.port=9360 \
     -Dcom.sun.management.jmxremote.autodiscovery=true \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -Dcom.sun.management.jmxremote.ssl=false \
     -Djava.rmi.server.hostname=localhost \
    -jar ~/wob-server-binaries/cards-of-wild.jar 2>&1 & printf "\n"
