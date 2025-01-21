package et3_projet.Classes;

import javax.swing.*;
import java.awt.*;


/*
Renvoit les information de l'utilisateur dans une fenetre
 */
public class PageUser extends JFrame{
    public PageUser(ClasseUtilisateur user) {
        this.setTitle("Votre Profil");
        this.setSize(200, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel panneau = new JPanel();
        panneau.setSize(this.getSize());
        this.setContentPane(panneau);

        Container conteneur = this.getContentPane();
        conteneur.setLayout(new BorderLayout());
        JTextArea zoneTexte = new JTextArea();
        zoneTexte.setEditable(false);
        zoneTexte.append(user.toString());
        zoneTexte.setSize(panneau.getSize());
        panneau.add(zoneTexte);

        this.setVisible(true);
        conteneur.add(new JScrollPane(zoneTexte), BorderLayout.CENTER);
    }
}
