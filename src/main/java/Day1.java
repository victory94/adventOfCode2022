import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) throws IOException{
        Day1 instance = new Day1();
        InputStream is = instance.getFileAsIOStream("input_day1.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        int i, index = 0;
        ArrayList<Integer> inList = new ArrayList<Integer>();
        String line;
        //Reads file with inputs, from resource folder
        while((line = br.readLine()) != null){
            //Empty line divides each elf
            if(line.isEmpty()){
                index++;
            }else{
                if(inList.size() == index){
                    inList.add(Integer.parseInt(line));
                }else{
                    int before_change = inList.get(index);
                    inList.set(index, before_change + Integer.parseInt(line));
                }
            }

        }
        br.close();
        isr.close();
        Collections.sort(inList);
        int elf_with_most_amount_of_calories = inList.get(inList.size() -1);
        System.out.println("Answer problem 1: " + elf_with_most_amount_of_calories);
        int calories_of_3_elves = inList.get(inList.size() -1) + inList.get(inList.size() -2) + inList.get(inList.size() -3);
        System.out.println("Answer problem 2: " + calories_of_3_elves);
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
