package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AnalyseOpponents {

    private static final String FIELD_SEPARATOR = "\t";
    private static final int DAMAGE_SPLIT_OFFSET = 18; //Length of: </b></td>     <td>

    public static void main(String[] args) {
        //String fileName = reformFileName(args);
        String file_name = "C:\\Users\\Amendil\\Desktop\\Innocent Criminal\\Siege\\6.htm"; //Avoid file prompt everytime
        File input = new File(file_name);
        try {
            Document doc = Jsoup.parse(input, "UTF-8");

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_name.replace(".htm", "_analyse_opp.tsv")), "UTF-8"));
            writer.write('\ufeff');
            writer.write("Pseudo" + FIELD_SEPARATOR + "D�g�ts" + FIELD_SEPARATOR + "Agilit�" + FIELD_SEPARATOR + "Perception\n");
            
            boolean is_attacking = doc.getElementsByClass("versus").first().child(0).html().contains("INNOCENT CRIMINAL");
            Elements teams = doc.getElementsByClass("amblist");
            Element team = is_attacking ? teams.get(1).child(0) : teams.get(0).child(0);

            Element summary = doc.getElementsByClass("ambsummary").first();
            String summary_html = summary.outerHtml();

            String line;
            for (Element child : team.children()) {
                line = "";
                line += child.text() + FIELD_SEPARATOR; // Pseudo

                int index = summary_html.indexOf(child.text());
                line += summary_html.substring(index + child.text().length() + DAMAGE_SPLIT_OFFSET).split(" / ")[0] + FIELD_SEPARATOR; //Damage

                String html = child.outerHtml();
                line += html.split("AGILIT�:.{0,22}<b>")[1].split("</b>")[0] + FIELD_SEPARATOR; //Agility
                line += html.split("PERCEPTION:.{0,22}<b>")[1].split("</b>")[0] + "\n"; //Perception

                writer.write(line);
            }

            writer.close();
            System.out.println("Program ended without errors");
        } catch (IOException e) {
            System.out.println("Document at path: " + file_name + " not found.");
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
