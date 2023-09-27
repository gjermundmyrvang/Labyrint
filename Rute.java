import java.util.ArrayList;

abstract class Rute {

    int radNr, kolNr;
    Labyrint lab;
    Rute[] naboer;
    boolean besoekt;
    static int teller = 1;
    int tall;


    Rute(int r, int k, Labyrint l) {
        radNr = r;
        kolNr = k;
        lab = l;
        naboer = new Rute[4];
        besoekt = false;
    }

    // Tar inn en nabo av Klassen Rute og legger den til i array over naboer
    // Skal bare legge til en nabo derfor sier jeg break dersom den finner en plass
    // i array som er null
    public void leggTilNabo(Rute nabo) {
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] == null) {
                naboer[i] = nabo;
                break;
            }
        }
    }

    void endreTegn(String t) {

    }

    // Ettersom alle de hvit-, sort- og aapningsrutene skal implementere
    // denne metoden forskjellig oppretter jeg en abstrakt metode
    abstract void finn(Rute fra, ArrayList<Tuppel> sti);
}
