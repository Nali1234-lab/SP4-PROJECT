import util.TextUI;

import java.util.Scanner;

public class Players {
    private Scanner scan = new Scanner(System.in);
    private TextUI ui = new TextUI();
    private String[] playerNames;
    private Teams teams;
    private boolean playWithTeam = false;
    private int count;

    public Players(Teams teams) {
        this.teams = teams;
    }



    public void playersKnockout(String msg) {
        int numPlayers = ui.promptNumeric(msg);
        playerNames = new String[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Indtast navn på spiller " + (i + 1) + ": ");
            playerNames[i] = scan.nextLine();
        }

        System.out.println("Skal spillerne opdeles i hold? (ja/nej): ");
        boolean loop = true;

        while (loop) {
            String choice = scan.nextLine().trim().toLowerCase();
            if (choice.equals("ja")) {
                playWithTeam = true;
                loop = false;

                while (true) {
                    int numTeams = ui.promptNumeric("Hvor mange hold skal der være?");
                    setCount(numTeams);

                    if (numTeams < 2 || numTeams % 2 != 0) {
                        System.out.println("Fejl: Du skal indtaste et lige tal større end 1.");
                    } else {
                        teams.setPlayerNames(playerNames);

                        System.out.println("Skal holdene vælges tilfældigt? (ja/nej): ");

                        while (true) {
                            String randomChoice = scan.nextLine().trim().toLowerCase();
                            if (randomChoice.equals("ja")) {
                                teams.assignRandomTeams(numTeams);
                                break;
                            } else if (randomChoice.equals("nej")) {
                                teams.assignManualTeams(numTeams);
                                break;
                            } else {
                            }
                        }
                        teams.printTeams();
                        break;
                    }
                }

            } else if (choice.equals("nej")) {
                loop = false;
                setCount(numPlayers);
                System.out.println("Alle spiller individuelt:");
                for (String name : playerNames) {
                    System.out.println("- " + name);
                }
            } else {
            }
        }
    }

    public void playersPoints(String msg) {
        int numPlayers = ui.promptNumeric(msg);
        playerNames = new String[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Indtast navn på spiller " + (i + 1) + ": ");
            playerNames[i] = scan.nextLine();
        }

        System.out.println("Skal spillerne opdeles i hold? (ja/nej): ");
        boolean loop = true;

        while (loop) {
            String choice = scan.nextLine().trim().toLowerCase();
            if (choice.equals("ja")) {
                playWithTeam = true;
                loop = false;
                int numTeams = ui.promptNumeric("Hvor mange hold skal der være?");
                teams.setPlayerNames(playerNames);

                System.out.println("Skal holdene vælges tilfældigt? (ja/nej): ");

                while (true) {
                    String randomChoice = scan.nextLine().trim().toLowerCase();
                    if (randomChoice.equals("ja")) {
                        teams.assignRandomTeams(numTeams);
                        break;
                    } else if (randomChoice.equals("nej")) {
                        teams.assignManualTeams(numTeams);
                        break;
                    } else {
                    }
                }
                teams.printTeams();

            } else if (choice.equals("nej")) {
                loop = false;
                System.out.println("Alle spiller individuelt:");
                for (String name : playerNames) {
                    System.out.println("- " + name);
                }
            } else {
            }
        }
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public Teams getTeams() {
        return teams;
    }

    public boolean isPlayWithTeam() {
        return playWithTeam;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
