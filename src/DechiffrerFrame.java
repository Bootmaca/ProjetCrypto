import javax.swing.*;
import java.awt.*;

public class DechiffrerFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel PanelContainReste;
    private JLabel LTitre;
    private JPanel PanelContainTitre;
    private JPanel PanelContainPartieFichAChiffrer;
    private JLabel LTitrePourFichierADechiffrer;
    private JButton BtChoisir1;
    private JPanel PanelContainPartieEmplacementFichChiffre;
    private JLabel LTitrePourEmplacementFichierDechiffre;
    private JPanel PanelContainPartieEmplacementFichCleSecrete;
    private JLabel LTitrePourFichierCleSecrete;
    private JPanel PanelContainBtAndInput1;
    private JTextField TfLink1;
    private JPanel PanelContainInput1;
    private JPanel PanelContainBtAndInput2;
    private JPanel PanelContainBtAndInput3;
    private JPanel PanelContainInput2;
    private JPanel PanelContainInput3;
    private JTextField TfLink2;
    private JButton BtChoisir3;
    private JButton BtChoisir2;
    private JTextField TfLink3;
    private JButton BtValider;
    private JPanel PanelBtValiderEtAnnuler;
    private JButton BtAnnuler;


    public DechiffrerFrame(){

        /**************************************** MAIN PANEL ****************************************/
        setContentPane(mainPanel);

        // Taille du panel
        setSize(1200,800);

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

        // Empêche la re-dimension du panel
        setResizable(false);

        // Change la couleur d'arrière-plan en black
        mainPanel.setBackground(Color.black);


        /**************************************** Label LTitre ****************************************/
        LTitre.setFont(new Font("Consolas", Font.BOLD, 65)); // Change la police le style et met en gras
        LTitre.setForeground(Color.white); // Change La couleur du texte

        /**************************************** Panel PanelContainTitre ****************************************/
        PanelContainTitre.setBackground(Color.black); // Change la couleur d'arrière-plan en black

        /**************************************** Panel PanelContainReste ****************************************/
        PanelContainReste.setBackground(Color.black); // Change la couleur d'arrière-plan en black

        /**************************************** Panel PanelContainPartieFichAChiffrer ****************************************/
        PanelContainPartieFichAChiffrer.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelContainPartieFichAChiffrer.setBorder(BorderFactory.createEmptyBorder(0, 40,0,0));

        /**************************************** Label LTitrePourFichierADechiffrer ****************************************/
        LTitrePourFichierADechiffrer.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras
        LTitrePourFichierADechiffrer.setForeground(Color.white); // Change La couleur du texte

        /**************************************** Panel PanelContainBtAndInput1 ****************************************/
        PanelContainBtAndInput1.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelContainBtAndInput1.setBorder(BorderFactory.createEmptyBorder(0, 30,0,225));

        /**************************************** Bouton BtChoisir1 ****************************************/
        BtChoisir1.setFocusPainted(false); // Empêche le focus auto
        BtChoisir1.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        BtChoisir1.setForeground(Color.orange); //Change la couleur du texte en orange
        BtChoisir1.setFont(new Font("Consolas", Font.BOLD, 22)); // Change la police le style et met en gras

        //Sélection d'un fichier à chiffrer
        BtChoisir1.addActionListener(e -> {
            String filename = TfLink1.getText();
            String newFileName = choisirUnFichier(filename);
            TfLink1.setText(newFileName);
        });

        /**************************************** Panel PanelContainInput1 ****************************************/
        PanelContainInput1.setBackground(Color.black); // Change la couleur d'arrière-plan en black

        /**************************************** TextField TfLink1 ****************************************/
        TfLink1.setText("C:\\");
        TfLink1.setFont(new Font("Consolas", Font.BOLD, 18));
        TfLink1.setForeground(Color.black);

        /**************************************** Panel PanelContainPartieEmplacementFichChiffre ****************************************/
        PanelContainPartieEmplacementFichChiffre.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelContainPartieEmplacementFichChiffre.setBorder(BorderFactory.createEmptyBorder(0, 40,0,0));

        /**************************************** Label LTitrePourEmplacementFichierDechiffre ****************************************/
        LTitrePourEmplacementFichierDechiffre.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras
        LTitrePourEmplacementFichierDechiffre.setText("<html><body><font color='white'>Choisir l'emplacement du fichier déchiffré </font><font color='red'>(ranger le dans un dossier caché)</font><font color='white'> :</font></body></html>");

        /**************************************** Panel PanelContainBtAndInput2 ****************************************/
        PanelContainBtAndInput2.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelContainBtAndInput2.setBorder(BorderFactory.createEmptyBorder(0, 30,0,225));

        /**************************************** Bouton BtChoisir2 ****************************************/
        BtChoisir2.setFocusPainted(false); // Empêche le focus auto
        BtChoisir2.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        BtChoisir2.setForeground(Color.orange); //Change la couleur du texte en orange
        BtChoisir2.setFont(new Font("Consolas", Font.BOLD, 22)); // Change la police le style et met en gras

        //Sélection d'un fichier à chiffrer
        BtChoisir2.addActionListener(e -> {
            String directoryname = TfLink2.getText();
            String newDirectoryName = choisirUnDossier(directoryname);
            TfLink2.setText(newDirectoryName);
        });

        /**************************************** Panel PanelContainInput2 ****************************************/
        PanelContainInput2.setBackground(Color.black); // Change la couleur d'arrière-plan en black

        /**************************************** TextField TfLink2 ****************************************/
        TfLink2.setText("C:\\");
        TfLink2.setFont(new Font("Consolas", Font.BOLD, 18));
        TfLink2.setForeground(Color.black);

        /**************************************** Panel PanelContainPartieFichAChiffrer ****************************************/
        PanelContainPartieEmplacementFichCleSecrete.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelContainPartieEmplacementFichCleSecrete.setBorder(BorderFactory.createEmptyBorder(0, 40,0,0));

        /**************************************** Label LTitrePourFichierCleSecrete ****************************************/
        LTitrePourFichierCleSecrete.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras
        LTitrePourFichierCleSecrete.setForeground(Color.white); // Change la couleur de police du label

        /**************************************** Panel PanelContainBtAndInput3 ****************************************/
        PanelContainBtAndInput3.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelContainBtAndInput3.setBorder(BorderFactory.createEmptyBorder(0, 30,0,225));

        /**************************************** Bouton BtChoisir3 ****************************************/
        BtChoisir3.setFocusPainted(false); // Empêche le focus auto
        BtChoisir3.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        BtChoisir3.setForeground(Color.orange); //Change la couleur du texte en orange
        BtChoisir3.setFont(new Font("Consolas", Font.BOLD, 22)); // Change la police le style et met en gras

        BtChoisir3.addActionListener(e -> {
            String fileName = TfLink3.getText();
            String newFileName = choisirUnFichier(fileName);
            TfLink3.setText(newFileName);
        });

        /**************************************** Panel PanelContainInput3 ****************************************/
        PanelContainInput3.setBackground(Color.black); // Change la couleur d'arrière-plan en black

        /**************************************** TextField TfLink3 ****************************************/
        TfLink3.setText("C:\\");
        TfLink3.setFont(new Font("Consolas", Font.BOLD, 18));
        TfLink3.setForeground(Color.black);

        /**************************************** Panel PanelBtValiderEtAnnuler ****************************************/
        PanelBtValiderEtAnnuler.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        PanelBtValiderEtAnnuler.setBorder(BorderFactory.createEmptyBorder(20, 30,30,30));

        /**************************************** Bouton BtAnnuler ****************************************/
        BtAnnuler.setFocusPainted(false); // Empêche le focus auto
        BtAnnuler.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        BtAnnuler.setForeground(Color.red); //Change la couleur du texte en orange
        BtAnnuler.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras

        /**************************************** Bouton BtValider ****************************************/
        BtValider.setFocusPainted(false); // Empêche le focus auto
        BtValider.setBackground(Color.black); // Change la couleur d'arrière-plan en black
        BtValider.setForeground(Color.green); //Change la couleur du texte en orange
        BtValider.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras


    }

    public String choisirUnFichier(String filename){
        String filenameChoose = filename; // Initialise le filename avec le precedent nom

        //Création et ouverture d'un fileChooser
        JFileChooser jc = new JFileChooser();
        //jc.setCurrentDirectory(new File("C:\\"));//Change le répertoire de base
        int isChooseFichier = jc.showOpenDialog(this);

        //Si un fichier a été choisi
        if(isChooseFichier != 1){
            filenameChoose = jc.getSelectedFile().getAbsolutePath(); //Stock le dossier choisis dans la variable qui sera retourné
        }
        return filenameChoose;
    }

    public String choisirUnDossier(String directory){
        String directoryChoose = directory; // Initialise le filename avec le precedent nom

        //Création et ouverture d'un fileChooser
        JFileChooser jc = new JFileChooser();
        jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int isChooseFichier = jc.showSaveDialog(this);

        //Si un fichier a été choisi
        if(isChooseFichier != 1){
            directoryChoose = jc.getSelectedFile().getAbsolutePath(); //Stock le dossier choisis dans la variable qui sera retourné
        }

        return directoryChoose;
    }

    /**************************************** Getter BtAnnuler ****************************************/
    public JButton getBtAnnuler(){
        return BtAnnuler;
    }

    /**************************************** Getter BtValider ****************************************/
    public JButton getBtValider(){
        return BtValider;
    }

    /**************************************** Getter TfLink1 ****************************************/
    public JTextField getTfLink1(){
        return TfLink1;
    }

    /**************************************** Getter TfLink2 ****************************************/
    public JTextField getTfLink2(){
        return TfLink2;
    }

    /**************************************** Getter TfLink3 ****************************************/
    public JTextField getTfLink3(){
        return TfLink3;
    }




}
