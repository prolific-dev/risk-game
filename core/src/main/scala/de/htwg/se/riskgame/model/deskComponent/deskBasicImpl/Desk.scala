package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.deskComponent.DeskInterface

case class Desk(fields: Matrix[Field], info: Info) extends DeskInterface:

  val size: Int = fields.size

  def this(fields: Matrix[Field]) = this(fields, Info(None, None, List(new Player(1), new Player(2)), 1))
  def this(size  : Int)           = this(new Matrix[Field](size))

  override def clearSelectedFields(): Desk = copy(info = info.clearSelectedFields())

  def selectField(field: Field): Desk = copy(info = info.selectField(field))

  override def selectFieldById(id: Int): Desk =
    fieldById(id) match
      case Some(field) => selectField(field)
      case None        => this

  override def selectFieldByCoords(row: Int, col: Int): Desk = selectField(fieldByCoords(row, col))

  def replaceField(field: Field, soldiers: Int, team: Team): Desk =
      field match
        case areaField: AreaField => copy(fields.replaceField(
          field.x,
          field.y,
          areaField
            .setSoldiers(soldiers)
            .setOccupier(Some(team))))
        case _ => this

  override def replaceFieldById(id: Int, soldiers: Int, team: Team): Desk =
        fields.fieldById(id) match
            case Some(field) => replaceField(field, soldiers, team)
            case None        => this

  override def replaceFieldByCoords(row: Int, col: Int, soldiers: Int, team: Team): Desk =
    replaceField(fields.fieldByCoords(row, col), soldiers, team)

  override def fieldById(id: Int): Option[Field] = fields.fieldById(id)

  override def fieldByCoords(row: Int, col: Int): Field = fields.fieldByCoords(row, col)

  override def toString(): String =
    val sb              = new StringBuilder()
    val infoString      = info.toString()
    val fieldLegend     = "\nField: ID (Soldiers)(OccupierID)(FieldType)\n"
    val topBottomBorder = ("+-" + ("----------------" * size)) + "-+\n"
    val leftBorder      = "| "
    val rightBorder     = " |\n"
    val space           = "  "

    sb.append(infoString)
    sb.append(fieldLegend)
    sb.append(topBottomBorder)
    (0 until size) foreach (i =>
         sb.append(leftBorder)
         (0 until size) foreach (j => sb.append(space + fieldByCoords(i, j).toString + space))
         sb.append(rightBorder))
    sb.append(topBottomBorder)
    sb.toString()
