package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	
	private StockTableModel table;
	private StockItem item;
	@Before
	public void setUp(){
		table = new StockTableModel();
		item = new StockItem(5L, "Komm", "Maitsev", 5.0, 5);
	}
	@Test
    public void testValidateNameUniqueness(){};
    @Test
    public void testHasEnoughInStock(){
    	
    };
    
    @Test
    public void testGetItemByIdWhenItemExists(){
    	table.addItem(item);
    	assertEquals(table.getItemById(5).toString(),item.toString());
    };
    
    @Test(expected=NoSuchElementException.class)
    public void testGetItemByIdWhenThrowsException(){
    	table.getItemById(3);
    };
}
