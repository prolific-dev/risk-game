package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

trait Field:
    def id                   : Int
    def x                    : Int
    def y                    : Int
    def mapSize              : Int
    def name                 : String
    def areaId               : Int
    def neighborCoordsTop    : Option[(Int, Int)]
    def neighborCoordsBottom : Option[(Int, Int)]
    def neighborCoordsLeft   : Option[(Int, Int)]
    def neighborCoordsRight  : Option[(Int, Int)]

abstract class AbstractField(x: Int, y: Int, mapSize: Int):
    def nTop   : Option[(Int, Int)] = calcNeighbors("top")
    def nBottom: Option[(Int, Int)] = calcNeighbors("bottom")
    def nLeft  : Option[(Int, Int)] = calcNeighbors("left")
    def nRight : Option[(Int, Int)] = calcNeighbors("right")

    private def calcNeighbors(direction: String): Option[(Int, Int)] =
        direction match
            case "top"    => if (y == 0) None else Some((x, y - 1))
            case "left"   => if (x == 0) None else Some((x - 1, y))
            case "bottom" => if (y == (mapSize - 1)) None else Some((x, y + 1))
            case "right"  => if (x == (mapSize - 1)) None else Some((x + 1, y))


object Field:

    def apply(id: Int, x: Int, y: Int, mapSize: Int, soldiers: Int, occupier: Option[Team], name: String, areaId: Int): Field =
        AreaField(id, x, y, mapSize, soldiers, occupier, name, areaId)

    def apply(kind: String, id: Int, x: Int, y: Int, mapSize: Int, name: String, areaId: Int): Field =
        kind match
            case "area"    => AreaField   (id, x, y, mapSize, 0, None, name, areaId)
            case "blocked" => BlockedField(id, x, y, mapSize, name, areaId)
            case "bridge"  => BridgeField(id, x, y, mapSize, name, areaId)


case class AreaField(
    id      : Int,
    x       : Int,
    y       : Int,
    mapSize : Int,
    soldiers: Int,
    occupier: Option[Team],
    name    : String,
    areaId  : Int) extends AbstractField(x, y, mapSize) with Field:

    def setSoldiers(newSoldiers: Int): AreaField = if newSoldiers < 0 then this else copy(soldiers = newSoldiers)

    def setOccupier(newOccupier: Option[Team]): AreaField = copy(occupier=newOccupier)

    override def neighborCoordsTop   : Option[(Int, Int)] = super.nTop
    override def neighborCoordsBottom: Option[(Int, Int)] = super.nBottom
    override def neighborCoordsLeft  : Option[(Int, Int)] = super.nLeft
    override def neighborCoordsRight : Option[(Int, Int)] = super.nRight

    override def toString: String =
        ""
        + {if (id < 10) " " else ""}
        + s"$id "
        + s"($soldiers)"
        + s"("
        + s"${occupier match
                case Some(occupier) => occupier.getId
                case None           => 0
            }"
        + s")"
        + s"(A)"

case class BlockedField(
    id      : Int,
    x       : Int,
    y       : Int,
    mapSize : Int,
    name    : String,
    areaId  : Int) extends AbstractField(x, y, mapSize) with Field:

    override def neighborCoordsTop   : Option[(Int, Int)] = super.nTop
    override def neighborCoordsBottom: Option[(Int, Int)] = super.nBottom
    override def neighborCoordsLeft  : Option[(Int, Int)] = super.nLeft
    override def neighborCoordsRight : Option[(Int, Int)] = super.nRight

    override def toString: String = "" + {if (id < 10) " " else ""} + s"$id (0)(0)(B)"


case class BridgeField(
    id      : Int,
    x       : Int,
    y       : Int,
    mapSize : Int,
    name    : String,
    areaId  : Int) extends AbstractField(x, y, mapSize) with Field:

    override def neighborCoordsTop   : Option[(Int, Int)] = super.nTop
    override def neighborCoordsBottom: Option[(Int, Int)] = super.nBottom
    override def neighborCoordsLeft  : Option[(Int, Int)] = super.nLeft
    override def neighborCoordsRight : Option[(Int, Int)] = super.nRight

    override def toString: String = "" + {if (id < 10) " " else ""} + s"$id (0)(0)(R)"
