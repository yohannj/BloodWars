package ref;

import static ref.BaseStat.DEFENCE;
import static ref.ItemCategory.HELMET;

import java.util.Arrays;
import java.util.List;

import util.Effect;

public enum Suffix {

    OF_DURABILITY(HELMET, new Effect(DEFENCE, 4));

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
