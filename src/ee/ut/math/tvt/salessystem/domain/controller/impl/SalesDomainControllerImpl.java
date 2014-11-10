package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.service.HibernateDataService;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private Logger logger = Logger.getLogger(SalesDomainControllerImpl.class);
	private SalesSystemModel model; 
	private HibernateDataService service;

	
	public SalesDomainControllerImpl(HibernateDataService service)
	{
		this.service = service;
	}
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		

		// Let's not assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		//throw new VerificationFailedException("Underaged!");
		double sum = 0;
		for(SoldItem item: goods){
			sum += item.getSum();
		}

		OrderHistoryItem historyItem = new OrderHistoryItem(sum, goods);
		service.addItem(historyItem);
		model.getOrderHistoryTableModel().addItem(historyItem);
		
		for (SoldItem item : goods){
			StockItem warehouseItem = model.getWarehouseTableModel().getItemById(item.getId());
			warehouseItem.setQuantity(warehouseItem.getQuantity() - item.getQuantity());
			service.updateItem(warehouseItem);
		}
		
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}
	
	public void addItemToWarehouse(StockItem item) throws VerificationFailedException 
	{
		StockItem existingItem = service.getStockItem(item.getId());
		if (existingItem == null)
		{
			service.addItem(item);
			model.getWarehouseTableModel().addItem(item);
			logger.debug(item + " added to stock table.");
		}
		else
		{
			int addQuantity = item.getQuantity();
			existingItem.setQuantity(existingItem.getQuantity() + addQuantity);
			service.updateItem(existingItem);
			model.getWarehouseTableModel().fireTableDataChanged();
			logger.debug(item.getName() + "'s quantity increased by " + addQuantity);			
		}
		
		
		//model.getWarehouseTableModel().addItem(item);
	}

	public List<StockItem> loadWarehouseState() 
	{
		
		List<StockItem> dataset = new ArrayList<StockItem>();
		dataset.addAll(service.getStockitem());

/*		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

		dataset.add(chips);
		dataset.add(chupaChups);
		dataset.add(frankfurters);
		dataset.add(beer);
		System.out.println(dataset.get(0).getId());*/
		
		return dataset;
	}
	
	public List<OrderHistoryItem> loadHistoryState() {
		List<OrderHistoryItem> dataset = new ArrayList<OrderHistoryItem>();
		dataset = service.getOrderHistoryItem();
		
		return dataset;
	}
	
	@Override
	public void setModel(SalesSystemModel model) {
		this.model = model;
	}

	@Override
	public void endSession() {
		HibernateUtil.closeSession();
	}
}
