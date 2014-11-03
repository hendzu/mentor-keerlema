package ee.ut.math.tvt.salessystem.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

/*	public List<StockItem> getStockitem() {
		List<StockItem> result = session.createQuery("from Stockitem").list();
		return result;
	}*/

	public List<StockItem> getStockitem() {
		return Collections.checkedList(session.createQuery("from Stockitem").list(), StockItem.class);
	}

	public List<SoldItem> getSoldItem() {
		List<SoldItem> result = session.createQuery("from Solditem").list();
		return result;
	}
/*
	public List<Speciality> getSpecialities() {
		List<Speciality> result = session.createQuery("from Speciality").list();
		return result;
	}*/

}
