package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.service.HibernateDataService;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> loadWarehouseState();
    public List<OrderHistoryItem> loadHistoryState();

    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

    /**
     * Commit business transaction - purchase of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(List<SoldItem> goods)
            throws VerificationFailedException;

    public void setModel(SalesSystemModel model);
    public void addItemToWarehouse(StockItem item) throws VerificationFailedException;
    public void endSession();
	public void editItemInWarehouse(StockItem item) throws VerificationFailedException;
    
}
