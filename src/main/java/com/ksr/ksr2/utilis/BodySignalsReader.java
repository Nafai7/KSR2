package com.ksr.ksr2.utilis;

import com.ksr.ksr2.model.BodySignals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BodySignalsReader {

    public static List<BodySignals> read(String fileName) {
        List<BodySignals> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fileName + ".csv"));
            int counter = 0;
            String line = br.readLine();
            while ((line = br.readLine()) != null && counter <= 10000) {
                String[] values = line.split(",");
                result.add(new BodySignals(Integer.valueOf(values[0]),
                        values[1],
                        Integer.valueOf(values[2]),
                        Double.valueOf(values[10]),
                        Double.valueOf(values[11]),
                        Double.valueOf(values[12]),
                        Double.valueOf(values[13]),
                        Double.valueOf(values[14]),
                        Double.valueOf(values[15]),
                        Double.valueOf(values[16]),
                        Double.valueOf(values[17]),
                        Double.valueOf(values[19]),
                        Double.valueOf(values[20]),
                        Boolean.valueOf(values[26])));
                counter++;
            }
        } catch (Exception ex) {
            System.err.print(ex);
        }

        return result;
    }
}
