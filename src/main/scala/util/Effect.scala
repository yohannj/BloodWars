package util

import ref.BaseStat
import entity.Entity

class Effect(val stat: BaseStat, val modifier: Int, val dynamicModifier: Int => Double, val dynamicModifierStat: BaseStat) {

  def this(stat: BaseStat, modifier: Int) {
    this(stat, modifier, null, null)
  }

  def getActualModifier(e: Entity): Int = {
    if (dynamicModifierStat == null) {
      modifier
    } else {
      modifier * Math.floor(dynamicModifier(e.get(dynamicModifierStat))).toInt
    }
  }
}