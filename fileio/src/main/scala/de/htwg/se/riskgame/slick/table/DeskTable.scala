package de.htwg.se.riskgame.fileio

import slick.jdbc.MySQLProfile.*
import slick.ast.ScalaBaseType.intType 
import slick.ast.ScalaBaseType.stringType 
import slick.lifted.Tag

class DeskTable(tag: Tag) extends Table[(Int, Int, Int, String, String, String, String, Int, Int, Int)](tag, "DESK"):
    def id                     = column[Int]   ("ID", O.PrimaryKey, O.AutoInc)
    def deskSize               = column[Int]   ("DESKSIZE")
    def playerTurn             = column[Int]   ("PLAYERTURN")
    def playerOne              = column[String]("PLAYERONE")
    def playerTwo              = column[String]("PLAYERTWO")
    def playerThree            = column[String]("PLAYERTHREE")
    def playerFour             = column[String]("PLAYERFOUR")
    def selectedFieldPrimary   = column[Int]   ("SELECTEDFIELDPRIMARY")
    def selectedFieldSecondary = column[Int]   ("SELECTEDFIELDSECONDARY")
    def fields                 = column[Int]   ("FIELDS")
    override def * =
        (
            id,
            deskSize,
            playerTurn,
            playerOne,
            playerTwo,
            playerThree,
            playerFour,
            selectedFieldPrimary,
            selectedFieldSecondary,
            fields
        )
