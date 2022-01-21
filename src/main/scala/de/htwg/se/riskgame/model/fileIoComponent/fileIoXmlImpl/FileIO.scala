package de.htwg.se.riskgame.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import de.htwg.se.riskgame.RiskGameModule
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface

import scala.xml.{Elem, XML}

class FileIO extends FileIOInterface {

  override def load: DeskInterface = {
    val file = scala.xml.XML.loadFile("desk.xml")
    val injector = Guice.createInjector(new RiskGameModule)
    var desk = injector.getInstance(classOf[DeskInterface])
    val fieldNodes = file \\ "field"
    for (field <- fieldNodes) {
      val row: Int = (field \ "@row").text.toInt
      val col: Int = (field \ "@col").text.toInt
      val value: String = (field \ "@value").text
      //desk = desk.set(row, col, )
    }
    desk
  }

  override def save(desk: DeskInterface): Unit = XML.save("desk.xml", deskToXml(desk))

  def deskToXml(desk: DeskInterface): Elem = {
    <desk size={ desk.size.toString }>
      {
        for {
          row <- 0 until desk.size
          col <- 0 until desk.size
        } yield fieldToXml(desk, row, col)
      }
    </desk>
  }

  def fieldToXml(desk: DeskInterface, row: Int, col: Int): Elem = {
    <field row={ row.toString } col={ col.toString } value={ desk.field(row, col).toString }>
    </field>
  }

}
