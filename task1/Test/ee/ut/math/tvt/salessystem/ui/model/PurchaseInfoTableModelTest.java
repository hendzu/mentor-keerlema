package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class PurchaseInfoTableModelTest {
	private PurchaseInfoTableModel table;
	private SoldItem solditem;
	private SoldItem solditem2;
	private StockItem stockitem;
	
	@Before
	public void setUp(){
		table = new PurchaseInfoTableModel();
		stockitem = new StockItem(5L, "Komm", "Maitsev", 5.0,5);
		solditem = new SoldItem();
	}
	
	@Test
	public void testgetColumnIfPresent(){
		solditem = new SoldItem(stockitem, 2);
		solditem2 = new SoldItem(stockitem, 4);
		table.addItem(solditem);
		System.out.println(table.getColumnValue(solditem, 4));
		assertEquals(10.0, (Double)table.getColumnValue(solditem, 4),0.0001);
		table.addItem(solditem2);
		assertEquals(25.0, (Double)table.getColumnValue(solditem, 4),0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testgetColumnNotExcisting(){
		table.getColumnValue(solditem, 8);
	}
	
	@Test(expected=NullPointerException.class)
	public void testaddItem(){
		solditem = null;
		table.addItem(solditem);
	}
}
