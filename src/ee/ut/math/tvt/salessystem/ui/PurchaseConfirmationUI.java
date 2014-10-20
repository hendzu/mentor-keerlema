package ee.ut.math.tvt.salessystem.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

public class PurchaseConfirmationUI extends JFrame{
	private static final Logger log = Logger.getLogger(PurchaseConfirmationUI.class);

	private JTextField totalSum;
	private JTextField payment;
	private JTextField change;
	
	private JButton acceptPaymentButt;
	private JButton cancelPaymentButt;
	
	private SalesDomainController domainController;
	private SalesSystemModel model;
	
	private static final long serialVersionUID = 1L;
	
	public PurchaseConfirmationUI(SalesDomainController sdc, final PurchaseInfoTableModel table,
			final SalesSystemModel model){
		
		domainController = sdc;
		this.model = model;
		JPanel panel = new JPanel();
		
		double sum= 0;
		for(SoldItem item:table.getTableRows()){
			sum += item.getSum();
		}
		
		totalSum = new JTextField(Double.toString(sum));
		change = new JTextField(8);
		payment = new JTextField(8);
		
		totalSum.setEditable(false);
		change.setEditable(false);
		
		acceptPaymentButt = new JButton("Accept");
		cancelPaymentButt = new JButton("Cancel");
		
		panel.add(new JLabel("Total sum:"));
		panel.add(totalSum);
		panel.add(new JLabel("Payment:"));
		panel.add(payment);
		panel.add(new JLabel("Change:"));
		panel.add(change);
		
		panel.add(acceptPaymentButt);
		panel.add(cancelPaymentButt);
		
		DocumentListener documentlistener = new DocumentListener(){
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (payment.getText().isEmpty()) {
					change.setText("");
				}
				else {
					changedUpdate(e);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				double Change = Double.parseDouble(totalSum.getText()) - Double.parseDouble(payment.getText());
				change.setText(Double.toString(Change));
			}
		};
		
		acceptPaymentButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.getOrderHistoryTableModel().addItem(new OrderHistoryItem(Double.parseDouble(totalSum.getText()), 
							new JTable(model.getOrderHistoryTableModel())));
					domainController.submitCurrentPurchase(table.getTableRows());
					table.clear();
					log.info("Sale complete");
				}
				catch (VerificationFailedException e1) {
					log.error(e1.getMessage());
					}
			}
		});

		payment.getDocument().addDocumentListener(documentlistener);
		add(panel);
		pack();
	}
}
