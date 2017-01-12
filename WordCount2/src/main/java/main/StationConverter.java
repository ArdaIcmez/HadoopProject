package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mathi on 12/01/2017.
 */
public class StationConverter {
    private static final Pattern REGEX_STATION = Pattern.compile("^([0-9]+);([^;]*);");
    private static Map<Integer,String> weatherStations = new HashMap<>();

    public static void prepareStations(FileReader file){
        try {
            BufferedReader br = new BufferedReader(file);
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                fillStations(line);
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("error Station parsing");
        }
    }
    private static void fillStations(String toParse){
        Matcher m = REGEX_STATION.matcher(toParse);
        if (m.find()) {
            weatherStations.put(Integer.parseInt(m.group(1)),m.group(2));
        }
    }

    public static String getStation(int idStation){
        return weatherStations.get(idStation);
    }
}
