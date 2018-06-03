package ref;

import static ref.BaseStat.AGILITY;
import static ref.BaseStat.APPEARANCE;
import static ref.BaseStat.BASE_HP_PERCENT;
import static ref.BaseStat.BLOOD_PER_HOUR;
import static ref.BaseStat.BLOOD_PTS_PERCENT;
import static ref.BaseStat.CHARISMA;
import static ref.BaseStat.CRIT_DMG_PERCENT;
import static ref.BaseStat.CRIT_HIT_PERCENT;
import static ref.BaseStat.CRIT_HIT_WITH_GUNS_PERCENT;
import static ref.BaseStat.DAMAGE;
import static ref.BaseStat.DEFENCE;
import static ref.BaseStat.HP_PER_HIT;
import static ref.BaseStat.INTELLIGENCE;
import static ref.BaseStat.KNOWLEDGE;
import static ref.BaseStat.LEVEL;
import static ref.BaseStat.LUCK;
import static ref.BaseStat.PERCEPTION;
import static ref.BaseStat.REPUTATION;
import static ref.BaseStat.RESILIENCE_PERCENT;
import static ref.BaseStat.STRENGH;
import static ref.BaseStat.TOUGHNESS;
import static ref.BaseStat.WEAPON_ATK_COUNT;
import static ref.BaseStat.WEAPON_CRIT_HIT_PERCENT;
import static ref.BaseStat.WEAPON_DMG;
import static ref.BaseStat.WEAPON_HIT;
import static ref.ItemCategory.AMULET;
import static ref.ItemCategory.ARMOUR;
import static ref.ItemCategory.HELMET;
import static ref.ItemCategory.ONE_HANDED_MELEE_WEAPON;
import static ref.ItemCategory.PANTS;
import static ref.ItemCategory.TWO_HANDED_MELEE_WEAPON;
import static util.DynamicModifierFunctions.PER_4;

import java.util.Arrays;
import java.util.List;

import util.Effect;

public enum Prefix {

    // Helmet
    HORNED(HELMET, new Effect(DAMAGE, 5)),
    SHAMANISTIC_HELMET(HELMET, new Effect(BLOOD_PTS_PERCENT, 10)),
    MALICIOUS(HELMET, new Effect(BLOOD_PTS_PERCENT, 5), new Effect(LUCK, 5)),
    BLOODY_HELMET(HELMET, new Effect(CRIT_DMG_PERCENT, 5)),
    RUNIC_HELMET(HELMET, new Effect(LUCK, 5), new Effect(DEFENCE, 10)),
    STRENGTHENED_HELMET(HELMET, new Effect(DEFENCE, 5)),
    BATTLE_HELMET(HELMET, new Effect(DEFENCE, 10)),
    RITUAL(HELMET, new Effect(BLOOD_PTS_PERCENT, 5), new Effect(LUCK, 5)),
    HARDENED(HELMET, new Effect(DEFENCE, 2)),
    HELPFUL(HELMET, new Effect(DEFENCE, 3)),
    ASSAULT(HELMET, new Effect(DEFENCE, 12), new Effect(RESILIENCE_PERCENT, 1)),
    DECORATIVE(HELMET, new Effect(APPEARANCE, 3), new Effect(DEFENCE, -2)),
    ELEGANT(HELMET, new Effect(APPEARANCE, 5), new Effect(DEFENCE, -2)),
    ARTISTIC(HELMET, new Effect(APPEARANCE, 8), new Effect(DEFENCE, -2)),
    BULLET_PROOF_HELMET(HELMET, new Effect(TOUGHNESS, 3), new Effect(DEFENCE, 6), new Effect(RESILIENCE_PERCENT, 1)),
    DEADLY_HELMET(HELMET, new Effect(TOUGHNESS, -5), new Effect(DAMAGE, 4)),
    MAGNETIC(HELMET, new Effect(INTELLIGENCE, 2), new Effect(KNOWLEDGE, -4)),
    TIGER_S_HELMET(HELMET, new Effect(AGILITY, 3), new Effect(TOUGHNESS, 2), new Effect(DEFENCE, -8)),
    LAZY(HELMET, new Effect(AGILITY, -5), new Effect(TOUGHNESS, 4), new Effect(DEFENCE, 7)),

