package ref;

import static ref.BaseStat.AGILITY;
import static ref.BaseStat.APPEARANCE;
import static ref.BaseStat.ATK_COUNT;
import static ref.BaseStat.BASE_HP_PERCENT;
import static ref.BaseStat.BLOOD_PTS_PERCENT;
import static ref.BaseStat.CHARISMA;
import static ref.BaseStat.CRIT_HIT_PERCENT;
import static ref.BaseStat.CRIT_HIT_WITH_GUNS_PERCENT;
import static ref.BaseStat.DAMAGE;
import static ref.BaseStat.DAMAGE_MAX;
import static ref.BaseStat.DAMAGE_REDUCTION_PERCENT;
import static ref.BaseStat.DEFENCE;
import static ref.BaseStat.DODGE_PERCENT;
import static ref.BaseStat.HIT;
import static ref.BaseStat.HP_PER_HIT;
import static ref.BaseStat.INTELLIGENCE;
import static ref.BaseStat.ITEM_DEFENCE_PERCENT;
import static ref.BaseStat.KNOWLEDGE;
import static ref.BaseStat.LEVEL;
import static ref.BaseStat.LUCK;
import static ref.BaseStat.MAX_DEF_FROM_ITEMS;
import static ref.BaseStat.OPP_DOES_NOT_ATTACK_IN_FIRST_ROUND;
import static ref.BaseStat.PERCEPTION;
import static ref.BaseStat.REPUTATION;
import static ref.BaseStat.REQUIREMENTS_LOWERED_PERCENT;
import static ref.BaseStat.RESILIENCE_PERCENT;
import static ref.BaseStat.STRENGH;
import static ref.BaseStat.TOUGHNESS;
import static ref.BaseStat.WEAPON_ATK_COUNT;
import static ref.BaseStat.WEAPON_DMG;
import static ref.BaseStat.WEAPON_DMG_MAX;
import static ref.BaseStat.WEAPON_DMG_MIN;
import static ref.BaseStat.WEAPON_HIT;
import static ref.ItemCategory.AMULET;
import static ref.ItemCategory.ARMOUR;
import static ref.ItemCategory.HELMET;
import static ref.ItemCategory.ONE_HANDED_MELEE_WEAPON;
import static ref.ItemCategory.PANTS;
import static ref.ItemCategory.RING;
import static ref.ItemCategory.TWO_HANDED_HEAVY_RANGED_WEAPON;
import static ref.ItemCategory.TWO_HANDED_LIGHT_RANGED_WEAPON;
import static ref.ItemCategory.TWO_HANDED_MELEE_WEAPON;
import static util.DynamicModifierFunctions.PER_4;

import java.util.Arrays;
import java.util.List;

import util.Effect;

public enum Suffix {

    // Helmet
    OF_DURABILITY(HELMET, new Effect(DEFENCE, 4)),
    OF_PROTECTION(HELMET, new Effect(DEFENCE, 6)),
    OF_THE_GLADIATOR(HELMET, new Effect(DEFENCE, 7)),
    OF_DRAGON_SCALE(HELMET, new Effect(DEFENCE, 9), new Effect(RESILIENCE_PERCENT, 1)),
    OF_POWER(HELMET, new Effect(DEFENCE, 10)),
    OF_BLOOD(HELMET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(DEFENCE, 2, PER_4(), LEVEL)),
    OF_MAGIC(HELMET, new Effect(BLOOD_PTS_PERCENT, -7), new Effect(DEFENCE, 2, PER_4(), LEVEL), new Effect(RESILIENCE_PERCENT, 1)),
    OF_PUNISHMENT(HELMET, new Effect(OPP_DOES_NOT_ATTACK_IN_FIRST_ROUND, 0)),
    OF_SENSES(HELMET, new Effect(PERCEPTION, 4)),
    OF_THE_PROPHET(HELMET, new Effect(PERCEPTION, 6)),
    OF_PRECOGNITION(HELMET, new Effect(PERCEPTION, 10)),
    OF_THE_WANDERER(HELMET, new Effect(DEFENCE, 2)),
    OF_CAUTION(HELMET, new Effect(PERCEPTION, 2)),
    OF_ADRENALINE_HELMET(HELMET, new Effect(STRENGH, 4), new Effect(PERCEPTION, -5), new Effect(AGILITY, 3)),
    OF_THE_SHEPHERD_HELMET(HELMET, new Effect(PERCEPTION, 7), new Effect(TOUGHNESS, -4), new Effect(DEFENCE, -4)),
    OF_THE_ADDICT_HELMET(HELMET, new Effect(STRENGH, -7), new Effect(APPEARANCE, -5), new Effect(TOUGHNESS, 4), new Effect(INTELLIGENCE, -6)),

