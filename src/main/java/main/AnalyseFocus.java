package main;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AnalyseFocus {

    public static void main(String[] args) throws IOException {
        //String fileName = reformFileName(args);
        String file_name = "C:\\Users\\Amendil\\Desktop\\Innocent Criminal\\Siege\\12.htm"; //Avoid file prompt everytime
        //String file_name = "C:\\Users\\Amendil\\Desktop\\10.htm"; //Avoid file prompt everytime
        File input = new File(file_name);
        Writer writer = null;
        try {
            Document doc = Jsoup.parse(input, "UTF-8");
            //new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName.replace(".htm", ".csv")), "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_name.replace(".htm", ".csv")), "UTF-8"));
            writer.write('\ufeff');
            writer.write("Pseudo,Focused\n");
            boolean is_attacking = doc.getElementsByClass("versus")
                    .first()
                    .child(0)
                    .html()
                    .contains("INNOCENT CRIMINAL");
            Elements teams = doc.getElementsByClass("amblist");
            Element team = is_attacking ? teams.get(0).child(0) : teams.get(1)
                    .child(0);

            Map<String, Integer> team_members_hit_received = new HashMap<String, Integer>();
            Set<String> team_members_alive = new HashSet<String>();

            for (Element child : team.children()) {
                team_members_hit_received.put(child.text(), 0);
                team_members_alive.add(child.text());
            }

            Elements rounds = doc.getElementsByClass("round");
            rounds.removeIf(round -> !round.nodeName().equals("ul"));

            String opponent_hit_class_name = is_attacking ? "defHit" : "atkHit";
            DecimalFormatSymbols french_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat decimal_format = new DecimalFormat("0.00", french_symbols);
            int count_undead_hit_received = 0;
            int count_undead = team_members_hit_received.size();
            String last_member_receiving_damage = "";

            for (int i = 0; i < rounds.size(); ++i) {
                Element round = rounds.get(i);
                for (Element line : round.children()) {
                    if (line.className().equals(opponent_hit_class_name)) {

                        //One of our players got attacked
                        boolean is_first_name = true;
                        for (Element b : line.getElementsByTag("b")) {
                            if (!b.className().isEmpty()) {
                                if (is_first_name) {
                                    is_first_name = false;
                                } else { //This b is our player
                                    ++count_undead_hit_received;
                                    last_member_receiving_damage = b.text();
                                    team_members_hit_received.put(last_member_receiving_damage, team_members_hit_received.get(last_member_receiving_damage) + 1); //Increase nb hit received
                                }
                            }
                        }

                    } else if (line.className().equals("playerDeath") && line.text().contains("finit sa non-vie")) {
                        //One player died
                        String name = line.getElementsByTag("b").first().text();
                        if (team_members_hit_received.containsKey(name)) {

                            //The player who died is on of our players :(
                            int count_hit_received = team_members_hit_received.get(name);
                            String focus = decimal_format.format((100.0 * count_hit_received)
                                    / count_undead_hit_received);
                            String theorical_focus = decimal_format.format((100.0 / count_undead));
                            System.out.println("Parmi les " + count_undead
                                    + " joueurs encore vivants, " + name
                                    + " a un focus de " + focus
                                    + "%, contre un focus théorique de "
                                    + theorical_focus + "%");
                            --count_undead;
                            count_undead_hit_received -= count_hit_received;
                            team_members_alive.remove(name);
                        }
                    }
                }

                //Focus at the end of the round
                if(count_undead > 0) {
                    System.out.println();

                    String theorical_focus = decimal_format.format((100.0 / count_undead));
                    System.out.println("Focus des " + count_undead
                            + " membres encore vivant au round " + i
                            + " le focus théorique étant de " + theorical_focus
                            + "%");

                    for (String member_name : team_members_alive) {
                        int count_hit_received = team_members_hit_received.get(member_name);
                        String focus = decimal_format.format((100.0 * count_hit_received)
                                / count_undead_hit_received);

                        System.out.println(member_name + " a un focus de "
                                + focus + "%");
                        writer.write(member_name + ","
                                + focus + "%\n");
                    }

                    System.out.println();
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Document at path: " + file_name + " not found.");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
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
