package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class OrderHistoryTableModelTest {
	
	private OrderHistoryTableModel table;
	private OrderHistoryItem item;
	private SoldItem solditem;
	private StockItem stockitem;
	
	@Before
	public void setUp(){
		table = new OrderHistoryTableModel();
		item = new OrderHistoryItem();
		stockitem = new StockItem(5L, "Komm", "Maitsev", 5.0);
		solditem = new SoldItem();
	}
	
	@Test
	public void testgetColumnIfPresent(){
		solditem.setStockItem(stockitem);
		solditem.setQuantity(2);
		item.addSoldItem(solditem);
		table.addItem(item);
		assertEquals(10.0, (Double)table.getColumnValue(item, 1), 0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testgetColumnNotExcisting(){
		table.getColumnValue(item, 3);
	}
	
	@Test(expected=NullPointerException.class)
	public void testaddItem(){
		item = null;
		table.addItem(item);
	}
}
