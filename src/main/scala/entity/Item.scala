package entity

import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer

import ref.BaseStat
import ref.ItemQuality
import ref.Prefix
import ref.Suffix
import ref.Support
import util.Effect
import ref.ItemCategory

class Item(val quality: ItemQuality, val support: Support, val prefix: Prefix = null, val suffix: Suffix = null) {

  if (prefix != null)
    require(support.getCat == prefix.getCat, "Item category mismatch between the support and the prefix, " + support.getCat + " is different than " + prefix.getCat)

  if (suffix != null)
    require(support.getCat == suffix.getCat, "Item category mismatch between the support and the suffix, " + support.getCat + " is different than " + suffix.getCat)

  private var baseEffects = ListBuffer[Effect]();
  if (support.getBaseDefence != 0)
    baseEffects += new Effect(BaseStat.DEFENCE, applyQualityModifier(support.getBaseDefence))
  if (support.getBaseMinDamage != 0)
    baseEffects += new Effect(BaseStat.WEAPON_DMG_MIN, applyQualityModifier(support.getBaseMinDamage))
  if (support.getBaseMaxDamage != 0)
    baseEffects += new Effect(BaseStat.WEAPON_DMG_MAX, applyQualityModifier(support.getBaseMaxDamage))

  private var otherEffects = JavaConverters.asScalaBufferConverter(support.getEffects).asScala.toList
  if (prefix != null)
    otherEffects = otherEffects.union(JavaConverters.asScalaBufferConverter(prefix.getEffects).asScala)

  if (suffix != null)
    otherEffects = otherEffects.union(JavaConverters.asScalaBufferConverter(suffix.getEffects).asScala)

  val effects = otherEffects
    .groupBy(f => (f.stat, f.dynamicModifierStat))
    .values.map(buf => buf.reduce((e1, e2) => new Effect(e1.stat, e1.modifier + e2.modifier, e1.dynamicModifier, e1.dynamicModifierStat)))
    .map(f => new Effect(f.stat, applyQualityModifier(f.modifier), f.dynamicModifier, f.dynamicModifierStat))
    .toBuffer
    .union(baseEffects)
    .groupBy(f => (f.stat, f.dynamicModifierStat))
    .values.map(buf => buf.reduce((e1, e2) => new Effect(e1.stat, e1.modifier + e2.modifier, e1.dynamicModifier, e1.dynamicModifierStat)))
    .toBuffer

  def get(baseStat: BaseStat, entity: Entity): Int = {
    val defaultValue =
      baseStat match {
        case BaseStat.WEAPON_ATK_COUNT => if (isWeapon) 1 else 0
        case _                         => 0
      }

    effects.filter(e => e.stat == baseStat)
      .map(e => e.getActualModifier(entity))
      .reduceOption(_ + _)
      .getOrElse(defaultValue)
  }

  def isHelmet = support.getCat == ItemCategory.HELMET
  def isArmour = support.getCat == ItemCategory.ARMOUR
  def isPants = support.getCat == ItemCategory.PANTS
  def isAmulet = support.getCat == ItemCategory.AMULET
  def isRing = support.getCat == ItemCategory.RING
  def isOneHandedWeapon = ItemCategory.ONE_HANDED_WEAPON_CATEGORIES.contains(support.getCat)
  def isTwoHandedWeapon = ItemCategory.TWO_HANDED_WEAPON_CATEGORIES.contains(support.getCat)
  def isWeapon = ItemCategory.WEAPON_CATEGORIES.contains(support.getCat)

  private def applyQualityModifier(baseModifier: Int): Int =
    if (baseModifier < 0)
      baseModifier
    else
      Math.ceil(Math.ceil(baseModifier * quality.getBaseQualityBonus) * quality.getLegendaryBonus).toInt
}