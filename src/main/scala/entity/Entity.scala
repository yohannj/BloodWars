package entity

import ref.DynamicModifierStat

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

  def get(stat: DynamicModifierStat): Int =
    stat match {
      case DynamicModifierStat.LEVEL        => level
      case DynamicModifierStat.STRENGH      => strengh
      case DynamicModifierStat.APPEARANCE   => appearance
      case DynamicModifierStat.INTELLIGENCE => intelligence
      case DynamicModifierStat.KNOWLEDGE    => knowledge
    }

}