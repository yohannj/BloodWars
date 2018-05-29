package koth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum EnchantFullStat {
    P_STR_1(EnchantStatName.STR, "+2", 100),
    P_STR_2(EnchantStatName.STR, "+4", 250),
    P_STR_3(EnchantStatName.STR, "+8", 1600),
    P_STR_4(EnchantStatName.STR, "+14", 3500),
    P_AGI_1(EnchantStatName.AGI, "+2", 100),
    P_AGI_2(EnchantStatName.AGI, "+4", 250),
    P_AGI_3(EnchantStatName.AGI, "+8", 2000),
    P_AGI_4(EnchantStatName.AGI, "+14", 7500),
    P_TOU_1(EnchantStatName.TOU, "+2", 100),
    P_TOU_2(EnchantStatName.TOU, "+4", 250),
    P_TOU_3(EnchantStatName.TOU, "+8", 800),
    P_TOU_4(EnchantStatName.TOU, "+14", 2500),
    P_APP_1(EnchantStatName.APP, "+2", 100),
    P_APP_2(EnchantStatName.APP, "+4", 250),
    P_APP_3(EnchantStatName.APP, "+8", 500),
    P_APP_4(EnchantStatName.APP, "+14", 1400),
    P_CHA_1(EnchantStatName.CHA, "+2", 100),
    P_CHA_2(EnchantStatName.CHA, "+4", 250),
    P_CHA_3(EnchantStatName.CHA, "+8", 1200),
    P_CHA_4(EnchantStatName.CHA, "+14", 2500),
    P_REP_1(EnchantStatName.REP, "+2", 100),
    P_REP_2(EnchantStatName.REP, "+4", 250),
    P_REP_3(EnchantStatName.REP, "+8", 1200),
    P_REP_4(EnchantStatName.REP, "+14", 2500),
    P_PER_1(EnchantStatName.PER, "+2", 100),
    P_PER_2(EnchantStatName.PER, "+4", 250),
    P_PER_3(EnchantStatName.PER, "+8", 2000),
    P_PER_4(EnchantStatName.PER, "+14", 7500),
    P_INT_1(EnchantStatName.INT, "+2", 100),
    P_INT_2(EnchantStatName.INT, "+4", 250),
    P_INT_3(EnchantStatName.INT, "+8", 1000),
    P_INT_4(EnchantStatName.INT, "+14", 2500),
    P_KNO_1(EnchantStatName.KNO, "+2", 100),
    P_KNO_2(EnchantStatName.KNO, "+4", 250),
    P_KNO_3(EnchantStatName.KNO, "+8", 1000),
    P_KNO_4(EnchantStatName.KNO, "+14", 2500),
    P_B_PTS_1(EnchantStatName.B_PTS, "+20", 100),
    P_B_PTS_2(EnchantStatName.B_PTS, "+40", 250),
    P_B_PTS_3(EnchantStatName.B_PTS, "+40", 1000),
    P_B_PTS_4(EnchantStatName.B_PTS, "+100", 3000),
    P_B_PTS_5(EnchantStatName.B_PTS, "+150", 7500),
    P_L_PTS_1(EnchantStatName.L_PTS, "+25", 100),
    P_L_PTS_2(EnchantStatName.L_PTS, "+50", 250),
    P_L_PTS_3(EnchantStatName.L_PTS, "+100", 500),
    P_L_PTS_4(EnchantStatName.L_PTS, "+250", 2000),
    P_L_PTS_5(EnchantStatName.L_PTS, "+500", 12500),
    P_L_PTS_6(EnchantStatName.L_PTS, "+1000", 22500),
    P_DOD_1(EnchantStatName.DOD, "+3%", 250),
    P_DOD_2(EnchantStatName.DOD, "+5%", 750),
    P_DOD_3(EnchantStatName.DOD, "+7%", 2000),
    P_DOD_4(EnchantStatName.DOD, "+11%", 7500),
    P_LUC_1(EnchantStatName.LUC, "+2", 250),
    P_LUC_2(EnchantStatName.LUC, "+5", 500),
    P_LUC_3(EnchantStatName.LUC, "+7", 1000),
    P_LUC_4(EnchantStatName.LUC, "+10", 2000),
    P_LUC_5(EnchantStatName.LUC, "+15", 4000),
    P_LUC_6(EnchantStatName.LUC, "+20", 8000),
    P_ATT_SUP_1(EnchantStatName.ATT_SUP, "+1", 12500),
    P_CH_1(EnchantStatName.CH, "+3%", 250),
    P_CH_2(EnchantStatName.CH, "+5%", 500),
    P_CH_3(EnchantStatName.CH, "+8%", 1500),
    P_CH_4(EnchantStatName.CH, "+12%", 4500),
    P_DEF_1(EnchantStatName.DEF, "+20", 250),
    P_DEF_2(EnchantStatName.DEF, "+30", 500),
    P_DEF_3(EnchantStatName.DEF, "+50", 1500),
    P_DEF_4(EnchantStatName.DEF, "+75", 2500),
    P_DEF_4_1(EnchantStatName.DEF_4, "+3", 3500),
    P_DEF_4_2(EnchantStatName.DEF_4, "+6", 6000),
    P_DAM_1(EnchantStatName.DAM, "+5", 250),
    P_DAM_2(EnchantStatName.DAM, "+7", 750),
    P_DAM_3(EnchantStatName.DAM, "+10", 5000),
    P_DAM_4(EnchantStatName.DAM, "+20", 15000),
    P_DAM_4_1(EnchantStatName.DAM_4, "+1", 25000),
    P_IGN_DEF_1(EnchantStatName.IGN_DEF, "+4%", 500),
    P_IGN_DEF_2(EnchantStatName.IGN_DEF, "+6%", 3500),
    P_IGN_DEF_3(EnchantStatName.IGN_DEF, "+10%", 10000),
    P_IGN_DEF_4(EnchantStatName.IGN_DEF, "+15%", 20000),
    P_HIT_1(EnchantStatName.HIT, "+5", 100),
    P_HIT_2(EnchantStatName.HIT, "+12", 250),
    P_HIT_3(EnchantStatName.HIT, "+18", 500),
    P_HIT_4(EnchantStatName.HIT, "+25", 1500);

    public static final Set<EnchantFullStat> UNIQUE_ENCHANT;
    public static final Map<EnchantFullStat, EnchantFullStat> MOST_IMPROVED_ENCHANT;
    static {
        Map<EnchantStatName, EnchantFullStat> bestEnchantPerStat = new HashMap<>();
        for (EnchantFullStat e : EnchantFullStat.values()) {
            EnchantFullStat previousEnchant = bestEnchantPerStat.putIfAbsent(e.getEName(), e);
            if (previousEnchant != null && previousEnchant.getFavourPoints() < e.getFavourPoints())
                bestEnchantPerStat.put(e.getEName(), e);
        }

        UNIQUE_ENCHANT = new HashSet<>(bestEnchantPerStat.values());

        MOST_IMPROVED_ENCHANT = new HashMap<>(EnchantFullStat.values().length);
        for (EnchantFullStat e : EnchantFullStat.values())
            MOST_IMPROVED_ENCHANT.put(e, bestEnchantPerStat.get(e.eName));
    }

    private EnchantStatName eName;
    private String value;
    private int favourPoints;

    private EnchantFullStat(EnchantStatName name, String value, int favourPoints) {
        this.eName = name;
        this.value = value;
        this.favourPoints = favourPoints;
    }

    public EnchantStatName getEName() {
        return eName;
    }

    public int getFavourPoints() {
        return favourPoints;
    }

    public String getValue() {
        return value;
    }

    public boolean isUnique() {
        return UNIQUE_ENCHANT.contains(this);
    }

    @Override
    public String toString() {
        return eName + " " + value;
    }

}
