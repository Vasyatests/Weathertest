package modules;

import java.io.*;

public class Logging {
    public static String logname = "apireqests.log";

    public static void writeStrToFile(String fileName, String text) { writeStrToFile(fileName, text, true); }
    static void writeStrToFile(String fileName, String text, boolean wrap) {
        File file = new File(fileName);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter
                (new FileOutputStream(file, true), "UTF-8"))) {
            if (wrap) bw.newLine();
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
