import util.TextUI;

import java.util.*;

public class KnockoutTournament {

    private Scanner scanner = new Scanner(System.in);
    private Teams teams;

    public KnockoutTournament(Teams teams) {
        this.teams = teams;
    }

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
                System.out.println("\nKamp: " + team1 + " vs " + team2);
                int choice = -1;
                while (choice != 1 && choice != 2) {
                    System.out.println("Hvem vandt?\n1. " + team1 + "\n2. " + team2);
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

    public void runTeamKnockoutTournament() {
        Map<String, List<String>> teamData = teams.getTeams();
        
        List<Team> tournamentTeams = new ArrayList<>();
        for (String teamName : teamData.keySet()) {
            tournamentTeams.add(new Team(teamName));
        }

        Collections.shuffle(tournamentTeams);
        
        MatchNode root = buildTree(tournamentTeams);
        Team winner = playTournament(root, scanner);

        System.out.println("\n🏆 Vinderen af turneringen er: " + winner);
    }

    public void runSingleKnockoutTournament(String[] playerNames) {
        List<Team> tournamentTeams = new ArrayList<>();
        for (String playerName : playerNames) {
            tournamentTeams.add(new Team(playerName));
        }

        Collections.shuffle(tournamentTeams);
        
        MatchNode root = buildTree(tournamentTeams);
        Team winner = playTournament(root, scanner);

        System.out.println("\n🏆 Vinderen af turneringen er: " + winner);
    }

    public MatchNode buildTree(List<Team> teams) {
        Queue<MatchNode> queue = new LinkedList<>();

        for (int i = 0; i < teams.size(); i += 2) {
            Team team1 = teams.get(i);
            Team team2 = teams.get(i + 1);
            queue.add(new MatchNode(team1, team2));
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