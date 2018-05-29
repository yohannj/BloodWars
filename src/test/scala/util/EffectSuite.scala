package util

import ref.BaseStat

class EffectSuite extends BloodWarsFunSuite {

  test("Without dynamic modifier, the actual modifier should be the given modifier") {
    val stat = BaseStat.DEFENCE
    val modifier = 2

    val effect = new Effect(stat, modifier)
    assert(stat == effect.stat)
    assert(2 == effect.getActualModifier(null))
  }
}