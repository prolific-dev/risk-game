package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl

import de.htwg.se.riskgame.model.deskComponent.DeskInterface

case class SelectRequest ()
case class SelectResponse(handled: Boolean, error: String, desk: DeskInterface)

object SelectHandler:
    def handleSelectRequest(selectRequest: SelectRequest): SelectResponse = ???


abstract class SelectHandler:
    val successor: Option[SelectHandler]

    def handleSelectRequest(selectRequest: SelectRequest): SelectHandler


class Check
