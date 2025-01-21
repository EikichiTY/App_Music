package et3_projet.Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import et3_projet.Exceptions.ExceptionUtilisateur;

import static et3_projet.Classes.ClasseAdulte.nouvel_utilisateur;
import static et3_projet.Classes.ClasseUtilisateur.liste_pseudo;

public class PageConnexion extends JFrame implements ActionListener {

    ClasseUtilisateur utilisateur;

    JButton connexion;
    JButton inscription;
    JButton submit_inscription;
    JButton submit_connexion;
    JButton retour_arriere;

    TextInvisible connexion_pseudo;
    TextInvisible connexion_mdp;
    TextInvisible inscription_nom;
    TextInvisible inscription_prenom;
    TextInvisible inscription_pseudo;
    TextInvisible inscription_mdp;
    TextInvisible inscription_age;

    ImageIcon back ;
    ImageIcon backgroundImage;
    JPanel fondecran;


    /* La classe PageConnexion permet de creer une fenetre sur laquelle l'utilisateur se connecte, ou s'inscrit.
     * il y a une verification de l'age et de la disponibilite du pseudo a l'incription
     * il y a une verfication des champs lors de la connexion
     * */
    public PageConnexion(){
        this.setTitle("Login / Sign in");


        //background
        backgroundImage = new ImageIcon("src/et3_projet/Images/loginbground.jpg");
        fondecran = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        this.setContentPane(fondecran);
        this.setVisible(true);


        //instanciation des bouttons
        connexion = new JButton();
        connexion.setBounds(300,200, 200,50);
        connexion.addActionListener(this);
        connexion.setText("Connexion");
        connexion.setFocusable(false);

        inscription = new JButton();
        inscription.setBounds(300,300,200,50);
        inscription.addActionListener(this);
        inscription.setText("Inscription");
        inscription.setFocusable(false);

        submit_connexion = new JButton();
        submit_connexion.setBounds(600,200, 100,70);
        submit_connexion.addActionListener(this);
        submit_connexion.setText("Connexion");
        submit_connexion.setFocusable(false);

        submit_inscription = new JButton();
        submit_inscription.setBounds(600,200, 100,70);
        submit_inscription.addActionListener(this);
        submit_inscription.setText("Inscription");
        submit_inscription.setFocusable(false);


        back = new ImageIcon(new ImageIcon("src/et3_projet/Images/retours.png").getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH));
        
        
        retour_arriere = new JButton(back);
        retour_arriere.setBounds(700,450, 50,50);
        retour_arriere.addActionListener(this);
        retour_arriere.setIcon(back);
        retour_arriere.setBackground(Color.white);


        //instanciation des champs inscription
        inscription_nom = new TextInvisible("Nom");
        inscription_nom.setBounds(50,10,400,50);
        inscription_nom.addActionListener(this);

        inscription_prenom = new TextInvisible("Prenom");
        inscription_prenom.setBounds(50,110,400,50);
        inscription_prenom.addActionListener(this);

        inscription_age = new TextInvisible("Age");
        inscription_age.setBounds(50,210,400,50);
        inscription_age.addActionListener(this);

        inscription_pseudo = new TextInvisible("Pseudo");
        inscription_pseudo.setBounds(50,310,400,50);
        inscription_pseudo.addActionListener(this);

        inscription_mdp = new TextInvisible("MDP");
        inscription_mdp.setBounds(50,410,400,50);
        inscription_mdp.addActionListener(this);


        //instanciation des champs connexion
        connexion_pseudo = new TextInvisible("Pseudo");
        connexion_pseudo.setBounds(50,150,400,50);
        connexion_pseudo.addActionListener(this);

        connexion_mdp = new TextInvisible("MDP");
        connexion_mdp.setBounds(50,300,400,50);
        connexion_mdp.addActionListener(this);


        //reste des parametres
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,550);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.add(connexion);
        this.add(inscription);

        this.add(connexion_pseudo);
        this.add(connexion_mdp);
        this.add(inscription_nom);
        this.add(inscription_prenom);
        this.add(inscription_pseudo);
        this.add(inscription_mdp);
        this.add(inscription_age);
        this.add(submit_connexion);
        this.add(submit_inscription);
        this.add(retour_arriere);


        connexion_pseudo.setVisible(false);
        connexion_mdp.setVisible(false);
        inscription_nom.setVisible(false);
        inscription_prenom.setVisible(false);
        inscription_pseudo.setVisible(false);
        inscription_mdp.setVisible(false);
        inscription_age.setVisible(false);
        submit_inscription.setVisible(false);
        submit_connexion.setVisible(false);
        retour_arriere.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==connexion){
            connexion.setVisible(false);
            inscription.setVisible(false);
            connexion_pseudo.setVisible(true);
            connexion_mdp.setVisible(true);
            submit_connexion.setVisible(true);
            retour_arriere.setVisible(true);

        }

        if(e.getSource() == inscription){
            connexion.setVisible(false);
            inscription.setVisible(false);
            inscription_nom.setVisible(true);
            inscription_prenom.setVisible(true);
            inscription_pseudo.setVisible(true);
            inscription_mdp.setVisible(true);
            inscription_age.setVisible(true);
            submit_inscription.setVisible(true);
            retour_arriere.setVisible(true);

        }

        if(e.getSource()== retour_arriere){
            connexion_pseudo.setVisible(false);
            connexion_mdp.setVisible(false);
            inscription_nom.setVisible(false);
            inscription_prenom.setVisible(false);
            inscription_pseudo.setVisible(false);
            inscription_mdp.setVisible(false);
            inscription_age.setVisible(false);
            submit_inscription.setVisible(false);
            submit_connexion.setVisible(false);
            retour_arriere.setVisible(false);
            connexion.setVisible(true);
            inscription.setVisible(true);
        }

        if (e.getSource() == submit_inscription) {
            if (inscription_nom.getText().equals("") || inscription_prenom.getText().equals("") || inscription_pseudo.getText().equals("") || inscription_mdp.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Vous devez remplir tous les champs pour vous inscrire", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean dejainscrit = false;
            for (Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()) {
                String key = mapentry.getKey();
                if (key.equals(inscription_pseudo.getText())) {
                    dejainscrit = true;
                    break;
                }
            }
            if (!dejainscrit) {
                try {
                    utilisateur = (ClasseAdulte) nouvel_utilisateur(inscription_nom.getText(), inscription_prenom.getText(), Integer.parseInt(inscription_age.getText()), inscription_pseudo.getText(), inscription_mdp.getText());
                    JOptionPane.showMessageDialog(this, inscription_pseudo.getText() + " a été inscrit", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } catch (ExceptionUtilisateur ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pseudo déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == submit_connexion) {
            boolean pseudoTrouve = false;
            for (Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()) {
                String key = mapentry.getKey();
                ClasseUtilisateur p_value = mapentry.getValue();
                if (key.equals(connexion_pseudo.getText())) {
                    pseudoTrouve = true;
                    if (p_value.mdp.equals(connexion_mdp.getText())) {
                        PageApplication app = new PageApplication(p_value);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
            if (!pseudoTrouve) {
                JOptionPane.showMessageDialog(this, "Pseudo incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
