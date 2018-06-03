package ref;

import static ref.BaseStat.AGILITY;
import static ref.BaseStat.APPEARANCE;
import static ref.BaseStat.BLOOD_PTS_PERCENT;
import static ref.BaseStat.CHARISMA;
import static ref.BaseStat.CRIT_HIT_PERCENT;
import static ref.BaseStat.DAMAGE;
import static ref.BaseStat.DAMAGE_MAX;
import static ref.BaseStat.IGNORE_ALL_DEF;
import static ref.BaseStat.IGNORE_DEF_PERCENT;
import static ref.BaseStat.IGNORE_HALF_DEF;
import static ref.BaseStat.LUCK;
import static ref.BaseStat.PERCEPTION;
import static ref.BaseStat.REPUTATION;
import static ref.BaseStat.RESILIENCE_PERCENT;
import static ref.BaseStat.TOUGHNESS;
import static ref.BaseStat.WEAPON_ATK_COUNT;
import static ref.BaseStat.WEAPON_CRIT_DMG_BONUS_PERCENT;
import static ref.BaseStat.WEAPON_HIT;

import java.util.Arrays;
import java.util.List;

import util.Effect;

public enum Support {

    // Helmet
    HARD_HAT(ItemCategory.HELMET, 3, 0, 0),
    HELMET(ItemCategory.HELMET, 7, 0, 0, new Effect(RESILIENCE_PERCENT, 1)),
    MASK(ItemCategory.HELMET, 3, 0, 0, new Effect(BLOOD_PTS_PERCENT, 10)),
    CIRCLET(ItemCategory.HELMET, 1, 0, 0, new Effect(CHARISMA, 5)),
    CAP(ItemCategory.HELMET, 1, 0, 0),
    BALACLAVA(ItemCategory.HELMET, 1, 0, 0, new Effect(REPUTATION, 2), new Effect(APPEARANCE, -5)),
    HAT(ItemCategory.HELMET, 2, 0, 0, new Effect(APPEARANCE, 3)),
    CROWN(ItemCategory.HELMET, 12, 0, 0, new Effect(DAMAGE_MAX, 5), new Effect(CHARISMA, 3), new Effect(REPUTATION, 3)),
    HEADBAND(ItemCategory.HELMET, 2, 0, 0, new Effect(APPEARANCE, 5), new Effect(PERCEPTION, 3)),
    BANDANNA(ItemCategory.HELMET, 4, 0, 0, new Effect(DAMAGE, 1), new Effect(APPEARANCE, 3), new Effect(PERCEPTION, 1)),

    // Armour
    JACKET(ItemCategory.ARMOUR, 5, 0, 0),
    VEST(ItemCategory.ARMOUR, 10, 0, 0),
    CHAIN_MAIL(ItemCategory.ARMOUR, 15, 0, 0),
    PLATE_MAIL(ItemCategory.ARMOUR, 20, 0, 0, new Effect(RESILIENCE_PERCENT, 1)),
    SHIRT(ItemCategory.ARMOUR, 2, 0, 0),
    SUIT(ItemCategory.ARMOUR, 2, 0, 0, new Effect(CHARISMA, 2), new Effect(APPEARANCE, 3)),
    FULL_PLATE_MAIL(ItemCategory.ARMOUR, 30, 0, 0, new Effect(RESILIENCE_PERCENT, 2)),
    CAPE(ItemCategory.ARMOUR, 0, 0, 0, new Effect(REPUTATION, 3), new Effect(AGILITY, 2), new Effect(TOUGHNESS, 2)),
    CORSET(ItemCategory.ARMOUR, 3, 0, 0, new Effect(REPUTATION, 2), new Effect(APPEARANCE, 4)),
    TAILCOAT(ItemCategory.ARMOUR, 4, 0, 0, new Effect(REPUTATION, 3), new Effect(APPEARANCE, 4), new Effect(AGILITY, -1)),

    // Pants
    TROUSERS(ItemCategory.PANTS, 5, 0, 0, new Effect(RESILIENCE_PERCENT, 1)),
    SHORTS(ItemCategory.PANTS, 2, 0, 0, new Effect(AGILITY, 2)),
    SKIRT(ItemCategory.PANTS, 4, 0, 0, new Effect(APPEARANCE, 3), new Effect(AGILITY, -2), new Effect(LUCK, 2)),
    KILT(ItemCategory.PANTS, 1, 0, 0, new Effect(REPUTATION, 1), new Effect(APPEARANCE, 2), new Effect(AGILITY, -1), new Effect(LUCK, 1)),

