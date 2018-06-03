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

  test("Manage effects like 'grant X per Y stat' dynamic modifier") {
    val stat = BaseStat.DAMAGE_MIN
    val modifier = 6
    val dynamicStat = BaseStat.LEVEL
    val dynamicModifier = 4

    val level1 = dynamicModifier * 2 - 1
    val level2 = level1 + 1
    val e1 = new FakeEntity(level1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    val e2 = new FakeEntity(level2, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    val effect = new Effect(stat, modifier, (entityLevel: Int) => entityLevel / dynamicModifier, dynamicStat)
    assert(stat == effect.stat)
    assert(modifier * Math.floor(level1 / dynamicModifier) == effect.getActualModifier(e1))
    assert(modifier * Math.floor(level2 / dynamicModifier) == effect.getActualModifier(e2))
    assert(effect.getActualModifier(e1) + modifier == effect.getActualModifier(e2))
  }

  test("Manage effects like 'X increased by Y% of a stat' dynamic modifier") {
    val stat = BaseStat.DAMAGE_MIN
    val modifier = 1
    val dynamicStat = BaseStat.INTELLIGENCE
    val dynamicModifier = 0.4

    val intel1 = 129
    val intel2 = 130
    val e1 = new FakeEntity(0, 0, 0, 0, 0, 0, 0, 0, intel1, 0);
    val e2 = new FakeEntity(0, 0, 0, 0, 0, 0, 0, 0, intel2, 0);

    val effect = new Effect(stat, modifier, (entityIntel: Int) => entityIntel * dynamicModifier, dynamicStat)
    assert(stat == effect.stat)
    assert(Math.floor(intel1 * dynamicModifier) == effect.getActualModifier(e1))
    assert(Math.floor(intel2 * dynamicModifier) == effect.getActualModifier(e2))
    assert(effect.getActualModifier(e1) + 1 == effect.getActualModifier(e2))
  }
}