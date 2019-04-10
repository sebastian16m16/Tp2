package ro.utcn.pt.assignment2.front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ro.utcn.pt.assignment2.back.Client;
import ro.utcn.pt.assignment2.back.ClockTime;
import ro.utcn.pt.assignment2.back.Queue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	ClockTime c = new ClockTime();
	boolean ON = false;
	ClockTime q1C = new ClockTime();
	ClockTime q2C = new ClockTime();
	ClockTime q3C = new ClockTime();
	ClockTime q4C = new ClockTime();

	int i = 0;

	Queue q1 = new Queue("Q1",c, q1C);
	Queue q2 = new Queue(q1,"Q2", c, q2C);
	Queue q3 = new Queue(q1,"Q3", c, q3C);
	Queue q4 = new Queue(q1,"Q4", c, q4C);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 816, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblQ_0 = new JLabel("Q1");
		lblQ_0.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_0.setBounds(73, 47, 69, 20);
		frame.getContentPane().add(lblQ_0);

		JLabel lblQ_1 = new JLabel("Q2");
		lblQ_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_1.setBounds(237, 47, 69, 20);
		frame.getContentPane().add(lblQ_1);

		JLabel lblQ_2 = new JLabel("Q3");
		lblQ_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_2.setBounds(468, 47, 69, 20);
		frame.getContentPane().add(lblQ_2);

		JLabel lblQ_3 = new JLabel("Q4");
		lblQ_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_3.setBounds(635, 47, 69, 20);
		frame.getContentPane().add(lblQ_3);

		final JLabel Cq1 = new JLabel("0");
		Cq1.setHorizontalAlignment(SwingConstants.CENTER);
		Cq1.setFont(new Font("Tahoma", Font.PLAIN, 46));
		Cq1.setBounds(57, 113, 104, 109);
		frame.getContentPane().add(Cq1);

		final JLabel Cq2 = new JLabel("0");
		Cq2.setHorizontalAlignment(SwingConstants.CENTER);
		Cq2.setFont(new Font("Tahoma", Font.PLAIN, 46));
		Cq2.setBounds(226, 113, 104, 109);
		frame.getContentPane().add(Cq2);

		final JLabel Cq3 = new JLabel("0");
		Cq3.setHorizontalAlignment(SwingConstants.CENTER);
		Cq3.setFont(new Font("Tahoma", Font.PLAIN, 46));
		Cq3.setBounds(446, 113, 104, 109);
		frame.getContentPane().add(Cq3);

		final JLabel Cq4 = new JLabel("0");
		Cq4.setHorizontalAlignment(SwingConstants.CENTER);
		Cq4.setFont(new Font("Tahoma", Font.PLAIN, 46));
		Cq4.setBounds(617, 113, 104, 109);
		frame.getContentPane().add(Cq4);

		textField = new JTextField();
		textField.setBounds(39, 380, 689, 147);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		final JLabel lblTime = new JLabel("Time: 0000");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(315, 339, 164, 37);
		frame.getContentPane().add(lblTime);

		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ON = true;
			}
		});
		btnStart.setBackground(Color.GREEN);
		btnStart.setBounds(226, 543, 115, 55);
		frame.getContentPane().add(btnStart);

		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ON = false;
			}
		});
		btnStop.setBackground(Color.RED);
		btnStop.setBounds(435, 543, 115, 55);
		frame.getContentPane().add(btnStop);

		JButton btnClientQ1 = new JButton("+Client");
		btnClientQ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (ON != false && q1.isAlive()) {
					Client c1 = new Client(i, c.getTime());
					i++;
					try {
						q1.enqueue(c1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					q1C.setTime(q1.getCount());
					Cq1.setText(q1C.getTime()+"");

					q2C.setTime(q2.getCount());
					Cq2.setText(q2C.getTime()+"");

					q3C.setTime(q3.getCount());
					Cq3.setText(q3C.getTime()+"");

					q4C.setTime(q4.getCount());
					Cq4.setText(q4C.getTime()+"");
				}
			}
		});
		btnClientQ1.setBounds(343, 294, 115, 29);
		frame.getContentPane().add(btnClientQ1);
		
		JCheckBox enableQ2 = new JCheckBox("Enable Q2");
		enableQ2.setHorizontalAlignment(SwingConstants.CENTER);
		enableQ2.setBounds(204, 253, 139, 29);
		frame.getContentPane().add(enableQ2);
		enableQ2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				q2.start();
			}
		});
		
		JCheckBox enableQ3 = new JCheckBox("Enable Q3");
		enableQ3.setHorizontalAlignment(SwingConstants.CENTER);
		enableQ3.setBounds(426, 253, 139, 29);
		frame.getContentPane().add(enableQ3);
		enableQ3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				q3.start();
			}
		});
		
		JCheckBox enableQ4 = new JCheckBox("Enable Q4");
		enableQ4.setHorizontalAlignment(SwingConstants.CENTER);
		enableQ4.setBounds(593, 253, 139, 29);
		frame.getContentPane().add(enableQ4);
		
		JCheckBox enableQ1 = new JCheckBox("Enable Q1");
		enableQ1.setHorizontalAlignment(SwingConstants.CENTER);
		enableQ1.setBounds(39, 253, 139, 29);
		frame.getContentPane().add(enableQ1);
		
		enableQ1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				q1.start();
			}
		});
		
		
		enableQ4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				q4.start();
			}
		});
	}

	public void organize(Queue q0, Queue qp, JCheckBox cBox){
		if(cBox.isEnabled()){
			
		}
	}
}
