public class LFSRPourChiffrer {
    private final byte[] fichier;
    private byte[] fichierChiffre;
    private byte[] cle;

    public LFSRPourChiffrer(byte[] fichier){
        this.fichier = fichier;
        this.fichierChiffre = new byte[fichier.length];
    }

    //Chiffrage basique
    public byte[] uneGraine() {

        //Création de l'objet permettant de créer des bits de manière aléatoire
        NombreAleatoire nombreAleatoire = new NombreAleatoire();

        // Création des premiers bit de la graine de manière aléatoire
        int longueurDepartXn = 16;

        byte[] xn;

        xn = nombreAleatoire.creerNombreAleatoire(longueurDepartXn, this.fichier.length);

        // Création de l'objet permettant d'augmenter la graine pour constituer la clé secrète
        AugmenterGraine augmenterXn = new AugmenterGraine(xn, new int[]{5, 6, 8, 16}, longueurDepartXn);

        // Initialisation des variables
        byte bitFichier;
        byte bitGraine;
        int longueurGraine;

        // Pour toute la longueur de la graine création du fichier chiffrer bit par bit
        for(int i = 0; i<this.fichier.length; i++){

            //Récupération du bit du fichier
            bitFichier = this.fichier[i];

            //Récupération de la longueur actuelle de la graine
            longueurGraine = augmenterXn.getLongueurGraine();

            // Si la longueur de la graine est moin grande que le bit qu'on veut récupérer
            if(longueurGraine-1 < i){
                augmenterXn.augmenterGraine(); // Augmente la graine de 1 bit
            }

            //Récupération du bit de la graine
            bitGraine = augmenterXn.getUnBitDeLaGraine(i);

            //Réalisation d'un xor entre le bit du fichier et celui de la graine
            this.fichierChiffre[i] = (byte) (bitFichier ^ bitGraine);
        }

        this.cle = augmenterXn.getGraine();

        return fichierChiffre;
    }


    // Chiffrage multiple
    public byte[] plusieursGraines() {

        this.cle = new byte[fichier.length];

        //Création de l'objet permettant de créer des bits de manière aléatoire
        NombreAleatoire nombreAleatoire = new NombreAleatoire();

        // Création des premiers bit des graines de manière aléatoire
        int longueurDepartVn = 25;
        byte[] vn = nombreAleatoire.creerNombreAleatoire(longueurDepartVn, this.fichier.length);

        int longueurDepartXn = 31;
        byte[] xn = nombreAleatoire.creerNombreAleatoire(longueurDepartXn, this.fichier.length);

        int longueurDepartYn = 33;
        byte[] yn = nombreAleatoire.creerNombreAleatoire(longueurDepartYn, this.fichier.length);

        int longueurDepartZn = 39;
        byte[] zn = nombreAleatoire.creerNombreAleatoire(longueurDepartZn, this.fichier.length);

        // Création des objets permettant d'augmenter les graines pour constituer les clés secrète
        AugmenterGraine augmenterVn = new AugmenterGraine(vn, new int[]{5, 13, 17, 25}, longueurDepartVn);
        AugmenterGraine augmenterXn = new AugmenterGraine(xn, new int[]{7, 15, 19, 31}, longueurDepartXn);
        AugmenterGraine augmenterYn = new AugmenterGraine(yn, new int[]{5, 9, 29, 33}, longueurDepartYn);
        AugmenterGraine augmenterZn = new AugmenterGraine(zn, new int[]{3, 11, 35, 39}, longueurDepartZn);


        // Initialisation des variables
        byte bitFichier;
        byte bitGraineVn;
        byte bitGraineXn;
        byte bitGraineYn;
        byte bitGraineZn;
        int longueurGraineVn;
        int longueurGraineXn;
        int longueurGraineYn;
        int longueurGraineZn;

        // Pour toute la longueur de la graine création du fichier chiffrer bit par bit
        for(int i = 0; i<this.fichier.length; i++){

            //Récupération du bit du fichier
            bitFichier = this.fichier[i];

            //Récupération de la longueur des graines
            longueurGraineVn = augmenterVn.getLongueurGraine();
            longueurGraineXn = augmenterXn.getLongueurGraine();
            longueurGraineYn = augmenterYn.getLongueurGraine();
            longueurGraineZn = augmenterZn.getLongueurGraine();

            // Si la longueur d'une des graines est moin grande que le bit qu'on veut récupérer on augmente la graine d'un bit
            if(longueurGraineZn-1 < i){
                augmenterVn.augmenterGraine();
                augmenterXn.augmenterGraine();
                augmenterYn.augmenterGraine();
                augmenterZn.augmenterGraine();
            }else if(longueurGraineYn-1 < i){
                augmenterVn.augmenterGraine();
                augmenterXn.augmenterGraine();
                augmenterYn.augmenterGraine();
            }else if(longueurGraineXn-1 < i){
                augmenterVn.augmenterGraine();
                augmenterXn.augmenterGraine();
            }else if(longueurGraineVn-1 < i){
                augmenterVn.augmenterGraine();
            }

            //Récupération du bit des graines
            bitGraineVn = augmenterVn.getUnBitDeLaGraine(i);
            bitGraineXn = augmenterXn.getUnBitDeLaGraine(i);
            bitGraineYn = augmenterYn.getUnBitDeLaGraine(i);
            bitGraineZn = augmenterZn.getUnBitDeLaGraine(i);

            byte xorTousLesBitsSelectionneDesGraines = (byte) (bitGraineVn ^ bitGraineXn ^ bitGraineYn ^ bitGraineZn);

            //Réalisation d'un xor entre le bit du fichier et celui de la graine
            this.fichierChiffre[i] = (byte) (bitFichier ^ xorTousLesBitsSelectionneDesGraines);

            // Ajout des infos dans le tableau
            this.cle[i] = xorTousLesBitsSelectionneDesGraines;
        }

        return fichierChiffre;
    }

    public byte[] getCle(){
        return cle;
    }

}
