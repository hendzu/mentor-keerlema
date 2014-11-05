package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

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
	
	private SalesSystemModel model; 
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		
		// Let's not assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		//throw new VerificationFailedException("Underaged!");
		double sum= 0;
		for(SoldItem item: goods){
			sum += item.getSum();
		}

		model.getOrderHistoryTableModel().addItem(
				new OrderHistoryItem(sum, goods));
		
		for (SoldItem item : goods){
			model.getWarehouseTableModel().getItemById(item.getId()).setQuantity(
					model.getWarehouseTableModel().getItemById(item.getId()).getQuantity() 
					- item.getQuantity());
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
		model.getWarehouseTableModel().addItem(item);
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		HibernateDataService service = new HibernateDataService();
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
		// XXX mock implementation
		HibernateDataService service = new HibernateDataService();
		List<OrderHistoryItem> dataset = new ArrayList<OrderHistoryItem>();
		dataset = service.getSpecialities();
		
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
