public class AugmenterGraine {
    private byte[] graine;
    private final int[] filtres;
    private int longueurGraine;

    public AugmenterGraine(byte[] graine, int[] filtres, int longueurGraine){
        this.graine = graine;
        this.filtres = filtres;
        this.longueurGraine = longueurGraine;
    }

    //Augmente la graine de 1 bit
    public void augmenterGraine(){
        byte bitARajouterALaFin = 0;

        //Pour tous les filtres on fait un xor avec les bit correspondant à la taille de la graine moins le filtre
        for (int filtre : filtres) {
            bitARajouterALaFin = (byte) (bitARajouterALaFin ^ graine[(longueurGraine) - filtre]);
        }

        //On rajoute le bit de la fin au tableau
        graine[longueurGraine] = bitARajouterALaFin;

        //Augmente la longueur de la graine de 1
        longueurGraine++;
    }

    public int getLongueurGraine(){
        return longueurGraine;
    }

    public byte getUnBitDeLaGraine(int numBit){
        return graine[numBit];
    }

    public byte[] getGraine() {
        return graine;
    }
}
