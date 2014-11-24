package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;

/**
 * Generic table model implementation suitable for extending.
 */
public abstract class SalesSystemTableModel<T extends DisplayableItem> extends 
AbstractSalesSystemTableModel<T> {

    private static final long serialVersionUID = 1L;

   // protected List<T> rows;

    public SalesSystemTableModel(final String[] headers) {
    	super(headers);
     //   rows = new ArrayList<T>();
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

    public int getRowCount() {
        //return rows.size();
    	return getTableRows().size();
    }

    public Object getValueAt(final int rowIndex, final int columnIndex) {
        //return getColumnValue(rows.get(rowIndex), columnIndex);
        return getColumnValue(getTableRows().get(rowIndex), columnIndex);
    }

    // search for item with the specified id
    public T getItemById(final long id) {
       // for (final T item : rows) 
    	for (final T item : getTableRows()){
            if (item.getId() == id)
                return item;
        }
        throw new NoSuchElementException();
    }

    public abstract List<T> getTableRows();
    //{
     //   return rows;
    //}
    
    public void clear(){
    	getTableRows().clear();
    	fireTableDataChanged();
    }
//    public void clear() {
 //       rows = new ArrayList<T>();
  //      fireTableDataChanged();
    //}

    public void populateWithData(final List<T> data)
    {
   //     rows.clear();
     //   rows.addAll(data);
        getTableRows().clear();
        getTableRows().addAll(data);
    }
    
    public void addRow(T row)
    {
        //rows.add(row);
    	getTableRows().add(row);
        fireTableDataChanged();
    }
    
    public T getRow(int index) {
       // return rows.get(index);
    	return getTableRows().get(index);
    }
}