    // Armour
    OF_THE_APPRENTICE(ARMOUR, new Effect(ITEM_DEFENCE_PERCENT, 25)),
    OF_THE_FENCER(ARMOUR, new Effect(ITEM_DEFENCE_PERCENT, 50)),
    OF_THE_MASTER(ARMOUR, new Effect(ITEM_DEFENCE_PERCENT, 75)),
    OF_SPEED(ARMOUR, new Effect(ATK_COUNT, 2), new Effect(CRIT_HIT_WITH_GUNS_PERCENT, -40)),
    OF_TOUGHNESS(ARMOUR, new Effect(DEFENCE, 20), new Effect(RESILIENCE_PERCENT, 2)),
    OF_CALIGULA(ARMOUR, new Effect(DAMAGE, 10), new Effect(DEFENCE, 10)),
    OF_DODGES_ARMOUR(ARMOUR, new Effect(DEFENCE, 10)),
    OF_THE_ASSASSIN(ARMOUR, new Effect(AGILITY, 5), new Effect(DEFENCE, 5)),
    OF_THE_THIEF(ARMOUR, new Effect(AGILITY, 3), new Effect(DEFENCE, 2)),
    OF_THE_CENTURION(ARMOUR, new Effect(DAMAGE, 8), new Effect(DEFENCE, 10)),
    OF_THE_GUARDSMAN(ARMOUR, new Effect(DAMAGE, 5), new Effect(DEFENCE, 5)),
    OF_THE_GUARD(ARMOUR, new Effect(DAMAGE, 2), new Effect(DEFENCE, 3)),
    OF_THE_COBRA(ARMOUR, new Effect(AGILITY, 5), new Effect(DAMAGE, 5), new Effect(DEFENCE, 2)),
    OF_THE_DEATH_SOWER(ARMOUR, new Effect(AGILITY, 10), new Effect(DAMAGE, 10), new Effect(DEFENCE, 5)),
    OF_THE_ORCHID(ARMOUR, new Effect(DAMAGE, 20), new Effect(DEFENCE, -10)),
    OF_ADRENALINE_ARMOUR(ARMOUR, new Effect(STRENGH, 4), new Effect(PERCEPTION, -8), new Effect(AGILITY, 5)),
    OF_THE_STRONGMAN_ARMOUR(ARMOUR, new Effect(STRENGH, 8), new Effect(AGILITY, -6)),
    OF_THE_ADDICT_ARMOUR(ARMOUR, new Effect(STRENGH, -1), new Effect(APPEARANCE, -2), new Effect(AGILITY, -1), new Effect(TOUGHNESS, 3), new Effect(INTELLIGENCE, -3)),
    OF_TURTLE_SHELL(ARMOUR, new Effect(AGILITY, -5), new Effect(TOUGHNESS, 4), new Effect(DEFENCE, 14), new Effect(RESILIENCE_PERCENT, 1)),
    OF_THE_LOOTER(ARMOUR, new Effect(CHARISMA, -8), new Effect(LUCK, 5)),

    // Pants
    OF_DODGES(PANTS, new Effect(AGILITY, 5), new Effect(DODGE_PERCENT, 2)),
    OF_THE_SNAKE(PANTS, new Effect(REPUTATION, 5), new Effect(LUCK, 5)),
    OF_THE_SCOUT(PANTS, new Effect(AGILITY, 5), new Effect(DEFENCE, 5)),
    OF_THE_NIGHT(PANTS, new Effect(AGILITY, 15)),
    OF_SILENT_MOVES(PANTS, new Effect(REPUTATION, 2), new Effect(AGILITY, 2)),
    OF_SECRECY(PANTS, new Effect(REPUTATION, 3), new Effect(AGILITY, 4)),
    OF_THE_SHADOW_HUNTER(PANTS, new Effect(REPUTATION, 4), new Effect(AGILITY, 6)),
    OF_THE_ROGUE(PANTS, new Effect(REPUTATION, 3), new Effect(DEFENCE, 2)),
    OF_THE_SMUGGLER(PANTS, new Effect(REPUTATION, 5), new Effect(DEFENCE, 4)),
    OF_THE_ARMS_DEALER(PANTS, new Effect(REPUTATION, 7), new Effect(DEFENCE, 6)),
    OF_THE_STRONGMAN_PANTS(PANTS, new Effect(STRENGH, 4), new Effect(AGILITY, -4)),
    OF_THE_ADDICT_PANTS(PANTS, new Effect(STRENGH, -6), new Effect(APPEARANCE, -3), new Effect(AGILITY, -1), new Effect(TOUGHNESS, 3), new Effect(INTELLIGENCE, -3)),
    OF_THE_SHEPHERD_PANTS(PANTS, new Effect(PERCEPTION, 3), new Effect(TOUGHNESS, -4), new Effect(DEFENCE, -4)),
    OF_THE_SUN(PANTS, new Effect(APPEARANCE, 2), new Effect(PERCEPTION, 2)),
    OF_THE_INCAS(PANTS, new Effect(RESILIENCE_PERCENT, 1), new Effect(TOUGHNESS, 3), new Effect(KNOWLEDGE, 3)),