    // Armour
    STRENGTHENED_ARMOUR(ARMOUR, new Effect(DEFENCE, 3)),
    STUDDED_ARMOUR(ARMOUR, new Effect(DEFENCE, 5)),
    SCALED(ARMOUR, new Effect(DEFENCE, 8)),
    FIELD(ARMOUR, new Effect(DEFENCE, 12), new Effect(RESILIENCE_PERCENT, 2)),
    BATTLE(ARMOUR, new Effect(LUCK, 5), new Effect(DEFENCE, 5)),
    SHAMANISTIC_ARMOUR(ARMOUR, new Effect(BLOOD_PTS_PERCENT, 5)),
    RUNIC_ARMOUR(ARMOUR, new Effect(LUCK, 5), new Effect(DEFENCE, 15), new Effect(RESILIENCE_PERCENT, 1)),
    BLOODY_ARMOUR(ARMOUR, new Effect(CRIT_DMG_PERCENT, 7)),
    LIGHT_ARMOUR(ARMOUR, new Effect(AGILITY, 3), new Effect(DEFENCE, -1)),
    FLEXIBLE_ARMOUR(ARMOUR, new Effect(AGILITY, 6), new Effect(DEFENCE, -2)),
    ELVISH_ARMOUR(ARMOUR, new Effect(AGILITY, 8), new Effect(DEFENCE, -1)),
    BULLET_PROOF_ARMOUR(ARMOUR, new Effect(TOUGHNESS, 5), new Effect(RESILIENCE_PERCENT, 1)),
    DEADLY_ARMOUR(ARMOUR, new Effect(TOUGHNESS, -12), new Effect(DAMAGE, 6)),
    TIGER_S_ARMOUR(ARMOUR, new Effect(AGILITY, 6), new Effect(TOUGHNESS, 3), new Effect(DEFENCE, -4)),
    REGAL(ARMOUR, new Effect(CHARISMA, 3), new Effect(APPEARANCE, 1)),
    HUNTER_S(ARMOUR, new Effect(PERCEPTION, 6), new Effect(KNOWLEDGE, 4), new Effect(CRIT_HIT_WITH_GUNS_PERCENT, -24)),

    // Pants
    RUNIC_PANTS(PANTS, new Effect(LUCK, 5), new Effect(DEFENCE, 10)),
    SHAMANISTIC_PANTS(PANTS, new Effect(BLOOD_PTS_PERCENT, 5)),
    BLOODY_PANTS(PANTS, new Effect(CRIT_DMG_PERCENT, 7)),
    LIGHT_PANTS(PANTS, new Effect(AGILITY, 2), new Effect(DEFENCE, 2)),
    STRENGTHENED_PANTS(PANTS, new Effect(DEFENCE, 5)),
    STUDDED_PANTS(PANTS, new Effect(DEFENCE, 7)),
    SPLINTED(PANTS, new Effect(AGILITY, -3), new Effect(DEFENCE, 10)),
    PANZER(PANTS, new Effect(RESILIENCE_PERCENT, 1), new Effect(AGILITY, -5), new Effect(DEFENCE, 14)),
    COMPOUND(PANTS, new Effect(RESILIENCE_PERCENT, 1), new Effect(AGILITY, -4), new Effect(DEFENCE, 15)),
    FLEXIBLE_PANTS(PANTS, new Effect(AGILITY, 4), new Effect(DEFENCE, -1)),
    ELVISH_PANTS(PANTS, new Effect(AGILITY, 6)),
    QUILTED(PANTS, new Effect(DEFENCE, 3)),
    BULLET_PROOF_PANTS(PANTS, new Effect(RESILIENCE_PERCENT, 1), new Effect(TOUGHNESS, 4)),
    DEADLY_PANTS(PANTS, new Effect(TOUGHNESS, -4), new Effect(DAMAGE, 3)),
    TIGER_S_PANTS(PANTS, new Effect(AGILITY, 4), new Effect(TOUGHNESS, 2), new Effect(DEFENCE, -3)),
    VELVET(PANTS, new Effect(REPUTATION, 3), new Effect(APPEARANCE, 2), new Effect(DEFENCE, -6)),
    SHORT(PANTS, new Effect(AGILITY, 2), new Effect(DEFENCE, -1)),

