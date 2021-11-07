import de.htwg.se.riskgame.util.{Observable, Observer}

val observable = new Observable
val observer1 = new TestObject
val observer2 = new TestObject

class TestObject extends Observer {
  def update: Unit = println("Ping")
}

observable.add(observer1)
observable.add(observer2)
observable.notifyObserver

observable.remove(observer1)
observable.notifyObserver
observable.remove(observer2)
observable.notifyObserver