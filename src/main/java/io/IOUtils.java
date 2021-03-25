package io;

import java.io.*;

public class IOUtils {

    public static void main(String[] args) {

        File file = new File("README1.md");
        System.out.println(file.getAbsolutePath() + "\n" +file.getPath());
        readByFis(file);
        //System.out.println(System.getProperty("user.dir"));

        //java E:\Java\idea_project\learn_project\target\classes\io\io.IOUtils.class
    }

    public void fileInputStream() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("");
    }

    private static long readByFis(File file) {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] buff = new byte[4096];
            long counts = 0;
            int offset = 0;
            while((offset = is.read(buff)) != -1) {
                counts = counts + offset;
            }

            return counts;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
