package et3_projet.Classes;
import et3_projet.Exceptions.ExceptionUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static et3_projet.Classes.ClasseUtilisateur.liste_pseudo;

public class PageInscrireEnfant extends JFrame implements ActionListener {

    ImageIcon backgroundImage;
    JPanel fondecran;
    TextInvisible inscription_nom;
    TextInvisible inscription_prenom;
    TextInvisible inscription_pseudo;
    TextInvisible inscription_mdp;
    TextInvisible inscription_age;
    JButton submit_inscription;
    ClasseUtilisateur utilisateur;

    //page d'iscription d'un enfant permet de detourner l'exception sur l'age qu'on dans la classe PageConnexion
    //reprends les meme elements
    //modification de l'action du bouton submit_inscription

    public PageInscrireEnfant(ClasseUtilisateur user) {
        this.setTitle("Inscrire un enfant");
        this.utilisateur = user;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 550);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        backgroundImage = new ImageIcon("src/et3_projet/Images/loginbground.jpg");
        fondecran = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondecran.setLayout(null);
        this.setContentPane(fondecran);

        inscription_nom = new TextInvisible("Nom");
        inscription_nom.setBounds(50, 10, 400, 50);
        inscription_nom.addActionListener(this);

        inscription_prenom = new TextInvisible("Prenom");
        inscription_prenom.setBounds(50, 110, 400, 50);
        inscription_prenom.addActionListener(this);

        inscription_age = new TextInvisible("Age");
        inscription_age.setBounds(50, 210, 400, 50);
        inscription_age.addActionListener(this);

        inscription_pseudo = new TextInvisible("Pseudo");
        inscription_pseudo.setBounds(50, 310, 400, 50);
        inscription_pseudo.addActionListener(this);

        inscription_mdp = new TextInvisible("MDP");
        inscription_mdp.setBounds(50, 410, 400, 50);
        inscription_mdp.addActionListener(this);

        submit_inscription = new JButton("Inscription");
        submit_inscription.setBounds(600, 200, 100, 70);
        submit_inscription.addActionListener(this);
        submit_inscription.setFocusable(false);

        fondecran.add(inscription_nom);
        fondecran.add(inscription_prenom);
        fondecran.add(inscription_age);
        fondecran.add(inscription_pseudo);
        fondecran.add(inscription_mdp);
        fondecran.add(submit_inscription);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit_inscription) {
            if (inscription_nom.getText().isEmpty() || inscription_prenom.getText().isEmpty() || inscription_pseudo.getText().isEmpty() || inscription_mdp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vous devez remplir tous les champs pour inscrire l'enfant", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean dejainscrit = false;
            for (Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()) {
                if (mapentry.getKey().equals(inscription_pseudo.getText())) {
                    dejainscrit = true;
                }
            }
            if (!dejainscrit) {
                try {
                    utilisateur.inscrire_enfant(inscription_nom.getText(), inscription_prenom.getText(), Integer.parseInt(inscription_age.getText()), inscription_pseudo.getText(), inscription_mdp.getText());
                    JOptionPane.showMessageDialog(this, inscription_pseudo.getText() + " a été inscrit", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } catch (ExceptionUtilisateur ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pseudo déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
