import util.TextUI;

import java.util.*;

public class KnockoutTournament {

    private TextUI ui = new TextUI();
    private Teams t = new Teams();
    Players players = new Players(t);

    public static class Team {
        String name;


        Team(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class MatchNode {
        Team team1;
        Team team2;
        MatchNode left;
        MatchNode right;
        Team winner;

        MatchNode(Team team1, Team team2) {
            this.team1 = team1;
            this.team2 = team2;
        }

        void playMatch(Scanner scanner) {
            if (team1 != null && team2 != null) {
                System.out.println("Match: " + team1 + " vs " + team2);
                int choice = -1;
                while (choice != 1 && choice != 2) {
                    System.out.print("Vælg vinder – skriv 1 for " + team1 + " eller 2 for " + team2 + ": ");
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldigt input. Prøv igen.");
                    }
                }
                winner = (choice == 1) ? team1 : team2;
            } else {
                winner = (team1 != null) ? team1 : team2;
            }
            System.out.println("→ Vinder: " + winner + "\n");
        }
    }

    public void runTournament() {
        Scanner scanner = new Scanner(System.in);
        List<Team> teams = new ArrayList<>();

        /*for (int i = 1; i <= players.getCount(); i++) {
            System.out.print("Navn på hold " + i + ": ");
            String name = scanner.nextLine();
            teams.add(new Team(name));
        }
        System.out.println(players.getCount());*/

        if (players.isPlayWithTeam()) {
            // Hvis der spilles med hold, brug holdnavne fra Teams-klassen
            for (int i = 0; i < players.getCount(); i++) {
                System.out.print("Navn på hold " + i + ": ");
                String name = scanner.nextLine();
                teams.add(new Team(name));
            }
        } else {

            for (String playerName : players.getPlayerNames()) {
                teams.add(new Team(playerName));
            }
        }

        MatchNode root = buildTree(teams);
        Team vinder = playTournament(root, scanner);

        System.out.println("\n🏆 Vinderen af turneringen er: " + vinder);
    }

    public MatchNode buildTree(List<Team> teams) {
        Queue<MatchNode> queue = new LinkedList<>();

        for (int i = 0; i < teams.size(); i += 2) {
            queue.add(new MatchNode(teams.get(i), teams.get(i + 1)));
        }

        while (queue.size() > 1) {
            MatchNode left = queue.poll();
            MatchNode right = queue.poll();
            MatchNode parent = new MatchNode(null, null);
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }

        return queue.poll();
    }

    public Team playTournament(MatchNode node, Scanner scanner) {
        if (node == null) return null;

        if (node.left != null && node.right != null) {
            node.team1 = playTournament(node.left, scanner);
            node.team2 = playTournament(node.right, scanner);
        }

        node.playMatch(scanner);
        return node.winner;
    }
}