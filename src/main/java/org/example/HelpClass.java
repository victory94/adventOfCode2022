package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HelpClass {

    public ArrayList<String> readFile(String fileName) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(getFileAsIOStream(fileName));
        BufferedReader br = new BufferedReader(isr);
        String line;
        //Reads file with inputs, from resource folder
        while((line = br.readLine())!=null)
        {
            //Empty line rock paper scissors
            if (!line.isEmpty()) {
                list.add(line.trim());
            }
        }
        br.close();
        isr.close();
        return list;
    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }
}
