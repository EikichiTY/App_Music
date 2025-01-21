package et3_projet.Classes;

import et3_projet.Exceptions.ExceptionUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static et3_projet.Classes.ClasseUtilisateur.liste_pseudo;

/*
Classe squelette du programme
Elle contient tout les element qui redirigent vers les fonctionnalites de l'application
 */
public class PageApplication extends JFrame implements ActionListener {

    ClasseUtilisateur utilisateur;

    JPanel contentPane;
    ImageIcon backgroundImage;
    ImageIcon back;
    JMenuBar menu;

    JMenu amis;
    JMenu logout;
    JMenu user;
    JMenu notification;

    JMenuItem liste;
    JMenuItem ajouter;
    JMenuItem retirer;
    JMenuItem enfant;

    JMenuItem voir_profil;

    JMenuItem exit;
    JMenuItem deconnexion;

    JMenuItem recommandation;

    JButton retour_arriere;
    JButton musique;
    JButton blindtest;
    JButton playlist;
    JButton voir_playlist;
    JButton new_playlist;
    JButton user_retirer;
    JButton user_ajouter;


    JLabel user_label;

    JTextField user_ami_ajout_retrait;
    TextInvisible new_playlist_nom;


    PageApplication(ClasseUtilisateur p){

        this.utilisateur = p;


        //background
        backgroundImage = new ImageIcon("src/et3_projet/Images/Background.jpg");
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        this.setContentPane(contentPane);
        this.setVisible(true);

        //button
        playlist = new JButton();
        playlist.setBounds(50,200, 250,100);
        playlist.addActionListener(this);
        playlist.setText("Playlists");
        playlist.setFocusable(false);

        musique = new JButton();
        musique.setBounds(350,200,250,100);
        musique.addActionListener(this);
        musique.setText("Musiques");
        musique.setFocusable(false);

        blindtest = new JButton();
        blindtest.setBounds(650,200, 250,100);
        blindtest.addActionListener(this);
        blindtest.setText("Blindtests");
        blindtest.setFocusable(false);

        user_retirer = new JButton();
        user_retirer.setBounds(80,250, 150,50);
        user_retirer.addActionListener(this);
        user_retirer.setText("Retirer l'ami");
        user_retirer.setFocusable(false);
        user_retirer.setVisible(false);

        user_ajouter = new JButton();
        user_ajouter.setBounds(80,250, 150,50);
        user_ajouter.addActionListener(this);
        user_ajouter.setText("Ajouter l'ami");
        user_ajouter.setFocusable(false);
        user_ajouter.setVisible(false);


        back = new ImageIcon(new ImageIcon("src/et3_projet/Images/retours.png").getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH));

        retour_arriere = new JButton(back);
        retour_arriere.setBounds(900,450, 50,50);
        retour_arriere.addActionListener(this);
        retour_arriere.setIcon(back);
        retour_arriere.setBackground(Color.white);
        retour_arriere.setVisible(false);

        new_playlist = new JButton();
        new_playlist.setBounds(150,200, 300,50);
        new_playlist.addActionListener(this);
        new_playlist.setText("Créer une playliste");
        new_playlist.setFocusable(false);
        new_playlist.setVisible(false);

        new_playlist_nom = new TextInvisible("Nom de la Playliste");
        new_playlist_nom.setBounds(150,275,300,50);
        new_playlist_nom.setVisible(false);

        voir_playlist = new JButton();
        voir_playlist.setBounds(550,200, 300,50);
        voir_playlist.addActionListener(this);
        voir_playlist.setText("Afficher les playlistes");
        voir_playlist.setFocusable(false);
        voir_playlist.setVisible(false);


        this.add(playlist);
        this.add(musique);
        this.add(blindtest);
        this.add(user_retirer);
        this.add(user_ajouter);
        this.add(retour_arriere);
        this.add(new_playlist);
        this.add(voir_playlist);


        //textfields
        user_ami_ajout_retrait = new JTextField();
        user_ami_ajout_retrait.setBounds(60,150,200,50);
        user_ami_ajout_retrait.addActionListener(this);
        user_ami_ajout_retrait.setVisible(false);

        this.add(user_ami_ajout_retrait);
        this.add(new_playlist_nom);


        //labels
        user_label = new JLabel(utilisateur.toString());
        user_label.setBounds(100,200,300,600);
        user_label.setVisible(false);
        user_label.setHorizontalTextPosition(JLabel.CENTER);
        user_label.setForeground(Color.white);
        this.add(user_label);

        //menu bar
        menu = new JMenuBar();

        amis = new JMenu("Amis");
        logout = new JMenu("Déconnexion");
        user = new JMenu("Utilisateur");
        notification = new JMenu("Notifications");

        menu.add(user);
        menu.add(notification);
        menu.add(amis);
        menu.add(logout);

        liste = new JMenuItem("Liste d'amis");
        ajouter = new JMenuItem("Ajouter des amis");
        retirer = new JMenuItem("Retirer des amis");
        enfant = new JMenuItem("Inscrire un membre enfant ");

