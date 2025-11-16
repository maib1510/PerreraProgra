package gui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Domain.Producto;

public class TiendaModel extends AbstractTableModel {

    private final List<Producto> productos;
    private final String[] columnas = {
        "ID", 
        "Nombre", 
        "Categoría", 
        "Precio (€)", 
        "Unidades Disponibles", 
        "Agotado"
    };

    public TiendaModel(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = productos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getId();
            case 1 -> p.getNombre();
            case 2 -> p.getCategoria();
            case 3 -> p.getPrecio();
            case 4 -> p.getUnidadesDisponibles();
            case 5 -> p.isAgotado();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 3 -> Double.class;        // Precio
            case 4 -> Integer.class;       // Unidades
            case 5 -> Boolean.class;       // Agotado
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
