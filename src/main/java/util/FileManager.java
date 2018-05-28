package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }

        return instance;
    }

    private final String resourcesPath = "output/";

    private FileManager() {
        //Nothing
    }

    /**
     * @param o
     * @param fileName
     * Save object o
     */
    public void save(Object o, String fileName) {
        File file = new File(resourcesPath + fileName);
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(o);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * @return
     * @throws IOException
     * Load object from File fileName
     */
    public Object load(String fileName) throws IOException {
        Object res = null;
        File file = new File(resourcesPath + fileName);
        try {
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream s = new ObjectInputStream(f);
            res = s.readObject();
            s.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void write(String fileName, String textToWrite, boolean append) {
        FileWriter fw;
        try {
            fw = new FileWriter(resourcesPath + fileName, append);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            if (!textToWrite.isEmpty())
                out.println(textToWrite);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * @param textToWrite
     * Create a file containing textToWrite
     * Warning: Overwrite existing file!
     */
    public void overwrite(String fileName, String textToWrite) {
        write(fileName, textToWrite, false);
    }

    /**
     * @param fileName
     * @param textToWrite
     * Write text at the end of a File (no overwriting)
     */
    public void append(String fileName, String textToWrite) {
        write(fileName, textToWrite, true);
    }

    public boolean isExistingFile(String fileName) {
        File f = new File(resourcesPath + fileName);
        return f.isFile();
    }

    public File getFile(String fileName) {
        return new File(resourcesPath + fileName);
    }

}