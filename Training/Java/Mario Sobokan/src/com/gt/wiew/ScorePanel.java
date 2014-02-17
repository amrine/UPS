package com.gt.wiew;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gt.utilities.Score;



public class ScorePanel extends Container{

	private int[] policeSize = {25, 24, 23, 22, 21, 20, 19, 18, 16, 14};
	private Dimension dimension;
	private Score[] list;
	
	public ScorePanel(Dimension dim, Score[] list) {
		super(dim);
		this.list = list;
		initPanel();
	}

        @Override
	protected void initPanel() {
		JPanel leftContent = new JPanel();
		JPanel rightContent = new JPanel();
		
		
		this.dimension = new Dimension(360, 530); 
		rightContent.setPreferredSize(this.dimension);
		rightContent.add(new JLabel(new ImageIcon("images/mario.jpg")), BorderLayout.CENTER);
		rightContent.setBackground(Color.white);
		
		this.dimension = new Dimension(500, 530);
		leftContent.setPreferredSize(this.dimension);
		leftContent.setBackground(Color.white);
		
		for(int i = 0; i < this.list.length; i++){
			JLabel lab = new JLabel((this.list[i].getNom().equals("")?"Inconnue": this.list[i].getNom())+" : "+this.list[i].getPoint()+((this.list[i].getPoint())<1 ?" point":" points"));
			lab.setFont(new Font("Comics Sans MS", Font.BOLD, policeSize[i]));
			lab.setPreferredSize(new Dimension(440, 40));
			leftContent.add(lab);
		}
		
		
		this.panel.add(leftContent, BorderLayout.CENTER);
		this.panel.add(rightContent, BorderLayout.WEST);
	}

}
