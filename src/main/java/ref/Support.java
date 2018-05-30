package ref;

import static ref.BaseStat.APPEARANCE;
import static ref.BaseStat.BLOOD_PTS;
import static ref.BaseStat.CHARISMA;
import static ref.BaseStat.DAMAGE;
import static ref.BaseStat.DAMAGE_MAX;
import static ref.BaseStat.PERCEPTION;
import static ref.BaseStat.REPUTATION;
import static ref.BaseStat.RESILIENCE;

import java.util.Arrays;
import java.util.List;

import util.Effect;

public enum Support {
    HARD_HAT(ItemCategory.HELMET, 3, 0, 0),
    HELMET(ItemCategory.HELMET, 7, 0, 0, new Effect(RESILIENCE, 0.01)),
    MASK(ItemCategory.HELMET, 3, 0, 0, new Effect(BLOOD_PTS, 0.1)),
    CIRCLET(ItemCategory.HELMET, 1, 0, 0, new Effect(CHARISMA, 5)),
    CAP(ItemCategory.HELMET, 1, 0, 0),
    BALACLAVA(ItemCategory.HELMET, 1, 0, 0, new Effect(REPUTATION, 2), new Effect(APPEARANCE, -5)),
    HAT(ItemCategory.HELMET, 2, 0, 0, new Effect(APPEARANCE, 3)),
    CROWN(ItemCategory.HELMET, 12, 0, 0, new Effect(DAMAGE_MAX, 5), new Effect(CHARISMA, 3), new Effect(REPUTATION, 3)),
    HEADBAND(ItemCategory.HELMET, 2, 0, 0, new Effect(APPEARANCE, 5), new Effect(PERCEPTION, 3)),
    BANDANNA(ItemCategory.HELMET, 4, 0, 0, new Effect(DAMAGE, 1), new Effect(APPEARANCE, 3), new Effect(PERCEPTION, 1));

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
