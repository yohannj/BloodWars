package entity

import scala.collection.JavaConverters
import ref.ItemQuality
import ref.Prefix
import ref.Suffix
import ref.Support
import ref.BaseStat
import util.Effect
import ref.DynamicModifierStat
import scala.collection.mutable.ListBuffer

class Item(val quality: ItemQuality, val support: Support, val prefix: Prefix = null, val suffix: Suffix = null) {

  if (prefix != null)
    require(support.getCat == prefix.getCat)

  if (suffix != null)
    require(support.getCat == suffix.getCat)

  private var baseEffects = ListBuffer[Effect]();
  if (support.getBaseDefence != 0)
    baseEffects += new Effect(BaseStat.DEFENCE, applyQualityModifier(support.getBaseDefence))
  if (support.getBaseMinDamage != 0)
    baseEffects += new Effect(BaseStat.DAMAGE_MIN, applyQualityModifier(support.getBaseMinDamage))
  if (support.getBaseMaxDamage != 0)
    baseEffects += new Effect(BaseStat.DAMAGE_MAX, applyQualityModifier(support.getBaseMaxDamage))

  private var otherEffects = JavaConverters.asScalaBufferConverter(support.getEffects).asScala.toList
  if (prefix != null)
    otherEffects = otherEffects.union(JavaConverters.asScalaBufferConverter(prefix.getEffects).asScala)

  if (suffix != null)
    otherEffects = otherEffects.union(JavaConverters.asScalaBufferConverter(suffix.getEffects).asScala)

  val effects = otherEffects
    .groupBy(f => (f.stat, f.dynamicModifierStat))
    .values.map(buf => buf.reduce((e1, e2) => new Effect(e1.stat, e1.modifier + e2.modifier, e1.dynamicModifierStat, e1.dynamicModifier)))
    .map(f => new Effect(f.stat, applyQualityModifier(f.modifier), f.dynamicModifierStat, f.dynamicModifier))
    .toBuffer
    .union(baseEffects)
    .groupBy(f => (f.stat, f.dynamicModifierStat))
    .values.map(buf => buf.reduce((e1, e2) => new Effect(e1.stat, e1.modifier + e2.modifier, e1.dynamicModifierStat, e1.dynamicModifier)))
    .toBuffer

  def get(baseStat: BaseStat, dynamicStat: DynamicModifierStat): Double = effects.filter(e => e.stat == baseStat && e.dynamicModifierStat == dynamicStat).headOption.map(f => f.modifier).getOrElse(0)

  private def applyQualityModifier(baseModifier: Double): Double = Math.ceil(Math.ceil(baseModifier * quality.getBaseQualityBonus) * quality.getLegendaryBonus);
}