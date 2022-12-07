package org.example;

import java.io.IOException;
import java.util.*;

public class Day7 {
    public static boolean test = false;
    public static void main(String[] args) throws IOException {

        HelpClass help = new HelpClass();
        ArrayList<String> string_list;
        if (test) {
            string_list = help.readFile("input1_day7.txt");
        } else {
            string_list = help.readFile("input_day7.txt");
        }
        Directory root = new Directory(null, "/");
        Directory current_dir = root;
        for (String s : string_list) {
            ArrayList<String> s_strings = new ArrayList<>(Arrays.asList(s.split(" ")));
            if (s_strings.get(0).equals("$")) {
                switch (s_strings.get(1)) {
                    case "cd":
                        if (!s_strings.contains("..")) {
                            if (s_strings.get(2).equals("/")) {
                                current_dir = root;
                            } else {
                                if (!current_dir.directories.containsKey(s_strings.get(2))) {
                                    current_dir.directories.put(s_strings.get(2), new Directory(current_dir, s_strings.get(2)));
                                } else {
                                    current_dir = current_dir.directories.get(s_strings.get(2));
                                }
                            }
                        } else{
                                current_dir = current_dir.parent;
                        }
                        break;
                    case "ls":
                        break;
                }
            }else if (s_strings.get(0).equals("dir")) {
                    if (!current_dir.directories.containsKey(s_strings.get(1))) {
                        current_dir.directories.put(s_strings.get(1), new Directory(current_dir, s_strings.get(1)));
                    }
            } else {
                current_dir.fileSizes.put(s_strings.get(1), Integer.parseInt(s_strings.get(0)));
            }
        }

        int amount_of_space_taken;
        amount_of_space_taken = getAmountOfSpaceTaken(root);

        int amount_of_space_to_free;
        amount_of_space_to_free = getAmountOfSpaceToFree(root);

        System.out.println("Answer problem 1: " + amount_of_space_taken);
        System.out.println("Answer problem 2: " + amount_of_space_to_free);
        }

        private static int getAmountOfSpaceToFree(Directory root){
            int unused = 30000000;
            int total = 70000000;
            int maxSpace = total - unused;
            List<Directory> largerFolders = root.foldersLargerThan(root.size() - maxSpace);
            List<Integer> large_sizes = new ArrayList<>();
            for(Directory dir: largerFolders){
                large_sizes.add(dir.size());
            }
            Collections.sort(large_sizes);
            return large_sizes.get(0);
        }

        private static int getAmountOfSpaceTaken(Directory root){
            List<Directory> smallerFolders = root.foldersSmallerThan(100000);
            int amount_of_space_taken = 0;
            for(Directory dir: smallerFolders){
                if(dir.size() <=100000){
                    amount_of_space_taken = amount_of_space_taken + dir.size();
                }
            }
            return amount_of_space_taken;
        }
    }


    class Directory {
        final String name_of_dir;
        Directory parent;
        private int size = -1;
        Map<String, Integer> fileSizes = new HashMap<>();
        Map<String, Directory> directories = new HashMap<>();

        Directory(Directory parent, String name_of_dir){
            this.parent = parent;
            this.name_of_dir = name_of_dir;
        }

        public int size(){
            if(this.size == -1){
                size = 0;
                for(Directory dir: directories.values()){
                    size += dir.size();
                }
                for(Integer fileSize: fileSizes.values()){
                    size += fileSize;
                }
            }
            return this.size;
        }

        public List<Directory> foldersSmallerThan(int maxSize) {
            List<Directory> smallFolders = new ArrayList<>();
            for (Directory subfolder : directories.values()) {
                if (subfolder.size() <= maxSize) {
                    smallFolders.add(subfolder);
                }
                smallFolders.addAll(subfolder.foldersSmallerThan(maxSize));
            }
            return smallFolders;
        }

        public List<Directory> foldersLargerThan(int minSize) {
            List<Directory> smallFolders = new ArrayList<>();
            for (Directory subfolder : directories.values()) {
                if (subfolder.size() >= minSize) {
                    smallFolders.add(subfolder);
                }
                smallFolders.addAll(subfolder.foldersLargerThan(minSize));
            }
            return smallFolders;
        }

    }


