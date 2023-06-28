package de.htwg.se.riskgame

import de.htwg.se.riskgame.fileio.FileIOService

object Persistence:
  def main(args: Array[String]): Unit =

    val service = new FileIOService
    service.fileioservice()