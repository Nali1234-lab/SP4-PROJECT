import java.util.ArrayList;


public class Turnering { private final String navn;
    private final String type;
    private final ArrayList<Hold> hold = new ArrayList<>();

    public Turnering(String navn, String type) {
        this.navn = navn;
        this.type = type;
    }

    public String getNavn() {
        return navn;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Hold> getHold() {
        return hold;
    }

    public void tilfoejHold(Hold hold) {
        this.hold.add(hold);
    }

    public void fjernHold(Hold hold) {
        this.hold.remove(hold);
    }
}
