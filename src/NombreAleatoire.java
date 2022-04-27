import org.json.JSONObject;
import java.util.Random;

public class NombreAleatoire {
    private final String[] tabDesVilles = new String[]{"Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Toulon", "Grenoble", "Dijon", "Angers", "Villeurbanne", "Brest", "Tours", "Limoges", "Amiens", "Perpignan", "Metz", "Mulhouse", "Rouen", "Argenteuil", "Caen", "Montreuil", "Nancy"};

    public NombreAleatoire(){}

    public byte[] creerNombreAleatoire(int tailleDemande, int tailleFichier) {

        //Récupère la taille la plus petite au cas où si le fichier est moin grand que la taille demandée
        int tailleMin = Math.min(tailleFichier, tailleDemande);

        //Récupère la taille la plus grande au cas où si le fichier est moin grand que la taille demandée
        int tailleMax = Math.max(tailleFichier, tailleDemande);

        //Créer d'un tableau d'entier en fonction de la taille du fichier
        byte[] tabInt = new byte[tailleMax];

        try {


            int nbVille = tabDesVilles.length;
            StringBuilder chaineContainInt = new StringBuilder();

            //Tant que la chaine de caractère est inférieur à la taille minimum demandée on continue
            while (chaineContainInt.length() < tailleMin){

                //Chiffre random pour choisir une ville aléatoire
                Random r = new Random();
                int chiffreRandom = (int)(Math.random() * ((nbVille - 1) + 1)); //Min + (int)(Math.random() * ((Max - Min) + 1)); (va de 0 au nombre de villes moins 1)

                //Ville selectionné
                String nomDeLaVille = tabDesVilles[chiffreRandom];

                //Récupération de la météo sous format json en fonction de la ville
                String url = "https://www.prevision-meteo.ch/services/json/"+nomDeLaVille;  // Url contenant le nom de la ville
                JsonReader reader = new JsonReader(); //Creation de l'objet JsonReader afin de pouvoir récupérer le json
                JSONObject json = reader.readJsonFromUrl(url);  // Appel de la méthode qui récupère le fichier json depuis l'url

                //Lire un json depuis une url renvoie parfois une erreur donc on rerécupère un fichier json si cela ce produit
                while(!json.toString().contains("city_info")){
                    //Chiffre random pour choisir une ville aléatoire
                    chiffreRandom = r.nextInt((nbVille-1) + 1); //(max - min + 1) + min; (va de 0 au nombre de villes moins 1)

                    //Ville selectionné
                    nomDeLaVille = tabDesVilles[chiffreRandom];

                    //Récupération de la météo sous format json en fonction de la ville
                    url = "https://www.prevision-meteo.ch/services/json/"+nomDeLaVille;  // Url contenant le nom de la ville
                    json = reader.readJsonFromUrl(url);  // Appel de la méthode qui récupère le fichier json depuis l'url
                }

                //Récupération de la temperature, de la vitesse du vent de l'humidité et de la pression en fonction de la ville sélectionné
                int temperature = Integer.parseInt(json.getJSONObject("current_condition").get("tmp").toString());
                int vitesseDuVent = Integer.parseInt(json.getJSONObject("current_condition").get("wnd_spd").toString());
                int humidite = Integer.parseInt(json.getJSONObject("current_condition").get("humidity").toString());
                int pression = (int) Float.parseFloat(json.getJSONObject("current_condition").get("pressure").toString());

                //Conversion des données récupérée précédemment sous format binaire
                chaineContainInt.append(Integer.toString(temperature, 2)).append(Integer.toString(vitesseDuVent, 2)).append(Integer.toString(humidite, 2)).append(Integer.toString(pression, 2));

            }

            //Pour i allant de 0 à la taille minimum recuperation du caractère numéro i de la chaine de caractère et mise dans le tableau à la place i
            for(int i =0; i<tailleMin; i++){
                tabInt[i] = (byte) Integer.parseInt(String.valueOf(chaineContainInt.charAt(i)));
            }
        }catch (Exception ignored){ // S'il y a un problème avec le site, on fait avec un chiffre random java
            for(int i =0; i<tailleMin; i++){
                int chiffreRandom = (int) (Math.random() * 2); //Min + (int)(Math.random() * ((Max - Min) + 1));
                tabInt[i] = (byte) chiffreRandom;
            }
        }

        return tabInt;
    }



}
