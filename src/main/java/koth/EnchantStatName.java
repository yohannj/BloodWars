package koth;

public enum EnchantStatName {
    STR("Force"),
    AGI("Agilité"),
    TOU("Résistance"),
    APP("Apparence"),
    CHA("Charisme"),
    REP("Réputation"),
    PER("Perception"),
    INT("Intelligence"),
    KNO("Savoir"),
    B_PTS("Points du sang"),
    L_PTS("Points de vie"),
    DOD("Esquive"),
    LUC("Chance"),
    ATT_SUP("Attaques supplémentaires pour chaque arme"),
    CH("Chance d'un coup critique"),
    DEF("Défense du personnage"),
    DEF_4("Défense pour chaque 4 niveaux de personnage"),
    DAM("Dommages de chaque arme"),
    DAM_4("Dommage pour chaque 4 niveaux de personnage"),
    IGN_DEF("Ignore % de la défense de l`ennemi"),
    HIT("Précision de chaque arme"),
    DAM_AND_CH("Dommages de chaque arme + Chance d'un coup critique");

    private String name;

    private EnchantStatName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
