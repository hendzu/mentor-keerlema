package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HistoryItemTest {
	
	OrderHistoryItem sale;
	ArrayList<SoldItem> items;
	SoldItem item1;
	SoldItem item2;
	SoldItem item3;
	
    
	@Before
	public void setUp(){
    	item1 = new SoldItem(new StockItem(4l,"lol", "", 5.0), 2);
    	item2 = new SoldItem(new StockItem(5l,"lol", "", 8.0), 2);
    	item3 = new SoldItem(new StockItem(6l,"lol", "", 9.0), 2);
	}
    @Test
    void testAddSoldItem(){
    	items = new ArrayList<SoldItem>();
    	items.add(item1);
    	sale = new OrderHistoryItem();
    	sale.setItems(items);
    };
    
    @Test
    void testGetSumWithNoItems(){
    	sale = new OrderHistoryItem();
    	assertEquals(sale.getPrice(), 0.0, 0.00001);
    };
    
    @Test
    void testGetSumWithOneItem(){
    	items = new ArrayList<SoldItem>();
    	items.add(item1);
    	sale = new OrderHistoryItem(10.0 , items);
    	assertEquals(sale.getPrice(), 10.0, 0.00001);
    };
    
    @Test
    void testGetSumWithMultipleItems(){
    	items = new ArrayList<SoldItem>();
    	items.add(item1);
    	items.add(item2);
    	items.add(item3);
    	sale = new OrderHistoryItem(10.0 , items);
    	assertEquals(sale.getPrice(), 44.0, 0.00001);
    }; 
}
