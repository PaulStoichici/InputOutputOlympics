import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\paul\\IdeaProjects\\InputOutputOlympics\\src\\biathlon_results.csv";
        String line = "";
        String csvSplitBy = ",";
        List<Athlete> athletes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] entry = line.split(csvSplitBy);
                String athleteNumber = entry[0];
                String athleteName = entry[1];
                String countryCode = entry[2];
                String skiTimeResult = entry[3];
                String firstShootingRange = entry[4];
                String secondShootingRange = entry[5];
                String thirdShootingRange = entry[6];
                int penalty = calculatePenalty(firstShootingRange) + calculatePenalty(secondShootingRange) + calculatePenalty(thirdShootingRange);

                Athlete athlete = new Athlete(athleteNumber, athleteName, countryCode, skiTimeResult, penalty);
                athletes.add(athlete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort athletes by final time result
        Collections.sort(athletes, new Comparator<Athlete>() {
            @Override
            public int compare(Athlete a1, Athlete a2) {
                return a1.getFinalTimeResult().compareTo(a2.getFinalTimeResult());
            }
        });

        // print the top 3 athletes
        System.out.println("Winner: " + athletes.get(0).getName() + " " + athletes.get(0).getFinalTimeResult());
        System.out.println("Runner-up: " + athletes.get(1).getName() + " " + athletes.get(1).getFinalTimeResult());
        System.out.println("Third Place: " + athletes.get(2).getName() + " " + athletes.get(2).getFinalTimeResult());
    }

    private static int calculatePenalty(String shootingRange) {
        int penalty = 0;
        for (char c : shootingRange.toCharArray()) {
            if (c == 'o') {
                penalty += 10;
            }
        }
        return penalty;
    }
}