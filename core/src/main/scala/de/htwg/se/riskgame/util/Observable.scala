package de.htwg.se.riskgame.util

class Observable:

  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer)   : Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObserver()   : Unit = subscribers.foreach(o => o.update)