        exit = new JMenuItem("Fermer l'application");
        deconnexion = new JMenuItem("Déconnexion");

        voir_profil = new JMenuItem("Votre Profil");

        recommandation = new JMenuItem("Recommandations");


        amis.add(liste);
        amis.add(ajouter);
        amis.add(retirer);
        amis.add(enfant);
        logout.add(exit);
        logout.add(deconnexion);
        notification.add(recommandation);
        user.add(voir_profil);

        if(utilisateur.age < 18){
            enfant.setVisible(false);
        }

        retirer.addActionListener(this);
        deconnexion.addActionListener(this);
        exit.addActionListener(this);
        enfant.addActionListener(this);
        ajouter.addActionListener(this);
        liste.addActionListener(this);
        user.addActionListener(this);
        voir_profil.addActionListener(this);
        recommandation.addActionListener(this);
        this.setJMenuBar(menu);

        this.setTitle("Plateforme de Musique");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispose();
        }
        if (e.getSource() == deconnexion) {
            this.dispose();
            new PageConnexion();
        }

        if(e.getSource()==musique){
            new PageChansons(utilisateur);
        }

        if(e.getSource() == voir_playlist){
            new PagePlaylist(this.utilisateur);
        }

        if(e.getSource()==recommandation){
            PageNotifications notification = new PageNotifications(this.utilisateur);
        }

        if(e.getSource()==blindtest){
            new PageBlindtest();
        }

        if(e.getSource()==enfant){
            new PageInscrireEnfant(utilisateur);
        }

        if (e.getSource() == ajouter) {
            user_ajouter.setVisible(true);
            user_ami_ajout_retrait.setVisible(true);
            playlist.setVisible(false);
            musique.setVisible(false);
            blindtest.setVisible(false);
            retour_arriere.setVisible(false);
            new_playlist.setVisible(false);
            voir_playlist.setVisible(false);
            new_playlist_nom.setVisible(false);
        }

        if (e.getSource() == retirer) {
            user_retirer.setVisible(true);
            user_ami_ajout_retrait.setVisible(true);
            playlist.setVisible(false);
            musique.setVisible(false);
            blindtest.setVisible(false);
            retour_arriere.setVisible(false);
            new_playlist.setVisible(false);
            voir_playlist.setVisible(false);
            new_playlist_nom.setVisible(false);
        }

        if(e.getSource()==user_retirer){
            try {
                utilisateur.retirer_amis(user_ami_ajout_retrait.getText());
                JOptionPane.showMessageDialog(this,(user_ami_ajout_retrait+" a ete retire de votre liste d'amis"), "Erreur", JOptionPane.INFORMATION_MESSAGE);
            } catch (ExceptionUtilisateur ex) {
                JOptionPane.showMessageDialog(this, "Pseudo déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            user_ami_ajout_retrait.setVisible(false);
            user_retirer.setVisible(false);
            playlist.setVisible(true);
            musique.setVisible(true);
            blindtest.setVisible(true);
        }

        if (e.getSource() == user_ajouter) {
            try {
                utilisateur.ajouter_amis(user_ami_ajout_retrait.getText());
                for (Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()) {
                    String key = mapentry.getKey();
                    ClasseUtilisateur p = mapentry.getValue();
                    if (key.equals(user_ami_ajout_retrait.getText())) {
                        p.liste_amis.add(utilisateur.username);
                    }
                }
                JOptionPane.showMessageDialog(this, "Ami :"+user_ami_ajout_retrait.getText()+ " a été ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (ExceptionUtilisateur ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } finally {
                user_ami_ajout_retrait.setVisible(false);
                user_ajouter.setVisible(false);
                playlist.setVisible(true);
                musique.setVisible(true);
                blindtest.setVisible(true);
            }
        }

        if(e.getSource()==voir_profil) {
            new PageUser(utilisateur);
        }

        if(e.getSource() == liste){
            new PageListeAmis(utilisateur);
        }

        if(e.getSource()==retour_arriere){
            playlist.setVisible(true);
            musique.setVisible(true);
            blindtest.setVisible(true);
            user_label.setVisible(false);
            retour_arriere.setVisible(false);
            new_playlist.setVisible(false);
            voir_playlist.setVisible(false);
            new_playlist_nom.setVisible(false);
        }

        if(e.getSource()==playlist){
            retour_arriere.setVisible(true);
            new_playlist.setVisible(true);
            voir_playlist.setVisible(true);
            new_playlist_nom.setVisible(true);
            playlist.setVisible(false);
            musique.setVisible(false);
            blindtest.setVisible(false);
        }

        if(e.getSource() == new_playlist){
            if(!new_playlist_nom.getText().equals("")){
                try {
                    utilisateur.creer_playlist(new_playlist_nom.getText());
                    JOptionPane.showMessageDialog(this, "La playliste "+new_playlist_nom.getText() + " a été crée", "Succès", JOptionPane.INFORMATION_MESSAGE);

                } catch (ExceptionUtilisateur ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}


