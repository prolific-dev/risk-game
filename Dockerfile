FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
RUN apt-get update && apt-get install -y curl nano
WORKDIR /risk-game
ADD ./core /risk-game/core
ADD ./fileio /risk-game/fileio
ADD ./mapcreator /risk-game/mapcreator
ADD ./mapeditor /risk-game/mapeditor
ADD ./build.sbt /risk-game/build.sbt
ADD ./project/plugins.sbt /risk-game/project/plugins.sbt
CMD sbt compile && tail -f /dev/null
