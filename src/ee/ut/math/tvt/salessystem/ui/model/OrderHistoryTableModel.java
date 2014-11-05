package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;

public class OrderHistoryTableModel extends SalesSystemTableModel<OrderHistoryItem>{
	private static final Logger log = Logger.getLogger(OrderHistoryTableModel.class);
	private static final long serialVersionUID = 1L;

	public OrderHistoryTableModel() {
		super(new String[] { "Date:Time", "Price"});
	}

	@Override
	protected Object getColumnValue(OrderHistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getTime();
		case 1:
			return item.getPrice();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");
		for (final OrderHistoryItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getTime() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	public void addItem(final OrderHistoryItem item){
		rows.add(item);
        log.debug("Added order Time: " + item.getTime() + " Price: " + item.getPrice());
        fireTableDataChanged();
	}

}
