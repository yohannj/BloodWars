package koth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Enchant {

    private static final Map<Set<EnchantFullStat>, Map<Integer, SortedMap<EnchantFullStat, Double>>> MEMOIZE_GET_POSSIBLE_BONUS_RATE = new HashMap<>();

    private static final long PRNG_BASE_SEED = 1337l;
    private static final Random PRNG = new Random(PRNG_BASE_SEED);

    private List<EnchantFullStat> bonus;
    private List<EnchantFullStat> bestBonus;
    private List<EnchantFullStat> malus;

    public Enchant() {
        bonus = new ArrayList<>(3);
        bestBonus = new ArrayList<>(3);
        malus = new ArrayList<>(3);
    }

    public static Enchant generate(int favourPoints, KothSite site, int lobbyistBonusLvl, int sponsorBonusLvl) {
        var doubleBonusRate = 0.01 * (7 + site.getIndex() + lobbyistBonusLvl * 2);
        var tripleBonusRate = 0.01 * (site.getIndex() + sponsorBonusLvl);

        var enchant = new Enchant();
        SortedMap<EnchantFullStat, Double> possibleBonus = getPossibleBonusRate(favourPoints, enchant);

        var hasSecondBonus = PRNG.nextDouble() < doubleBonusRate;
        var hasThirdBonus = hasSecondBonus && PRNG.nextDouble() < tripleBonusRate;

        double prngBonusDraw, cumSum;

        cumSum = 0d;
        prngBonusDraw = PRNG.nextDouble();
        for (Entry<EnchantFullStat, Double> entry : possibleBonus.entrySet()) {
            cumSum += entry.getValue();

            if (prngBonusDraw < cumSum) {
                enchant.addBonus(entry.getKey());
                break;
            }
        }

        if (hasSecondBonus) {
            cumSum = 0d;
            prngBonusDraw = PRNG.nextDouble();
            for (Entry<EnchantFullStat, Double> entry : getPossibleBonusRate(0, enchant).entrySet()) {
                cumSum += entry.getValue();

                if (prngBonusDraw < cumSum) {
                    enchant.addBonus(entry.getKey());
                    break;
                }
            }
        }

        if (hasThirdBonus) {
            cumSum = 0d;
            prngBonusDraw = PRNG.nextDouble();
            for (Entry<EnchantFullStat, Double> entry : getPossibleBonusRate(0, enchant).entrySet()) {
                cumSum += entry.getValue();

                if (prngBonusDraw < cumSum) {
                    enchant.addBonus(entry.getKey());
                    break;
                }
            }
        }

        return enchant;
    }

    public static void resetPRNG() {
        PRNG.setSeed(PRNG_BASE_SEED);
    }

    public void addBonus(EnchantFullStat singleBonus) {
        bonus.add(singleBonus);
        bestBonus.add(getBestPossibleBonus(singleBonus));
    }

    public void addMalus(EnchantFullStat singleMalus) {
        malus.add(singleMalus);
    }

    public List<EnchantFullStat> getBestBonus() {
        return bestBonus;
    }

    public List<EnchantFullStat> getBonus() {
        return bonus;
    }

    public List<EnchantFullStat> getMalus() {
        return malus;
    }

    public int getPlayersFavourPoints() {
        return (int) Math.ceil(bonus.stream().map(e -> e.getFavourPoints() / 100.0).reduce((e1, e2) -> e1 + e2).get());
    }

    public Set<EnchantFullStat> getUniqueBonus() {
        return bonus.stream().filter(b -> b.isUnique()).collect(Collectors.toSet());
    }

    public boolean hasUniqueBonus() {
        return bonus.stream().anyMatch(b -> b.isUnique());
    }

    @Override
    public String toString() {
        //return StringUtils.join(bonus, ", ") + " => " + StringUtils.join(bestBonus, ", ");
        return StringUtils.join(bestBonus, ", ");
    }

    private EnchantFullStat getBestPossibleBonus(EnchantFullStat e) {
        return EnchantFullStat.MOST_IMPROVED_ENCHANT.get(e);
    }

    private static SortedMap<EnchantFullStat, Double> getPossibleBonusRate(int favourPoints, Enchant currentEnchant) {
        var res = MEMOIZE_GET_POSSIBLE_BONUS_RATE.computeIfAbsent(currentEnchant.getUniqueBonus(), k -> new HashMap<>())
                                                 .computeIfAbsent(favourPoints, k -> new TreeMap<>());

        if (!res.isEmpty())
            return res;

        Set<EnchantFullStat> forbiddenBonus = currentEnchant.getUniqueBonus();

        double totalFavour = 0;
        Set<EnchantFullStat> possibleBonus = new HashSet<>();
        for (EnchantFullStat e : EnchantFullStat.values()) {
            if (e.getFavourPoints() >= favourPoints && !forbiddenBonus.contains(e)) {
                possibleBonus.add(e);
                totalFavour += 1.0 / e.getFavourPoints();
            }
        }

        for (EnchantFullStat e : possibleBonus) {
            res.put(e, 1 / (totalFavour * e.getFavourPoints()));
        }

        return res;
    }
}
