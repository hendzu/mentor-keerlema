package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SoldItemTest {
	StockItem item1;
	
	@Before
	public void setUP(){
		item1 = new StockItem(5L, "Komm", "Maitsev", 5.0);
	}
	
	@Test
    public void testGetSum(){
		SoldItem test = new SoldItem(item1, 3);
		assertEquals(test.getSum(), 15.0, 0.0001);
	};
	
	@Test
    public void testGetSumWithZeroQuantity(){
		SoldItem test = new SoldItem(item1, 0);
		assertEquals(test.getSum(), 0.0, 0.0001);
    };
}
