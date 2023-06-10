package TestInstance;


import Enchere.Participant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static List<Participant> readfile(String filePath) {
        int startLine = 8;
        Participant temp;
        List<Participant> tempList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip lines until the desired start line
            for (int i = 0; i < startLine - 1; i++) {
                br.readLine();
            }
            String line;
            while ((line = br.readLine()) != null && !line.equals("#")) {
                // Split the line by space
                String[] values = line.split("\\s+");
                temp = new Participant();
                // Process the values
                temp.montant = Double.parseDouble(values[1]);
                for (int i=2; i< values.length-1; i++) {
                    temp.listObject.add(Integer.parseInt(values[i]));
                }
                tempList.add(temp);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }
}

