import java.util.ArrayList;

class HvitRute extends Rute {


    HvitRute(int r, int k, Labyrint l) {
        super(r, k, l);
    }

    @Override
    public String toString() {
        return tegn;
    }

    String tegn = "   ";

    // Hvit rute skal gaa gjennom alle sine naboer og kalle metoden 'finn'
    // Ruten er besoekt derfor setter jeg den boolske variabelen 'besoekt' til true
    @Override
    void finn(Rute fra, ArrayList<Tuppel> sti) {
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        nySti.add(new Tuppel(radNr, kolNr));
        besoekt = true;
        tall = teller;
        teller++;
        // For aa at det ser bedre ut naar man skriver ut stegene programmet har tatt
        /*if (tall < 10) {
            tegn = " " + tall + " ";
        } else if (tall >= 100) {
            tegn = "" + tall;
        } else {
            tegn = " " + tall;
        }*/
        for (Rute nabo : naboer) {
                if (nabo != fra && !nabo.besoekt) {
                    nabo.finn(this, nySti);
                }
            }
    }

    @Override
    void endreTegn(String t) {
        tegn = t;
    }



}
