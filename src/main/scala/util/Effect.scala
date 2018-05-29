package util

import ref.BaseStat
import ref.DynamicModifierStat

class Effect(val stat: BaseStat, modifier: Double, dynamicModifierStat: DynamicModifierStat, dynamicModifier: Double) {

  def this(stat: BaseStat, modifier: Double) {
    this(stat, modifier, null, 0)
  }

  def getActualModifier(e: Entity): Double =
    dynamicModifierStat match {
      case DynamicModifierStat.LEVEL => 1.0
      case _ => modifier
    }
}