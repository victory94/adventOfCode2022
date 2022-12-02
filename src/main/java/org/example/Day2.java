package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
    public  ArrayList<String> winnig_combo = new ArrayList<>(Arrays.asList("AY", "BZ", "CX"));
    public ArrayList<String> losing_combo = new ArrayList<>(Arrays.asList("AZ", "BX", "CY"));
    public ArrayList<String> draw_combo = new ArrayList<>(Arrays.asList("AX", "BY", "CZ"));
    public static void main(String[] args) throws IOException {
        Map<String, Integer> choice_map = Stream.of(new Object[][] {
                { "Z", 3 },
                { "Y", 2 },
                { "X", 1 },
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
        Day2 instance = new Day2();
        InputStream is = instance.getFileAsIOStream("input_day2.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        int score = 0, score2 = 0;
        String line;
        //Reads file with inputs, from resource folder
        while((line = br.readLine()) != null){
            //Empty line rock paper scissors
            if(!line.isEmpty()){
                String[] choices = line.trim().split("\\s+");
                score = score + instance.game_score(choices[0] + choices[1]);
                score = score + choice_map.get(choices[1]);
                String new_choice = instance.get_end_choice(choices[0], choices[1]);
                score2 = score2 + choice_map.get(new_choice);
                score2 = score2 + instance.game_score(choices[0] + new_choice);
            }

        }
        br.close();
        isr.close();
        System.out.println("Answer problem 1: " + score);
        System.out.println("Answer problem 2: " + score2);
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


    private int game_score(String check){
        if(winnig_combo.contains(check)){
           return 6;
        }else if(losing_combo.contains(check)){
            return 0;
        }else {
            return 3;
        }
    }

    private String get_end_choice(String elf, String challenger){
        String return_choice = "";
        if(challenger.compareTo("Z") == 0){
            for(String s : winnig_combo){
                if(elf.charAt(0) == s.charAt(0)){
                    return_choice = s.substring(1);
                }
            }
        }else if(challenger.compareTo("X") == 0){
            for(String s : losing_combo){
                if(elf.charAt(0) == s.charAt(0)){
                    return_choice = s.substring(1);
                }
            }
        }else{
            for(String s : draw_combo){
                if(elf.charAt(0) == s.charAt(0)){
                    return_choice = s.substring(1);
                }
            }
        }
        return return_choice;
    }


}
