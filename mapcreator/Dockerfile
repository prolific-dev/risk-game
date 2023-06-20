FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /opt/risk-game/mapcreator

RUN apt-get update && apt-get install -y curl nano && apt-get clean

COPY . /opt/risk-game/mapcreator/

EXPOSE 8082

CMD sbt run
