package main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mathi on 12/01/2017.
 */
public class StationConverter {
    private static final Pattern REGEX_STATION = Pattern.compile("^([0-9]+);([^;]*);");
    private static Map<String,String> weatherStations = new HashMap<>();

    public static void prepareStations(String postesName){
        InputStream inputStream = StationConverter.class.getClassLoader()
                .getResourceAsStream(postesName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            reader.readLine();
            while((line = reader.readLine()) != null) {
                fillStations(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillStations(String toParse){
        Matcher m = REGEX_STATION.matcher(toParse);
        if (m.find()) {
            weatherStations.put(m.group(1),m.group(2));
        }
    }

    public static String getStation(String idStation){
        return weatherStations.get(idStation);
    }
}
