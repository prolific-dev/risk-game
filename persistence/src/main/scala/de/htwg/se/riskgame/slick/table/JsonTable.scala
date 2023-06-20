package de.htwg.se.riskgame.slick.table

import slick.jdbc.MySQLProfile.*
import slick.lifted.Tag
import slick.ast.ScalaBaseType.stringType
import slick.ast.ScalaBaseType.intType

class JsonTable(tag: Tag) extends Table[(Int, String)](tag, "JSON"):
  def id       = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def jsonFile = column[String]("JSONFILE")

  override def * =
    (
      id,
      jsonFile
    )

