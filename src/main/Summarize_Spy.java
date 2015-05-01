package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Summarize_Spy {

    private static final String[] fields = { "ID", "NOM", "RACE", "SEXE", "NIVEAU", "TYPE D'ARMES", "DEFENSE", "FORCE", "AGILITE",
            "RESISTANCE", "PERCEPTION", "SAVOIR", "NOM_CASQUE", "STAT_CASQUE", "NOM_ARMURE", "STAT_ARMURE", "NOM_PANTALON",
            "STAT_PANTALON", "NOM_AMULETTE", "STAT_AMULETTE", "NOM_ANNEAU_1", "STAT_ANNEAU_1", "NOM_ANNEAU_2", "STAT_ANNEAU_2",
            "NOM_ARME_1", "STAT_ARME_1", "NOM_ARME_2", "STAT_ARME_2" };
    private static final String field_separator = "\t";

    private static final Map<String, String> abbreviation_du_type;
    static {
        abbreviation_du_type = new HashMap<String, String>();
        abbreviation_du_type.put("Arme à une main", "CAC 1H");
        abbreviation_du_type.put("Arme à deux mains", "CAC 2H");
        abbreviation_du_type.put("Arme à feu à une main", "GUN 1H");
        abbreviation_du_type.put("Arme à feu à deux mains", "GUN 2H");
        abbreviation_du_type.put("Arme à distance à deux mains (lourde)", "AAD LOURD");
        abbreviation_du_type.put("Arme à distance à deux mains (légère)", "AAD LEGER");
    }

    private static enum ItemsType {
        CASQUE("Casque"), ARMURE("Armure"), PANTALON("Pantalon"), AMULETTE("Amulette"), ANNEAU("Anneau"), ARME("Arme");

        private String name;

        private ItemsType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Item {
        private String name;
        private String stats;
        private String owner;

        public Item(String name, String stats, String owner) {
            this.name = name;
            this.stats = stats;
            this.owner = owner;
        }
        
        public String getAgility() {
            Integer agility = 0;
            
            String[] stats_splitted = stats.split("AGILITÉ +");
            for(int i = 0; i < stats_splitted.length; ++i) {
                agility += Integer.parseInt(stats_splitted[i].split(",")[0]);
            }
            stats_splitted = stats.split("AGILITÉ -");
            for(int i = 0; i < stats_splitted.length; ++i) {
                agility -= Integer.parseInt(stats_splitted[i].split(",")[0]);
            }
            
            return agility.toString();
        }
        
        public String getPerception() {
            Integer perception = 0;
            
            String[] stats_splitted = stats.split("PERCEPTION +");
            for(int i = 0; i < stats_splitted.length; ++i) {
                perception += Integer.parseInt(stats_splitted[i].split(",")[0]);
            }
            stats_splitted = stats.split("PERCEPTION -");
            for(int i = 0; i < stats_splitted.length; ++i) {
                perception -= Integer.parseInt(stats_splitted[i].split(",")[0]);
            }
            
            return perception.toString();
        }

        @Override
        public String toString() {
            return name.split("_")[0] + field_separator + owner + field_separator + stats + field_separator + getAgility() + field_separator + getPerception();
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((owner == null) ? 0 : owner.hashCode());
            result = prime * result + ((stats == null) ? 0 : stats.hashCode());
            return result;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Item other = (Item) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (owner == null) {
                if (other.owner != null)
                    return false;
            } else if (!owner.equals(other.owner))
                return false;
            if (stats == null) {
                if (other.stats != null)
                    return false;
            } else if (!stats.equals(other.stats))
                return false;
            return true;
        }
    }

    private static Set<String> players_id;
    private static Map<String, List<Map<String, String>>> players_info;
    private static Map<ItemsType, Set<Item>> all_items;
    private static Writer writer;
    private static Writer writer_stuff;

    public static void main(String[] args) {
        String folder_name = "C:\\Users\\Amendil\\Desktop\\Innocent Criminal\\Spy";
        File folder = new File(folder_name);

        players_id = new HashSet<String>();
        players_info = new HashMap<String, List<Map<String, String>>>();
        all_items = new HashMap<ItemsType, Set<Item>>();
        for (ItemsType it : ItemsType.values()) {
            all_items.put(it, new HashSet<Item>());
        }

        if (folder.isDirectory()) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folder_name + "\\infos membres.tsv"), "UTF-8"));
                writer_stuff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folder_name + "\\stuffs.tsv"), "UTF-8"));

                writeHeader();

                //Iterate on every spy
                for (final File file : folder.listFiles()) {
                    if (file.getName().endsWith(".htm")) {
                        Document doc = Jsoup.parse(file, "UTF-8");
                        mineSpyInfo(doc.getElementsByClass("msg-content").first());
                    }
                }

                writeRecords();
                writeStuff();

                writer.close();
                writer_stuff.close();

                System.out.println("Program ended without errors");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void mineSpyInfo(Element spy_content) {
        Element name_and_id_elem = spy_content.getElementsByClass("players").first();
        String id = name_and_id_elem.attr("href").split("&uid=")[1];

        if (!players_id.contains(id)) {
            players_id.add(id);
            players_info.put(id, new ArrayList<Map<String, String>>());
        }

        Map<String, String> player_info = new HashMap<String, String>();

        player_info.put("ID", id);
        player_info.put("NOM", name_and_id_elem.text());

        String[] html_split_by_lines = spy_content.html().split("<br>");

        player_info.put("RACE", html_split_by_lines[15].split("<b>")[1].split("</b>")[0]);
        player_info.put("SEXE", html_split_by_lines[16].split("<b>")[1].split("</b>")[0]);
        player_info.put("NIVEAU", html_split_by_lines[18].split("<b>")[1].split("</b>")[0]);

        int stats_index = 36;
        while (!html_split_by_lines[stats_index].contains("<b>Les statistiques"))
            ++stats_index;

        Elements stats = Jsoup.parse(html_split_by_lines[stats_index]).getElementsByTag("span");
        player_info.put("DEFENSE", stats.get(0).text());
        player_info.put("FORCE", stats.get(1).text());
        player_info.put("AGILITE", stats.get(2).text());
        player_info.put("RESISTANCE", stats.get(3).text());
        player_info.put("PERCEPTION", stats.get(7).text());
        player_info.put("SAVOIR", stats.get(9).text());

        Elements items = spy_content.getElementsByClass("item-link");
        int anneau_index = 1;
        int arme_index = 1;
        for (Element item : items) {
            Elements divs = Jsoup.parse(item.outerHtml().split("overlib\\('")[1].split("',CAPTIONFONTCLASS")[0]).getElementsByTag("div");

            String type = divs.get(2).text().split("\\(P")[0].trim(); //Remove gender if exist (i.e. Casque (Pour femme)), but keep light/heavy specialisation of AAD weapon
            if (type.equals("Anneau")) {
                type += "_" + (anneau_index++);
            } else if (type.startsWith("Arme")) {
                player_info.put("TYPE D'ARMES", abbreviation_du_type.get(type));
                type = "Arme_" + (arme_index++);
            }
            type = type.toUpperCase();

            int attributs_index = divs.get(3).text().contains("Attributs") ? 3 : 4;
            String attributs = divs.get(attributs_index).text().split("Attributs:")[1].trim();

            player_info.put("NOM_" + type, item.text());
            player_info.put("STAT_" + type, attributs);
        }

        addItems(player_info);

        players_info.get(id).add(player_info);
    }

    private static void addItems(Map<String, String> player_info) {
        //System.out.println("NOM_" + ItemsType.CASQUE.toString().toUpperCase());
        //System.out.println(player_info.get("NOM_" + ItemsType.CASQUE.toString().toUpperCase()));
        //System.out.println("STAT_" + ItemsType.CASQUE.toString().toUpperCase());
        //System.out.println(player_info.get("STAT_" + ItemsType.CASQUE.toString().toUpperCase()));
        //System.out.println(player_info.get("NOM"));
        all_items.get(ItemsType.CASQUE)
                 .add(new Item(player_info.get("NOM_" + ItemsType.CASQUE.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                    + ItemsType.CASQUE.toString()
                                                                                                                                      .toUpperCase()), player_info.get("NOM")));
        all_items.get(ItemsType.ARMURE)
                 .add(new Item(player_info.get("NOM_" + ItemsType.ARMURE.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                    + ItemsType.ARMURE.toString()
                                                                                                                                      .toUpperCase()), player_info.get("NOM")));
        all_items.get(ItemsType.PANTALON)
                 .add(new Item(player_info.get("NOM_" + ItemsType.PANTALON.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                      + ItemsType.PANTALON.toString()
                                                                                                                                          .toUpperCase()), player_info.get("NOM")));
        all_items.get(ItemsType.AMULETTE)
                 .add(new Item(player_info.get("NOM_" + ItemsType.AMULETTE.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                      + ItemsType.AMULETTE.toString()
                                                                                                                                          .toUpperCase()), player_info.get("NOM")));
        all_items.get(ItemsType.ANNEAU)
                 .add(new Item(player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_1"), player_info.get("STAT_"
                                                                                                                           + ItemsType.ANNEAU.toString()
                                                                                                                                             .toUpperCase()
                                                                                                                           + "_1"), player_info.get("NOM")));
        if (player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_2")
                       .equals(player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_1"))) {
            all_items.get(ItemsType.ANNEAU)
                     .add(new Item(player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_2") + "_2", player_info.get("STAT_"
                                                                                                                                      + ItemsType.ANNEAU.toString()
                                                                                                                                                        .toUpperCase()
                                                                                                                                      + "_2"), player_info.get("NOM")));
        } else {
            all_items.get(ItemsType.ANNEAU)
                     .add(new Item(player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_2"), player_info.get("STAT_"
                                                                                                                               + ItemsType.ANNEAU.toString()
                                                                                                                                                 .toUpperCase()
                                                                                                                               + "_2"), player_info.get("NOM")));
        }
        all_items.get(ItemsType.ARME)
                 .add(new Item(player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_1"), player_info.get("STAT_"
                                                                                                                         + ItemsType.ARME.toString()
                                                                                                                                         .toUpperCase()
                                                                                                                         + "_1"), player_info.get("NOM")));
        if (player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_2") != null) {
            if (player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_2")
                           .equals(player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_1"))) {
                all_items.get(ItemsType.ARME)
                         .add(new Item(player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_2") + "_2", player_info.get("STAT_"
                                                                                                                                        + ItemsType.ARME.toString()
                                                                                                                                                        .toUpperCase()
                                                                                                                                        + "_2"), player_info.get("NOM")));
            } else {
                all_items.get(ItemsType.ARME)
                         .add(new Item(player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_2"), player_info.get("STAT_"
                                                                                                                                 + ItemsType.ARME.toString()
                                                                                                                                                 .toUpperCase()
                                                                                                                                 + "_2"), player_info.get("NOM")));
            }
        }
    }

    private static void writeHeader() throws IOException {
        String header = "";
        int field_added = 0;
        //Add BOM, to force UTF-8
        header += '\ufeff';
        for (String field : fields) {
            if (field_added++ > 0)
                header += field_separator;
            header += field;
        }
        writer.write(header + "\n");

        header = "";
        header += '\ufeff';
        header += "Items" + field_separator + "Propriétaire" + field_separator + "Item_STAT" + field_separator + "Agilité" + field_separator + "Perception";
        writer_stuff.write(header + "\n");
    }

    private static void writeRecords() throws IOException {
        for (String id : players_id) {
            for (Map<String, String> player_info : players_info.get(id)) {
                String record = "";
                int field_added = 0;
                for (String field : fields) {
                    if (field_added++ > 0)
                        record += field_separator;
                    record += player_info.get(field);
                }
                writer.write(record + "\n");
            }
        }
    }

    private static void writeStuff() throws IOException {
        ItemsType[] ordered_items_type = { ItemsType.CASQUE, ItemsType.ARMURE, ItemsType.PANTALON, ItemsType.AMULETTE, ItemsType.ANNEAU,
                ItemsType.ARME };
        for (int i = 0; i < ordered_items_type.length; ++i) {
            ItemsType type = ordered_items_type[i];
            writer_stuff.write(type.toString() + field_separator + field_separator + "\n");
            List<Item> items = new ArrayList<Item>(all_items.get(type));
            Collections.sort(items, new Comparator<Item>() {

                @Override
                public int compare(Item o1, Item o2) {
                    if (o1.owner.compareTo(o2.owner) != 0)
                        return o1.owner.toLowerCase().compareTo(o2.owner.toLowerCase());
                    return o1.name.compareTo(o2.name);
                }
            });
            for (Item item : items) {
                writer_stuff.write(item.toString() + "\n");
            }
        }
    }

}
