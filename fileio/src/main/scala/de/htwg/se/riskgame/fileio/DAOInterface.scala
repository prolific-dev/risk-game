package de.htwg.se.riskgame.fileio

trait DAOInterface:
    def save: Unit
    def load: Unit
    def list: Unit
