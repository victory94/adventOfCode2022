package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day8 {
    public static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    public static boolean test = false;
    public static void main(String[] args) throws IOException {

        HelpClass help = new HelpClass();
        ArrayList<String> string_list;
        if(test){
            string_list = help.readFile("input1_day8.txt");
        }else{
            string_list = help.readFile("input_day8.txt");
        }
        createMatrix(string_list);
        ArrayList<Integer> seenTrees = getSeenTrees();
        ArrayList<Integer> scenicTrees = getScenicTrees();
        int amount_of_trees_seen = seenTrees.size();
        Collections.sort(scenicTrees);
        int largest_scenic_amount = scenicTrees.get(scenicTrees.size()-1);

        System.out.println("Answer problem 1: " + amount_of_trees_seen);
        System.out.println("Answer problem 2: " + largest_scenic_amount);
    }

    public static void createMatrix(ArrayList<String> strings){
        for(String s: strings){
            matrix.add(new ArrayList<>(Arrays.stream(s.split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())));
        }
    }

    public static ArrayList<Integer> getSeenTrees(){
        ArrayList<Integer> seen_trees = new ArrayList<>();
        for(int i = 0; i<matrix.size(); i++){
            for(int j = 0; j<matrix.get(i).size(); j++){
                if(i == 0 || i == matrix.size() -1 || j == 0|| j == matrix.get(i).size() -1){
                    seen_trees.add(matrix.get(i).get(j));
                }else{
                    if(canSeeTree(i,j)){
                        seen_trees.add(matrix.get(i).get(j));
                    }
                }
            }
        }

        return seen_trees;
    }

    public static ArrayList<Integer> getScenicTrees(){
        ArrayList<Integer> seen_trees = new ArrayList<>();
        for(int i = 0; i<matrix.size(); i++){
            for(int j = 0; j<matrix.get(i).size(); j++){
                if(i != 0 || i != matrix.size() -1 || j != 0|| j != matrix.get(i).size() -1){
                    seen_trees.add(getScenicScore(i,j));
                }
            }
        }
        return seen_trees;
    }

    public static boolean canSeeTree(int row, int column){
        int size_to_compare = matrix.get(row).get(column);
        boolean visible_from_a_direction = false;
        //checks rows above and below
        for(int i = 0; i< matrix.size(); i++){
            if(i != row){
                if(matrix.get(i).get(column) < size_to_compare){
                    visible_from_a_direction = true;
                }else{
                    visible_from_a_direction = false;
                    if(i > row){
                        break;
                    }else{
                        i = row;
                    }
                }
            }else{
                if(visible_from_a_direction){
                    break;
                }
            }
        }
        if (visible_from_a_direction){
            return true;
        }

        //checks column before and after
        for(int i = 0; i< matrix.get(row).size(); i++){
            if(i != column){
                if(matrix.get(row).get(i) < size_to_compare){
                    visible_from_a_direction = true;
                }else{
                    visible_from_a_direction = false;
                    if(i > column){
                        break;
                    }else{
                        i = column;
                    }
                }
            }else{
                if(visible_from_a_direction){
                    break;
                }
            }
        }
        return visible_from_a_direction;
    }

    public static int getScenicScore(int row, int column){
        int score = 1;
        int amount_of_trees = 0;
        int size_to_compare = matrix.get(row).get(column);

        //check from row, forwards
        for(int i = row + 1; i< matrix.size(); i++){
            amount_of_trees ++;
            if(matrix.get(i).get(column) >= size_to_compare){
                break;
            }
        }
        score = score * amount_of_trees;
        amount_of_trees = 0;
        //check from row, backwards
        for(int i = row - 1; i >= 0; i--){
            amount_of_trees++;
            if(matrix.get(i).get(column) >= size_to_compare){
                break;
            }
        }
        score = score * amount_of_trees;
        amount_of_trees = 0;
        //checks column before and after
        //check from column, forwards
        for(int i = column + 1; i< matrix.get(row).size(); i++){
            amount_of_trees ++;
            if(matrix.get(row).get(i) >= size_to_compare){
                break;
            }
        }
        score = score * amount_of_trees;
        amount_of_trees = 0;
        //check from column, backwards
        for(int i = column - 1; i >= 0; i--){
            amount_of_trees ++;
            if(matrix.get(row).get(i) >= size_to_compare){
                break;
            }
        }

        return score * amount_of_trees;
    }
}
