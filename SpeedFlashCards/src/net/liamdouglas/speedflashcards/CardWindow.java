package net.liamdouglas.speedflashcards;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

public class CardWindow extends JFrame {

	private CardLogic cl = new CardLogic();
	private JPanel contentPane;
	private final JLabel lblFront;
	private final JLabel lblBack;
	

	/**
	 * Create the frame.
	 */
	public CardWindow() {
		//TODO: don't load everything if the files don't work?
		if ((cl.prepareCardsFile() == true) && (cl.loadCardsFile() == true)) {}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		lblFront = new JLabel("Front");
		lblFront.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.NORTH, lblFront, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblFront, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblFront, -10, SpringLayout.EAST, panel);
		panel.add(lblFront);
		
		lblBack = new JLabel("Back");
		sl_panel.putConstraint(SpringLayout.WEST, lblBack, 0, SpringLayout.WEST, lblFront);
		sl_panel.putConstraint(SpringLayout.EAST, lblBack, 0, SpringLayout.EAST, lblFront);
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBack);
		
		JButton btnNext = new JButton("Next");
		sl_panel.putConstraint(SpringLayout.SOUTH, lblBack, -34, SpringLayout.NORTH, btnNext);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNext, -24, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNext, -130, SpringLayout.EAST, panel);
		btnNext.setHorizontalAlignment(SwingConstants.LEFT);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.timeTaken();
				displayNextCard();
			}
		});
		panel.add(btnNext);
		
		//display the first card
		displayNextCard();
	}
	
	
	/**
	 * Get the next card to be displayed and update the necessary labels.
	 */
	private void displayNextCard() {
		Card c = cl.getNextCard();
		lblFront.setText(c.front);
		lblBack.setText(c.back);
	}

}
