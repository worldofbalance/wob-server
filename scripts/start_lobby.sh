#!/usr/bin/env bash
printf "\n" | java -jar ~/wob-server-binaries/lobby.jar \
     -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
     -XX:StartFlightRecording=settings=default \
     -XX:FlightRecorderOptions=dumponexit=true,dumponexitpath=$HOME \
     -Dcom.sun.management.jmxremote.port=7091 \
     -Dcom.sun.management.jmxremote.autodiscovery=true \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -Dcom.sun.management.jmxremote.ssl=false \
     -Djava.rmi.server.hostname=localhost \
     2>&1 \
    & printf "\n"
