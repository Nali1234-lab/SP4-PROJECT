public class StartMenu {

    private String tournamentName;

    public StartMenu(String name) {
        this.tournamentName = name;

    }

    public void startSession() {

        System.out.println("Velkommen til " + this.tournamentName);
        new Tournament().buildTournament();
    }
}