    // Amulet
    RUBY_AMULET(AMULET, new Effect(APPEARANCE, 4), new Effect(CHARISMA, 3)),
    DIAMOND_AMULET(AMULET, new Effect(APPEARANCE, 5), new Effect(CHARISMA, 6)),
    EMERALD_AMULET(AMULET, new Effect(APPEARANCE, 1), new Effect(CHARISMA, 2)),
    TWISTED_AMULET(AMULET, new Effect(CHARISMA, 4), new Effect(REPUTATION, 3)),
    VENGEFUL_AMULET(AMULET, new Effect(APPEARANCE, -3), new Effect(REPUTATION, 6)),
    INSIDIOUS_AMULET(AMULET, new Effect(APPEARANCE, -5), new Effect(REPUTATION, 8)),
    BLACK_AMULET(AMULET, new Effect(APPEARANCE, -8), new Effect(REPUTATION, 12)),
    COPPER_AMULET(AMULET, new Effect(APPEARANCE, 2)),
    SILVER_AMULET(AMULET, new Effect(APPEARANCE, 3)),
    GOLDEN_AMULET(AMULET, new Effect(APPEARANCE, 5)),
    PLATINUM_AMULET(AMULET, new Effect(APPEARANCE, 7)),
    ARCHAIC_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -20), new Effect(LUCK, 5)),
    HYPNOTISING_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -20), new Effect(LUCK, 5)),
    DANCING_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -20), new Effect(LUCK, 5)),
    ANIMAL_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -20), new Effect(LUCK, 5)),
    DIGNIFIED_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    CUNNING_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    URSINE_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    HARD_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    ASTRAL_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    ELASTIC_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    CARDINAL_S_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    NECROMANCER_S_AMULET(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 2)),
    SOLAR_AMULET(AMULET, new Effect(APPEARANCE, 3), new Effect(PERCEPTION, 3)),
    PLASTIC_AMULET(AMULET, new Effect(APPEARANCE, 2), new Effect(CHARISMA, 3), new Effect(REPUTATION, -4)),
    ARACHNID_AMULET(AMULET, new Effect(AGILITY, 4), new Effect(TOUGHNESS, 3)),
    HAWK_S_AMULET(AMULET, new Effect(REPUTATION, 3), new Effect(PERCEPTION, 4)),
    TITANIUM_AMULET(AMULET, new Effect(STRENGH, 3), new Effect(CHARISMA, 4)),

    // Ring
    RUBY_RING(AMULET, new Effect(APPEARANCE, 3), new Effect(CHARISMA, 3)),
    DIAMOND_RING(AMULET, new Effect(APPEARANCE, 5), new Effect(CHARISMA, 5)),
    EMERALD_RING(AMULET, new Effect(APPEARANCE, 1), new Effect(CHARISMA, 1)),
    TWISTED_RING(AMULET, new Effect(CHARISMA, 3), new Effect(REPUTATION, 3)),
    VENGEFUL_RING(AMULET, new Effect(APPEARANCE, -3), new Effect(REPUTATION, 5)),
    INSIDIOUS_RING(AMULET, new Effect(APPEARANCE, -5), new Effect(REPUTATION, 7)),
    BLACK_RING(AMULET, new Effect(APPEARANCE, -8), new Effect(REPUTATION, 10)),
    COPPER_RING(AMULET, new Effect(APPEARANCE, 1)),
    SILVER_RING(AMULET, new Effect(APPEARANCE, 2)),
    GOLDEN_RING(AMULET, new Effect(APPEARANCE, 4)),
    PLATINUM_RING(AMULET, new Effect(APPEARANCE, 6)),
    ARCHAIC_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 5)),
    HYPNOTISING_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 5)),
    DANCING_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 5)),
    ANIMAL_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -10), new Effect(LUCK, 5)),
    DIGNIFIED_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    CUNNING_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    URSINE_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    HARD_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    ASTRAL_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    ELASTIC_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    CARDINAL_S_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    NECROMANCER_S_RING(AMULET, new Effect(BLOOD_PTS_PERCENT, -5), new Effect(LUCK, 2)),
    SOLAR_RING(AMULET, new Effect(APPEARANCE, 2), new Effect(PERCEPTION, 2)),
    PLASTIC_RING(AMULET, new Effect(APPEARANCE, 2), new Effect(CHARISMA, 2), new Effect(REPUTATION, -3)),
    ARACHNID_RING(AMULET, new Effect(AGILITY, 3), new Effect(TOUGHNESS, 4)),
    HAWK_S_RING(AMULET, new Effect(REPUTATION, 3), new Effect(PERCEPTION, 3)),
    TITANIUM_RING(AMULET, new Effect(STRENGH, 2), new Effect(CHARISMA, 2), new Effect(DEFENCE, 6)),

    // 1h melee
    LIGHT_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 5)),
    AGILE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_ATK_COUNT, 2)),
    CRUEL_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 5)),
    CURSED_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 2, PER_4(), LEVEL), new Effect(BASE_HP_PERCENT, -10)),
    DEMONIC_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 10), new Effect(WEAPON_DMG, 2, PER_4(), LEVEL), new Effect(BASE_HP_PERCENT, -7)),
    ANTIQUE_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 10), new Effect(WEAPON_DMG, 5)),
    SHARP_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 2)),
    SERRATED_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 8)),
    MURDEROUS_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 12)),
    REINFORCING_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(BASE_HP_PERCENT, 10), new Effect(BLOOD_PER_HOUR, -100)),
    CARING_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(BASE_HP_PERCENT, 15), new Effect(BLOOD_PER_HOUR, -200)),
    POISONED_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_CRIT_HIT_PERCENT, 5)),
    VENOMOUS_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_CRIT_HIT_PERCENT, 3)),
    BITING_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(WEAPON_CRIT_HIT_PERCENT, 1)),
    FAST_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(AGILITY, 5), new Effect(WEAPON_ATK_COUNT, 2)),
    SHINING_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(PERCEPTION, 2), new Effect(TOUGHNESS, 2)),
    BONY_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(BLOOD_PTS_PERCENT, 5), new Effect(TOUGHNESS, 2), new Effect(DAMAGE, -4)),
    MYSTIC_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(INTELLIGENCE, 2), new Effect(DAMAGE, 5)),
    FRIENDLY_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 3), new Effect(REPUTATION, 2), new Effect(BASE_HP_PERCENT, 7)),
    CRYSTAL_ONE_HANDED_MELEE(ONE_HANDED_MELEE_WEAPON, new Effect(APPEARANCE, 3), new Effect(DAMAGE, 6)),

    // 2h melee
    LIGHT_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(AGILITY, 7)),
    AGILE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(AGILITY, 4), new Effect(WEAPON_ATK_COUNT, 2)),
    CRUEL_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 7)),
    CURSED_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 2, PER_4(), LEVEL), new Effect(BASE_HP_PERCENT, -7)),
    DEMONIC_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(AGILITY, 13), new Effect(WEAPON_DMG, 2, PER_4(), LEVEL), new Effect(BASE_HP_PERCENT, -5)),
    ANTIQUE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(AGILITY, 15), new Effect(WEAPON_DMG, 7)),
    HEAVY_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_HIT, -5), new Effect(WEAPON_DMG, 15)),
    BROAD_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_HIT, 8), new Effect(WEAPON_DMG, 8)),
    SHARP_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(WEAPON_DMG, 7)),
    SERRATED_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 3), new Effect(WEAPON_DMG, 13)),
    MURDEROUS_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 5), new Effect(WEAPON_DMG, 17)),
    REINFORCING_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(BASE_HP_PERCENT, 15), new Effect(BLOOD_PER_HOUR, -150)),
    CARING_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(BASE_HP_PERCENT, 20), new Effect(BLOOD_PER_HOUR, -250)),
    POISONED_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 15)),
    VENOMOUS_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 10)),
    BITING_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 5)),
    SHINING_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(PERCEPTION, 3), new Effect(TOUGHNESS, 4)),
    MYSTIC_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(INTELLIGENCE, 4), new Effect(WEAPON_DMG, 9)),
    CRYSTAL_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CHARISMA, 4), new Effect(REPUTATION, 3), new Effect(BASE_HP_PERCENT, 9)),
    RADIATING_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(CRIT_HIT_PERCENT, 10), new Effect(BLOOD_PTS_PERCENT, -5), new Effect(STRENGH, 3), new Effect(TOUGHNESS, -10),
            new Effect(WEAPON_DMG, 10), new Effect(HP_PER_HIT, -10)),
    EXPENSIVE_TWO_HANDED_MELEE(TWO_HANDED_MELEE_WEAPON, new Effect(REPUTATION, 4));

    private ItemCategory cat;
    private List<Effect> effects;

    private Prefix(ItemCategory cat, Effect... effects) {
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
