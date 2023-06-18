FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
WORKDIR /risk-game/mapcreator
RUN apt-get update && apt-get install -y curl nano
COPY . /risk-game/mapcreator/
CMD sbt run