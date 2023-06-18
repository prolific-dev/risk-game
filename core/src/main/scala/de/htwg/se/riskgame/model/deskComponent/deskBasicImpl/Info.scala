package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

case class Info(
    selectedFieldPrimary  : Option[Field],
    selectedFieldSecondary: Option[Field],
    playerList            : List[Player],
    playerTurn            : Int):

    def this() = this(None, None, Nil, 1)

    def clearSelectedFields(): Info = copy(selectedFieldPrimary = None, selectedFieldSecondary = None)

    def selectField(field: Field): Info =
        selectedFieldPrimary match
            case None          => copy(selectedFieldPrimary = Some(field))
            case Some(primary) =>
                selectedFieldSecondary match
                    case Some(secondary) =>
                        field match
                            case _ if primary.equals(field) || secondary.equals(field) => 
                                copy(selectedFieldSecondary = None)
                            case _                                                     =>
                                copy(selectedFieldPrimary = Some(field), selectedFieldSecondary = None)
                    case None            =>
                        field match
                            case _ if primary.equals(field) => copy(selectedFieldPrimary = None)
                            case _ if !primary.isInstanceOf[AreaField] => copy(selectedFieldPrimary = Some(field))
                            case _ : AreaField => copy(selectedFieldSecondary = Some(field))
                            case _             => copy(selectedFieldPrimary = Some(field))
                        

    override def toString: String =

        def selectToString(select: Option[Field]): String =
            s"${select match
                    case Some(field) => field.toString
                    case None        => "--"}"

        ""
        + s"\nPlayer-Turn: $playerTurn\n"
        + s"\nchosenField(1): "
        + s"${selectToString(selectedFieldPrimary)}"
        + s"\nchosenField(2): "
        + s"${selectToString(selectedFieldSecondary)}\n"
