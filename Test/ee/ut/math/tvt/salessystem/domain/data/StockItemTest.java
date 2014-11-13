package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {
	
	StockItem item1;
	
	@Before
	public void setUp(){
		item1 = new StockItem(5L, "Komm", "Maitsev", 5.0);
	}
	
	@Test
    public void testClone(){
		StockItem item2 = (StockItem) item1.clone();
		assertEquals(item2.toString(),item1.toString());
	};
    
    @Test
    public void testGetColumn(){
    	assertEquals(item1.getColumn(0),5L);
    }; 
}
