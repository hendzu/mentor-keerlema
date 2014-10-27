package ee.ut.math.tvt.salessystem.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private JTextField totalSumField;
	private JTextField paymentField;
	private JTextField changeField;
	
	private JButton acceptPaymentButt;
	private JButton cancelPaymentButt;
	
	private double change;
	
	private static final long serialVersionUID = 1L;
	
	public double getSum() {
		return Double.parseDouble(totalSumField.getText());
	}
	
	public double getChange() {
		return change;
	}

	public PurchaseConfirmationUI(final PurchaseInfoTableModel table) {
		final JPanel panel = new JPanel();
		
		double sum= 0;
		for(SoldItem item:table.getTableRows()){
			sum += item.getSum();
		}
		
		totalSumField = new JTextField(Double.toString(sum));
		changeField = new JTextField(8);
		paymentField = new JTextField(8);
		
		totalSumField.setEditable(false);
		changeField.setEditable(false);
		
		acceptPaymentButt = new JButton("Accept");
		cancelPaymentButt = new JButton("Cancel");
		
		panel.add(new JLabel("Total sum:"));
		panel.add(totalSumField);
		panel.add(new JLabel("Payment:"));
		panel.add(paymentField);
		panel.add(new JLabel("Change:"));
		panel.add(changeField);
		
		panel.add(acceptPaymentButt);
		panel.add(cancelPaymentButt);
		
		DocumentListener documentlistener = new DocumentListener(){
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (paymentField.getText().isEmpty()) {
					changeField.setText("");
				}
				else {
					changedUpdate(e);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				setChange(Double.parseDouble(totalSumField.getText()) - 
						Double.parseDouble(paymentField.getText()));
			}
		};
		
		//Could probably do better here
		final PurchaseConfirmationUI windowRef = this;
		ActionListener choiceMade = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				windowRef.setVisible(false);
			}
		};
		
		acceptPaymentButt.addActionListener(choiceMade);
		cancelPaymentButt.addActionListener(choiceMade);

		paymentField.getDocument().addDocumentListener(documentlistener);
		add(panel);
		pack();
	}
	
	private void setChange(double change) {
		this.change = change;
		changeField.setText(Double.toString(change));
	}
	
	public void addAcceptListener(ActionListener listener) {
		acceptPaymentButt.addActionListener(listener);
	}
	public void addCancelListener(ActionListener listener) {
		cancelPaymentButt.addActionListener(listener);
	}
}
