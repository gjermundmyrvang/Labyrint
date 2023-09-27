import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Labyrint {

    // Initialiserer et todimensjonalt array som holder paa rute-objekter
    Rute[][] ruteObjekter;

    // initialiserer antall rader og kolonner som bestemmes av filen
    int antR, antK;

    // Tuppel-arrarlist
    ArrayList<Tuppel> sti = new ArrayList<>();

    ArrayList<ArrayList<Tuppel>> stier = new ArrayList<>();


    // konstruktoeren tar inn filnavn, kaller metoden som leser filen og oppretter
    // 2dim-arrayet.
    // deretter kaller den settNaboer metoden som gir enhver rute oversikt over sine 4naboer (nord, soer, oest, vest)
    Labyrint(String filnavn) {
        lesFil(filnavn);
        settNaboer();
        System.out.println(this);
    }

    // Metoden tar inn filnavn eller pathway til en fil
    // Oppretter foerst rutenettet basert paa foerste linje i filen som indikierer
    // rader og kolonner
    void lesFil(String f) {

        try {
            Scanner filSc = new Scanner(new File(f));
            antR = filSc.nextInt();
            antK = filSc.nextInt();
            ruteObjekter = new Rute[antR][antK];

            // iterer gjennom filen og oppretter rute-objekter basert paa tegn eller posisjon(aapning)
            for (int rx = 0; rx < antR; rx++) {
                String linje = filSc.next();
                for (int kx = 0; kx < antK; kx++) {
                    if (linje.charAt(kx) == '.' && erAapning(rx, kx)) {
                        ruteObjekter[rx][kx] = new Aapning(rx, kx, this);
                    } else if (linje.charAt(kx) == '#') {
                        ruteObjekter[rx][kx] = new SortRute(rx, kx, this);
                    } else {
                        ruteObjekter[rx][kx] = new HvitRute(rx, kx, this);
                    }
                }
            }

            filSc.close();

        } catch (FileNotFoundException e) {
            System.exit(0);
        }

    }

    // Metoden itererer gjennom alle rutenen i 2dim-arrayet og
    // for hver rute finner den rutens naboer ved hjelp av en hjelpemetode 'hentRute'
    // deretter sjekker om det faktisk er en rute og hvis det er det så legger den til ruten
    // i nabo-array ved hjelp av 'leggTilNabo' metoden
    void settNaboer() {
        for (int rx = 0; rx < antR; rx++) {
            for (int kx = 0; kx < antK; kx++) {
                Rute rute = ruteObjekter[rx][kx];

                Rute[] naboer = {hentRute(rx-1, kx), hentRute(rx+1, kx),
                                 hentRute(rx, kx+1), hentRute(rx, kx-1)};

                for (Rute nabo:naboer) {
                    if (rute != null) {
                        rute.leggTilNabo(nabo);
                    }
                }
            }
        }
    }

    Rute hentRute(int r, int k) {
        if (r < 0 || k < 0) {
            return null;
        } else if (r >= ruteObjekter.length) {
            return null;
        } else if (k >= ruteObjekter[r].length) {
            return null;
        } else return ruteObjekter[r][k];
    }

    // hjelpemetode for aa si om en gitt posisjon er en aapning
    boolean erAapning(int r, int k) {
        return r == 0 || r == antR-1 || k == 0 || k == antK-1;
    }


    // 
    ArrayList<ArrayList<Tuppel>> finnUtveiFra(int rad, int kol) {
        Rute start = ruteObjekter[rad][kol];
        if (start.toString()=="#") {
            System.out.println("Kan ikke starte paa sort rute!");
        } else {
            start.finn(null, sti);
        }

        return stier;
    }

    void leggTilTuppel(ArrayList<Tuppel> stiTilUtvei) {
        stier.add(stiTilUtvei);
    }

    void endreTegn(int r, int k) {
        Rute rute = hentRute(r, k);
        rute.endreTegn(" . ");
    }




    // Overskriver toString slik at hver linje blir skrevet ut korrekt
    @Override
    public String toString() {
        String streng = "";
        for (int rx = 0; rx < antR; rx++) {
            for (int kx = 0; kx < antK; kx++) {
                streng += ruteObjekter[rx][kx];
            }
            streng += "\n";
        }
        return streng;
    }

    // Denne metoden soerger for at den boolske variabelen 'besoekt' settes til
    // false igjen, dersom man skal kjoere programmet flere ganger
   /* void nullstill() {
        for (int rx = 0; rx < antR; rx++) {
            for (int kx = 0; kx < antK; kx++) {
                ruteObjekter[rx][kx].besoekt = false;
            }
        }

    }*/

}
