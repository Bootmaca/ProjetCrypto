import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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


        /**************************************** Panel PanelContainBt ****************************************/
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

    public void chiffrement(boolean isSimple, String fichierAChiffrer, String emplacementFichierChiffre, String emplacementCle) throws IOException, JSONException {

        //Récupérer le fichier sous forme de byte
        byte[] fichier = Files.readAllBytes(Paths.get(fichierAChiffrer));

        //
        LFSRPourChiffrer lfsrPourChiffrer = new LFSRPourChiffrer(fichier);

        byte[] fichierCompresse = lfsrPourChiffrer.plusieursGraines();

        int[] cle = lfsrPourChiffrer.getCle();

        System.out.println(java.util.Arrays.toString(fichier));

        System.out.println(java.util.Arrays.toString(fichierCompresse));

        Files.write(Paths.get("C:\\Users\\cleme\\OneDrive\\Bureau\\Cours\\M1_info\\Semestre_2\\Codage_Et_Cryptographie\\Projet\\ProjetCrypto\\src\\Image\\test2.txt"), fichierCompresse);

//        Files.write(Paths.get("C:\\Users\\cleme\\OneDrive\\Bureau\\Cours\\M1_info\\Semestre_2\\Codage_Et_Cryptographie\\Projet\\ProjetCrypto\\src\\Image\\test2.txt"), fichierCompresse);

//        LFSRPourDechiffrer lfsrPourDechiffrer = new LFSRPourDechiffrer(fichierCompresse, cle);
//
//        byte[] fichierDecompresse = lfsrPourDechiffrer.dechiffrer();
//
//        System.out.println(java.util.Arrays.toString(fichierDecompresse));
//
//        Files.write(Paths.get("C:\\Users\\cleme\\OneDrive\\Bureau\\Cours\\M1_info\\Semestre_2\\Codage_Et_Cryptographie\\Projet\\ProjetCrypto\\src\\Image\\test3.txt"), fichierDecompresse);
    }


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, JSONException, IOException {
        //Permet d'avoir l'arrondi sur les boutons
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

        String chemin = "C:\\Users\\cleme\\OneDrive\\Documents\\DBMS ex.planation.docx";



        //Ouvre le panel
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        //Action sur le bouton chiffrer
        mainFrame.BtChiffrer.addActionListener(e -> {
            initialisationTypeChiffrageFrame(mainFrame);
        });


    }

    public static void initialisationTypeChiffrageFrame(MainFrame mainFrame){
        TypeChiffrageFrame typeChiffrageFrame = new TypeChiffrageFrame();
        typeChiffrageFrame.setVisible(true);
        mainFrame.setVisible(false);

        //Fermeture de l'appli chiffrer frame
        typeChiffrageFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        typeChiffrageFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                typeChiffrageFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        typeChiffrageFrame.getBtSimple().addActionListener(e1 -> {
            initialisationChiffrerFrame(typeChiffrageFrame, mainFrame, true);
        });

        typeChiffrageFrame.getBtMultiple().addActionListener(e1 -> {
            initialisationChiffrerFrame(typeChiffrageFrame, mainFrame, false);
        });

        typeChiffrageFrame.getBtAnnuler().addActionListener(e1 -> {
            typeChiffrageFrame.dispose();
            mainFrame.setVisible(true);
        });
    }

    public static void initialisationChiffrerFrame(TypeChiffrageFrame typeChiffrageFrame, MainFrame mainFrame, boolean isSimple){
        ChiffrerFrame chiffrerFrame = new ChiffrerFrame();
        chiffrerFrame.setVisible(true);
        typeChiffrageFrame.setVisible(false);

        //Fermeture de l'appli chiffrer frame
        chiffrerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        chiffrerFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                chiffrerFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        // Appuie sur le bouton Annuler
        chiffrerFrame.getBtAnnuler().addActionListener(e12 -> {
            chiffrerFrame.dispose();
            typeChiffrageFrame.setVisible(true);
        });

        // Appuie sur le bouton Valider
        chiffrerFrame.getBtValider().addActionListener(e1 -> {
            chiffrerFrame.dispose();
            String fichierAChiffrer = chiffrerFrame.getTfLink1().getText();
            String emplacementFichierChiffre = chiffrerFrame.getTfLink2().getText();
            String emplacementCle = chiffrerFrame.getTfLink3().getText();

            //Si les infos ne sont pas valide affichage d'une message dialog
            if(Objects.equals(fichierAChiffrer, "C:\\") || Objects.equals(emplacementFichierChiffre, "C:\\") || Objects.equals(emplacementCle, "C:\\")){
                JOptionPane.showMessageDialog( null, "Veuillez rentrer des informations valide","Erreur", JOptionPane.WARNING_MESSAGE);
                chiffrerFrame.setVisible(true);
            }else{
                initialisationLoaderFrame(mainFrame, isSimple, fichierAChiffrer, emplacementFichierChiffre, emplacementCle);
            }


        });
    }

    public static void initialisationLoaderFrame(MainFrame mainFrame, boolean isSimple, String fichierAChiffrer, String emplacementFichierChiffre, String emplacementCle){

        LoaderChiffrerFrame loaderChiffrerFrame = new LoaderChiffrerFrame();

        //Lors de la fermeture de la Frame demande la confirmation avant de la fermet et d'afficher la main frame
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

        //Lors de la fermeture de la Frame la ferme et affiche la mainFrame
        WindowAdapter windowAdapterFermerSansConfirmation = new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                loaderChiffrerFrame.dispose();
                mainFrame.setVisible(true);
            }
        };


        Thread t1 = new Thread(() -> {
            loaderChiffrerFrame.setVisible(true);
            loaderChiffrerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            loaderChiffrerFrame.addWindowListener(windowAdapterFermerAvecConfirmation);
        });

        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            LoaderChiffrerFrame.fin(loaderChiffrerFrame);
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




}
