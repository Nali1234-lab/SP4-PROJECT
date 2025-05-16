import util.TextUI;

public class Tournament {

    private TextUI ui = new TextUI();
    private Teams teams = new Teams();
    private Players player = new Players(teams);
    private PointTournament pt = new PointTournament();
    private KnockoutTournament kn = new KnockoutTournament();

    public Tournament() {

    }

    public void buildTournament() {
        System.out.println("Vælg tuneringstype:\n1. Point System\n2. Knockout System");
        while (true) {
            int chooseType = ui.promptNumeric();

            if (chooseType == 1) {
                player.playersPoints("Hvor mange spillere vil du have?");
                pointSystem();
                break;
            } else if (chooseType == 2) {
                player.playersKnockout("Hvor mange spillere vil du have?");
                knockoutSystem();
                break;
            } else {
                System.out.println("Vælg venligst en af systemerne");
            }
        }
    }

    public void knockoutSystem() {

        while (true) {
            System.out.println("\nHovedmenu:");
            System.out.println("-------------------");
            System.out.println("1. Start turnering");
            System.out.println("2. Vis spillere");
            System.out.println("3. Vis hold");
            System.out.println("4. Afslut");
            System.out.println("Vælg en mulighed: ");

            int choose = ui.promptNumeric();

            switch (choose) {
                case 1:
                    if(player.isPlayWithTeam()){
                        kn.runTournament();
                    } else {
                        pt.startSinglePointTournament(player.getPlayerNames());
                    }
                    break;
                case 2:
                    for (String name : player.getPlayerNames()) {
                        System.out.println("- " + name);
                    }
                    break;
                case 3:
                    teams.printTeams();
                    break;
                case 4:
                    System.out.println("Programmet afsluttes.");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt valg!");
            }
        }
    }

    public void pointSystem() {

        while (true) {
            System.out.println("\nHovedmenu:");
            System.out.println("-------------------");
            System.out.println("1. Start turnering");
            System.out.println("2. Vis spillere");
            System.out.println("3. Vis hold");
            System.out.println("4. Afslut");
            System.out.println("Vælg en mulighed: ");

            int choose = ui.promptNumeric();

            switch (choose) {
                case 1:
                    if(player.isPlayWithTeam()){
                        pt.startTeamPointTournament();
                    } else {
                        pt.startSinglePointTournament(player.getPlayerNames());
                    }
                    break;
                case 2:
                    for (String name : player.getPlayerNames()) {
                        System.out.println("- " + name);
                    }
                break;
                case 3:
                    teams.printTeams();
                    break;
                case 4:
                    System.out.println("Programmet afsluttes.");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt valg!");
            }
        }
    }
}