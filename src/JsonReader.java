import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class JsonReader {

    public String Read(Reader re) throws IOException {
        StringBuilder str = new StringBuilder();     // Pour stocker l'url en string
        int temp;

        //  re.read() retourne -1 quand c'est la fin du buffer de la donnée ou la fin du fichier
        do {
            temp = re.read();       //lit caractère par caractère
            str.append((char) temp);
        } while (temp != -1);
        return str.toString();

    }

    //Recupération d'un fichier json en fonction d'une url
    public JSONObject readJsonFromUrl(String link) {
        try (InputStream input = new URL(link).openStream()) {
            BufferedReader re = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String Text = Read(re);
            return new JSONObject(Text);
        } catch (Exception e) {
            return null;
        }
    }
}