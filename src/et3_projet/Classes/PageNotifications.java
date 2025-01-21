package et3_projet.Classes;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Vector;

/*Affiche les notifications des recommandations de chanson envoyees par les amis

 */
public class PageNotifications extends JFrame {
    private ClasseUtilisateur utilisateur;
    private JTable table;
    private JScrollPane scrollPane;
    private TableauNonModifiable model;
    private TableRowSorter<TableauNonModifiable> sorter;


    public PageNotifications(ClasseUtilisateur user) {
        this.utilisateur = user;

        this.setTitle("Recommandations");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);



        if (utilisateur.notification.isEmpty()) {
            JLabel vide = new JLabel("Pas de nouvelles recommandations");
            vide.setVerticalAlignment(JLabel.CENTER);
            vide.setHorizontalAlignment(JLabel.CENTER);
            this.add(vide);
        }
        else {
            // Créer un NonEditableTableModel avec les noms de colonnes
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Ami");
            columnNames.add("Artiste");
            columnNames.add("Titre");
            model = new TableauNonModifiable(columnNames, 0);

            // Créer le JTable avec le modèle
            table = new JTable(model);
            sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
            scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            for (int i = 0; i < utilisateur.notification.size(); i++) {
                addRow(utilisateur.notification.get(i)[0], utilisateur.notification.get(i)[1], utilisateur.notification.get(i)[2]);
            }
        }
    }




    private void addRow(String ami, String artiste, String titre) {
        String[] rowData = new String[3];
        rowData[0] = ami;
        rowData[1] = artiste;
        rowData[2] = titre;
        model.addRow(rowData);
    }
}


