package entity

import ref.BaseStat
import util.BloodWarsFunSuite
import util.Effect
import ref.ItemQuality
import ref.Support
import ref.Suffix
import ref.Prefix

class ItemSuite extends BloodWarsFunSuite {

  test("Hard hat") {
    val normal = new Item(ItemQuality.NORMAL, Support.HARD_HAT, null, null)
    val good = new Item(ItemQuality.GOOD, Support.HARD_HAT, null, null)
    val perfect = new Item(ItemQuality.PERFECT, Support.HARD_HAT, null, null)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, Support.HARD_HAT, null, null)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, Support.HARD_HAT, null, null)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, Support.HARD_HAT, null, null)
    val epic = new Item(ItemQuality.EPIC, Support.HARD_HAT, null, null)

    assert(1 == normal.effects.size)
    assert(1 == good.effects.size)
    assert(1 == perfect.effects.size)
    assert(1 == ln.effects.size)
    assert(1 == lg.effects.size)
    assert(1 == lp.effects.size)
    assert(1 == epic.effects.size)

    assert(3 == normal.get(BaseStat.DEFENCE, null))
    assert(5 == good.get(BaseStat.DEFENCE, null))
    assert(6 == perfect.get(BaseStat.DEFENCE, null))
    assert(5 == ln.get(BaseStat.DEFENCE, null))
    assert(7 == lg.get(BaseStat.DEFENCE, null))
    assert(9 == lp.get(BaseStat.DEFENCE, null))
    assert(11 == epic.get(BaseStat.DEFENCE, null))
  }

  test("Horned Hard hat") {
    val normal = new Item(ItemQuality.NORMAL, Support.HARD_HAT, Prefix.HORNED, null)
    val good = new Item(ItemQuality.GOOD, Support.HARD_HAT, Prefix.HORNED, null)
    val perfect = new Item(ItemQuality.PERFECT, Support.HARD_HAT, Prefix.HORNED, null)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, Support.HARD_HAT, Prefix.HORNED, null)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, Support.HARD_HAT, Prefix.HORNED, null)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, Support.HARD_HAT, Prefix.HORNED, null)
    val epic = new Item(ItemQuality.EPIC, Support.HARD_HAT, Prefix.HORNED, null)

    assert(2 == normal.effects.size)
    assert(2 == good.effects.size)
    assert(2 == perfect.effects.size)
    assert(2 == ln.effects.size)
    assert(2 == lg.effects.size)
    assert(2 == lp.effects.size)
    assert(2 == epic.effects.size)

    assert(3 == normal.get(BaseStat.DEFENCE, null))
    assert(5 == good.get(BaseStat.DEFENCE, null))
    assert(6 == perfect.get(BaseStat.DEFENCE, null))
    assert(5 == ln.get(BaseStat.DEFENCE, null))
    assert(7 == lg.get(BaseStat.DEFENCE, null))
    assert(9 == lp.get(BaseStat.DEFENCE, null))
    assert(11 == epic.get(BaseStat.DEFENCE, null))

    assert(5 == normal.get(BaseStat.DAMAGE, null))
    assert(8 == good.get(BaseStat.DAMAGE, null))
    assert(10 == perfect.get(BaseStat.DAMAGE, null))
    assert(7 == ln.get(BaseStat.DAMAGE, null))
    assert(11 == lg.get(BaseStat.DAMAGE, null))
    assert(14 == lp.get(BaseStat.DAMAGE, null))
    assert(18 == epic.get(BaseStat.DAMAGE, null))
  }

  test("Hard hat of Durability") {
    val normal = new Item(ItemQuality.NORMAL, Support.HARD_HAT, null, Suffix.OF_DURABILITY)
    val good = new Item(ItemQuality.GOOD, Support.HARD_HAT, null, Suffix.OF_DURABILITY)
    val perfect = new Item(ItemQuality.PERFECT, Support.HARD_HAT, null, Suffix.OF_DURABILITY)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, Support.HARD_HAT, null, Suffix.OF_DURABILITY)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, Support.HARD_HAT, null, Suffix.OF_DURABILITY)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, Support.HARD_HAT, null, Suffix.OF_DURABILITY)
    val epic = new Item(ItemQuality.EPIC, Support.HARD_HAT, null, Suffix.OF_DURABILITY)

    assert(1 == normal.effects.size)
    assert(1 == good.effects.size)
    assert(1 == perfect.effects.size)
    assert(1 == ln.effects.size)
    assert(1 == lg.effects.size)
    assert(1 == lp.effects.size)
    assert(1 == epic.effects.size)

    assert(7 == normal.get(BaseStat.DEFENCE, null))
    assert(11 == good.get(BaseStat.DEFENCE, null))
    assert(14 == perfect.get(BaseStat.DEFENCE, null))
    assert(11 == ln.get(BaseStat.DEFENCE, null))
    assert(16 == lg.get(BaseStat.DEFENCE, null))
    assert(20 == lp.get(BaseStat.DEFENCE, null))
    assert(25 == epic.get(BaseStat.DEFENCE, null))
  }

  test("Horned Hard hat of Durability") {
    val normal = new Item(ItemQuality.NORMAL, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)
    val good = new Item(ItemQuality.GOOD, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)
    val perfect = new Item(ItemQuality.PERFECT, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)
    val epic = new Item(ItemQuality.EPIC, Support.HARD_HAT, Prefix.HORNED, Suffix.OF_DURABILITY)

    assert(2 == normal.effects.size)
    assert(2 == good.effects.size)
    assert(2 == perfect.effects.size)
    assert(2 == ln.effects.size)
    assert(2 == lg.effects.size)
    assert(2 == lp.effects.size)
    assert(2 == epic.effects.size)

    assert(7 == normal.get(BaseStat.DEFENCE, null))
    assert(11 == good.get(BaseStat.DEFENCE, null))
    assert(14 == perfect.get(BaseStat.DEFENCE, null))
    assert(11 == ln.get(BaseStat.DEFENCE, null))
    assert(16 == lg.get(BaseStat.DEFENCE, null))
    assert(20 == lp.get(BaseStat.DEFENCE, null))
    assert(25 == epic.get(BaseStat.DEFENCE, null))

    assert(5 == normal.get(BaseStat.DAMAGE, null))
    assert(8 == good.get(BaseStat.DAMAGE, null))
    assert(10 == perfect.get(BaseStat.DAMAGE, null))
    assert(7 == ln.get(BaseStat.DAMAGE, null))
    assert(11 == lg.get(BaseStat.DAMAGE, null))
    assert(14 == lp.get(BaseStat.DAMAGE, null))
    assert(18 == epic.get(BaseStat.DAMAGE, null))
  }
}