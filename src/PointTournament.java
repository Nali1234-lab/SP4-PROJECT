import util.TextUI;

import java.util.*;

public class PointTournament {
    private TextUI ui = new TextUI();
    private Teams teams = new Teams();

    public void startSinglePointTournament(String[] playerNames) {
        Map<String, Integer> pointTable = new HashMap<>();
        for (String name : playerNames) {
            pointTable.put(name, 0);
        }

        List<String[]> matches = new ArrayList<>();
        for (int i = 0; i < playerNames.length - 1; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                matches.add(new String[]{playerNames[i], playerNames[j]});
            }
        }

        Collections.shuffle(matches);


        for (String[] match : matches) {
            String player1 = match[0];
            String player2 = match[1];

            System.out.println("\nKamp: " + player1 + " vs " + player2);
            System.out.println("Hvem vandt?\n1. " + player1 + "\n2. " + player2);


            while(true) {
                int winner = ui.promptNumeric();

                if (winner == 1) {
                    pointTable.put(player1, pointTable.get(player1) + 1);
                    break;
                } else if (winner == 2) {
                    pointTable.put(player2, pointTable.get(player2) + 1);
                    break;
                } else {
                    System.out.println("Ugyldigt valg – skriv venligst hvem der vandt.");
                }
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(pointTable.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nSlutstilling:");
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.printf("%-12s | %2d point\n", entry.getKey(), entry.getValue());
        }
    }

    public void startTeamPointTournament() {
        Map<String, List<String>> team = teams.getTeams();
        Map<String, Integer> pointTable = teams.getTeamPoints();

        List<String[]> matches = new ArrayList<>();
        List<String> teamNames = new ArrayList<>(team.keySet());

        for (int i = 0; i < teamNames.size() - 1; i++) {
            for (int j = i + 1; j < teamNames.size(); j++) {
                matches.add(new String[]{teamNames.get(i), teamNames.get(j)});
            }
        }

        Collections.shuffle(matches);

        for (String[] match : matches) {
            String team1 = match[0];
            String team2 = match[1];

            System.out.println("\nKamp: " + team1 + " vs " + team2);
            System.out.println("Hvem vandt?\n1. " + team1 + "\n2. " + team2);

            while (true) {
                int winner = ui.promptNumeric();

                if (winner == 1) {
                    pointTable.put(team1, pointTable.get(team1) + 1);
                    break;
                } else if (winner == 2) {
                    pointTable.put(team2, pointTable.get(team2) + 1);
                    break;
                } else {
                    System.out.println("Ugyldigt valg. Prøv igen.");
                }
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(pointTable.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nSlutstilling:");
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.printf("%-12s | %2d point\n", entry.getKey(), entry.getValue());
        }
    }
}
