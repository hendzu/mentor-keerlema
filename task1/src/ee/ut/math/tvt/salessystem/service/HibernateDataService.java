package ee.ut.math.tvt.salessystem.service;

import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();
	
	public<T> void addItem(T item)
	{
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
	}
	
	public<T> void updateItem(T item)
	{
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
	}
	
	public List<StockItem> getStockitem() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}
	
	public StockItem getStockItem(Long id)
	{
		return (StockItem)session.get(StockItem.class, id);
	}

	public List<SoldItem> getSoldItem() {
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}

	public List<OrderHistoryItem> getOrderHistoryItem() {
		List<OrderHistoryItem> result = session.createQuery("from OrderHistoryItem").list();
		return result;
	}

}
