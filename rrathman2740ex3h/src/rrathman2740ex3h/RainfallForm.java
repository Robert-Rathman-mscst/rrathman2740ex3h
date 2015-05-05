package rrathman2740ex3h;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class RainfallForm extends JFrame {

	private JPanel contentPane;
	private JList rainfallList;
	private JLabel totalLabel;
	private JTextField txtUpdate;
	private JLabel avgLabel;
	private JLabel highLabel;
	private JLabel lowLabel;
	private JButton btnUpdate;
	private String [] strRainfall = {
			"1.2", "2.7", "2.2", "3.1", "2.9", "5.1",
			"3.2", "2.7", "3.6", "1.8", "2.2", "1.7" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RainfallForm frame = new RainfallForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RainfallForm() {
		setTitle("RRathman 2740 Ex3H Rainfall");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonthlyRainfall = new JLabel("Monthly Rainfall:");
		lblMonthlyRainfall.setBounds(10, 11, 95, 14);
		contentPane.add(lblMonthlyRainfall);
		
		JList list = new JList();
		list.setBackground(UIManager.getColor("Label.background"));
		list.setEnabled(false);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"01 Jan", "02 Feb", "03 Mar", "04 Apr", "05 May", "06 Jun", "07 Jul", "08 Aug", "09 Sep", "10 Oct", "11 Nov", "12 Dec"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(20, 36, 46, 203);
		contentPane.add(list);
		
		rainfallList = new JList(strRainfall);
		rainfallList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_rainfallList_valueChanged(arg0);
			}
		});
		rainfallList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rainfallList.setBounds(76, 34, 41, 203);
		contentPane.add(rainfallList);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(152, 58, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setBounds(152, 83, 46, 14);
		contentPane.add(lblAverage);
		
		JLabel lblHighest = new JLabel("Highest:");
		lblHighest.setBounds(152, 108, 46, 14);
		contentPane.add(lblHighest);
		
		JLabel lblLowest = new JLabel("Lowest:");
		lblLowest.setBounds(152, 133, 46, 14);
		contentPane.add(lblLowest);
		
		totalLabel = new JLabel("0.0");
		lblTotal.setLabelFor(totalLabel);
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		totalLabel.setBounds(218, 54, 46, 18);
		contentPane.add(totalLabel);
		
		avgLabel = new JLabel("0.0");
		lblAverage.setLabelFor(avgLabel);
		avgLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		avgLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		avgLabel.setBounds(218, 83, 46, 18);
		contentPane.add(avgLabel);
		
		highLabel = new JLabel("0.0");
		lblHighest.setLabelFor(highLabel);
		highLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		highLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		highLabel.setBounds(218, 108, 46, 18);
		contentPane.add(highLabel);
		
		lowLabel = new JLabel("0.0");
		lblLowest.setLabelFor(lowLabel);
		lowLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lowLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lowLabel.setBounds(218, 133, 46, 18);
		contentPane.add(lowLabel);
		
		txtUpdate = new JTextField();
		txtUpdate.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUpdate.setText("0.0");
		txtUpdate.setBounds(76, 242, 41, 20);
		contentPane.add(txtUpdate);
		txtUpdate.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnUpdate_actionPerformed(e);
			}
		});
		btnUpdate.setBounds(56, 273, 89, 23);
		btnUpdate.setEnabled(false);
		contentPane.add(btnUpdate);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnCalculate_actionPerformed(arg0);
			}
		});
		btnCalculate.setBounds(194, 158, 89, 23);
		contentPane.add(btnCalculate);
	}
	protected void do_btnCalculate_actionPerformed(ActionEvent arg0) {
		Rainfall rainfall = new Rainfall(strRainfall);
		
		DecimalFormat fmt = new DecimalFormat("0.0");
		totalLabel.setText(fmt.format(rainfall.getTotal()));
		avgLabel.setText(fmt.format(rainfall.getAverage()));
		highLabel.setText(fmt.format(rainfall.getHighest()));
		lowLabel.setText(fmt.format(rainfall.getLowest()));
	}
	protected void do_btnUpdate_actionPerformed(ActionEvent e) {
		int selectedIndex = rainfallList.getSelectedIndex();
		double r = Double.parseDouble(txtUpdate.getText());
		strRainfall[selectedIndex] = Double.toString(r);
		rainfallList.repaint();
		
		txtUpdate.setText("0.0");
		btnUpdate.setEnabled(false);
		totalLabel.setText("");
		avgLabel.setText("");
		highLabel.setText("");
		lowLabel.setText("");
	}
	protected void do_rainfallList_valueChanged(ListSelectionEvent arg0) {
		btnUpdate.setEnabled(true);
		txtUpdate.setText((String) rainfallList.getSelectedValue());
		txtUpdate.requestFocus();
		txtUpdate.selectAll();
	}
}
