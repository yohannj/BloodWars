package koth.main;

import java.util.HashMap;
import java.util.Map;

import koth.Enchant;
import koth.EnchantFullStat;
import koth.KothSite;

public class EnchantmentsForecast {

    private static final int INIT_FAVOUR_POINTS = 22501;
    private static final int NB_COMPUTATION = 100000;
    private static final int DAYS_TO_COMPUTE = 100;
    private static final KothSite SITE = KothSite.HORACE_S_STRONGHOLD;
    private static final int LOBBYIST_BONUS_LEVEL = 5;
    private static final int SPONSOR_BONUS_LEVEL = 5;
    private static final int MIN_BONUS_WANTED = 2;
    private static final Enchant CURRENT_ENCHANT;
    private static final Map<EnchantFullStat, Integer> WANTED_BONUS_WEIGHTED;
    static {
        CURRENT_ENCHANT = new Enchant();
        CURRENT_ENCHANT.addBonus(EnchantFullStat.P_DAM_4_1);
        CURRENT_ENCHANT.addBonus(EnchantFullStat.P_CH_4);

        WANTED_BONUS_WEIGHTED = new HashMap<>();

        // Amendil
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DAM_4_1, 100);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_ATT_SUP_1, 70);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DAM_4, 50);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_CH_4, 130);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_B_PTS_5, 35);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_IGN_DEF_4, 25);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_AGI_4, 20);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_STR_4, 20);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_PER_4, 15);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_L_PTS_6, 10);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DOD_4, 10);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_HIT_4, 10);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_LUC_6, 5);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DEF_4_2, 5);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_TOU_4, 1);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_CHA_4, 1);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_REP_4, 1);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_KNO_4, 1);

        // Consti
        /*WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DAM_4_1, 100);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_AGI_4, 60);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_CH_4, 50);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DAM_4, 50);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_B_PTS_5, 40);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_ATT_SUP_1, 25);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_IGN_DEF_4, 25);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_STR_4, 20);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_PER_4, 15);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_L_PTS_6, 10);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DOD_4, 10);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_HIT_4, 10);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_LUC_6, 5);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_DEF_4_2, 5);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_TOU_4, 1);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_CHA_4, 1);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_REP_4, 1);
        WANTED_BONUS_WEIGHTED.put(EnchantFullStat.P_KNO_4, 1);*/

        for (EnchantFullStat e : EnchantFullStat.values()) {
            if (!WANTED_BONUS_WEIGHTED.containsKey(e)) {
                WANTED_BONUS_WEIGHTED.put(e, 0);
            }
        }
    }

    private static final int CURRENT_ENCHANT_WANTED_VALUE = getEnchantWantedValue(CURRENT_ENCHANT);

    public static void main(String[] args) {
        double sameOrbetterEnchantChance = 0;
        double betterEnchantChance = 0;
        long meanFavourPointsInTheEnd = 0;

        for (int i = 0; i < NB_COMPUTATION; ++i) {
            int favourPoints = INIT_FAVOUR_POINTS;
            int sameOrbetterEnchantCount = 0;
            int betterEnchantCount = 0;
            for (int j = 0; j < DAYS_TO_COMPUTE; ++j) {
                Enchant e = Enchant.generate(favourPoints, SITE, LOBBYIST_BONUS_LEVEL, SPONSOR_BONUS_LEVEL);
                favourPoints = Math.min(24999, favourPoints + e.getPlayersFavourPoints());

                if (e.getBonus().size() >= MIN_BONUS_WANTED && getEnchantWantedValue(e) >= CURRENT_ENCHANT_WANTED_VALUE) {
                    ++sameOrbetterEnchantCount;

                    if (getEnchantWantedValue(e) > CURRENT_ENCHANT_WANTED_VALUE) {
                        ++betterEnchantCount;
                    }
                }

                /*if (e.bonus.size() > 2) {
                    //System.out.println("Day #" + j + " - " + e);
                    System.out.println(e);
                }*/
            }

            sameOrbetterEnchantChance += sameOrbetterEnchantCount * 1.0 / (DAYS_TO_COMPUTE * NB_COMPUTATION);
            betterEnchantChance += betterEnchantCount * 1.0 / (DAYS_TO_COMPUTE * NB_COMPUTATION);
            meanFavourPointsInTheEnd += favourPoints;
        }

        System.out.println(beautify(sameOrbetterEnchantChance, true) + " (~" + beautify(sameOrbetterEnchantChance * DAYS_TO_COMPUTE, false) + ") of your enchants in the next " + DAYS_TO_COMPUTE
                           + " days should be at least as great as the current one");
        System.out.println(beautify(betterEnchantChance, true) + " (~" + beautify(betterEnchantChance * DAYS_TO_COMPUTE, false) + ") of your enchants in the next " + DAYS_TO_COMPUTE
                           + " days should be better than the current one");
        System.out.println("On average, after " + DAYS_TO_COMPUTE + " days, you should have around " + (meanFavourPointsInTheEnd / NB_COMPUTATION) + " favour points");
    }

    private static int getEnchantWantedValue(Enchant e) {
        int res = 0;
        for (EnchantFullStat singleBonus : e.getBestBonus()) {
            res += WANTED_BONUS_WEIGHTED.get(singleBonus);
        }

        return res;
    }

    private static String beautify(Double d, boolean percent) {
        int tenThousand = (int) (d * 10000);
        return percent ? (tenThousand / 100.0) + "%"
                       : Double.toString((tenThousand / 100) / 100.0);
    }

}
