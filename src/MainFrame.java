import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrame extends JFrame{
    private JPanel mainPanel;
    private JButton BtChiffrer;
    private JPanel PanelContainBt;
    private JButton BtDechiffrer;
    private JLabel LTitre;
    private JPanel PanelContainTitre;


    public MainFrame(){

        /**************************************** MAIN PANEL ****************************************/
        setContentPane(mainPanel);

        // Taille du panel
        setSize(1050,750);

        // Font du titre
        setFont(new Font("System", Font.PLAIN, 11));

        // Calcul afin de positionner le titre au milieu
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("Chiffrer ou Déchiffrer ?");
        int y = fm.stringWidth(" ");
        int z = getWidth()/2  - (x/2) -20;
        int w = z/y;
        String pad ="";
        pad = String.format("%"+w+"s", pad);

        // Titre du panel
        setTitle(pad+"Chiffrer ou Déchiffrer ?");

        // Icon du panel
        Image icon = Toolkit.getDefaultToolkit().getImage("src/Image/cryptographie3.jpg");
        setIconImage(icon);

        // Permet de quitter l'applis quand on appuye sur la croix
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Empêche la redimension du panel
        setResizable(false);

        // Change la couleur d'arrière plan en black
        mainPanel.setBackground(Color.black);


        /**************************************** Label LTitre ****************************************/
        LTitre.setFont(new Font("Consolas", Font.BOLD, 65)); // Change la police le style et met en gras
        LTitre.setForeground(Color.white); // Change La couleur du texte


        /**************************************** Bouton BtChiffrer ****************************************/
        BtChiffrer.setFocusPainted(false); // Empêche le focus auto
        BtChiffrer.setBackground(Color.black); // Change la couleur d'arrière plan en black
        BtChiffrer.setForeground(Color.orange); //Change la couleur du texte en orange
        BtChiffrer.setFont(new Font("Consolas", Font.BOLD, 45)); // Change la police le style et met en gras


        /**************************************** Bouton BtDechiffrer ****************************************/
        BtDechiffrer.setFocusPainted(false); //Empêche le focus auto
        BtDechiffrer.setBackground(Color.black); // Change la couleur d'arrière plan en black
        BtDechiffrer.setForeground(Color.orange); //Change la couleur du texte en orange
        BtDechiffrer.setFont(new Font("Consolas", Font.BOLD, 45)); // Change la police le style et met en gras


        /**************************************** Panel PanelContainBt ****************************************/
        PanelContainBt.setBackground(Color.black); // Change la couleur d'arrière plan en black


        /**************************************** Panel PanelContainTitre ****************************************/
        PanelContainTitre.setBackground(Color.black); // Change la couleur d'arrière plan en black

    }

    public static String getNomFichier(String chemin){
        return new File(chemin).getName();
    }

    public static String getExtensionFichier(String nomFichier){
        String extension = "";
        final Pattern PATTERN = Pattern.compile("(.*)\\.(.*)");
        Matcher m = PATTERN.matcher(nomFichier);
        if (m.find()) {
            extension = m.group(2);
        }
        return extension;
    }

    public static String getNomFichierSansExtension(String nomFichier){
        String nom = "";
        final Pattern PATTERN = Pattern.compile("(.*)\\.(.*)");
        Matcher m = PATTERN.matcher(nomFichier);
        if (m.find()) {
            nom = m.group(1);
        }
        return nom;
    }

    public static void chiffrement(boolean isSimple, String fichierAChiffrer, String emplacementFichierChiffre, String emplacementCle) throws IOException, JSONException {

        // Récupérer le fichier sous forme de byte
        byte[] fichier = Files.readAllBytes(Paths.get(fichierAChiffrer));

        // Chiffrement du fichier sélectionné selon la méthode choisis
        LFSRPourChiffrer lfsrPourChiffrer = new LFSRPourChiffrer(fichier);
        byte[] fichierChiffre;
        if(isSimple){
            fichierChiffre = lfsrPourChiffrer.uneGraine();
        }else{
            fichierChiffre = lfsrPourChiffrer.plusieursGraines();
        }

        // Récupération de la clé
        byte[] cle = lfsrPourChiffrer.getCle();

        // Récupération du nom et de l'extension du fichier à chiffrer
        String nomFichier = getNomFichier(fichierAChiffrer);
        String nomSansExtension = getNomFichierSansExtension(nomFichier);
        String extensionFichier = getExtensionFichier(nomFichier);

        // Enregistrement du fichier chiffre dans le chemin spécifié
        Path pathCompletFichierChiffre = Paths.get(emplacementFichierChiffre + "\\" + nomSansExtension + "(chiffré)." + extensionFichier);
        Files.write(pathCompletFichierChiffre, fichierChiffre);

        // Enregistrement de la clé dans le chemin spécifié
        Path pathCompletCle = Paths.get(emplacementCle + "\\cle" + nomSansExtension);
        Files.write(pathCompletCle, cle);
    }

    public static void dechiffrement(String fichierADechiffrer, String emplacementFichierDechiffre, String fichierCle) throws IOException {

        // Récupération du nom et de l'extension du fichier
        String nomFichierChiffre = getNomFichier(fichierADechiffrer);
        String nomSansExtension = getNomFichierSansExtension(nomFichierChiffre);
        String extensionFichier = getExtensionFichier(nomFichierChiffre);

        // Un fichier chiffré contient "(chiffré)" dans son nom on va donc l'enlever
        String nomSansExtensionEtSansChiffre = nomSansExtension.replaceAll("\\(chiffré\\)", "");

        // Récupérer le fichier chiffré sous forme de byte
        byte[] fichierChiffre = Files.readAllBytes(Paths.get(fichierADechiffrer));

        // Récupération de la clé sous forme de byte
        byte[] recupCle = Files.readAllBytes(Paths.get(fichierCle));

        // Déchiffrement du fichier
        LFSRPourDechiffrer lfsrPourDechiffrer = new LFSRPourDechiffrer(fichierChiffre, recupCle);
        byte[] fichierDechiffre = lfsrPourDechiffrer.dechiffrer();

        // Enregistrement de ce fichier dans l'emplacement indiqué
        Path pathCompletFichierDeChiffre = Paths.get(emplacementFichierDechiffre + "\\" + nomSansExtensionEtSansChiffre + "(déchiffré)." + extensionFichier);
        Files.write(pathCompletFichierDeChiffre, fichierDechiffre);
    }


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // Permet d'avoir l'arrondi sur les boutons
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

        String chemin = "C:\\Users\\cleme\\OneDrive\\Documents\\test(chiffré)";


        // Ouvre le panel
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        // Action lors du clic sur le bouton Chiffrer
        mainFrame.BtChiffrer.addActionListener(e -> initialisationTypeChiffrageFrame(mainFrame));

        // Action lors du clic sur le bouton Déchiffrer
        mainFrame.BtDechiffrer.addActionListener(e -> initialisationDechiffrerFrame(mainFrame));


    }

    public static void initialisationTypeChiffrageFrame(MainFrame mainFrame){

        // Création et ouverture de la frame TypeChiffrageFrame
        TypeChiffrageFrame typeChiffrageFrame = new TypeChiffrageFrame();
        typeChiffrageFrame.setVisible(true);

        // Rendre invisible la frame mainFrame
        mainFrame.setVisible(false);

        // Action lors du clic sur la croix en haut à droite
        typeChiffrageFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        typeChiffrageFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                typeChiffrageFrame.dispose();// Fermeture de la frame actuel
                mainFrame.setVisible(true);// Rendre visible la frame mainFrame
            }
        });

        // Actions lors du clic sur le bouton Simple
        typeChiffrageFrame.getBtSimple().addActionListener(e1 -> initialisationChiffrerFrame(typeChiffrageFrame, mainFrame, true));

        // Action lors du clic sur le bouton Multiple
        typeChiffrageFrame.getBtMultiple().addActionListener(e1 -> initialisationChiffrerFrame(typeChiffrageFrame, mainFrame, false));

        //Action lors du clic sur le bouton Annuler
        typeChiffrageFrame.getBtAnnuler().addActionListener(e1 -> {
            typeChiffrageFrame.dispose();// Fermeture de la frame actuel
            mainFrame.setVisible(true);// Rendre visible la frame mainFrame
        });
    }

    public static void initialisationChiffrerFrame(TypeChiffrageFrame typeChiffrageFrame, MainFrame mainFrame, boolean isSimple){

        // Création et ouverture de la frame ChiffrerFrame
        ChiffrerFrame chiffrerFrame = new ChiffrerFrame();
        chiffrerFrame.setVisible(true);

        // Rendre invisible la frame typeChiffrageFrame
        typeChiffrageFrame.setVisible(false);

        // Action lors du clic sur la croix en haut à droite
        chiffrerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        chiffrerFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                chiffrerFrame.dispose();// Fermeture de la frame actuel
                mainFrame.setVisible(true);// Rendre visible la frame mainFrame
            }
        });

        // Action lors du clic sur le bouton Annuler
        chiffrerFrame.getBtAnnuler().addActionListener(e12 -> {
            chiffrerFrame.dispose();// Fermeture de la frame actuel
            typeChiffrageFrame.setVisible(true);// Rendre visible la frame mainFrame
        });

        // Action lors du clic sur le bouton Valider
        chiffrerFrame.getBtValider().addActionListener(e1 -> {

            // Rends invisible la frame actuel
            chiffrerFrame.setVisible(false);

            // Récupération des informations contenue dans les textFiels
            String fichierAChiffrer = chiffrerFrame.getTfLink1().getText();
            String emplacementFichierChiffre = chiffrerFrame.getTfLink2().getText();
            String emplacementCle = chiffrerFrame.getTfLink3().getText();

            // Si les infos ne sont pas valide affichage d'un message dialog
            if(Objects.equals(fichierAChiffrer, "C:\\") || Objects.equals(emplacementFichierChiffre, "C:\\") || Objects.equals(emplacementCle, "C:\\")){
                JOptionPane.showMessageDialog( null, "Veuillez rentrer des informations valide","Erreur", JOptionPane.WARNING_MESSAGE);
                chiffrerFrame.setVisible(true);
            }else{
                initialisationLoaderChiffrerFrame(mainFrame, isSimple, fichierAChiffrer, emplacementFichierChiffre, emplacementCle);
            }

        });
    }

    public static void initialisationLoaderChiffrerFrame(MainFrame mainFrame, boolean isSimple, String fichierAChiffrer, String emplacementFichierChiffre, String emplacementCle){

        // Création de la frame LoaderChiffrerFrame
        LoaderChiffrerFrame loaderChiffrerFrame = new LoaderChiffrerFrame();

        // Lors de la fermeture de la Frame demande la confirmation avant de la fermet et d'afficher la main frame
        WindowAdapter windowAdapterFermerAvecConfirmation = new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int reponse = JOptionPane.showConfirmDialog(loaderChiffrerFrame,
                        "Voulez-vous vraiment quitter l'application? \n Cela annulera le chiffrement en cours",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    loaderChiffrerFrame.dispose();
                    mainFrame.setVisible(true);
                }
            }
        };

        // Lors de la fermeture de la Frame la ferme et affiche la mainFrame
        WindowAdapter windowAdapterFermerSansConfirmation = new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                loaderChiffrerFrame.dispose();
                mainFrame.setVisible(true);
            }
        };


        // Premier Thread permettant de rendre visible la frame
        Thread t1 = new Thread(() -> {
            loaderChiffrerFrame.setVisible(true);
            loaderChiffrerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            loaderChiffrerFrame.addWindowListener(windowAdapterFermerAvecConfirmation);
        });

        t1.start();

        // Deuxième Thread permettant de réaliser le chiffrage
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                chiffrement(isSimple, fichierAChiffrer,emplacementFichierChiffre,emplacementCle);
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
            loaderChiffrerFrame.fin();
            loaderChiffrerFrame.removeWindowListener(windowAdapterFermerAvecConfirmation);
            loaderChiffrerFrame.addWindowListener(windowAdapterFermerSansConfirmation);
        });

        t2.start();

        // Appuye sur le bouton Valider
        loaderChiffrerFrame.getBtValider().addActionListener(e11 -> {
            loaderChiffrerFrame.dispose();
            mainFrame.setVisible(true);
        });
    }



    public static void initialisationDechiffrerFrame(MainFrame mainFrame){

        // Création et ouverture de la frame ChiffrerFrame
        DechiffrerFrame dechiffrerFrame = new DechiffrerFrame();
        dechiffrerFrame.setVisible(true);

        // Rendre invisible la frame mainFrame
        mainFrame.setVisible(false);

        // Fermeture de l'appli chiffrer frame
        dechiffrerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dechiffrerFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dechiffrerFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        // Appuie sur le bouton Annuler
        dechiffrerFrame.getBtAnnuler().addActionListener(e12 -> {
            dechiffrerFrame.dispose();
            mainFrame.setVisible(true);
        });

        // Appuie sur le bouton Valider
        dechiffrerFrame.getBtValider().addActionListener(e1 -> {

            // Rends invisible la frame actuel
            dechiffrerFrame.setVisible(false);

            // Récupération des informations contenue dans les textFiels
            String fichierADechiffrer = dechiffrerFrame.getTfLink1().getText();
            String emplacementFichierDechiffre = dechiffrerFrame.getTfLink2().getText();
            String fichiercle = dechiffrerFrame.getTfLink3().getText();

            // Si les infos ne sont pas valide affichage d'un message dialog
            if(Objects.equals(fichierADechiffrer, "C:\\") || Objects.equals(emplacementFichierDechiffre, "C:\\") || Objects.equals(fichiercle, "C:\\")){
                JOptionPane.showMessageDialog( null, "Veuillez rentrer des informations valide","Erreur", JOptionPane.WARNING_MESSAGE);
                dechiffrerFrame.setVisible(true);
            }else{
                initialisationLoaderDechiffreFrame(mainFrame, fichierADechiffrer, emplacementFichierDechiffre, fichiercle);
            }


        });
    }

    public static void initialisationLoaderDechiffreFrame(MainFrame mainFrame, String fichierADechiffrer, String emplacementFichierDechiffre, String fichiercle){

        LoaderDechiffrerFrame loaderDechiffrerFrame = new LoaderDechiffrerFrame();

        // Lors de la fermeture de la Frame demandé la confirmation avant de la fermet et d'afficher la main frame
        WindowAdapter windowAdapterFermerAvecConfirmation = new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int reponse = JOptionPane.showConfirmDialog(loaderDechiffrerFrame,
                        "Voulez-vous vraiment quitter l'application? \n Cela annulera le chiffrement en cours",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    loaderDechiffrerFrame.dispose();
                    mainFrame.setVisible(true);
                }
            }
        };

        // Lors de la fermeture de la Frame la ferme et affiche la mainFrame
        WindowAdapter windowAdapterFermerSansConfirmation = new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                loaderDechiffrerFrame.dispose();
                mainFrame.setVisible(true);
            }
        };


        // Premier Thread permettant de rendre visible la frame
        Thread t1 = new Thread(() -> {
            loaderDechiffrerFrame.setVisible(true);
            loaderDechiffrerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            loaderDechiffrerFrame.addWindowListener(windowAdapterFermerAvecConfirmation);
        });

        t1.start();

        // Deuxième Thread permettant de réaliser le déchiffrage
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                dechiffrement(fichierADechiffrer,emplacementFichierDechiffre,fichiercle);
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            loaderDechiffrerFrame.fin();
            loaderDechiffrerFrame.removeWindowListener(windowAdapterFermerAvecConfirmation);
            loaderDechiffrerFrame.addWindowListener(windowAdapterFermerSansConfirmation);
        });

        t2.start();

        // Appuye sur le bouton Valider
        loaderDechiffrerFrame.getBtValider().addActionListener(e11 -> {
            loaderDechiffrerFrame.dispose();
            mainFrame.setVisible(true);
        });
    }


}
