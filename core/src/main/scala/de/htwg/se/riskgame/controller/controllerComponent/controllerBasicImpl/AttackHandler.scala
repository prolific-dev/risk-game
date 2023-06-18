package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl

import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.AreaField
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Team
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface

case class AttackRequest (sourceField: Option[Field], targetField: Option[Field], controller: ControllerInterface)
case class AttackResponse(handled: Boolean, error: String, desk: DeskInterface)

object AttackHandler:
    val performAttack       = new PerformAttack      (None)
    val checkSourceSoldiers = new CheckSourceSoldiers(Some(performAttack))
    val checkFieldNeighbor  = new CheckFieldNeighbor (Some(checkSourceSoldiers))
    val checkSelectedField  = new CheckSelectedField (Some(checkFieldNeighbor))


    def handleAttackRequest(attackRequest: AttackRequest): AttackResponse =
        checkSelectedField.handleAttackRequest(attackRequest)


abstract class AttackHandler:
    val successor: Option[AttackHandler]

    def handleAttackRequest(attackRequest: AttackRequest): AttackResponse

class CheckSelectedField(val successor: Option[AttackHandler]) extends AttackHandler:
  override def handleAttackRequest(attackRequest: AttackRequest): AttackResponse =
    attackRequest.sourceField match
        case None              => AttackResponse(false, "No source field", attackRequest.controller.desk)
        case Some(sourceField) =>
            attackRequest.targetField match
                case None              => AttackResponse(false, "No target field", attackRequest.controller.desk)
                case Some(targetField) =>
                    successor match
                        case None            => AttackResponse(false, "No successor for CheckSelectedField", attackRequest.controller.desk)
                        case Some(successor) => successor.handleAttackRequest(attackRequest)


class CheckFieldNeighbor(val successor: Option[AttackHandler]) extends AttackHandler:

    override def handleAttackRequest(attackRequest: AttackRequest): AttackResponse =
        val sourceField: Field = attackRequest.sourceField.get
        val targetField: Field = attackRequest.targetField.get

        val sourceNeighborList: List[Option[(Int, Int)]] = List(
            sourceField.neighborCoordsTop, 
            sourceField.neighborCoordsBottom,
            sourceField.neighborCoordsLeft,
            sourceField.neighborCoordsRight
        )

        sourceNeighborList.contains(Some((targetField.x, targetField.y))) match
            case false => AttackResponse(false, "Target is no neighbor", attackRequest.controller.desk)
            case true  =>
                successor match
                    case None            => AttackResponse(false, "No successor for CheckFieldNeighbor", attackRequest.controller.desk)
                    case Some(successor) => successor.handleAttackRequest(attackRequest) 

class CheckSourceSoldiers(val successor: Option[AttackHandler]) extends AttackHandler:

    override def handleAttackRequest(attackRequest: AttackRequest): AttackResponse =
        val sourceField: Field = attackRequest.sourceField.get

        sourceField.asInstanceOf[AreaField].soldiers match
            case soldiers if soldiers <= 1 => AttackResponse(false, "Not enough soldiers to attack", attackRequest.controller.desk)
            case _                         =>
                successor match
                    case None            => AttackResponse(false, "No successor for CheckSourceSoldiers", attackRequest.controller.desk)
                    case Some(successor) => successor.handleAttackRequest(attackRequest)

class PerformAttack(val successor: Option[AttackHandler]) extends AttackHandler:
    
    override def handleAttackRequest(attackRequest: AttackRequest): AttackResponse =
        val sourceField   : Field = attackRequest.sourceField.get
        val targetField   : Field = attackRequest.targetField.get
        val sourceId      : Int   = sourceField.id
        val targetId      : Int   = targetField.id
        val sourceTeam    : Team  = sourceField.asInstanceOf[AreaField].occupier.get
        val targetTeam    : Team  = targetField.asInstanceOf[AreaField].occupier.get
        val sourceSoldiers: Int   = sourceField.asInstanceOf[AreaField].soldiers
        val targetSoldiers: Int   = targetField.asInstanceOf[AreaField].soldiers

        attackRequest.controller.clearSelectedFields()

        if (sourceSoldiers < targetSoldiers)
            AttackResponse(true, "", attackRequest.controller.desk.replaceFieldById(sourceField.id, 1, sourceTeam))
        else if (sourceSoldiers == targetSoldiers)
            AttackResponse(true, "", attackRequest.controller.desk
                                        .replaceFieldById(sourceId, 1, sourceTeam)
                                        .replaceFieldById(targetId, 1, targetTeam))
        else
            AttackResponse(true, "", attackRequest.controller.desk
                                        .replaceFieldById(sourceId, 1, sourceTeam)
                                        .replaceFieldById(targetId, sourceSoldiers - 1, sourceTeam))
