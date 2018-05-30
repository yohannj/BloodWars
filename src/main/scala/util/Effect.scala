package util

import ref.BaseStat
import ref.DynamicModifierStat
import entity.Entity

class Effect(val stat: BaseStat, val modifier: Double, val dynamicModifierStat: DynamicModifierStat, val dynamicModifier: Int => Double) {

  def this(stat: BaseStat, modifier: Double) {
    this(stat, modifier, null, null)
  }

  def getActualModifier(e: Entity): Double = {
    if (dynamicModifierStat == null) {
      modifier
    } else {
      modifier * Math.floor(dynamicModifier(e.get(dynamicModifierStat)))
    }
  }
}