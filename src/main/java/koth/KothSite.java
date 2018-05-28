package koth;

public enum KothSite {
    SLUMS(1, "Banlieues"),
    HORACE_S_STRONGHOLD(2, "La Forteresse de l`Horace"),
    SMALL_GATE(3, "Petite porte"),
    FORGE(4, "La Forgerie"),
    MARKET(5, "Le Marché"),
    HOSPITAL(6, "L`Hôpital"),
    FUEL_DEPOT(7, "Le Stock du carburant"),
    CITADEL(8, "La Citadelle");

    private int index;
    private String frenchName;

    private KothSite(int index, String frenchName) {
        this.index = index;
        this.frenchName = frenchName;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return frenchName;
    }
}
