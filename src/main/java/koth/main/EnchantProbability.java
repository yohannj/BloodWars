package koth.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import koth.Enchant;
import koth.EnchantFullStat;
import koth.KothSite;
import util.FileManager;

public class EnchantProbability {

    private static final String CSV_SEPARATOR = ";";
    private static final int ENCHANT_COUNT_PER_FAVOUR = 10000;
    private static final int LOBBYIST_BONUS_LEVEL = 5;
    private static final int MAX_FAVOUR = 24999;
    private static final int SPONSOR_BONUS_LEVEL = 5;
    private static final int MAX_BONUS = 3;
    private static final String OUTPUT_FILE_NAME = "result.csv";

    public static void main(String[] args) {
        var favourPointsToTest = Arrays.stream(EnchantFullStat.values()).map(e -> e.getFavourPoints() + 1).filter(favour -> favour < MAX_FAVOUR).distinct().collect(Collectors.toList());
        favourPointsToTest.add(0);
        Collections.sort(favourPointsToTest);

        var kothSites = Arrays.asList(KothSite.values());
        Collections.sort(kothSites, (s1, s2) -> Integer.compare(s1.getIndex(), s2.getIndex()));

        //String header = "Range" + CSV_SEPARATOR + StringUtils.join(kothSites, CSV_SEPARATOR);
        //StringBuilder sb = new StringBuilder(header);

        var enchantRates = new HashMap<List<EnchantFullStat>, Map<Integer, Map<KothSite, Double>>>();
        for (int favourIdx = 0; favourIdx < favourPointsToTest.size(); ++favourIdx) {
            System.out.println((favourIdx + 1) + "/" + favourPointsToTest.size());
            int favourPoints = favourPointsToTest.get(favourIdx);
            //String range = favourPoints + (favourIdx + 1 < favourPointsToTest.size() ? "-" + (favourPointsToTest.get(favourIdx + 1) - 1)
            //                                                                         : "+");

            for (KothSite site : kothSites) {
                System.out.println(site);
                Enchant.resetPRNG();

                var enchantsCount = new HashMap<List<EnchantFullStat>, AtomicInteger>();
                for (int i = 0; i < ENCHANT_COUNT_PER_FAVOUR; ++i) {
                    Enchant enchant = Enchant.generate(favourPoints, site, LOBBYIST_BONUS_LEVEL, SPONSOR_BONUS_LEVEL);
                    var bestBonus = enchant.getBestBonus();
                    bestBonus.sort(Comparator.comparing(EnchantFullStat::name));
                    enchantsCount.computeIfAbsent(bestBonus, k -> new AtomicInteger()).incrementAndGet();
                }

                for (var entry : enchantsCount.entrySet())
                    enchantRates.computeIfAbsent(entry.getKey(), k -> new HashMap<Integer, Map<KothSite, Double>>()).computeIfAbsent(favourIdx, k -> new HashMap<KothSite, Double>())
                                .put(site, entry.getValue().get() * 1.0 / ENCHANT_COUNT_PER_FAVOUR);
            }
        }

        FileManager.getInstance().overwrite(OUTPUT_FILE_NAME, "");
        for (Entry<List<EnchantFullStat>, Map<Integer, Map<KothSite, Double>>> entry : enchantRates.entrySet()) {
            var k = entry.getKey();
            var v = entry.getValue();

            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < MAX_BONUS; ++i) {
                if (i > 0)
                    sb2.append(CSV_SEPARATOR);

                if (i < k.size())
                    sb2.append(k.get(i));
            }

            for (int favourIdx = 0; favourIdx < favourPointsToTest.size(); ++favourIdx) {
                var rateBySite = v.get(favourIdx);
                for (var site : kothSites) {
                    var rate = rateBySite == null || !rateBySite.containsKey(site) ? 0d
                                                                                   : rateBySite.get(site);
                    sb2.append(CSV_SEPARATOR).append(rate);
                }
            }

            FileManager.getInstance().append(OUTPUT_FILE_NAME, sb2.toString());
        }

    }
}
