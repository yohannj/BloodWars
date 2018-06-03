package entity

import ref.BaseStat
import org.apache.commons.lang3.NotImplementedException

abstract class Entity {

  def level: Int
  def strengh: Int
  def agility: Int
  def toughness: Int
  def appearance: Int
  def charisma: Int
  def reputation: Int
  def perception: Int
  def intelligence: Int
  def knowledge: Int

  def get(stat: BaseStat): Int =
    stat match {
      case BaseStat.LEVEL        => level
      case BaseStat.STRENGH      => strengh
      case BaseStat.APPEARANCE   => appearance
      case BaseStat.INTELLIGENCE => intelligence
      case BaseStat.KNOWLEDGE    => knowledge
      case _                     => throw new IllegalArgumentException("Cannot retrieve the stat " + stat)
    }

}