package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;

public abstract class AbstractSalesSystemTableModel<T extends DisplayableItem> extends
        AbstractTableModel {

    private static final long serialVersionUID = 1L;

    protected final String[] headers;

    public AbstractSalesSystemTableModel(final String[] headers) {
        this.headers = headers;
    }

    /**
     * @param item
     *            item describing selected row
     * @param columnIndex
     *            selected column index
     * @return value displayed in column with specified index
     */
    protected abstract Object getColumnValue(T item, int columnIndex);

    public int getColumnCount() {
        return headers.length;
    }

    public abstract int getRowCount();

    public abstract Object getValueAt(final int rowIndex, final int columnIndex); 

    // search for item with the specified id
    public abstract T getItemById(final long id); 

    public abstract List<T> getTableRows(); 

    public abstract void clear(); 

    public abstract void populateWithData(final List<T> data);
    
    public abstract void addRow(T row); 
}
