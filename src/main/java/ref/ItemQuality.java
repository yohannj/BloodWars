package ref;

public enum ItemQuality {
    NORMAL(1, 1),
    GOOD(1.5, 1),
    PERFECT(2, 1),
    LEGENDARY_NORMAL(1, 1.35),
    LEGENDARY_GOOD(1.5, 1.35),
    LEGENDARY_PERFECT(2, 1.35),
    EPIC(2.5, 1.35);

    private double baseQualityBonus;
    private double legendaryBonus;

    private ItemQuality(double baseQualityBonus, double legendaryBonus) {
        this.baseQualityBonus = baseQualityBonus;
        this.legendaryBonus = legendaryBonus;
    }

    public double getBaseQualityBonus() {
        return baseQualityBonus;
    }

    public double getLegendaryBonus() {
        return legendaryBonus;
    }
}
