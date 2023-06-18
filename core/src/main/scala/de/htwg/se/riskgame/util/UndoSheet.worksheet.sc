class UndoManager:
    
    private var undoStack: List[Int] = Nil
    private var redoStack: List[Int] = Nil

    def doStep(command: Int): Unit =
        undoStack = command :: undoStack
        println("doStep " + command + " :: " + undoStack.toString + " :: " + redoStack.toString)
        

    def undoStep(): Unit =
        undoStack match
            case Nil =>
            case head :: stack =>
                undoStack = stack
                redoStack = head :: redoStack
                println("undoStep " + head + " :: " + undoStack.toString + " :: " + redoStack.toString)

    def redoStep(): Unit =
        redoStack match
            case Nil =>
            case head :: stack =>
                redoStack = stack
                undoStack = head :: undoStack
                println("redoStep " + head + " :: " + undoStack.toString + " :: " + redoStack.toString)

val um: UndoManager = new UndoManager

um.doStep(1)
um.doStep(2)
um.doStep(3)

um.undoStep()
um.undoStep()
um.undoStep()

um.redoStep()
um.redoStep()
um.redoStep()