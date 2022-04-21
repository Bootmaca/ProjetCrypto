import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoaderDechiffrerFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel PanelContainReste;
    public JLabel LTitre;
    private JPanel PanelContainTitre;
    private JButton BtValider;
    private JPanel PanelBtValider;
    private JLabel LImage;



    public LoaderDechiffrerFrame() {

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

        // Empêche la redimension du panel
        setResizable(false);

        // Change la couleur d'arrière plan en black
        mainPanel.setBackground(Color.black);


        /**************************************** Label LTitre ****************************************/
        LTitre.setFont(new Font("Consolas", Font.BOLD, 65)); // Change la police le style et met en gras
        LTitre.setForeground(Color.white); // Change La couleur du texte

        /**************************************** Panel PanelContainTitre ****************************************/
        PanelContainTitre.setBackground(Color.black); // Change la couleur d'arrière plan en black

        /**************************************** Panel PanelContainReste ****************************************/
        PanelContainReste.setBackground(Color.black); // Change la couleur d'arrière plan en black
        PanelContainReste.setBorder(BorderFactory.createEmptyBorder(0, 0,50,0));

        /**************************************** Panel PanelBtValider ****************************************/
        PanelBtValider.setBackground(Color.black); // Change la couleur d'arrière plan en black
        PanelBtValider.setBorder(BorderFactory.createEmptyBorder(0, 0,30,0));

        /**************************************** Bouton BtValider ****************************************/
        BtValider.setFocusPainted(false); // Empêche le focus auto
        BtValider.setBackground(Color.black); // Change la couleur d'arrière plan en black
        BtValider.setForeground(Color.green); //Change la couleur du texte en orange
        BtValider.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras
        BtValider.setEnabled(false);

        Icon iconLoader = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Image/loader.gif")));
        LImage.setIcon(iconLoader);
    }

    /**************************************** Fonction de fin du chargement ****************************************/
    public void fin() {
        this.LTitre.setText("Déchiffrement terminé !");
        this.BtValider.setEnabled(true);
        this.LImage.setIcon(null);
        Icon imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Image/Valide5.png")));
        this.LImage.setIcon(imgIcon);
    }

    /**************************************** Getteur BtValider ****************************************/
    public JButton getBtValider() {
        return BtValider;
    }



}
