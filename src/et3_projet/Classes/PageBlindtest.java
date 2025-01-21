package et3_projet.Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*Cree une page blindtest sur 3 manche en jeu solo
*L'utilisateur voit son resultat a la fin
 */
public class PageBlindtest extends JFrame implements ActionListener {
    private List<ClasseChanson> chansons;
    private List<ClasseChanson> manchesEnCours;
    private ClasseChanson chansonCourante;
    private JTextArea areaParoles;
    private JTextField reponseArtiste;
    private JTextField reponseTitre;
    private JButton validerButton;
    private JLabel messageLabel;
    private JLabel labelArtiste;
    private JLabel labelTitre;
    private int score;
    private int mancheActuelle;

    public PageBlindtest() {
        new ClasseDatabase();
        chansons = new ArrayList<>(ClasseDatabase.data);
        Collections.shuffle(chansons); // Mélanger les chansons

        manchesEnCours = new ArrayList<>(3); // Sélectionner 3 chansons aléatoires
        for (int i = 0; i < 3; i++) {
            manchesEnCours.add(chansons.remove(0));
        }

        // Configuration de la fenêtre
        this.setTitle("Blind Test");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // zone de texte pour les paroles
        areaParoles = new JTextArea(5, 30);
        areaParoles.setEditable(false);
        areaParoles.setLineWrap(true);
        areaParoles.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(areaParoles);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(scrollPane, constraints);

        // champs de réponses
        labelArtiste = new JLabel("Artiste:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        mainPanel.add(labelArtiste, constraints);

        reponseArtiste = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(reponseArtiste, constraints);

        labelTitre = new JLabel("Titre:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(labelTitre, constraints);

        reponseTitre = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        mainPanel.add(reponseTitre, constraints);

        validerButton = new JButton("Valider");
        validerButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        mainPanel.add(validerButton, constraints);

        // fin de la partie
        messageLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        mainPanel.add(messageLabel, constraints);

        add(mainPanel, BorderLayout.CENTER);

        score = 0;
        mancheActuelle = 0;
        afficherChansonSuivante();
    }

    private void afficherChansonSuivante() {
        if (manchesEnCours.isEmpty()) {
            messageLabel.setText("Fin du jeu. Score final : " + score);
            validerButton.setEnabled(false);
            reponseArtiste.setEnabled(false);
            reponseTitre.setEnabled(false);
        } else {
            chansonCourante = manchesEnCours.remove(0);
            String paroles = chansonCourante.getParoles();
            int maxLength = 100;
            if (paroles.length() > maxLength) {
                paroles = paroles.substring(0, maxLength) + "...";
            }
            areaParoles.setText(paroles);
            reponseArtiste.setText("");
            reponseTitre.setText("");
            messageLabel.setText("Manche " + (mancheActuelle + 1) + "/3");
            mancheActuelle++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == validerButton) {
            String reponseArtiste = this.reponseArtiste.getText().trim().toLowerCase();
            String reponseTitre = this.reponseTitre.getText().trim().toLowerCase();
            String artisteCorrect = chansonCourante.getArtiste().toLowerCase();
            String titreCorrect = chansonCourante.getTitre().toLowerCase();

            if (reponseArtiste.equals(artisteCorrect)) {
                score += 1;
            }
            if (reponseTitre.equals(titreCorrect)) {
                score += 1;
            }
            afficherChansonSuivante();
        }
    }
}