package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
    public static boolean test = false;
    public static void main(String[] args) throws IOException {

        HelpClass help = new HelpClass();
        ArrayList<String> string_list;
        if(test){
            string_list = help.readFile("input1_day6.txt");
        }else{
            string_list = help.readFile("input_day6.txt");
        }
        int amount_of_letters = get_answer_list(string_list, 4);
        int amount_of_letters2 = get_answer_list(string_list, 14);

        System.out.println("Answer problem 1: " + amount_of_letters);
        System.out.println("Answer problem 2: " + amount_of_letters2);
    }

    private static boolean check_if_repeat(ArrayList<String> s_list){
        Set<String> set = new HashSet<>(s_list);
        return s_list.size() == set.size();
    }

    private static ArrayList<String> addToList(int start_pos, int amount_of_string, String string1){
        ArrayList<String> list = new ArrayList<>();
        for(int i =start_pos; i>start_pos - amount_of_string; i--){
            list.add(Character.toString(string1.charAt(i)));
        }
        return list;
    }

    private static int get_answer_list(ArrayList<String> string_list, int length_of_string_check){
        for(String s: string_list){
            for(int i = 0; i<s.length(); i++){
                if (i> length_of_string_check -1){
                    ArrayList<String> s_list = addToList(i, length_of_string_check, s);
                    boolean no_repeat = check_if_repeat(s_list);
                    if(no_repeat){
                        return i +1;
                    }
                }
            }
        }
        return 0;

    }
}
