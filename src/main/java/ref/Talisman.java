package ref;

public enum Talisman {
    AMBITION("Ambition", 1, 1, 2, 0, 4),
    LEVIATHAN("Léviathan", 2, 0, 1, 1, 4),
    BEHEMOTH("Béhémoth", 1, 2, 0, 1, 4),
    ZIZ("Ziz", 0, 1, 1, 2, 4),
    STONE_OF_EVIL("Pierre du mal", 1, 0, 1, 1, 3),
    STONE_OF_GOODNESS("Pierre du bien", 1, 0, 1, 1, 3),
    STONE_OF_SPACE("Pierre d'espace", 1, 1, 0, 1, 3),
    STONE_OF_TIME("Pierre du temps", 1, 1, 0, 1, 3),
    CLAWS_OF_THE_NIGHT("Les griffes de la nuit", 1, 2, 1, 1, 5),
    LIFE_AND_DEATH("Vie et mort", 1, 1, 3, 0, 5),
    VOID_OF_SILENCE("L'abime du silence", 1, 1, 1, 2, 5),
    ULTIMATE_POWER("La puissance du pouvoir", 2, 1, 2, 0, 5),
    BESTIAL_FURY("La furie bestiale", 0, 3, 0, 2, 5),
    AURA_OF_THE_BEAST("L'aura bestiale", 2, 1, 2, 0, 5),
    MASK_OF_POWER("Le masque du pouvoir", 2, 0, 2, 1, 5),
    MASK_OF_FEAR("Le masque de l'Effroi", 2, 1, 1, 1, 5),
    SILENT_HUNTER("Le chasseur silencieux", 1, 2, 1, 1, 5),
    SONG_OF_BLOOD("Le chant du sang", 1, 1, 3, 0, 5);

    private String frenchName;
    private int red;
    private int green;
    private int blue;
    private int yellow;
    private int total;

    private Talisman(String frenchName, int red, int green, int blue, int yellow, int total) {
        this.frenchName = frenchName;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.yellow = yellow;
        this.total = total;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getYellow() {
        return yellow;
    }

    public int getTotal() {
        return total;
    }
}