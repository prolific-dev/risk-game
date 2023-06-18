FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
WORKDIR /risk-game/core
RUN apt-get update && apt-get install -y curl nano
COPY . /risk-game/core/
CMD sbt run
