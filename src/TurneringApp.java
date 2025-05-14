import java.util.ArrayList;
import java.util.Scanner;

public class TurneringApp {    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Hold> holdListe = new ArrayList<>();
    private final ArrayList<Turnering> turneringer = new ArrayList<>();

    public void start() {
        System.out.println("Velkommen til Fodboldturneringssystemet!");

        while (true) {
            System.out.println("\nHovedmenu:");
            System.out.println("1. Opret hold");
            System.out.println("2. Opret turnering");
            System.out.println("3. Registrer kampresultat");
            System.out.println("4. Vis alle hold");
            System.out.println("5. Vis alle turneringer");
            System.out.println("6. Afslut");
            System.out.print("Vælg en mulighed: ");

            int valg = scanner.nextInt();
            scanner.nextLine(); // Ryd buffer

            switch (valg) {
                case 1 -> opretHold();
                case 2 -> opretTurnering();
                case 3 -> registrerKamp();
                case 4 -> visHold();
                case 5 -> visTurneringer();
                case 6 -> {
                    System.out.println("Programmet afsluttes.");
                    return;
                }
                default -> System.out.println("Ugyldigt valg!");
            }
        }
    }

    private void opretHold() {
        System.out.print("Indtast holdnavn: ");
        String navn = scanner.nextLine();
        System.out.print("Indtast træner: ");
        String traener = scanner.nextLine();

        Hold nytHold = new Hold(navn, traener);
        holdListe.add(nytHold);
        System.out.println(navn + " er oprettet!");
    }

    private void opretTurnering() {
        System.out.print("Indtast turneringsnavn: ");
        String navn = scanner.nextLine();
        System.out.print("Vælg type (1=Point, 2=Knockout): ");
        int typeValg = scanner.nextInt();
        scanner.nextLine();

        Turnering turnering = new Turnering(navn, typeValg == 1 ? "Point" : "Knockout");
        turneringer.add(turnering);
        System.out.println(navn + " turnering oprettet!");
    }

    private void registrerKamp() {
        if (turneringer.isEmpty()) {
            System.out.println("Opret en turnering først!");
            return;
        }

        System.out.println("Vælg turnering:");
        for (int i = 0; i < turneringer.size(); i++) {
            System.out.println((i + 1) + ". " + turneringer.get(i).getNavn());
        }
        int turValg = scanner.nextInt() - 1;
        scanner.nextLine();

        Turnering valgtTur = turneringer.get(turValg);

        System.out.println("Vælg hjemmehold:");
        visHold();
        int hjemmeValg = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Vælg udehold:");
        visHold();
        int udeValg = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Mål for hjemmehold: ");
        int hjemmeMaal = scanner.nextInt();
        System.out.print("Mål for udehold: ");
        int udeMaal = scanner.nextInt();
        scanner.nextLine();

        Hold hjemmeHold = holdListe.get(hjemmeValg);
        Hold udeHold = holdListe.get(udeValg);

        if (valgtTur.getType().equals("Knockout")) {
            if (hjemmeMaal > udeMaal) {
                valgtTur.fjernHold(udeHold);
                System.out.println(udeHold.getNavn() + " er ude af turneringen!");
            } else if (udeMaal > hjemmeMaal) {
                valgtTur.fjernHold(hjemmeHold);
                System.out.println(hjemmeHold.getNavn() + " er ude af turneringen!");
            } else {
                System.out.println("Uafgjort - begge hold går videre");
            }
        } else {
            if (hjemmeMaal > udeMaal) {
                hjemmeHold.tilfoejPoint(3);
            } else if (udeMaal > hjemmeMaal) {
                udeHold.tilfoejPoint(3);
            } else {
                hjemmeHold.tilfoejPoint(1);
                udeHold.tilfoejPoint(1);
            }
            System.out.println("Point opdateret!");
        }
    }

    private void visHold() {
        if (holdListe.isEmpty()) {
            System.out.println("Ingen hold oprettet endnu!");
            return;
        }
        for (int i = 0; i < holdListe.size(); i++) {
            Hold hold = holdListe.get(i);
            System.out.println((i + 1) + ". " + hold.getNavn() + " (" + hold.getTraener() + ") - " + hold.getPoint() + " point");
        }
    }

    private void visTurneringer() {
        if (turneringer.isEmpty()) {
            System.out.println("Ingen turneringer oprettet endnu!");
            return;
        }
        for (Turnering tur : turneringer) {
            System.out.println("\n" + tur.getNavn() + " (" + tur.getType() + ")");
            System.out.println("Deltagende hold:");
            for (Hold hold : tur.getHold()) {
                System.out.println("- " + hold.getNavn());
            }
        }
    }
}


