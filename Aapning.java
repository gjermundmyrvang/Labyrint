import java.util.ArrayList;

class Aapning extends HvitRute {

    Aapning(int r, int k, Labyrint l) {
        super(r, k, l);
    }

    String tegn = "   ";
    @Override
    public String toString() {
        return tegn;
    }

    @Override
    void finn(Rute fra, ArrayList<Tuppel> sti) {
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        nySti.add(new Tuppel(radNr, kolNr));
        tall = teller;
        teller++;
        /*if (tall < 10) {
            tegn = " " + tall + " ";
        } else if (tall >= 100) {
            tegn = "" + tall;
        } else {
            tegn = " " + tall;
        }*/
        // Programmet har funnet en aapning, skriver ut posisjonen til aapningen.
        System.out.println("(" + radNr + ", " + kolNr + ")");

        lab.leggTilTuppel(nySti);
    }

    @Override
    void endreTegn(String t) {
        tegn = t;
    }







}
