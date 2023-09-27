import java.util.ArrayList;

class SortRute extends Rute {

    SortRute(int r, int k, Labyrint l) {
        super(r, k, l);
    }

    @Override
    public String toString() {
        return " # ";
    }

    // Sort rute skal ikke gjoere noe
    @Override
    void finn(Rute fra, ArrayList<Tuppel> sti) {}

}
