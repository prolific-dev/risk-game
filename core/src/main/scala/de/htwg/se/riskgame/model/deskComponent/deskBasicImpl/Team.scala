package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

enum Team(id: Int, rgb: Int):
  
  def getId : Int = id
  def getRgb: Int = rgb

  case NoTeam extends Team(0, 0xFFFFFF)
  case Blue   extends Team(1, 0x0000FF)
  case Red    extends Team(2, 0xFF0000)
  case Green  extends Team(3, 0x00FF00)
  case Yellow extends Team(4, 0xFFFF00)

end Team
