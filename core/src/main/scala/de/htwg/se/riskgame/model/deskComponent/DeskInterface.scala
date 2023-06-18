package de.htwg.se.riskgame.model.deskComponent

import  de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Team
import  de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import  de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Info

trait DeskInterface:
    def info: Info
    def size: Int
    def fieldById(id: Int): Option[Field]
    def fieldByCoords(row: Int, col: Int): Field
    def clearSelectedFields(): DeskInterface
    def selectFieldById(id: Int): DeskInterface
    def selectFieldByCoords(row: Int, col: Int): DeskInterface
    def replaceFieldById(id: Int, soldiers: Int, team: Team): DeskInterface
    def replaceFieldByCoords(row: Int, col: Int, soldiers: Int, team: Team): DeskInterface

    override def toString: String

