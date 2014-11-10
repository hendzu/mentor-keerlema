	package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.service.HibernateDataService;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
    // Warehouse model
    private StockTableModel warehouseTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;
    
    //History model
    private OrderHistoryTableModel orderHistoryTableModel;

    private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        this.domainController.setModel(this);
        this.domainController.setService(new HibernateDataService());
        
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();
        orderHistoryTableModel = new OrderHistoryTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        orderHistoryTableModel.populateWithData(domainController.loadHistoryState());

    }

    public OrderHistoryTableModel getOrderHistoryTableModel() {
		return orderHistoryTableModel;
	}

	public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }
    
}
