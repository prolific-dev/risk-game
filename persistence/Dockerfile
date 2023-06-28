FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /opt/risk-game/persistence

RUN apt-get update && apt-get install -y curl nano && apt-get clean

COPY . /opt/risk-game/persistence/

EXPOSE 8082

CMD sbt run