    // Amulet
    AMULET(ItemCategory.AMULET, 0, 0, 0, new Effect(APPEARANCE, 4)),
    CHAIN(ItemCategory.AMULET, 0, 0, 0, new Effect(CHARISMA, -1), new Effect(REPUTATION, 3)),
    NECKLACE(ItemCategory.AMULET, 0, 0, 0, new Effect(CHARISMA, -1), new Effect(REPUTATION, -1), new Effect(APPEARANCE, 8)),
    NECKERCHIEF(ItemCategory.AMULET, 0, 0, 0, new Effect(CHARISMA, 4), new Effect(APPEARANCE, 3)),
    TIE(ItemCategory.AMULET, 0, 0, 0, new Effect(CHARISMA, 1), new Effect(REPUTATION, 1), new Effect(APPEARANCE, 2)),

    // Ring
    RING(ItemCategory.RING, 0, 0, 0, new Effect(APPEARANCE, 2)),
    SIGNET(ItemCategory.RING, 0, 0, 0, new Effect(REPUTATION, 2), new Effect(APPEARANCE, 1)),
    BRACELET(ItemCategory.RING, 0, 0, 0, new Effect(CHARISMA, 3), new Effect(REPUTATION, -2)),

    // 1h melee
    FIST(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 1, 2),
    KNIFE(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 5, 6, new Effect(WEAPON_HIT, 5)),
    DAGGER(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 7, 8, new Effect(WEAPON_HIT, 8)),
    CLUB(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 3, 6, new Effect(WEAPON_HIT, 4)),
    SWORD(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 9, 12, new Effect(WEAPON_HIT, 6)),
    THUNDERFIST(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 10, 18, new Effect(WEAPON_HIT, -4), new Effect(WEAPON_ATK_COUNT, 2)),
    RAPIER(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 7, 10, new Effect(WEAPON_HIT, 10)),
    AXE(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 6, 25, new Effect(WEAPON_HIT, 8), new Effect(WEAPON_CRIT_DMG_BONUS_PERCENT, 10)),
    KNUCKLE_DUSTER(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 2, 3, new Effect(WEAPON_HIT, -4), new Effect(WEAPON_ATK_COUNT, 2)),
    KAMA(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 14, 32, new Effect(WEAPON_CRIT_DMG_BONUS_PERCENT, 20)),
    WAKIZASHI(ItemCategory.ONE_HANDED_MELEE_WEAPON, 0, 22, 45, new Effect(WEAPON_HIT, 10), new Effect(WEAPON_CRIT_DMG_BONUS_PERCENT, 30)),

    // 2h melee
    TWO_HANDED_AXE(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 20, 40, new Effect(WEAPON_HIT, -16), new Effect(CRIT_HIT_PERCENT, 10)),
    PIKE(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 5, 40, new Effect(WEAPON_HIT, -12), new Effect(CRIT_HIT_PERCENT, 9)),
    SCYTHE(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 35, 50, new Effect(WEAPON_HIT, -24), new Effect(CRIT_HIT_PERCENT, 12)),
    MACE(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 15, 30, new Effect(WEAPON_HIT, -4), new Effect(CRIT_HIT_PERCENT, 7)),
    TWO_HANDED_SWORD(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 30, 40, new Effect(CRIT_HIT_PERCENT, 11)),
    CROWBAR(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 20, 35, new Effect(WEAPON_HIT, -8), new Effect(CRIT_HIT_PERCENT, 8)),
    FLAIL(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 35, 65, new Effect(WEAPON_HIT, -22), new Effect(CRIT_HIT_PERCENT, 13)),
    KATANA(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 40, 65, new Effect(WEAPON_HIT, -34), new Effect(CRIT_HIT_PERCENT, 14)),
    CHAINSAW(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 50, 70, new Effect(WEAPON_HIT, -40), new Effect(CRIT_HIT_PERCENT, 15)),
    HALBERD(ItemCategory.TWO_HANDED_MELEE_WEAPON, 0, 40, 55, new Effect(WEAPON_HIT, -28), new Effect(CRIT_HIT_PERCENT, 13)),

    // 1h gun
    GLOCK(ItemCategory.ONE_HANDED_GUN, 0, 6, 12, new Effect(WEAPON_ATK_COUNT, 2)),
    MAGNUM(ItemCategory.ONE_HANDED_GUN, 0, 10, 16, new Effect(WEAPON_ATK_COUNT, 2)),
    UZI(ItemCategory.ONE_HANDED_GUN, 0, 1, 15, new Effect(WEAPON_ATK_COUNT, 3)),
    DESERT_EAGLE(ItemCategory.ONE_HANDED_GUN, 0, 12, 18, new Effect(WEAPON_ATK_COUNT, 2), new Effect(CRIT_HIT_PERCENT, 1)),
    BERETTA(ItemCategory.ONE_HANDED_GUN, 0, 4, 9, new Effect(WEAPON_ATK_COUNT, 3)),
    SCORPIO(ItemCategory.ONE_HANDED_GUN, 0, 3, 12, new Effect(WEAPON_ATK_COUNT, 4)),
    MP5K(ItemCategory.ONE_HANDED_GUN, 0, 6, 18, new Effect(WEAPON_ATK_COUNT, 3), new Effect(CRIT_HIT_PERCENT, 2)),

