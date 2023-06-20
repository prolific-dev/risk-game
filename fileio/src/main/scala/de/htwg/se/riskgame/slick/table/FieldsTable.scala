package de.htwg.se.riskgame.fileio


import slick.jdbc.MySQLProfile.*
import slick.lifted.Tag
import slick.ast.ScalaBaseType.stringType
import slick.ast.ScalaBaseType.intType

class FieldsTable(tag: Tag) extends Table[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)](tag, "FIELDS"):
    def id         = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def fieldOne   = column[Int]("FIELDONE")
    def fieldTwo   = column[Int]("FIELDTWO")
    def fieldThree = column[Int]("FIELDTHREE")
    def fieldFour  = column[Int]("FIELDFOUR")
    def fieldFive  = column[Int]("FIELDFIVE")
    def fieldSix   = column[Int]("FIELDSIX")
    def fieldSeven = column[Int]("FIELDSEVEM")
    def fieldEight = column[Int]("FIELDEIGHT")
    def fieldNine  = column[Int]("FIELDNINE")
    override def * =
        (
            id,
            fieldOne,
            fieldTwo,
            fieldThree,
            fieldFour,
            fieldFive,
            fieldSix,
            fieldSeven,
            fieldEight,
            fieldNine
        )