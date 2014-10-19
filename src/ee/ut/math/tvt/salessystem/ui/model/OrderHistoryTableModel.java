package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
//TODO Luua klass orderite jaoks, siis muuta siin ära SoldItem order tüübiks
public class OrderHistoryTableModel extends SalesSystemTableModel<SoldItem>{

	public OrderHistoryTableModel() {
		super(new String[] { "Date", "Time", "Price"});
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

}
