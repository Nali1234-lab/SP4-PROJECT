
public class Team {
    private final String name;
    private int points;

    public Team(String name) {
        this.name = name;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void givePoints(int points) {
        this.points += points;
    }
}


