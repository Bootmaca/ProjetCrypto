import org.json.JSONException;
import org.json.JSONObject;
import java.util.Random;

public class NombreAleatoire {
    private final String[] tabDesVilles = new String[]{"Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Toulon", "Grenoble", "Dijon", "Angers", "Villeurbanne", "Brest", "Tours", "Limoges", "Amiens", "Perpignan", "Metz", "Mulhouse", "Rouen", "Argenteuil", "Caen", "Montreuil", "Nancy"};

    public NombreAleatoire(){}

    public int[] creerNombreAletoire(int tailleDemande, int tailleFichier) throws JSONException {

        //Récupère la taille la plus petite au cas ou si le fichier est moin grand que la taille demandé
        int tailleMin = Math.min(tailleFichier, tailleDemande);

        //Récupère la taille la plus grande au cas ou si le fichier est moin grand que la taille demandé
        int tailleMax = Math.max(tailleFichier, tailleDemande);

        //Créer d'un tableau d'entier en fonction de la taille du fichier
        int[] tabInt = new int[tailleMax];

        int nbVille = tabDesVilles.length;
        StringBuilder chaineContainInt = new StringBuilder();

        //Tant que la chaine de caractère est inférieur à la taille minimum demandé on coninue
        while (chaineContainInt.length() < tailleMin){

            //Chiffre random pour choisir une ville aléatoire
            Random r = new Random();
            int chiffreRandom = r.nextInt((nbVille-1) + 1); //(max - min + 1) + min; (va de 0 au nombre de ville moins 1)

            //Ville selectionné
            String nomDeLaVille = tabDesVilles[chiffreRandom];

            //Réupération de la méteo sous format json en fonction de la ville
            String url = "https://www.prevision-meteo.ch/services/json/"+nomDeLaVille;  // Url contenant le nom de la ville
            JsonReader reader = new JsonReader(); //Creation de l'objet JsonReader afin de pouvoir récupérer le json
            JSONObject json = reader.readJsonFromUrl(url);  // Appel de la méthode qui recupère le fichier json depuis l'url

            //Lire un json depuis une url renvoie parfois une erreur donc on rerécupère un fichier json si cela ce produit
            while(!json.toString().contains("city_info")){
                //Chiffre random pour choisir une ville aléatoire
                chiffreRandom = r.nextInt((nbVille-1) + 1); //(max - min + 1) + min; (va de 0 au nombre de ville moins 1)

                //Ville selectionné
                nomDeLaVille = tabDesVilles[chiffreRandom];

                //Réupération de la méteo sous format json en fonction de la ville
                url = "https://www.prevision-meteo.ch/services/json/"+nomDeLaVille;  // Url contenant le nom de la ville
                json = reader.readJsonFromUrl(url);  // Appel de la méthode qui recupère le fichier json depuis l'url
            }

            //Récupération de la temperature, de la vitesse du vent de l'humidite et de la pression en fonction de la ville selectionné
            int temperature = Integer.parseInt(json.getJSONObject("current_condition").get("tmp").toString());
            int vitesseDuVent = Integer.parseInt(json.getJSONObject("current_condition").get("wnd_spd").toString());
            int humidite = Integer.parseInt(json.getJSONObject("current_condition").get("humidity").toString());
            int pression = (int) Float.parseFloat(json.getJSONObject("current_condition").get("pressure").toString());

            //Conversion des données récupéré précedement sous format binaire
            chaineContainInt.append(Integer.toString(temperature, 2)).append(Integer.toString(vitesseDuVent, 2)).append(Integer.toString(humidite, 2)).append(Integer.toString(pression, 2));

        }

        //Pour i allant de 0 à la taille minimum récuperation du caratère numéro i de la chaine de caractère et mise dans le tableau à la place i
        for(int i =0; i<tailleMin; i++){
            tabInt[i] = Integer.parseInt(String.valueOf(chaineContainInt.charAt(i)));
        }

        return tabInt;
    }


}
