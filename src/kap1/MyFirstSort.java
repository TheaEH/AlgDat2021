package kap1;

/**
 * Windows [alt] + [enter] mens du holder på klassen for å lage tester
 */
public class MyFirstSort {
    public static void main(String[] args) {
        System.out.println("Hello algdat");

        int[] values = {1, 7, 2, 4, 6, 9};

        myFirstSort(values);
    }

    /**
     * Denne funksjonen tar inn et array med verdier (heltall),
     * og sorterer dem "in place" i synkende rekkefølge
     * @param values Verdier vi skal sortere
     */
    public static void myFirstSort(int[] values) {
        for (int k = 0; k < values.length - 1; ++k) {
            //Sjekk at vi får fornuftig svar for *ett* tilfelle.
            //Vi må utføre ordentlig testing før vi
            //faktisk kan stole på kildekoden vår
            int max_index = findMax(values, k, values.length);
            //System.out.println("Største verdi ligger på plass "
            //        + max_index + " og har verdi " + values[max_index]);

            //Bytte plass på tall på plass 0 og max_index
            int temp = values[k];
            values[k] = values[max_index];
            values[max_index] = temp;

            System.out.println();
        }
        // Printer ut arrayet etter sortering
        /*
        for (int value : values) {
            System.out.print(value + ", ");
        }
        */
    }

    /**
     * findMax finner største tall i et array
     * men bare innenfor tallen [fra, til)
     */
    public static int findMax(int[] values, int fra, int til) {
        //Initialiser ved å se på første "kort"
        //Dette er det største tallet jeg har funnet
        //så langt
        int index = fra;
        int max_value = values[fra];

        //Sjekk at grensene for løkken er riktig.
        // [1, values.length)
        for (int i=fra+1; i<til; ++i) {
            //Sjekk om vi har funnet nytt største tall
            if (values[i] > max_value) {
                max_value = values[i];
                index = i;
            }
        }

        return index;
    }

    /**
     * Findmax - finner index til største og nest største tall
     *
     */
    public static int findTwoMaxIndices(int[] values, int fra, int til) {
        //Sjekk at grensene for løkken gir mening
        if (til - fra < 2) {
            throw new ArrayIndexOutOfBoundsException("Intervallet er ikke stort nok");
        }
        //Tester at vi ikke har for stor til-verdi
        if (til > values.length) {
            throw new ArrayIndexOutOfBoundsException("Til er ikke lang nok");
        }

        //Indekser:             0  1  2  3  4  5
        //values.length = 6
        //Starter med verdiene {1, 7, 2, 4, 6, 9}

        //Initialiser ved å se på første to verdier (1, 7)
        //Dette er det største og nest største tallet jeg har funnet
        //så langt
        int index_max = fra; //1
        int index_nestmax = fra+1; //7

        int max_value = values[fra];
        int nest_max_value = values[fra+1];

        if (max_value < nest_max_value) {
            //Burde refaktoreres i en swap(...) metode
            //for å gjøre koden mer lesbar og mindre sjanse for feil
            int temp = max_value;
            max_value = nest_max_value;
            nest_max_value = temp;

            temp = index_max;
            index_max = index_nestmax;
            index_nestmax = temp;
        }

        //Hvis nest_max har større verdi enn max må vi bytte om på dem

        // Status så langt:
        // Array: {1, 7, 2, 4, 6, 9}
        // Vi har sett op de to første elementene {1, 7}
        // max_value = 7, index_max = 1
        // nest_max_value = 1, index_nestmax = 0
        // Nå må vi løpet gjennom resten av arrayet

        // Intervallet vårt er [fra, til)
        // Siden vi har sjekket de to første tallene
        // starter vi løkka fra i = start
        for (int i=fra+2; i<til; ++i) {
            //Sjekk om vi har funnet nytt største tall
            int curr = values[i]; // Verdien løkka sjekker

                if (curr > max_value) {
                    int temp = max_value;
                    max_value = curr;
                    nest_max_value = temp;

                    // Oppdater indeksene
                    temp = index_max;
                    index_max = i;
                    index_nestmax = temp;
                }
                else if (curr > nest_max_value){
                    nest_max_value = curr;
                    index_nestmax = i;
                }
                else {
                    break;
                    // Ingenting, max og nest_max er større begge to
                }
        }
        return index_max;
    }
}
