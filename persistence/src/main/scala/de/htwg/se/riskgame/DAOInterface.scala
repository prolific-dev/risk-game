package de.htwg.se.riskgame

trait DAOInterface:
    def save: Unit
    def load: Unit
    def list: Unit
