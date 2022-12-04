package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public static void main(String[] args) throws IOException {
        Day4 instance = new Day4();

        int score = 0, score2 = 0;
        HelpClass help = new HelpClass();
        ArrayList<String> string_list = help.readFile("input_day4.txt");
        for(String s : string_list){
            String[] strings = s.split(",");
            if(instance.isInSameRAnge(strings[0], strings[1], "same")){
                score++;
            }
            if(instance.isInSameRAnge(strings[0], strings[1], "overlap")){
                score2++;
            }
        }

        System.out.println("Answer problem 1: " + score);
        System.out.println("Answer problem 2: " + score2);
    }

    private boolean isInSameRAnge(String s1, String s2, String condition){
        List<Integer> array1 = Arrays.stream(s1.split("-")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> array2 = Arrays.stream(s2.split("-")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> range, range2;
        if ((array1.get(1) - array1.get(0)) <= (array2.get(1) - array2.get(0))){
            range = IntStream.rangeClosed(array1.get(0),array1.get(1)).boxed().collect(Collectors.toList());
            range2 = IntStream.rangeClosed(array2.get(0),array2.get(1)).boxed().collect(Collectors.toList());
        }else{
            range = IntStream.rangeClosed(array2.get(0),array2.get(1)).boxed().collect(Collectors.toList());
            range2 = IntStream.rangeClosed(array1.get(0),array1.get(1)).boxed().collect(Collectors.toList());
        }

        for (Integer i : range) {
            if (!range2.contains(i) && condition.equals("same")) {
                return false;
            }else if(range2.contains(i) && condition.equals("overlap")){
                return true;
            }
        }
        return condition.equals("same");
    }

}
