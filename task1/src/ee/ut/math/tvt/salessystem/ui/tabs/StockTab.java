package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;


public class StockTab {

	private JButton addItem;
	
	private JButton increaseQuantity;
	
	private static final Logger log = Logger.getLogger(StockTab.class);

	private SalesDomainController controller;
	private SalesSystemModel model;

	private JTable table;


	public StockTab(SalesDomainController controller, SalesSystemModel model) {
		this.controller = controller;
		this.model = model;
		this.table = new JTable(model.getWarehouseTableModel());
	}

	// warehouse stock tab - consists of a menu and a table
	public Component draw() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		panel.add(drawStockMenuPane(), gc);

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawStockMainPane(), gc);
		return panel;
	}

	// warehouse menu
	private Component drawStockMenuPane() {
		JPanel panel = new JPanel();

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();

		panel.setLayout(gb);

		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;

		addItem = new JButton("Add new item");
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonPressed();
			}
		});
		increaseQuantity = new JButton("Add to stock");
		increaseQuantity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				increaseButtonPressed();
			}
		});
		gc.gridwidth = GridBagConstraints.RELATIVE;
		gc.weightx = 1.0;
		panel.add(addItem, gc);
		panel.add(increaseQuantity, gc);

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}
	private void addButtonPressed()
	{
		addItem.setEnabled(false);
		final JFrame addItemFrame = new JFrame();
		final JPanel addItemPanel = new JPanel();
		
		GridBagLayout gbl = new GridBagLayout();
		addItemPanel.setLayout(gbl);
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;
		
		final JButton submit = new JButton("Submit");
		final JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItem.setEnabled(true);
				addItemFrame.dispose();
			}
		});
		
		
		final String[] headers = model.getWarehouseTableModel().getHeaders();
		final ArrayList<JTextField> fields = new ArrayList<JTextField>();
		for (String header : headers)
		{
			JLabel label = new JLabel(header);
			addItemPanel.add(label, gc);
			JTextField field = new JTextField();
			addItemPanel.add(field, gc);
			fields.add(field);
		}
		fields.get(0).setText(Integer.toString(model.getWarehouseTableModel().getTableRows().size()+1));
		fields.get(0).setEditable(false);
		JLabel label = new JLabel("Description");
		addItemPanel.add(label, gc);
		JTextField field = new JTextField();
		addItemPanel.add(field, gc);
		fields.add(field);
		fields.get(1).requestFocus();
		fields.get(3).addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				try{
				if(Integer.parseInt(fields.get(3).getText()) < 1){
					fields.get(3).setText("1");
				}
				}catch(NumberFormatException nfe){
					
				}
			}
			
		});
		submit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Long id = 0L;
				String name = "";
				String desc = "";
				double price = 0.0;
				int quantity = 0;
				int element = 0;
				int i = 0;
				try
				{
					for (; i < fields.size(); i++){
					JTextField field = fields.get(i);
					switch(element){
					case 0:
						id = Long.parseLong(field.getText());
						break;
					case 1:
						name = field.getText();
						break;
					case 2:
						price = Double.parseDouble(field.getText());
						break;
					case 3:
						quantity = Integer.parseInt(field.getText());
						break;
					case 4:
						desc = field.getText();
						break;
					}
					element++;			
					}
					controller.addItemToWarehouse(
							new StockItem(id, name, desc, price, quantity));
					addItem.setEnabled(true);
					addItemFrame.dispose();
				}
				
				
				catch (NumberFormatException nfe) {
					String message = "Invalid input in: " + headers[i];
					fields.get(i).requestFocus();
					log.error(message);
					JOptionPane.showMessageDialog(null, message);
				}
				 
				catch (VerificationFailedException ex) {
					log.error(ex.getMessage());
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				
			}
		});
		
		addItemPanel.add(submit, gc);
		addItemPanel.add(cancel);
		
		addItemFrame.add(addItemPanel);
		addItemFrame.pack();
		addItemFrame.setVisible(true);
		
	}
	
	private void increaseButtonPressed()
	{
		StockItem item;
		try {
			int row = table.getSelectedRow();
			item = model.getWarehouseTableModel().getTableRows()
					.get(row);
		} catch (Exception e1) {
			log.error("No item selected!");
			return;
		}
		
		
		increaseQuantity.setEnabled(false);
		final JFrame increaseQuantityFrame = new JFrame();
		final JPanel increaseQuantityPanel = new JPanel();
		
		GridBagLayout gbl = new GridBagLayout();
		increaseQuantityPanel.setLayout(gbl);
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;
		
		final JButton submit = new JButton("Submit");
		final JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				increaseQuantity.setEnabled(true);
				increaseQuantityFrame.dispose();
			}
		});
		
		
		final String[] headers = model.getWarehouseTableModel().getHeaders();
		final ArrayList<JTextField> fields = new ArrayList<JTextField>();
		headers[3]="Add quantity";
		for (String header : headers)
		{
			JLabel label = new JLabel(header);
			increaseQuantityPanel.add(label, gc);
			JTextField field = new JTextField();
			increaseQuantityPanel.add(field, gc);
			fields.add(field);
		}
		fields.get(0).setText(Long.toString(item.getId()));
		fields.get(1).setText(item.getName());
		fields.get(2).setText(Double.toString(item.getPrice()));
		fields.get(0).setEditable(false);
		JLabel label = new JLabel("Description");
		increaseQuantityPanel.add(label, gc);
		JTextField field = new JTextField();
		field.setText(item.getDescription());
		increaseQuantityPanel.add(field, gc);
		fields.add(field);
		fields.get(1).requestFocus();
		fields.get(3).addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				try{
				if(Integer.parseInt(fields.get(3).getText()) < 1){
					fields.get(3).setText("1");
				}
				}catch(NumberFormatException nfe){
					
				}
			}
			
		});
		submit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Long id = 0L;
				String name = "";
				String desc = "";
				double price = 0.0;
				int quantity = 0;
				int element = 0;
				int i = 0;
				try
				{
					for (; i < fields.size(); i++){
					JTextField field = fields.get(i);
					switch(element){
					case 0:
						id = Long.parseLong(field.getText());
						break;
					case 1:
						name = field.getText();
						break;
					case 2:
						price = Double.parseDouble(field.getText());
						break;
					case 3:
						quantity = Integer.parseInt(field.getText());
						break;
					case 4:
						desc = field.getText();
						break;
					}
					element++;			
					}
					controller.editItemInWarehouse(
							new StockItem(id, name, desc, price, quantity));
					increaseQuantity.setEnabled(true);
					increaseQuantityFrame.dispose();
				}
				
				
				catch (NumberFormatException nfe) {
					String message = "Invalid input in: " + headers[i];
					fields.get(i).requestFocus();
					log.error(message);
					JOptionPane.showMessageDialog(null, message);
				}
				
				catch (VerificationFailedException ex) {
					log.error(ex.getMessage());
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				
			}
		});
		
		increaseQuantityPanel.add(submit, gc);
		increaseQuantityPanel.add(cancel);
		
		increaseQuantityFrame.add(increaseQuantityPanel);
		increaseQuantityFrame.pack();
		increaseQuantityFrame.setVisible(true);
		
	}

	// table of the wareshouse stock
	private Component drawStockMainPane() {
		JPanel panel = new JPanel();


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

		panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
		return panel;
	}

}
