package main;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import ref.Talisman;

public class TalismanCombinations {

    public static final int MAX_INDIVIDUAL_RUNES = 5;
    public static final int MAX_RUNES = 4 * MAX_INDIVIDUAL_RUNES;
    public static final String SEPARATOR = ",";

    public static void main(String args[]) {
        System.out.println("start");
        Set<Set<Talisman>> added_to_to_check = new HashSet<>();

        Deque<Set<Talisman>> to_check = new ArrayDeque<>();
        Set<Talisman> init = new HashSet<>();
        to_check.addLast(init);

        Set<Talisman> current;
        while ((current = to_check.poll()) != null) {
            //boolean can_add_more = false;

            //Try to add a talisman to current combi
            for (Talisman t : Talisman.values()) {
                if (current.contains(t)) { //Cannot have the same talisman multiple time
                    continue;
                }

                Set<Talisman> test_combi = new HashSet<>();
                test_combi.addAll(current);
                test_combi.add(t);
                if (isValidCombination(test_combi)) {
                    //Found a combi with one more talisman
                    //can_add_more = true;

                    if (!added_to_to_check.contains(test_combi)) {
                        //We did not add to to_check this combi
                        added_to_to_check.add(test_combi);
                        to_check.addLast(test_combi);
                        printCombinaison(test_combi);
                    }

                }

            }

        }
    }

    public static void printCombinaison(Set<Talisman> talismans) {
        //Rouge   Verte   Bleue   Jaune   Nombre  Ambition    Léviathan   Béhémoth    Ziz Pierre du mal   Pierre du bien  Pierre d'espace Pierre du temps Les griffes de la nuit  Vie et mort L'abime du silence  La puissance du pouvoir La furie bestiale   L'aura bestiale Le masque du pouvoir    Le masque de l'Effroi   Le chasseur silencieux  Le chant du sang
        String runes_and_nb_talismans = nbTotal(talismans) + SEPARATOR + nbRed(talismans) + SEPARATOR + nbGreen(talismans) + SEPARATOR
                                        + nbBlue(talismans) + SEPARATOR + nbYellow(talismans) + SEPARATOR + talismans.size();
        String combi_talismans_info = "";
        for (Talisman t : Talisman.values()) {
            if (talismans.contains(t)) {
                combi_talismans_info += "," + 1;
            } else {
                combi_talismans_info += "," + 0;
            }
        }
        System.out.println(runes_and_nb_talismans + combi_talismans_info);
    }

    public static boolean isValidCombination(Set<Talisman> talismans) {
        return nbRed(talismans) <= MAX_INDIVIDUAL_RUNES && nbGreen(talismans) <= MAX_INDIVIDUAL_RUNES
               && nbBlue(talismans) <= MAX_INDIVIDUAL_RUNES && nbYellow(talismans) <= MAX_INDIVIDUAL_RUNES
               && nbTotal(talismans) <= MAX_RUNES;
    }

    public static int nbRed(Set<Talisman> talismans) {
        int res = 0;
        for (Talisman talisman : talismans) {
            res += talisman.getRed();
        }
        return res;
    }

    public static int nbGreen(Set<Talisman> talismans) {
        int res = 0;
        for (Talisman talisman : talismans) {
            res += talisman.getGreen();
        }
        return res;
    }

    public static int nbBlue(Set<Talisman> talismans) {
        int res = 0;
        for (Talisman talisman : talismans) {
            res += talisman.getBlue();
        }
        return res;
    }

    public static int nbYellow(Set<Talisman> talismans) {
        int res = 0;
        for (Talisman talisman : talismans) {
            res += talisman.getYellow();
        }
        return res;
    }

    public static int nbTotal(Set<Talisman> talismans) {
        int res = 0;
        for (Talisman talisman : talismans) {
            res += talisman.getTotal();
        }
        return res;
    }

}
