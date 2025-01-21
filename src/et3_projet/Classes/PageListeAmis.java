package et3_projet.Classes;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Vector;

/*
Affiche la liste d'amis de l'utilisateur sur une fenetre
 */
public class PageListeAmis extends JFrame{
    ClasseUtilisateur utilisateur;
    private TableauNonModifiable model;

    public PageListeAmis(ClasseUtilisateur user) {
        this.utilisateur = user;

        this.setTitle("Amis");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        if (utilisateur.liste_amis.isEmpty()) {
            JLabel vide = new JLabel("Aucun ami n'a été ajouté");
            vide.setVerticalAlignment(JLabel.CENTER);
            vide.setHorizontalAlignment(JLabel.CENTER);
            this.add(vide);
        } else {
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Amis");
            model = new TableauNonModifiable(columnNames, 0);
            JTable table = new JTable(model);
            TableRowSorter<TableauNonModifiable> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
            JScrollPane scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            for (String ami : utilisateur.liste_amis) {
                addRow(ami);
            }
        }
    }
    private void addRow(String nom) {
        String[] rowData = new String[1];
        rowData[0] = nom;
        model.addRow(rowData);
        model.fireTableDataChanged();
    }
}
