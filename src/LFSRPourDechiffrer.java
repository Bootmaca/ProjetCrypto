public class LFSRPourDechiffrer {
    private final byte[] fichierChiffre;
    private final int[] cle;
    private byte[] fichierDecompresse;

    public LFSRPourDechiffrer(byte[] fichierChiffre, int[] cle){
        this.fichierChiffre = fichierChiffre;
        this.cle = cle;
        this.fichierDecompresse = new byte[fichierChiffre.length];
    }

    public byte[] dechiffrer(){

        //pour chaque bit du fichier chiffrer on fait un xor avec le bit de la cle et on le met dans le fichier
        for(int i = 0; i < this.fichierChiffre.length; i++){
            fichierDecompresse[i] = (byte) (fichierChiffre[i] ^ cle[i]);
        }
        return fichierDecompresse;
    }



}