    // Amulet
    OF_ACCURACY_AMULET(AMULET, new Effect(AGILITY, 3)),
    OF_BEAUTY_AMULET(AMULET, new Effect(APPEARANCE, 3)),
    OF_AUTHORITY_AMULET(AMULET, new Effect(CHARISMA, 3)),
    OF_STRENGTH_AMULET(AMULET, new Effect(STRENGH, 3)),
    OF_GENIUS_AMULET(AMULET, new Effect(INTELLIGENCE, 3)),
    OF_WISDOM_AMULET(AMULET, new Effect(KNOWLEDGE, 3)),
    OF_HARD_SKIN_AMULET(AMULET, new Effect(AGILITY, -1), new Effect(TOUGHNESS, 5)),
    OF_MISDEMEANOUR_AMULET(AMULET, new Effect(APPEARANCE, -1), new Effect(REPUTATION, 5)),
    OF_LUCK_AMULET(AMULET, new Effect(LUCK, 10)),
    OF_BLOOD_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, 10)),
    OF_THE_WEREWOLF_AMULET(AMULET, new Effect(DAMAGE_REDUCTION_PERCENT, 4)),
    OF_EASINESS_AMULET(AMULET, new Effect(REQUIREMENTS_LOWERED_PERCENT, 15)),
    OF_CONCENTRATION_AMULET(AMULET, new Effect(INTELLIGENCE, 4), new Effect(KNOWLEDGE, 4)),
    OF_LEVITATION_AMULET(AMULET, new Effect(STRENGH, 4), new Effect(AGILITY, 4)),
    OF_GUILE_AMULET(AMULET, new Effect(CHARISMA, 4), new Effect(REPUTATION, 4)),
    OF_THE_PILGRIM_AMULET(AMULET, new Effect(TOUGHNESS, 3), new Effect(KNOWLEDGE, 2)),
    OF_TALENT_AMULET(AMULET, new Effect(APPEARANCE, 1), new Effect(STRENGH, 1), new Effect(CHARISMA, 1), new Effect(REPUTATION, 1), new Effect(PERCEPTION, 1), new Effect(AGILITY, 1),
            new Effect(TOUGHNESS, 1), new Effect(INTELLIGENCE, 1), new Effect(KNOWLEDGE, 1), new Effect(LUCK, 1), new Effect(HIT, 1)),
    OF_YOUTH_AMULET(AMULET, new Effect(APPEARANCE, 2), new Effect(REPUTATION, 2)),
    OF_ART_AMULET(AMULET, new Effect(REPUTATION, -9), new Effect(INTELLIGENCE, 3), new Effect(LUCK, 8)),
    OF_THE_MADMAN_AMULET(AMULET, new Effect(REPUTATION, 3), new Effect(KNOWLEDGE, 2)),

    // Ring
    OF_ACCURACY_RING(RING, new Effect(AGILITY, 2)),
    OF_BEAUTY_RING(RING, new Effect(APPEARANCE, 2)),
    OF_AUTHORITY_RING(RING, new Effect(CHARISMA, 2)),
    OF_STRENGTH_RING(RING, new Effect(STRENGH, 2)),
    OF_GENIUS_RING(RING, new Effect(INTELLIGENCE, 2)),
    OF_WISDOM_RING(RING, new Effect(KNOWLEDGE, 2)),
    OF_HARD_SKIN_RING(RING, new Effect(AGILITY, -1), new Effect(TOUGHNESS, 3)),
    OF_MISDEMEANOUR_RING(RING, new Effect(APPEARANCE, -1), new Effect(REPUTATION, 3)),
    OF_LUCK_RING(RING, new Effect(LUCK, 5)),
    OF_BLOOD_RING(RING, new Effect(BLOOD_PTS_PERCENT, 5)),
    OF_THE_WEREWOLF_RING(RING, new Effect(DAMAGE_REDUCTION_PERCENT, 2)),
    OF_EASINESS_RING(RING, new Effect(REQUIREMENTS_LOWERED_PERCENT, 10)),
    OF_CONCENTRATION_RING(RING, new Effect(INTELLIGENCE, 3), new Effect(KNOWLEDGE, 3)),
    OF_LEVITATION_RING(RING, new Effect(STRENGH, 3), new Effect(AGILITY, 3)),
    OF_GUILE_RING(RING, new Effect(CHARISMA, 3), new Effect(REPUTATION, 3)),
    OF_THE_BAT_RING(RING, new Effect(APPEARANCE, -8), new Effect(PERCEPTION, 3), new Effect(AGILITY, 3)),
    OF_YOUTH_RING(RING, new Effect(APPEARANCE, 2), new Effect(STRENGH, 3), new Effect(AGILITY, 2)),
    OF_ART_RING(RING, new Effect(APPEARANCE, 2), new Effect(REPUTATION, 4)),
    OF_THE_MADMAN_RING(RING, new Effect(REPUTATION, -6), new Effect(INTELLIGENCE, 4), new Effect(LUCK, 5)),
    OF_THE_FOX_RING(RING, new Effect(REPUTATION, 3), new Effect(KNOWLEDGE, 2)),

    // 1h melee
    OF_PAIN_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, -5), new Effect(WEAPON_DMG, 10)),
    OF_VENGEANCE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 20), new Effect(MAX_DEF_FROM_ITEMS, 0)),
    OF_BLOOD_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 8), new Effect(BLOOD_PTS_PERCENT, -10)),
    OF_DRACULA_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 8), new Effect(BLOOD_PTS_PERCENT, 5)),
    OF_THE_PLAGUE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 10), new Effect(DEFENCE, -8)),
    OF_POWER_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 5)),
    OF_AUTHORITY_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 5)),
    OF_AGILITY_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 3)),
    OF_HITTING_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 5)),
    OF_PRECISION_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 8)),
    OF_SKILL_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 12)),
    OF_COURAGE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 3), new Effect(AGILITY, 4)),
    OF_VALOUR_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 6), new Effect(AGILITY, 7)),
    OF_THE_EMPEROR_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 7), new Effect(AGILITY, 10)),
    OF_THE_LEADER_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 3)),
    OF_THE_ANCESTORS_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 8)),
    OF_THE_CLAN_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 2), new Effect(REPUTATION, 3), new Effect(PERCEPTION, 1), new Effect(TOUGHNESS, 2), new Effect(LUCK, 1)),
    OF_CONTUSION_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, -5), new Effect(KNOWLEDGE, 3)),
    OF_THE_CULT_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, -1), new Effect(REPUTATION, 2), new Effect(WEAPON_HIT, 5)),
    OF_THE_CONQUEROR_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(REPUTATION, 3), new Effect(KNOWLEDGE, 2)),
    OF_HORSESHOE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(LUCK, 2), new Effect(BASE_HP_PERCENT, 5)),
    OF_SUICIDE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG_MAX, 4), new Effect(AGILITY, 6), new Effect(MAX_DEF_FROM_ITEMS, 0)),

    // 2h melee
    OF_PAIN_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(AGILITY, -1), new Effect(WEAPON_DMG, 17)),
    OF_VENGEANCE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 27), new Effect(MAX_DEF_FROM_ITEMS, 0)),
    OF_BLOOD_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 14), new Effect(BLOOD_PTS_PERCENT, -20)),
    OF_DRACULA_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 18), new Effect(BLOOD_PTS_PERCENT, 10)),
    OF_THE_PLAGUE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 22), new Effect(DEFENCE, -12)),
    OF_POWER_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 9)),
    OF_AUTHORITY_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 9)),
    OF_RUSE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG_MIN, -4), new Effect(REPUTATION, 3)),
    OF_THE_GAMBLER_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG_MIN, -4), new Effect(WEAPON_DMG_MAX, 4), new Effect(REPUTATION, 5)),
    OF_THE_INQUISITOR_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG_MAX, 8), new Effect(REPUTATION, 9)),
    OF_BETRAYAL_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(REPUTATION, 3), new Effect(WEAPON_DMG, 9)),
    OF_THE_BASILISK_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 5), new Effect(WEAPON_DMG_MAX, 10), new Effect(REPUTATION, 10), new Effect(AGILITY, 2),
            new Effect(WEAPON_DMG, 10)),
    OF_THE_AUTOCRAT_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 3), new Effect(WEAPON_DMG_MAX, 13), new Effect(REPUTATION, 7), new Effect(AGILITY, 2),
            new Effect(WEAPON_DMG, 20)),
    OF_THE_CONQUEROR_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(REPUTATION, 3), new Effect(KNOWLEDGE, 2)),
    OF_HORSESHOE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(LUCK, 4), new Effect(HP_PER_HIT, 3)),
    OF_SUICIDE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(DAMAGE_MAX, 8), new Effect(AGILITY, 12), new Effect(MAX_DEF_FROM_ITEMS, 0)),
    OF_THE_BLOODSUCKER_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(STRENGH, 2), new Effect(TOUGHNESS, 2), new Effect(BASE_HP_PERCENT, 8)),
    OF_LEAD_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(TOUGHNESS, 3), new Effect(WEAPON_HIT, -10), new Effect(WEAPON_DMG, 6)),

    // 2h light ranged
    OF_FIRE_RATE_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(WEAPON_ATK_COUNT, 1)),
    OF_LONG_RANGE_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(STRENGH, 2), new Effect(WEAPON_HIT, 10), new Effect(WEAPON_DMG, 8)),
    OF_PRECISION_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(AGILITY, 8)),
    OF_PERFECTION_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(LUCK, 5), new Effect(WEAPON_HIT, 10), new Effect(WEAPON_DMG, 10)),
    OF_THE_DRYAD_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(REPUTATION, 5), new Effect(PERCEPTION, 4), new Effect(AGILITY, 6), new Effect(TOUGHNESS, -4), new Effect(WEAPON_HIT, 8),
            new Effect(WEAPON_DMG, 6)),
    OF_REACTION_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(PERCEPTION, 4), new Effect(AGILITY, 5), new Effect(WEAPON_HIT, 6), new Effect(CRIT_HIT_PERCENT, 8), new Effect(WEAPON_DMG, 8)),
    OF_THE_WOLF_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(PERCEPTION, 7), new Effect(AGILITY, 7), new Effect(TOUGHNESS, 5), new Effect(WEAPON_HIT, 15), new Effect(WEAPON_DMG, 6)),
    OF_VENGEANCE_LIGHT_RANGE(TWO_HANDED_LIGHT_RANGED_WEAPON, new Effect(WEAPON_DMG, 15), new Effect(MAX_DEF_FROM_ITEMS, 0)),

    // 2h heavy ranged
    OF_FIRE_RATE_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(WEAPON_ATK_COUNT, 1)),
    OF_LONG_RANGE_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(STRENGH, 2), new Effect(WEAPON_HIT, 10), new Effect(WEAPON_DMG, 8)),
    OF_PRECISION_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(AGILITY, 8)),
    OF_PERFECTION_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(LUCK, 5), new Effect(WEAPON_HIT, 10), new Effect(WEAPON_DMG, 10)),
    OF_THE_DRYAD_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(REPUTATION, 5), new Effect(PERCEPTION, 4), new Effect(AGILITY, 6), new Effect(TOUGHNESS, -4), new Effect(WEAPON_HIT, 8),
            new Effect(WEAPON_DMG, 6)),
    OF_REACTION_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(PERCEPTION, 4), new Effect(AGILITY, 5), new Effect(WEAPON_HIT, 6), new Effect(CRIT_HIT_PERCENT, 8), new Effect(WEAPON_DMG, 8)),
    OF_THE_WOLF_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(PERCEPTION, 7), new Effect(AGILITY, 7), new Effect(TOUGHNESS, 5), new Effect(WEAPON_HIT, 15), new Effect(WEAPON_DMG, 6)),
    OF_VENGEANCE_HEAVY_RANGE(TWO_HANDED_HEAVY_RANGED_WEAPON, new Effect(WEAPON_DMG, 15), new Effect(MAX_DEF_FROM_ITEMS, 0));

    private ItemCategory cat;
    private List<Effect> effects;

    private Suffix(ItemCategory cat, Effect... effects) {
        this.cat = cat;
        this.effects = Arrays.asList(effects);
    }

    public ItemCategory getCat() {
        return cat;
    }

    public List<Effect> getEffects() {
        return effects;
    }

}
