package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl


import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Team
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.AreaField

import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface

class AttackCommand(sourceField: Option[Field], targetField: Option[Field], controller: ControllerInterface) extends Command:
    private var preMemento : DeskInterface = controller.desk
    private var pastMemento: DeskInterface = controller.desk

    private val handler = AttackHandler

    def doStep()  : Unit =
        val request:  AttackRequest  = AttackRequest(sourceField, targetField, controller)
        val response: AttackResponse = handler.handleAttackRequest(request)

        controller.desk = response.desk
        pastMemento     = controller.desk
        
    def undoStep(): Unit = controller.desk = preMemento
    def redoStep(): Unit = controller.desk = pastMemento