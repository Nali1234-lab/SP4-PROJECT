
public class Hold {
    private final String navn;
    private final String traener;
    private int point;

    public Hold(String navn, String traener) {
        this.navn = navn;
        this.traener = traener;
        this.point = 0;
    }

    public String getNavn() {
        return navn;
    }

    public String getTraener() {
        return traener;
    }

    public int getPoint() {
        return point;
    }

    public void tilfoejPoint(int point) {
        this.point += point;
    }
}


