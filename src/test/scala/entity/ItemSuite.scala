package entity

import ref.BaseStat
import ref.ItemQuality
import ref.Prefix
import ref.Suffix
import ref.Support
import util.BloodWarsFunSuite
import util.FakeEntity

class ItemSuite extends BloodWarsFunSuite {

  val fakeEntity = new FakeEntity(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)

  test("Support only, all quality") {
    val support = Support.HARD_HAT
    val prefix = null
    val suffix = null

    val normal = new Item(ItemQuality.NORMAL, support, prefix, suffix)
    val good = new Item(ItemQuality.GOOD, support, prefix, suffix)
    val perfect = new Item(ItemQuality.PERFECT, support, prefix, suffix)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, support, prefix, suffix)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, support, prefix, suffix)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, support, prefix, suffix)
    val epic = new Item(ItemQuality.EPIC, support, prefix, suffix)

    assert(1 == normal.effects.size)
    assert(1 == good.effects.size)
    assert(1 == perfect.effects.size)
    assert(1 == ln.effects.size)
    assert(1 == lg.effects.size)
    assert(1 == lp.effects.size)
    assert(1 == epic.effects.size)

    assert(3 == normal.get(BaseStat.DEFENCE, fakeEntity))
    assert(5 == good.get(BaseStat.DEFENCE, fakeEntity))
    assert(6 == perfect.get(BaseStat.DEFENCE, fakeEntity))
    assert(5 == ln.get(BaseStat.DEFENCE, fakeEntity))
    assert(7 == lg.get(BaseStat.DEFENCE, fakeEntity))
    assert(9 == lp.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == epic.get(BaseStat.DEFENCE, fakeEntity))
  }

  test("No suffix, all quality") {
    val support = Support.HARD_HAT
    val prefix = Prefix.HORNED
    val suffix = null

    val normal = new Item(ItemQuality.NORMAL, support, prefix, suffix)
    val good = new Item(ItemQuality.GOOD, support, prefix, suffix)
    val perfect = new Item(ItemQuality.PERFECT, support, prefix, suffix)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, support, prefix, suffix)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, support, prefix, suffix)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, support, prefix, suffix)
    val epic = new Item(ItemQuality.EPIC, support, prefix, suffix)

    assert(2 == normal.effects.size)
    assert(2 == good.effects.size)
    assert(2 == perfect.effects.size)
    assert(2 == ln.effects.size)
    assert(2 == lg.effects.size)
    assert(2 == lp.effects.size)
    assert(2 == epic.effects.size)

    assert(3 == normal.get(BaseStat.DEFENCE, fakeEntity))
    assert(5 == good.get(BaseStat.DEFENCE, fakeEntity))
    assert(6 == perfect.get(BaseStat.DEFENCE, fakeEntity))
    assert(5 == ln.get(BaseStat.DEFENCE, fakeEntity))
    assert(7 == lg.get(BaseStat.DEFENCE, fakeEntity))
    assert(9 == lp.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == epic.get(BaseStat.DEFENCE, fakeEntity))

    assert(5 == normal.get(BaseStat.DAMAGE, fakeEntity))
    assert(8 == good.get(BaseStat.DAMAGE, fakeEntity))
    assert(10 == perfect.get(BaseStat.DAMAGE, fakeEntity))
    assert(7 == ln.get(BaseStat.DAMAGE, fakeEntity))
    assert(11 == lg.get(BaseStat.DAMAGE, fakeEntity))
    assert(14 == lp.get(BaseStat.DAMAGE, fakeEntity))
    assert(18 == epic.get(BaseStat.DAMAGE, fakeEntity))
  }

  test("no prefix, all quality") {
    val support = Support.HARD_HAT
    val prefix = null
    val suffix = Suffix.OF_DURABILITY

    val normal = new Item(ItemQuality.NORMAL, support, prefix, suffix)
    val good = new Item(ItemQuality.GOOD, support, prefix, suffix)
    val perfect = new Item(ItemQuality.PERFECT, support, prefix, suffix)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, support, prefix, suffix)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, support, prefix, suffix)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, support, prefix, suffix)
    val epic = new Item(ItemQuality.EPIC, support, prefix, suffix)

    assert(1 == normal.effects.size)
    assert(1 == good.effects.size)
    assert(1 == perfect.effects.size)
    assert(1 == ln.effects.size)
    assert(1 == lg.effects.size)
    assert(1 == lp.effects.size)
    assert(1 == epic.effects.size)

    assert(7 == normal.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == good.get(BaseStat.DEFENCE, fakeEntity))
    assert(14 == perfect.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == ln.get(BaseStat.DEFENCE, fakeEntity))
    assert(16 == lg.get(BaseStat.DEFENCE, fakeEntity))
    assert(20 == lp.get(BaseStat.DEFENCE, fakeEntity))
    assert(25 == epic.get(BaseStat.DEFENCE, fakeEntity))
  }

  test("prefix and suffix, all quality") {
    val support = Support.HARD_HAT
    val prefix = Prefix.HORNED
    val suffix = Suffix.OF_DURABILITY

    val normal = new Item(ItemQuality.NORMAL, support, prefix, suffix)
    val good = new Item(ItemQuality.GOOD, support, prefix, suffix)
    val perfect = new Item(ItemQuality.PERFECT, support, prefix, suffix)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, support, prefix, suffix)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, support, prefix, suffix)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, support, prefix, suffix)
    val epic = new Item(ItemQuality.EPIC, support, prefix, suffix)

    assert(2 == normal.effects.size)
    assert(2 == good.effects.size)
    assert(2 == perfect.effects.size)
    assert(2 == ln.effects.size)
    assert(2 == lg.effects.size)
    assert(2 == lp.effects.size)
    assert(2 == epic.effects.size)

    assert(7 == normal.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == good.get(BaseStat.DEFENCE, fakeEntity))
    assert(14 == perfect.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == ln.get(BaseStat.DEFENCE, fakeEntity))
    assert(16 == lg.get(BaseStat.DEFENCE, fakeEntity))
    assert(20 == lp.get(BaseStat.DEFENCE, fakeEntity))
    assert(25 == epic.get(BaseStat.DEFENCE, fakeEntity))

    assert(5 == normal.get(BaseStat.DAMAGE, fakeEntity))
    assert(8 == good.get(BaseStat.DAMAGE, fakeEntity))
    assert(10 == perfect.get(BaseStat.DAMAGE, fakeEntity))
    assert(7 == ln.get(BaseStat.DAMAGE, fakeEntity))
    assert(11 == lg.get(BaseStat.DAMAGE, fakeEntity))
    assert(14 == lp.get(BaseStat.DAMAGE, fakeEntity))
    assert(18 == epic.get(BaseStat.DAMAGE, fakeEntity))
  }

  test("percent stat on prefix") {
    val item = new Item(ItemQuality.GOOD, Support.HARD_HAT, Prefix.SHAMANISTIC_HELMET, null)

    assert(2 == item.effects.size)

    assert(5 == item.get(BaseStat.DEFENCE, fakeEntity))
    assert(15 == item.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
  }

  test("malus should keep the same value for all quality") {
    val support = Support.HARD_HAT
    val prefix = null
    val suffix = Suffix.OF_BLOOD

    val normal = new Item(ItemQuality.NORMAL, support, prefix, suffix)
    val good = new Item(ItemQuality.GOOD, support, prefix, suffix)
    val perfect = new Item(ItemQuality.PERFECT, support, prefix, suffix)
    val ln = new Item(ItemQuality.LEGENDARY_NORMAL, support, prefix, suffix)
    val lg = new Item(ItemQuality.LEGENDARY_GOOD, support, prefix, suffix)
    val lp = new Item(ItemQuality.LEGENDARY_PERFECT, support, prefix, suffix)
    val epic = new Item(ItemQuality.EPIC, support, prefix, suffix)

    assert(3 == normal.effects.size)
    assert(3 == good.effects.size)
    assert(3 == perfect.effects.size)
    assert(3 == ln.effects.size)
    assert(3 == lg.effects.size)
    assert(3 == lp.effects.size)
    assert(3 == epic.effects.size)

    assert(7 == normal.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == good.get(BaseStat.DEFENCE, fakeEntity))
    assert(14 == perfect.get(BaseStat.DEFENCE, fakeEntity))
    assert(11 == ln.get(BaseStat.DEFENCE, fakeEntity))
    assert(17 == lg.get(BaseStat.DEFENCE, fakeEntity))
    assert(21 == lp.get(BaseStat.DEFENCE, fakeEntity))
    assert(25 == epic.get(BaseStat.DEFENCE, fakeEntity))

    assert(-10 == normal.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
    assert(-10 == good.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
    assert(-10 == perfect.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
    assert(-10 == ln.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
    assert(-10 == lg.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
    assert(-10 == lp.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
    assert(-10 == epic.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
  }

  test("bonus and malus on a single stat") {
    val item = new Item(ItemQuality.LEGENDARY_PERFECT, Support.MASK, Prefix.SHAMANISTIC_HELMET, Suffix.OF_BLOOD)

    assert(3 == item.effects.size)

    assert(21 == item.get(BaseStat.DEFENCE, fakeEntity))
    assert(27 == item.get(BaseStat.BLOOD_PTS_PERCENT, fakeEntity))
  }

  test("weapon atk count") {
    val supportSingleAtk = new Item(ItemQuality.EPIC, Support.CLUB, null, null)
    val supportMultiAtk = new Item(ItemQuality.EPIC, Support.KNUCKLE_DUSTER, null, null)
    val supportSingleAtkWithPrefix = new Item(ItemQuality.EPIC, Support.CLUB, Prefix.AGILE_ONE_HANDED_MELEE, null)
    val supportMultiAtkWithPrefix = new Item(ItemQuality.EPIC, Support.KNUCKLE_DUSTER, Prefix.AGILE_ONE_HANDED_MELEE, null)

    assert(3 == supportSingleAtk.effects.size)
    assert(4 == supportMultiAtk.effects.size)
    assert(4 == supportSingleAtkWithPrefix.effects.size)
    assert(4 == supportMultiAtkWithPrefix.effects.size)

    assert(1 == supportSingleAtk.get(BaseStat.WEAPON_ATK_COUNT, fakeEntity))
    assert(7 == supportMultiAtk.get(BaseStat.WEAPON_ATK_COUNT, fakeEntity))
    assert(7 == supportSingleAtkWithPrefix.get(BaseStat.WEAPON_ATK_COUNT, fakeEntity))
    assert(14 == supportMultiAtkWithPrefix.get(BaseStat.WEAPON_ATK_COUNT, fakeEntity))
  }
}