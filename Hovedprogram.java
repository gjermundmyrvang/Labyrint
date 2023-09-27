import java.util.ArrayList;
import java.util.Scanner;

class Hovedprogram {

    public static void main(String[] args) {
        // filnavn/pathway til fil
        String filnavn = args[0];
        // oppretter en instans av klassen labyrint
        //Labyrint lab = new Labyrint(filnavn);

        // Soerger for at bruker gir inn filnavn eller pathway til fil
        if (args.length < 1) {
            System.out.println("\nOppgi hele filbanen til filen");
            System.out.println("Feks: java Test /path/to/filnavn.in\n");
            return;
        }

        // Skriver ut labyrinten slik at bruker kan se hvordan den ser ut
        // toString soerger for at utskriften ser bra ut
        System.out.println("\nSaann ser labyrinten ut:");
        //System.out.println("\n" + lab);
        Labyrint lab = new Labyrint(filnavn);

        // Oppretter en scanner til å lese gjennom en fil
        Scanner input = new Scanner(System.in);

        // Lokke kjorer til bruker skriver '-1'
        System.out.println("\nSkriv inn koordinater 'rad' 'kolonne' ('-1' for aa avslutte)");
        String linje = input.nextLine();;
        while (!linje.equalsIgnoreCase("-1")) {

            int r, k;
            String[] bit = linje.split(" ");
            while (bit.length != 2) {
                System.out.println("Feil format! Skriv slik: 'radnr' 'kolnr'");
                linje = input.nextLine();
                bit = linje.split(" ");
            }
            r = Integer.parseInt(bit[0]);
            k = Integer.parseInt(bit[1]);


            System.out.println("\nAapninger:");
            lab.finnUtveiFra(r, k);
            // Droppet metoden fordi jeg valgte aa at labyrinten skrives ut fra konstruktoeren
            //lab.nullstill();




            ArrayList<Tuppel> raskesteUtvei = null;
            if (!lab.stier.isEmpty()) {
                raskesteUtvei = lab.stier.get(0);
            }

            System.out.println("\nUtveier:");
            int teller = 1;
            for (ArrayList<Tuppel> t: lab.stier) {
                if (t.size() < raskesteUtvei.size()) {
                    raskesteUtvei = t;
                }
                System.out.println();
                System.out.println("Loesning " + teller + ":\n");
                System.out.println(t + "\n");
                teller++;
            }

            if (raskesteUtvei != null) {
                for (Tuppel tuppel : raskesteUtvei) {
                    lab.endreTegn(tuppel.rad, tuppel.kol);
                }
            }


            System.out.println("Raskeste vei fra pos illustrert:\n");
            System.out.println(lab);


            System.out.println("\nSkriv inn nye koordinater ('-1' for aa avslutte)");
            linje = input.nextLine();
            lab = new Labyrint(filnavn);
        }

        }
}


