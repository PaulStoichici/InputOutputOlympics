public class Athlete {
    private String number;
    private String name;
    private String countryCode;
    private String skiTimeResult;
    private int penalty;
    private String finalTimeResult;

    public Athlete(String number, String name, String countryCode, String skiTimeResult, int penalty) {
        this.number = number;
        this.name = name;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.penalty = penalty;
        this.finalTimeResult = calculateFinalTimeResult();
    }
    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }

    public int getPenalty() {
        return penalty;
    }

    public String getFinalTimeResult() {
        return finalTimeResult;
    }
    private String calculateFinalTimeResult() {
        String[] time = skiTimeResult.split(":");
        int minutes = Integer.parseInt(time[0]);
        int seconds = Integer.parseInt(time[1]);
        seconds += penalty;
        minutes += seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