    // 2h gun
    AK_47(ItemCategory.TWO_HANDED_GUN, 0, 15, 25, new Effect(WEAPON_ATK_COUNT, 5)),
    SHOTGUN(ItemCategory.TWO_HANDED_GUN, 0, 20, 60, new Effect(WEAPON_HIT, 10), new Effect(CRIT_HIT_PERCENT, 25), new Effect(IGNORE_HALF_DEF, 0)),
    FLAME_THROWER(ItemCategory.TWO_HANDED_GUN, 0, 15, 50, new Effect(WEAPON_HIT, -10), new Effect(CRIT_HIT_PERCENT, 50), new Effect(IGNORE_ALL_DEF, 0)),
    FN_FAL(ItemCategory.TWO_HANDED_GUN, 0, 30, 45, new Effect(WEAPON_ATK_COUNT, 3)),
    SNIPER_RIFLE(ItemCategory.TWO_HANDED_GUN, 0, 40, 40, new Effect(PERCEPTION, 4), new Effect(AGILITY, -5), new Effect(CRIT_HIT_PERCENT, 50), new Effect(IGNORE_ALL_DEF, 0)),
    HUNTING_RIFLE(ItemCategory.TWO_HANDED_GUN, 0, 10, 20, new Effect(PERCEPTION, 2), new Effect(WEAPON_ATK_COUNT, 2), new Effect(CRIT_HIT_PERCENT, 20), new Effect(IGNORE_DEF_PERCENT, 40)),
    SEMI_AUTOMATIC_SNIPER_RIFLE(ItemCategory.TWO_HANDED_GUN, 0, 15, 25, new Effect(WEAPON_ATK_COUNT, 2), new Effect(CRIT_HIT_PERCENT, 25), new Effect(IGNORE_DEF_PERCENT, 30)),

    // 2h light ranged
    BOW(ItemCategory.TWO_HANDED_LIGHT_RANGED_WEAPON, 0, 22, 26, new Effect(WEAPON_HIT, -5), new Effect(WEAPON_ATK_COUNT, 3)),
    SHORT_BOW(ItemCategory.TWO_HANDED_LIGHT_RANGED_WEAPON, 0, 18, 22, new Effect(WEAPON_HIT, 3), new Effect(WEAPON_ATK_COUNT, 2)),
    THROWING_KNIFE(ItemCategory.TWO_HANDED_LIGHT_RANGED_WEAPON, 0, 14, 18, new Effect(WEAPON_HIT, 5), new Effect(WEAPON_ATK_COUNT, 3)),
    THROWING_AXE(ItemCategory.TWO_HANDED_LIGHT_RANGED_WEAPON, 0, 18, 24, new Effect(WEAPON_ATK_COUNT, 4)),
    SHURIKEN(ItemCategory.TWO_HANDED_LIGHT_RANGED_WEAPON, 0, 10, 13, new Effect(WEAPON_HIT, 7), new Effect(WEAPON_ATK_COUNT, 5)),

    // 2h heavy ranged
    LONG_BOW(ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON, 0, 27, 42, new Effect(WEAPON_ATK_COUNT, 2)),
    COMPOSITE_BOW(ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON, 0, 45, 55, new Effect(WEAPON_ATK_COUNT, 3)),
    CROSSBOW(ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON, 0, 20, 40, new Effect(WEAPON_ATK_COUNT, 2), new Effect(CRIT_HIT_PERCENT, 8), new Effect(IGNORE_DEF_PERCENT, 15)),
    HEAVY_CROSSBOW(ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON, 0, 30, 70, new Effect(WEAPON_HIT, -10), new Effect(CRIT_HIT_PERCENT, 12), new Effect(IGNORE_DEF_PERCENT, 20)),
    JAVELIN(ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON, 0, 30, 50, new Effect(WEAPON_ATK_COUNT, 2)),
    PILUM(ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON, 0, 45, 60, new Effect(WEAPON_ATK_COUNT, 2));

    private ItemCategory cat;
    private int baseDefence;
    private int baseMinDamage;
    private int baseMaxDamage;
    private List<Effect> effects;

    private Support(ItemCategory cat, int baseDefence, int baseMinDamage, int baseMaxDamage, Effect... effects) {
        this.cat = cat;
        this.baseDefence = baseDefence;
        this.baseMinDamage = baseMinDamage;
        this.baseMaxDamage = baseMaxDamage;
        this.effects = Arrays.asList(effects);
    }

    public ItemCategory getCat() {
        return cat;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public int getBaseMinDamage() {
        return baseMinDamage;
    }

    public int getBaseMaxDamage() {
        return baseMaxDamage;
    }

    public List<Effect> getEffects() {
        return effects;
    }

}
