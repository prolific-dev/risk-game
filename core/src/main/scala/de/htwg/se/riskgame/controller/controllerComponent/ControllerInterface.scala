package de.htwg.se.riskgame.controller.controllerComponent

import de.htwg.se.riskgame.util.Observable
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Team
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.util.UndoManager

trait ControllerInterface extends Observable:
  var desk: DeskInterface

  def attackField        ()                                  : Unit
  def clearSelectedFields()                                  : Unit
  def createDesk         ()                                  : Unit
  def undo               ()                                  : Unit
  def redo               ()                                  : Unit
  def save               ()                                  : Unit
  def load               ()                                  : Unit
  def setField           (id: Int, soldiers: Int, team: Team): Unit
  def selectFieldById    (id: Int)                           : Unit
  def selectFieldByCoords(row: Int, col: Int)                : Unit
  def deskToString       ()                                  : String
