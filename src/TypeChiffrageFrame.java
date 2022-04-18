import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TypeChiffrageFrame extends JFrame{
    private JPanel mainPanel;
    private JButton BtSimple;
    private JPanel PanelContainBt;
    private JButton BtMultiple;
    private JLabel LTitre;
    private JPanel PanelContainTitre;
    private JPanel PanelBtAnnuler;
    private JButton BtAnnuler;


    public TypeChiffrageFrame(){

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


        /**************************************** Bouton BtChiffrer ****************************************/
        BtSimple.setFocusPainted(false); // Empêche le focus auto
        BtSimple.setBackground(Color.black); // Change la couleur d'arrière plan en black
        BtSimple.setForeground(Color.orange); //Change la couleur du texte en orange
        BtSimple.setFont(new Font("Consolas", Font.BOLD, 45)); // Change la police le style et met en gras


        /**************************************** Bouton BtDechiffrer ****************************************/
        BtMultiple.setFocusPainted(false); //Empêche le focus auto
        BtMultiple.setBackground(Color.black); // Change la couleur d'arrière plan en black
        BtMultiple.setForeground(Color.orange); //Change la couleur du texte en orange
        BtMultiple.setFont(new Font("Consolas", Font.BOLD, 45)); // Change la police le style et met en gras


        /**************************************** Panel PanelContainBt ****************************************/
        PanelContainBt.setBackground(Color.black); // Change la couleur d'arrière plan en black

        /**************************************** Panel PanelContainBt ****************************************/
        PanelContainTitre.setBackground(Color.black); // Change la couleur d'arrière plan en black

        /**************************************** Panel PanelBtAnnuler ****************************************/
        PanelBtAnnuler.setBackground(Color.black); // Change la couleur d'arrière plan en black
        PanelBtAnnuler.setBorder(BorderFactory.createEmptyBorder(0, 30,25,30));

        /**************************************** Bouton BtAnnuler ****************************************/
        BtAnnuler.setFocusPainted(false); // Empêche le focus auto
        BtAnnuler.setBackground(Color.black); // Change la couleur d'arrière plan en black
        BtAnnuler.setForeground(Color.red); //Change la couleur du texte en orange
        BtAnnuler.setFont(new Font("Consolas", Font.BOLD, 25)); // Change la police le style et met en gras

    }

    public JButton getBtSimple() {
        return BtSimple;
    }

    public JButton getBtMultiple() {
        return BtMultiple;
    }

    public JButton getBtAnnuler() {
        return BtAnnuler;
    }
}
