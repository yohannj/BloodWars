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

    private static final String[] fields = { "ID", "NOM", "RACE", "SEXE", "NIVEAU", "AAD LEGER", "AAD LOURD", "CAC 1H", "CAC 2H", "GUN 1H",
            "GUN 2H", "FORCE", "AGILITE", "RESISTANCE", "PERCEPTION", "SAVOIR" };
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

        public Integer getStat(String name) {
            return Summarize_Spy.getStat(name, stats);
        }

        @Override
        public String toString() {
            return name.split("_")[0] + field_separator + owner + field_separator + stats + field_separator + getStat("AGILITÉ")
                   + field_separator + getStat("PERCEPTION");
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
    private static Map<String, Map<String, String>> players_info;
    private static Map<ItemsType, Set<Item>> all_items;
    private static Writer writer;
    private static Writer writer_stuff;

    public static void main(String[] args) {
        String folder_name = "C:\\Users\\Amendil\\Desktop\\Innocent Criminal\\Spy";
        File folder = new File(folder_name);

        players_id = new HashSet<String>();
        players_info = new HashMap<String, Map<String, String>>();
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
        String type_arme = "";

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
        Integer strengh = Integer.parseInt(stats.get(1).text());
        Integer agility = Integer.parseInt(stats.get(2).text());
        Integer toughness = Integer.parseInt(stats.get(3).text());
        Integer perception = Integer.parseInt(stats.get(7).text());
        Integer knowledge = Integer.parseInt(stats.get(9).text());
        Set<String> sets_effects = new HashSet<String>();

        Elements items = spy_content.getElementsByClass("item-link");
        int anneau_index = 1;
        int arme_index = 1;
        for (Element item : items) {
            Elements divs = Jsoup.parse(item.outerHtml().split("overlib\\('")[1].split("',CAPTIONFONTCLASS")[0]).getElementsByTag("div");

            String type = divs.get(2).text().split("\\(P")[0].trim(); //Remove gender if exist (i.e. Casque (Pour femme)), but keep light/heavy specialisation of AAD weapon
            if (type.equals("Anneau")) {
                type += "_" + (anneau_index++);
            } else if (type.startsWith("Arme")) {
                type_arme = abbreviation_du_type.get(type);
                type = "Arme_" + (arme_index++);
            }
            type = type.toUpperCase();

            int attributs_index = divs.get(3).text().contains("Attributs") ? 3 : 4;
            String attributs = divs.get(attributs_index).text().split("Attributs:")[1].trim();
            Elements set_attributs = divs.get(attributs_index).getElementsByClass("setItem");
            if (set_attributs.size() > 0) {
                int index = attributs.indexOf(set_attributs.get(0).text());
                sets_effects.add(attributs.substring(index));
                attributs = attributs.substring(0, index).trim();

            }

            player_info.put("NOM_" + type, item.text());
            player_info.put("STAT_" + type, attributs);
        }

        List<Item> item_added = addItems(player_info);

        for (int i = 0; i < item_added.size(); ++i) {
            Item item = item_added.get(i);
            strengh -= item.getStat("FORCE");
            agility -= item.getStat("AGILITÉ");
            toughness -= item.getStat("RÉSISTANCE");
            perception -= item.getStat("PERCEPTION");
            knowledge -= item.getStat("SAVOIR");
        }
        for (String s : sets_effects) {
            strengh -= getStat("FORCE", s);
            agility -= getStat("AGILITÉ", s);
            toughness -= getStat("RÉSISTANCE", s);
            perception -= getStat("PERCEPTION", s);
            knowledge -= getStat("SAVOIR", s);
        }

        player_info.put("FORCE", strengh.toString());
        player_info.put("AGILITE", agility.toString());
        player_info.put("RESISTANCE", toughness.toString());
        player_info.put("PERCEPTION", perception.toString());
        player_info.put("SAVOIR", knowledge.toString());

        if (!players_id.contains(id)) {
            players_id.add(id);
            players_info.put(id, player_info);
            players_info.get(id).put("AAD LEGER", "Non");
            players_info.get(id).put("AAD LOURD", "Non");
            players_info.get(id).put("CAC 1H", "Non");
            players_info.get(id).put("CAC 2H", "Non");
            players_info.get(id).put("GUN 1H", "Non");
            players_info.get(id).put("GUN 2H", "Non");
            players_info.get(id).put(type_arme, "Oui");
        } else {
            //Update some stat. Code is clearly not optimized but that should works
            players_info.get(id).put(type_arme, "Oui");
            players_info.get(id).put("FORCE", strengh.toString());
            players_info.get(id).put("AGILITE", agility.toString());
            players_info.get(id).put("RESISTANCE", toughness.toString());
            players_info.get(id).put("PERCEPTION", perception.toString());
            players_info.get(id).put("SAVOIR", knowledge.toString());
        }

        //players_info.get(id).add(player_info);
    }

    private static List<Item> addItems(Map<String, String> player_info) {
        //System.out.println("NOM_" + ItemsType.CASQUE.toString().toUpperCase());
        //System.out.println(player_info.get("NOM_" + ItemsType.CASQUE.toString().toUpperCase()));
        //System.out.println("STAT_" + ItemsType.CASQUE.toString().toUpperCase());
        //System.out.println(player_info.get("STAT_" + ItemsType.CASQUE.toString().toUpperCase()));
        //System.out.println(player_info.get("NOM"));
        List<Item> item_added = new ArrayList<Item>();
        Item helmet = new Item(player_info.get("NOM_" + ItemsType.CASQUE.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                    + ItemsType.CASQUE.toString()
                                                                                                                                      .toUpperCase()), player_info.get("NOM"));
        Item armour = new Item(player_info.get("NOM_" + ItemsType.ARMURE.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                    + ItemsType.ARMURE.toString()
                                                                                                                                      .toUpperCase()), player_info.get("NOM"));
        Item pant = new Item(player_info.get("NOM_" + ItemsType.PANTALON.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                    + ItemsType.PANTALON.toString()
                                                                                                                                        .toUpperCase()), player_info.get("NOM"));
        Item amulet = new Item(player_info.get("NOM_" + ItemsType.AMULETTE.toString().toUpperCase()), player_info.get("STAT_"
                                                                                                                      + ItemsType.AMULETTE.toString()
                                                                                                                                          .toUpperCase()), player_info.get("NOM"));
        Item ring_1 = new Item(player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_1"), player_info.get("STAT_"
                                                                                                                           + ItemsType.ANNEAU.toString()
                                                                                                                                             .toUpperCase()
                                                                                                                           + "_1"), player_info.get("NOM"));
        Item ring_2 = player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_2")
                                 .equals(player_info.get("NOM_" + ItemsType.ANNEAU.toString().toUpperCase() + "_1"))
                                                                                                                    ? new Item(player_info.get("NOM_"
                                                                                                                                               + ItemsType.ANNEAU.toString()
                                                                                                                                                                 .toUpperCase()
                                                                                                                                               + "_2")
                                                                                                                               + "_2", player_info.get("STAT_"
                                                                                                                                                       + ItemsType.ANNEAU.toString()
                                                                                                                                                                         .toUpperCase()
                                                                                                                                                       + "_2"), player_info.get("NOM"))
                                                                                                                    : new Item(player_info.get("NOM_"
                                                                                                                                               + ItemsType.ANNEAU.toString()
                                                                                                                                                                 .toUpperCase()
                                                                                                                                               + "_2"), player_info.get("STAT_"
                                                                                                                                                                        + ItemsType.ANNEAU.toString()
                                                                                                                                                                                          .toUpperCase()
                                                                                                                                                                        + "_2"), player_info.get("NOM"));
        Item weapon_1 = new Item(player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_1"), player_info.get("STAT_"
                                                                                                                           + ItemsType.ARME.toString()
                                                                                                                                           .toUpperCase()
                                                                                                                           + "_1"), player_info.get("NOM"));
        Item weapon_2 = null;

        if (player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_2") != null) {
            weapon_2 = player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_2")
                                  .equals(player_info.get("NOM_" + ItemsType.ARME.toString().toUpperCase() + "_1"))
                                                                                                                   ? new Item(player_info.get("NOM_"
                                                                                                                                              + ItemsType.ARME.toString()
                                                                                                                                                              .toUpperCase()
                                                                                                                                              + "_2")
                                                                                                                              + "_2", player_info.get("STAT_"
                                                                                                                                                      + ItemsType.ARME.toString()
                                                                                                                                                                      .toUpperCase()
                                                                                                                                                      + "_2"), player_info.get("NOM"))
                                                                                                                   : new Item(player_info.get("NOM_"
                                                                                                                                              + ItemsType.ARME.toString()
                                                                                                                                                              .toUpperCase()
                                                                                                                                              + "_2"), player_info.get("STAT_"
                                                                                                                                                                       + ItemsType.ARME.toString()
                                                                                                                                                                                       .toUpperCase()
                                                                                                                                                                       + "_2"), player_info.get("NOM"));
        }

        all_items.get(ItemsType.CASQUE).add(helmet);
        all_items.get(ItemsType.ARMURE).add(armour);
        all_items.get(ItemsType.PANTALON).add(pant);
        all_items.get(ItemsType.AMULETTE).add(amulet);
        all_items.get(ItemsType.ANNEAU).add(ring_1);
        all_items.get(ItemsType.ANNEAU).add(ring_2);
        all_items.get(ItemsType.ARME).add(weapon_1);
        if (weapon_2 != null)
            all_items.get(ItemsType.ARME).add(weapon_2);

        item_added.add(helmet);
        item_added.add(armour);
        item_added.add(pant);
        item_added.add(amulet);
        item_added.add(ring_1);
        item_added.add(ring_2);
        item_added.add(weapon_1);
        if (weapon_2 != null)
            item_added.add(weapon_2);

        return item_added;

    }

    public static Integer getStat(String stat_name, String text) {
        Integer stat_value = 0;

        String[] stats_splitted = text.split(stat_name + " ");
        for (int i = 1; i < stats_splitted.length; ++i) {
            stat_value += Integer.parseInt(stats_splitted[i].split(",")[0]); //Parse the sign and the value
        }

        return stat_value;
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
        header += "Items" + field_separator + "Propriétaire" + field_separator + "Item_STAT" + field_separator + "Agilité"
                  + field_separator + "Perception";
        writer_stuff.write(header + "\n");
    }

    private static void writeRecords() throws IOException {
        for (String id : players_id) {
            Map<String, String> player_info = players_info.get(id);
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
