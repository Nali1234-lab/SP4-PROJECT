import java.util.*;

public class Teams {
    private Scanner scan = new Scanner(System.in);
    private Map<String, List<String>> teams = new LinkedHashMap<>();
    private String[] playerNames;
    private Map<String, Integer> teamPoints = new LinkedHashMap<>();


    public Teams() {
    }

    public void assignRandomTeams(int numTeams) {
        for (int i = 1; i <= numTeams; i++) {
            String teamName = "Hold " + i;
            teams.put(teamName, new ArrayList<>());
            teamPoints.put(teamName, 0);
        }

        List<String> shuffled = new ArrayList<>(Arrays.asList(playerNames));

        Collections.shuffle(shuffled);

        int teamIndex = 1;
        for (String name : shuffled) {
            String teamName = "Hold " + teamIndex;
            teams.get(teamName).add(name);
            teamIndex = (teamIndex % numTeams) + 1;
        }
    }

    public void assignManualTeams(int numTeams) {
        for (int i = 1; i <= numTeams; i++) {
            String teamName = "Hold " + i;
            teams.put(teamName, new ArrayList<>());
            teamPoints.put(teamName, 0);
        }

        List<String> remainingPlayers = new ArrayList<>(Arrays.asList(playerNames));

        int currentTeam = 1;

        while (!remainingPlayers.isEmpty() && currentTeam <= numTeams) {
            String teamName = "Hold " + currentTeam;
            System.out.println("\nTilgængelige spillere: " + String.join(", ", remainingPlayers));
            System.out.println("Tilføj spillere til " + teamName + " (skriv 'næste hold' for at gå videre):");

            while (true) {
                System.out.print("Indtast spiller-navn: ");
                String input = scan.nextLine().trim();

                if (input.equalsIgnoreCase("næste hold")) {
                    currentTeam++;
                    break;
                } else if (remainingPlayers.contains(input)) {
                    teams.get(teamName).add(input);
                    remainingPlayers.remove(input);
                } else {
                    System.out.println("Ugyldigt navn eller allerede valgt. Prøv igen.");
                }

                if (remainingPlayers.isEmpty()) {
                    break;
                }
            }
        }
    }

    public void printTeams() {
        System.out.println("Holdopdeling:");
        for (Map.Entry<String, List<String>> entry : teams.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public Map<String, List<String>> getTeams() {
        return teams;
    }

    public Map<String, Integer> getTeamPoints() {
        return teamPoints;
    }
}


