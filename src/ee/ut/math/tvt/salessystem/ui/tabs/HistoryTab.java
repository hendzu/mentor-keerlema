package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	private final SalesDomainController domainController;
	private SalesSystemModel model;
	private ArrayList<OrderHistoryItem> historylist = new ArrayList<OrderHistoryItem>();

	private JTable InfoTable;

    public HistoryTab(SalesDomainController domainController, SalesSystemModel model){
    	this.domainController = domainController;
    	this.model = model;
    	
    	InfoTable = new JTable(model.getCurrentPurchaseTableModel());
    } 
    
    public Component draw() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.WEST;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 0.2;
        gc.weighty = 1.0;
        panel.add(drawHistoryMainPane(), gc);
        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawPurchaseInfoPane(), gc);
        return panel;
      }
    
    
    public Component drawHistoryMainPane() {
    	JPanel panel = new JPanel();
        GridBagConstraints gc = new GridBagConstraints();
   	    GridBagLayout gb = new GridBagLayout();
   	    gc.fill = GridBagConstraints.BOTH;
   	    gc.weightx = 1.0;
   	    gc.weighty = 1.0;
   	    
        panel.setLayout(gb);
    	panel.setBorder(BorderFactory.createTitledBorder("Order History"));
    	final JTable HistoryTable = new JTable(model.getOrderHistoryTableModel());
   	    HistoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				OrderHistoryItem item = historylist.get(HistoryTable.convertColumnIndexToModel(HistoryTable.getSelectedRow()));
				InfoTable = item.getItems();				
			}   		
    	});
    	
        JTableHeader header = HistoryTable.getTableHeader();
   	    header.setReorderingAllowed(false);
   	    
        JScrollPane scrollPane = new JScrollPane(HistoryTable);
   	    panel.add(scrollPane, gc);
        return panel;
    }
    
    public Component drawPurchaseInfoPane(){
    	JPanel panel = new JPanel();
    	
        JTableHeader header = InfoTable.getTableHeader();
   	    header.setReorderingAllowed(false);
   	    
        JScrollPane scrollPane = new JScrollPane(InfoTable);
        
        GridBagConstraints gc = new GridBagConstraints();
   	    GridBagLayout gb = new GridBagLayout();
   	    gc.fill = GridBagConstraints.BOTH;
   	    gc.weightx = 1.0;
   	    gc.weighty = 1.0;
   	    
        panel.setLayout(gb);
   	    panel.add(scrollPane, gc);
    	panel.setBorder(BorderFactory.createTitledBorder("Order Info"));
    	
    	return panel;
    }
}