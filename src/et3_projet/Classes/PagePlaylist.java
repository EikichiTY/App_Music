package et3_projet.Classes;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/*
Page de visualtion et action sur l'ensemble des playlistes
 */
public class PagePlaylist extends JFrame implements ActionListener {
    private ClasseUtilisateur utilisateur;
    private JTable table;
    private JButton acceder;
    private JButton supprimer;
    private JScrollPane scrollPane;
    private TableauNonModifiable model;
    private TableRowSorter<TableauNonModifiable> sorter;
    private String liste_action;
    private int row;


    public PagePlaylist(ClasseUtilisateur user) {
        this.utilisateur = user;

        this.setTitle("Playslistes");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        if (utilisateur.toutes_playlist.isEmpty()) {
            JLabel vide = new JLabel("Aucune playliste créée");
            vide.setVerticalAlignment(JLabel.CENTER);
            vide.setHorizontalAlignment(JLabel.CENTER);
            this.add(vide);
        }
        else {
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Nom de la Playliste");
            model = new TableauNonModifiable(columnNames, 0);

            table = new JTable(model);
            sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
            scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            for (Map.Entry<String, List<ClasseChanson>> mapentry : utilisateur.toutes_playlist.entrySet()) {
                addRow(mapentry.getKey());
            }
            JPanel options = new JPanel();
            options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

            JPanel acces = new JPanel(new FlowLayout(FlowLayout.LEFT));
            acceder = new JButton("Acceder a la Playliste");
            acceder.addActionListener(this);
            acceder.setFocusable(false);
            acceder.setHorizontalAlignment(SwingConstants.CENTER);
            acces.add(acceder);
            options.add(acces);

            JPanel suppression = new JPanel(new FlowLayout(FlowLayout.LEFT));
            supprimer = new JButton("Supprimer la Playliste");
            supprimer.addActionListener(this);
            supprimer.setFocusable(false);
            suppression.add(supprimer);
            options.add(suppression);

            getContentPane().add(options, BorderLayout.SOUTH);


            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        row = table.rowAtPoint(e.getPoint());
                        if ((row != -1)) {
                            liste_action = new String();
                            liste_action = (String) table.getValueAt(row, 0);
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

    private void addRow(String nom) {
        String[] rowData = new String[1];
        rowData[0] = nom;
        model.addRow(rowData);
        model.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == supprimer){
            utilisateur.supprimer_playlist(liste_action);
            model.removeRow(row);
            model.fireTableDataChanged();
        }

        if(e.getSource()==acceder){
            for (Map.Entry<String, List<ClasseChanson>> mapentry : utilisateur.toutes_playlist.entrySet()) {
                if (mapentry.getKey().equals(liste_action)) {
                    PagePlaylist_bis bis = new PagePlaylist_bis(utilisateur, new Paire<>(mapentry.getKey(), mapentry.getValue()));
                    this.dispose();
                }
            }
        }
    }
}