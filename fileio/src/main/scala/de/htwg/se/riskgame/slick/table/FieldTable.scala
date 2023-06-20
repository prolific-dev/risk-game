package de.htwg.se.riskgame.fileio

import slick.jdbc.MySQLProfile.*
import slick.lifted.Tag
import slick.ast.ScalaBaseType.stringType
import slick.ast.ScalaBaseType.intType

class FieldTable(tag: Tag) extends Table[(Int, Int, String, String, Int, Int, Int, Int, Int)](tag, "FIELD"):
    def id        = column[Int]   ("ID", O.PrimaryKey, O.AutoInc)
    def fieldId   = column[Int]   ("FIELDID")
    def fieldType = column[String]("FIELDTYPE")
    def name      = column[String]("NAME")
    def x         = column[Int]   ("X")
    def y         = column[Int]   ("Y")
    def areadId   = column[Int]   ("AREAID")
    def soldiers  = column[Int]   ("SOLDIERS")
    def occupier  = column[Int]   ("OCCUPIER")
    override def * =
        (
            id,
            fieldId,
            fieldType,
            name,
            x,
            y,
            areadId,
            soldiers,
            occupier
        )