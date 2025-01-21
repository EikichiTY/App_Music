package et3_projet.Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

/*
Page d'action au sein d'une playliste : ecout suppression d'une chanson
 */
public class PagePlaylist_bis extends JFrame implements ActionListener {
    private ClasseUtilisateur utilisateur;
    private Paire<String, List<ClasseChanson>> liste_chansons;

    private JTable table;
    private JButton play;
    private JButton supprimer;

    private JScrollPane scrollPane;
    private TableauNonModifiable model;

    ClasseChanson chanson;

    int row;

    public PagePlaylist_bis(ClasseUtilisateur user, Paire<String, List<ClasseChanson>> playlist) {
        this.utilisateur = user;
        this.liste_chansons = playlist;
        this.setTitle("Playsliste : " + playlist.getPremier());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        if (liste_chansons.getSecond().isEmpty()) {
            JLabel vide = new JLabel("Aucun morceau n'a été ajouté");
            vide.setVerticalAlignment(JLabel.CENTER);
            vide.setHorizontalAlignment(JLabel.CENTER);
            this.add(vide);
        }
        else {
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Artiste");
            columnNames.add("Titre");
            model = new TableauNonModifiable(columnNames, 0);

            table = new JTable(model);
            scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            for (ClasseChanson morceau : liste_chansons.getSecond()) {
                addRow(morceau.getArtiste(),morceau.getTitre());
            }

            JPanel options = new JPanel();
            options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

            JPanel jouer_musique = new JPanel(new FlowLayout(FlowLayout.LEFT));
            play = new JButton("Jouer le morceau");
            play.addActionListener(this);
            play.setFocusable(false);
            play.setHorizontalAlignment(SwingConstants.CENTER);
            jouer_musique.add(play);
            options.add(jouer_musique);

            JPanel supprimer_musique = new JPanel(new FlowLayout(FlowLayout.LEFT));
            supprimer = new JButton("Supprimer le morceau");
            supprimer.addActionListener(this);
            supprimer.setFocusable(false);
            supprimer.setHorizontalAlignment(SwingConstants.CENTER);
            supprimer_musique.add(supprimer);
            options.add(supprimer_musique);

            getContentPane().add(options, BorderLayout.SOUTH);

            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        row = table.rowAtPoint(e.getPoint());
                        if ((row != -1)) {
                            chanson  = new ClasseChanson();
                            chanson = liste_chansons.getSecond().get(row);
                        }
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
    }

    private void addRow(String artiste,String titre) {
        String[] rowData = new String[2];
        rowData[0] = artiste;
        rowData[1]=titre;
        model.addRow(rowData);
        model.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play){
            chanson.jouerChanson();
        }
        if(e.getSource() == supprimer){
            List<ClasseChanson> chansons = utilisateur.toutes_playlist.get(liste_chansons.getPremier());
            chansons.remove(row);
            utilisateur.toutes_playlist.put(liste_chansons.getPremier(), chansons);
            model.removeRow(row);
            model.fireTableDataChanged();
        }
    }
}