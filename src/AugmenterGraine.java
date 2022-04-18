public class AugmenterGraine {
    private int[] graine;
    private final int[] filtres;
    private int longueurGraine;

    public AugmenterGraine(int[] graine, int[] filtres, int longueurGraine){
        this.graine = graine;
        this.filtres = filtres;
        this.longueurGraine = longueurGraine;
    }

    //Augmente la graine de 1 bit
    public void augmenterGraine(){
        int bitARajouterALaFin = 0;

        //Pour tous les filtres on fait un xor avec les bit correspondant à la taille de la graine moins le filtre
        for (int filtre : filtres) {
            bitARajouterALaFin = bitARajouterALaFin ^ graine[(longueurGraine) - filtre];
        }

        //On rajoute le bit de la fin au tableau
        graine[longueurGraine] = bitARajouterALaFin;

        //Augmente la longueur de la graine de 1
        longueurGraine++;
    }

    public int getLongueurGraine(){
        return longueurGraine;
    }

    public int getUnBitDeLaGraine(int numBit){
        return graine[numBit];
    }

    public int[] getGraine() {
        return graine;
    }
}
