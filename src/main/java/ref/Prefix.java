package ref;

import static ref.BaseStat.DAMAGE;
import static ref.ItemCategory.HELMET;

import java.util.Arrays;
import java.util.List;

import util.Effect;

public enum Prefix {

    HORNED(HELMET, new Effect(DAMAGE, 5));

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
