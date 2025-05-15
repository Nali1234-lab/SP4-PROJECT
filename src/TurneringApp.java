import java.util.ArrayList;
import java.util.Scanner;

import util.TextUI;

public class TurneringApp {
    private TextUI ui = new TextUI();
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Team> teamList = new ArrayList<>();
    private final ArrayList<Tournament> tournaments = new ArrayList<>();

    public void start() {
        System.out.println("Velkommen til TMS");

        while (true) {
            System.out.println("\nHovedmenu:");
            System.out.println("-------------------------");
            System.out.println("1. Opret hold");
            System.out.println("2. Opret turnering");
            System.out.println("3. Registrer kampresultat");
            System.out.println("4. Vis alle hold");
            System.out.println("5. Vis alle turneringer");
            System.out.println("6. Afslut");
            System.out.println("Vælg en mulighed: ");

            int valg = scanner.nextInt();
            scanner.nextLine(); // Ryd buffer

            switch (valg) {
                case 1 -> buildTeam();
                case 2 -> buildTournament();
                case 3 -> matchRegistration();
                case 4 -> showTeams();
                case 5 -> showTournament();
                case 6 -> {
                    System.out.println("Programmet afsluttes.");
                    System.exit(0);
                }
                default -> System.out.println("Ugyldigt valg!");
            }
        }
    }

    private void buildTeam() {
        System.out.println("Indtast holdnavn: ");
        String name = scanner.nextLine();

        Team newTeam = new Team(name);
        teamList.add(newTeam);
        System.out.println(name + " er oprettet!");
    }

    private void buildTournament() {
        System.out.println("Indtast turneringsnavn: ");
        String name = scanner.nextLine();
        System.out.println("Vælg type (1=Point, 2=Knockout): ");
        int chooseType = scanner.nextInt();
        scanner.nextLine();

        Tournament tournament = new Tournament(name, chooseType == 1 ? "Point" : "Knockout");
        tournaments.add(tournament);
        System.out.println(name + " turnering oprettet!");
    }

    private void matchRegistration() {
        if (tournaments.isEmpty()) {
            System.out.println("Opret en turnering først!");
            return;
        }

        System.out.println("Vælg turnering:");
        for (int i = 0; i < tournaments.size(); i++) {
            System.out.println((i + 1) + ". " + tournaments.get(i).getName());
        }

        while (true) {

            int tourChoice = ui.promptNumeric() - 1;

            if (tourChoice < tournaments.size() && tourChoice >= 0) {

                Tournament chooseTour = tournaments.get(tourChoice);
                ArrayList<Team> knockoutTeams = teamList;

                System.out.println("Vælg hjemmehold:");
                showTeams();
                int homeChoice = ui.promptNumeric() - 1;

                System.out.println("Vælg udehold:");
                showTeamsExcluding(homeChoice);
                int awayChoice = ui.promptNumeric() - 1;

                if (chooseTour.getType().equals("Knockout")) {
                    System.out.println("Hvem tabte:");
                    System.out.println("1. " + knockoutTeams.get(homeChoice).getName() + "\n" + "2. " +knockoutTeams.get(awayChoice).getName());                    int whoLost = ui.promptNumeric() - 1;
                    chooseTour.removeTeam(knockoutTeams.get(whoLost));
                    System.out.println(knockoutTeams.get(whoLost).getName() + " er ude af turneringen!");
                    knockoutTeams.remove(whoLost);

                } else {
                    System.out.println("Hvem vandt:");
                    System.out.println("1. " + teamList.get(homeChoice).getName() + "\n" + "2. " +teamList.get(awayChoice).getName());
                    int whoWon = ui.promptNumeric() - 1;
                    teamList.get(whoWon).givePoints(1);
                    System.out.println("Point opdateret!");
                }
                break;
            } else {
                System.out.println("Ugyldigt nummer.. prøv igen");
            }
        }
    }

    private void showTeams() {
        if (teamList.isEmpty()) {
            System.out.println("Ingen hold oprettet endnu!");
            return;
        }
        for (int i = 0; i < teamList.size(); i++) {
            System.out.println((i + 1) + ". " + teamList.get(i).getName());
        }
    }

    public void showTeamsExcluding(int excludedIndex) {
        for (int i = 0; i < teamList.size(); i++) {
            if (i != excludedIndex) {
                System.out.println((i + 1) + ". " + teamList.get(i).getName());
            }
        }
    }

    private void showTournament() {
        if (tournaments.isEmpty()) {
            System.out.println("Ingen turneringer oprettet endnu!");
            return;
        }
        for (Tournament tour : tournaments) {
            System.out.println("\n" + tour.getName() + " (" + tour.getType() + ")");
            System.out.println("Deltagende hold:");
            for (Team team : tour.getHold()) {
                System.out.println("- " + team.getName());
            }
        }
    }

    private void footballSystem() {
         /*System.out.println("Mål for hjemmehold: ");
                int homeGoal = scanner.nextInt();
                System.out.println("Mål for udehold: ");
                int awayGoal = scanner.nextInt();
                scanner.nextLine();

                Team homeTeam = teamList.get(homeChoice);
                Team awayTeam = teamList.get(awayChoice);

                if (chooseTour.getType().equals("Knockout")) {
                    if (homeGoal > awayGoal) {
                        chooseTour.removeTeam(awayTeam);
                        System.out.println(awayTeam.getName() + " er ude af turneringen!");
                    } else if (awayGoal > homeGoal) {
                        chooseTour.removeTeam(homeTeam);
                        System.out.println(homeTeam.getName() + " er ude af turneringen!");
                    } else {
                        System.out.println("Uafgjort - begge hold går videre");
                    }
                } else {
                    if (homeGoal > awayGoal) {
                        homeTeam.givePoints(3);
                    } else if (awayGoal > homeGoal) {
                        awayTeam.givePoints(3);
                    } else {
                        homeTeam.givePoints(1);
                        awayTeam.givePoints(1);
                    }
                    System.out.println("Point opdateret!");
                }*/
    }
}


