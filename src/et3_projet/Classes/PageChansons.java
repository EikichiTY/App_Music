package et3_projet.Classes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Vector;
import static et3_projet.Classes.ClasseDatabase.data;
import static et3_projet.Classes.ClasseUtilisateur.liste_pseudo;

/* La classe PageChansoon permet de creer une fenetre sur laquelle l'utilisateur visualise toutes les chansons de la classe ClasseDatabase
* il peut ecouter, ajouter a une playliste et envoyer un morceau a un ami
* */

public class PageChansons extends JFrame implements ActionListener {
    private ClasseUtilisateur utilisateur;
    private ClasseChanson chanson;
    private ClasseDatabase database;

    private JTable table;
    private TableauNonModifiable model;
    private TableRowSorter<TableauNonModifiable> sorter;

    private JScrollPane scrollPane;
    private JPanel searchPanel;
    private JPanel envoyerPanel;
    private JPanel playlistPanel;
    private JPanel lancerMusique;

    private JTextField searchField;
    private JTextField envoiAmiField;
    private JTextField nom_playlist;

    private JButton envoyer_musique;
    private JButton ajoutPlaylist;
    private JButton play;
    private JButton recherche;



    public PageChansons(ClasseUtilisateur utilisateur) {
        this.utilisateur = utilisateur;

        database = new ClasseDatabase();
        this.setTitle("Musiques");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        // on cree "model" un tableau non modifiable trié
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Artiste");
        columnNames.add("Titre");
        columnNames.add("Paroles");
        model = new TableauNonModifiable(columnNames, 0);

        // on cree une jtable grace a model
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // on ajoute la base de donnees
        for (int i = 0; i < data.size(); i++)
            addRow(data.get(i).getArtiste(), data.get(i).getTitre(), data.get(i).getParoles());


        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        recherche = new JButton("Rechercher");
        recherche.setFocusable(false);
        recherche.setEnabled(false);
        searchPanel.add(recherche);
        searchField = new JTextField(25);
        searchPanel.add(searchField);
        getContentPane().add(searchPanel, BorderLayout.NORTH);

        JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));


        envoyerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        envoyer_musique = new JButton("Envoyer à un ami : ");
        envoyer_musique.addActionListener(this);
        envoyer_musique.setFocusable(false);
        envoyerPanel.add(envoyer_musique);
        envoiAmiField = new JTextField(18);
        envoyerPanel.add(envoiAmiField);
        options.add(envoyerPanel);

        playlistPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ajoutPlaylist = new JButton("Ajouter a une playliste : ");
        ajoutPlaylist.addActionListener(this);
        ajoutPlaylist.setFocusable(false);
        playlistPanel.add(ajoutPlaylist);
        nom_playlist = new JTextField(15);
        playlistPanel.add(nom_playlist);
        options.add(playlistPanel);

        lancerMusique = new JPanel(new FlowLayout(FlowLayout.CENTER));
        play = new JButton("Jouer la musique");
        play.addActionListener(this);
        lancerMusique.add(play);
        options.add(lancerMusique);

        getContentPane().add(options, BorderLayout.SOUTH);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(searchField.getText());
            }
        });


        // Ajouter un MouseListener pour détecter les clics sur les lignes
        // lecture de chanson par envoie dans le terminal
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int row = table.rowAtPoint(e.getPoint());
                    if ((row != -1)) {
                        chanson = new ClasseChanson((String) table.getValueAt(row, 0), (String) table.getValueAt(row, 1), (String) table.getValueAt(row, 2));
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

    private void addRow(String artiste, String titre, String paroles) {
        String[] rowData = new String[3];
        rowData[0] = artiste;
        rowData[1] = titre;
        rowData[2] = paroles;
        model.addRow(rowData);
    }

    private void filterData(String searchText) {
        searchText = searchField.getText().toLowerCase();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == envoyer_musique) {
            for (String ami : utilisateur.liste_amis) {
                if (ami.equals(envoiAmiField.getText())) {
                    for (Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()) {
                        if (mapentry.getKey().equals(ami)) {
                            utilisateur.recommander_chanson(mapentry.getValue(), chanson);
                        }
                    }
                }
            }
        }

        if (e.getSource() == play) {
            chanson.jouerChanson();
        }

        if (e.getSource() == ajoutPlaylist) {
            utilisateur.ajouter_playlist(nom_playlist.getText(),chanson);
        }
    }
}

