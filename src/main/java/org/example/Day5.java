package org.example;
import java.io.IOException;
import java.util.*;

public class Day5 {
    public static Map<Integer, ArrayList<String>> stackMover9000 = new HashMap<>();
    public static Map<Integer, ArrayList<String>> stackMover9001 = new HashMap<>();
    public static Boolean test = false;
    public static void main(String[] args) throws IOException {
        Day5 instance = new Day5();

        HelpClass help = new HelpClass();
        ArrayList<String> string_list;
        if(test){
            string_list = help.readFile("input1_day5.txt");
        }else{
            string_list = help.readFile("input_day5.txt");
        }

        String last_top_crates = instance.crateMover9000(string_list);
        String last_top_scores_9001 = instance.crateMover9001(string_list);
        System.out.println("Answer problem 1: " + last_top_crates);
        System.out.println("Answer problem 2: " + last_top_scores_9001);
    }

    //For initial test data
    private static void createStackInput1(int stack_to_fill){
        Map<Integer, ArrayList<String>> stack = new HashMap<>();
        stack.put(1, new ArrayList<>(Arrays.asList("Z", "N")));
        stack.put(2,new ArrayList<>(Arrays.asList("M", "C","D")));
        stack.put(3, new ArrayList<>(Collections.singletonList("P")));
        if(stack_to_fill == 9000){
            stackMover9000 = stack;
        }else{
            stackMover9001 = stack;
        }
    }

    private static void createStackInput(int stack_to_fill){
        Map<Integer, ArrayList<String>> stack = new HashMap<>();
        stack.put(1, new ArrayList<>(Arrays.asList("V", "C", "D", "R", "Z", "G", "B", "W")));
        stack.put(2, new ArrayList<>(Arrays.asList("G", "W", "F", "C", "B", "S", "T", "V")));
        stack.put(3, new ArrayList<>(Arrays.asList("C","B", "S", "N", "W")));
        stack.put(4, new ArrayList<>(Arrays.asList("Q", "G", "M", "N", "J", "V", "C", "P")));
        stack.put(5, new ArrayList<>(Arrays.asList("T", "S", "L", "F", "D", "H", "B")));
        stack.put(6, new ArrayList<>(Arrays.asList("J", "V", "T", "W", "M", "N")));
        stack.put(7, new ArrayList<>(Arrays.asList("P", "F", "L", "C", "S", "T", "G")));
        stack.put(8, new ArrayList<>(Arrays.asList("B", "D","Z")));
        stack.put(9, new ArrayList<>(Arrays.asList("M", "N", "Z", "W")));
        if(stack_to_fill == 9000){
            stackMover9000 = stack;
        }else{
            stackMover9001 = stack;
        }
    }

    private static void moveStacks9000(int from, int to, int amount_of_boxes){
        for(int i = 0; i< amount_of_boxes; i++) {
            String box = stackMover9000.get(from).get(stackMover9000.get(from).size()-1);
            stackMover9000.get(to).add(box);
            stackMover9000.get(from).remove(stackMover9000.get(from).size()-1);
        }
    }

    private static void moveStacks9001(int from, int to, int amount_of_boxes){
        ArrayList<String> tempList = new ArrayList<>();
        for(int i = 0; i< amount_of_boxes; i++) {
            String box = stackMover9001.get(from).get(stackMover9001.get(from).size()-1);
            tempList.add(0, box);
            stackMover9001.get(from).remove(stackMover9001.get(from).size()-1);
        }
        stackMover9001.get(to).addAll(tempList);
    }

    private static String getTopCrates(int stackMover){
        StringBuilder topCrates = new StringBuilder();
        Map<Integer, ArrayList<String>> stack_to_check;
        if(stackMover == 9000){
            stack_to_check = stackMover9000;
        }else{
            stack_to_check = stackMover9001;
        }
        for(Integer key: stack_to_check.keySet()){
            topCrates.append(stack_to_check.get(key).get(stack_to_check.get(key).size() - 1));
        }
        return topCrates.toString();
    }


    private String crateMover9000(ArrayList<String> string_list){
        if(test){
            Day5.createStackInput1(9000);
        }else{
            Day5.createStackInput(9000);
        }

        for(String s : string_list){
            List<Integer> array_with_val = Day5.getMoveFromTo(s);
            Day5.moveStacks9000(array_with_val.get(1), array_with_val.get(2), array_with_val.get(0));
        }

        return Day5.getTopCrates(9000);
    }


    private String crateMover9001(ArrayList<String> string_list){
        if(test){
            Day5.createStackInput1(9001);
        }else{
            Day5.createStackInput(9001);
        }

        for(String s : string_list){
            List<Integer> array_with_val = Day5.getMoveFromTo(s);
            Day5.moveStacks9001(array_with_val.get(1), array_with_val.get(2), array_with_val.get(0));
        }

        return Day5.getTopCrates(9001);
    }

    private static List<Integer> getMoveFromTo(String s){
        List<Integer> int_array = new ArrayList<>();
        String[] split_string = s.split("\\s+");
        for(int i = 0; i<split_string.length; i++){
            switch (split_string[i]) {
                case "move":
                case "from":
                case "to":
                    int_array.add(Integer.parseInt(split_string[i + 1]));
                    break;
            }
        }
        return int_array;
    }
}
