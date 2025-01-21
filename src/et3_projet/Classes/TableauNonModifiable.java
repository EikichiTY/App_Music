package et3_projet.Classes;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableauNonModifiable extends DefaultTableModel {
    public TableauNonModifiable(Vector<String> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Toutes les cellules sont non modifiables
    }
}
