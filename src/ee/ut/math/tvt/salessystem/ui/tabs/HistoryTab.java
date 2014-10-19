package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	private final SalesDomainController domainController;
	private SalesSystemModel model;
    // TODO - implement!

    public HistoryTab(SalesDomainController domainController, SalesSystemModel model){
    	this.domainController = domainController;
    	this.model = model;
    } 
    
    public Component draw() {
    	JPanel panel = new JPanel();
    	JTable table = new JTable(model.getOrderHistoryTableModel());

        JTableHeader header = table.getTableHeader();
   	    header.setReorderingAllowed(false);
   	    
        JScrollPane scrollPane = new JScrollPane(table);
        
        GridBagConstraints gc = new GridBagConstraints();
   	    GridBagLayout gb = new GridBagLayout();
   	    gc.fill = GridBagConstraints.BOTH;
   	    gc.weightx = 1.0;
   	    gc.weighty = 1.0;
   	    
        panel.setLayout(gb);
   	    panel.add(scrollPane, gc);
    	panel.setBorder(BorderFactory.createTitledBorder("Order History"));
        return panel;
    }
}