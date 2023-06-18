package de.htwg.se.riskgame.aview.tui

import scala.io.StdIn.readLine
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.util.Observer
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Team

import scala.annotation.tailrec

class TUI(controller: ControllerInterface) extends Observer:
    controller.add(this)

    private def processInputLine(input: String): Unit =
        input match
            case "n" => controller.createDesk()
            case "a" => controller.attackField()
            case "s" =>
                println("Which Field ID?")
                val id       = readLine.toInt
                println("How many soldiers?")
                val soldiers = readLine.toInt
                println("Which team?")
                val team = readLine match
                    case "blue"   => Team.Blue
                    case "red"    => Team.Red
                    case "green"  => Team.Green
                    case "yellow" => Team.Yellow
                    case _        => Team.NoTeam
                controller.setField(id, soldiers, team)
            case "f" =>
                println("Which Field ID?")
                val id = readLine.toInt
                controller.selectFieldById(id)
            case "c" => controller.clearSelectedFields()
            case "r" => controller.redo()
            case "u" => controller.undo()
            case "m" => controller.save()
            case "l" => controller.load()
            case "h" =>
                val manual = """|
                | _______________________________________
                ||                                       |
                ||#######################################|
                || This is the Manual of Risk Game       |
                ||#######################################|
                ||                                       |
                ||=======================================|
                || Key  <>    Function                   |
                ||=======================================|
                ||                                       |
                ||  n   <>    Create New Desk            |
                ||                                       |
                ||  a   <>    Attack Field               |
                ||                                       |
                ||  s   <>    Set Field                  |
                ||                                       |
                ||     Steps to do:                      |
                ||                                       |
                ||         1. Choose Field ID            |
                ||         2. Choose amount of soldiers  |
                ||         3. Choose team                |
                ||                                       |
                ||  f   <>    Select Field               |
                ||                                       |
                ||     Steps to do:                      |
                ||                                       |
                ||         1. Choose Field ID            |
                ||                                       |
                ||  c   <>    Clear Selection            |
                ||                                       |
                ||  r   <>    Redo                       |
                ||                                       |
                ||  u   <>    Undo                       |
                ||                                       |
                ||  q   <>    Quit                       |
                ||                                       |
                ||                                       |
                ||_______________________________________|
                |
                |""".stripMargin
                println(manual)
            case _ =>


    @tailrec
    private def inputLoop(): Unit =
        readLine match
            case "q"           =>
            case input: String => processInputLine(input); inputLoop()

    def run(): Unit =
        println("Welcome to Risk Game!")
        inputLoop()

    override def update: Unit = println(controller.deskToString())
