package de.htwg.se.riskgame.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.riskgame.RiskGameModule
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop

import scala.xml.{Elem, XML}

class FileIO @Inject extends FileIOInterface {

  override def loadGuiMapDataPath(): Map[String, String] = ???

  override def loadMap(desk: DeskInterface): DeskInterface = ???

  override def load: DeskInterface = {
    val file = scala.xml.XML.loadFile("src/main/resources/memory/desk.xml")
    val injector = Guice.createInjector(new RiskGameModule)
    var desk = injector.getInstance(classOf[DeskInterface])
    val fieldNodes = file \\ "field"
    for (field <- fieldNodes) {
      val row: Int = (field \ "@row").text.toInt
      val col: Int = (field \ "@col").text.toInt
      val name: String = (field \ "@name").text
      name match {
        case "x" => desk = desk.set(row, col, Field("x"))
        case _ =>
          val troops: Int = (field \ "@troops").text.toInt
          val team: Team = (field \ "@team").text match {
            case "No Team" => Team.NO_TEAM
            case "Blue" => Team.BLUE
            case "Red" => Team.RED
          }
          desk = desk.set(row, col, Field(name, Troop(troops, team)))
      }
    }
    desk
  }

  override def save(desk: DeskInterface): Unit = scala.xml.XML.save("src/main/resources/memory/desk.xml", deskToXml(desk))

  def deskToXml(desk: DeskInterface): Elem = {
    <desk size={desk.size.toString} teams={desk.info.teams.toString()} playerTurn={desk.info.playerTurn.toString}>
      {
        for {
          row <- 0 until desk.size
          col <- 0 until desk.size
        } yield fieldToXml(desk, row, col)
      }
    </desk>
  }

  def fieldToXml(desk: DeskInterface, row: Int, col: Int): Elem = {
    <field
    row={row.toString}
    col={col.toString}
    name={desk.field(row, col).getName}
    troops={desk.field(row, col).getTroop.getOrElse("-1").toString}
    team={desk.field(row, col).team.getName}></field>
  }

}
