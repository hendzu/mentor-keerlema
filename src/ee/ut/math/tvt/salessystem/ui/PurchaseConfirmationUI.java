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

import ee.ut.math.tvt.salessystem.domain.data.OrderHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;


public class PurchaseConfirmationUI extends JFrame{

	private JTextField totalSum;
	private JTextField payment;
	private JTextField change;
	
	private JButton acceptPaymentButt;
	private JButton cancelPaymentButt;
	
	private SalesSystemModel model;
	
	private static final long serialVersionUID = 1L;
	
	public JButton getAcceptPaymentButt() {
		return acceptPaymentButt;
	}

	public JButton getCancelPaymentButt() {
		return cancelPaymentButt;
	}

	public PurchaseConfirmationUI(final PurchaseInfoTableModel table, final SalesSystemModel model){
		this.model = model;
		final JPanel panel = new JPanel();
		
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
				model.getOrderHistoryTableModel().addItem(new OrderHistoryItem(Double.parseDouble(totalSum.getText()), 
						new JTable(model.getCurrentPurchaseTableModel())));			
			}
		});
		
		//Could probably do better here
		final PurchaseConfirmationUI windowRef = this;
		cancelPaymentButt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				windowRef.setVisible(false);
			}
		});

		payment.getDocument().addDocumentListener(documentlistener);
		add(panel);
		pack();
	}
	
	public void addAcceptListener(ActionListener listener) {
		acceptPaymentButt.addActionListener(listener);
	}
	public void addCancelListener(ActionListener listener) {
		cancelPaymentButt.addActionListener(listener);
	}
}
