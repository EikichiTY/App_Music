package et3_projet.Classes;

import javax.swing.*;
import java.awt.*;

/*
Permet l'affichage des paroles d'une chanson sur une fenetre
 */

public class PageLecture extends JFrame {
    public PageLecture(String texte,String titre) {
        this.setTitle(titre);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // création d'un panneau pour y ajouter les composants
        JPanel panneau = new JPanel();
        panneau.setSize(this.getSize());
        this.setContentPane(panneau);

        // création d'une zone de texte
        Container conteneur = this.getContentPane();
        conteneur.setLayout(new BorderLayout());
        JTextArea zoneTexte = new JTextArea();
        zoneTexte.setEditable(false);
        zoneTexte.append(texte);
        zoneTexte.setSize(panneau.getSize());
        panneau.add(zoneTexte);


        this.setVisible(true);
        conteneur.add(new JScrollPane(zoneTexte), BorderLayout.CENTER);
    }

}

