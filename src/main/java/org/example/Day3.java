package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    public  Map<String, Integer> points_map = Stream.of(new Object[][] {
            {"a" ,1 },{"b" ,2 },{"c" ,3 },{"d" ,4 },{"e" ,5 },{"f" ,6 }, {"g" ,7 },{"h" ,8 },{"i" ,9 },{"j" ,10},{"k" ,11},{"l" ,12},{"m" ,13},{"n" ,14},{"o" ,15},{"p" ,16}, {"q" ,17},{"r" ,18},{"s" ,19},{"t" ,20},{"u" ,21},{"v" ,22},{"w" ,23}, {"x" ,24}, {"y" ,25},{"z" ,26},{"A" ,27},{"B" ,28},{"C" ,29},{"D" ,30},{"E" ,31},{"F" ,32},            {"G" ,33},            {"H" ,34},{"I" ,35},{"J" ,36},{"K" ,37},{"L" ,38},{"M" ,39},{"N" ,40},{"O" ,41},{"P" ,42},{"Q" ,43},            {"R" ,44},            {"S" ,45},            {"T" ,46},            {"U" ,47},            {"V" ,48},            {"W" ,49},            {"X" ,50},            {"Y" ,51},{"Z" ,52},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
    public static void main(String[] args) throws IOException {
    Day3 instance = new Day3();

    int score = 0, score2 = 0;
    HelpClass help = new HelpClass();
    ArrayList<String> string_list = help.readFile("input_day3.txt");
    for(int i = 0; i<string_list.size(); i++){
        int middle = string_list.get(i).length()/2;
        score = score + instance.getScore(string_list.get(i).substring(0,middle), string_list.get(i).substring(middle));
        if(i%3 == 0) {
            score2 = score2 + instance.getScore(string_list.get(i), string_list.get(i + 1), string_list.get(i + 2));
        }
    }
    System.out.println("Answer problem 1: " + score);
    System.out.println("Answer problem 2: " + score2);
}

    private int getScore(String s1, String s2){
        for(String s: s1.split("")){
            if(s2.contains(s)){
                return points_map.get(s);
            }
        }
        return 0;
    }

    private int getScore(String s1, String s2, String s3){
        for(String s: s1.split("")){
            if(s2.contains(s) && s3.contains(s)){
                return points_map.get(s);
            }
        }
        return 0;
    }
}
