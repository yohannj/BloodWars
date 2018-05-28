package main;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AnalyzeAndras {

    public enum AtkType {
        DODGE,
        MISS,
        ATK
    }

    public static void main(String[] args) throws IOException {
        int nbHit = 0;
        int nbDodge = 0;
        int nbMiss = 0;
        int nbSpecialAfterDodge = 0;
        int nbSpecialAfterMiss = 0;
        int nbHitAfterSpecial = 0;

        //String fileName = reformFileName(args);
        String file_name = "D:\\anime\\RC andras.htm"; //Avoid file prompt everytime
        //String file_name = "C:\\Users\\Amendil\\Desktop\\10.htm"; //Avoid file prompt everytime
        File input = new File(file_name);
        Document doc = Jsoup.parse(input, "UTF-8");
        //new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName.replace(".htm", ".csv")), "UTF-8"));

        AtkType last_atk_type = null;
        Set<String> team_members_under_special;

        Elements rounds = doc.getElementsByClass("round");
        rounds.removeIf(round -> !round.nodeName().equals("ul"));

        for (int i = 0; i < rounds.size(); ++i) {
            Element round = rounds.get(i);
            team_members_under_special = new HashSet<>();
            for (Element line : round.children()) {
                Elements b_tags = line.getElementsByTag("b");
                String player = b_tags.get(0).text();

                if (line.className().equals("atkHit")) {
                    if ((b_tags.size() < 2 || !"Andras".equals(b_tags.get(1).text()))
                        && (b_tags.size() < 3 || !"Andras".equals(b_tags.get(2).text()))
                        && (b_tags.size() < 4 || !"Andras".equals(b_tags.get(3).text()))) {
                        continue;
                    }

                    ++nbHit;

                    if (line.text().contains("Andras wykonuje serię zwodów i unika trafienia")) {
                        ++nbDodge;
                        last_atk_type = AtkType.DODGE;
                    } else if (line.text().contains("Andras nie zostaje trafiony")) {
                        ++nbMiss;
                        last_atk_type = AtkType.MISS;
                    } else {
                        last_atk_type = AtkType.ATK;

                        if (team_members_under_special.contains(player)) {
                            ++nbHitAfterSpecial;
                        }
                    }
                } else if (line.html().contains("w furię i zaczyna atakować swoich towarzyszy!")) {
                    team_members_under_special.add(player);

                    switch (last_atk_type) {
                    case ATK:
                        System.err.println(line);
                        break;
                    case DODGE:
                        ++nbSpecialAfterDodge;
                        break;
                    case MISS:
                        ++nbSpecialAfterMiss;
                        break;
                    default:
                        System.err.println(line);
                        break;

                    }
                }
            }
        }

        System.out.println("Nb hit: " + nbHit);
        System.out.println("Nb dodge: " + nbDodge + " (" + (100 * nbDodge / nbHit) + "%)");
        System.out.println("Nb miss: " + nbMiss + " (" + (100 * nbMiss / (nbHit - nbDodge)) + "%)");
        System.out.println("Nb special after dodge: " + nbSpecialAfterDodge + " (" + (100 * nbSpecialAfterDodge / nbDodge) + "%)");
        System.out.println("Nb special after miss: " + nbSpecialAfterMiss + " (" + (100 * nbSpecialAfterMiss / nbMiss) + "%)");
        System.out.println("Nb atk after special: " + nbHitAfterSpecial);
    }

    /**
     * Convert a file path that have been separated into String[] because of spaces into a single String.
     * @param pathElements
     * @return path into a unique String
     */
    public static String reformFileName(String[] pathElements) {
        String fileName = "";
        for (int i = 0; i < pathElements.length; ++i) {
            fileName += pathElements[i];
            if (i != pathElements.length - 1) {
                fileName += " ";
            }
        }
        return fileName;
    }

}
