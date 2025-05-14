import java.util.ArrayList;


public class Tournament { private final String name;
    private final String type;
    private final ArrayList<Team> team = new ArrayList<>();

    public Tournament(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Team> getHold() {
        return team;
    }

    public void addTeam(Team team) {
        this.team.add(team);
    }

    public void removeTeam(Team team) {
        this.team.remove(team);
    }
}
