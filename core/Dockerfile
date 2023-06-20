FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /opt/risk-game/core

RUN apt-get update && apt-get install -y curl nano && apt-get clean

COPY . /opt/risk-game/core/

EXPOSE 8080

CMD sbt compile && tail -f
