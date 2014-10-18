package ee.ut.math.tvt.salessystem.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseConfirmationUI extends JFrame{

	private JTextField totalSum;
	private JTextField payment;
	private JTextField change;
	
	private JButton acceptPayment;
	private JButton cancelPayment;
	
	private static final long serialVersionUID = 1L;
	
	public PurchaseConfirmationUI(PurchaseInfoTableModel table){
		JPanel panel = new JPanel();
		
		totalSum.setEditable(false);
		change.setEditable(false);
		double sum= 0;
		for(SoldItem item:table.getTableRows()){
			sum += item.getSum();
		}
		totalSum = new JTextField(Double.toString(sum));
		change = new JTextField("0");
		
		panel.add(new JLabel("Total sum:"));
		panel.add(totalSum);
		
		panel.add(new JLabel("Payment:"));
		panel.add(payment);
		
		panel.add(new JLabel("Change:"));
		panel.add(change);
		
		DocumentListener documentlistener = new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				double Change = Double.parseDouble(totalSum.getText()) - Double.parseDouble(payment.getText());
				change.setText(Double.toString(Change));
				
			}
		};
		
		payment.getDocument().addDocumentListener(documentlistener);
		add(panel);
		pack();
	}
}
