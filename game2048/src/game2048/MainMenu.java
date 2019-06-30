package game2048;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.*;

public class MainMenu {
	Game2048 game;
	JFrame frame; //��������
	
	//�����溯��
	public void mainFace() {
		frame=new JFrame("������");
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		
		JButton b1 = new JButton(new ImageIcon("images/newGame.png"));	
		JButton b2 = new JButton(new ImageIcon("images/about.png"));	
		JButton b3 = new JButton(new ImageIcon("images/exit.png"));
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game=new Game2048(400,400);
				game.setTitle("2048");
				game.setLocationRelativeTo(null);
		        game.setVisible(true);
		        game.newGame();
				frame.dispose(); //������ر�
			}
		});
		
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //������ر�
				otherFace(); //������һ������
				
			}
		});
		
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //������ر�
			}
		});
		frame.setLayout(new GridLayout(3,1));
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.setSize(400,400);
		frame.setLocation(100,50);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public void otherFace() {
		final JFrame oframe=new JFrame("about");
		oframe.setLayout(new BorderLayout());
		
		JTextArea displayArea=new JTextArea();
		oframe.add(displayArea,BorderLayout.CENTER);
		displayArea.setFont(new Font("Serif",Font.BOLD,20));
		displayArea.setForeground(Color.black);
		displayArea.append("\n  ������ͨ���������ϡ�������������Ϸ\n"
				+ "  ����Ļ���޿��ƶ�����ʱΪʧ����Ϸ����\n"
				+"  ������2048���ɹ����ɽ���\n");
		
		JTextArea notes=new JTextArea();
		oframe.add(notes,BorderLayout.NORTH);
		notes.setFont(new Font("΢���ź�",Font.BOLD,10));
		notes.setForeground(Color.black);
		notes.append("\t���Ʋ�����Ϸ���ܾ�������Ϸ\n"
				+ "\t�ʶ���Ϸ���ԣ�������Ϸ����");
		
		JButton button1=new JButton("����������");
		oframe.add(button1,BorderLayout.SOUTH);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true); //��������ʾ
				oframe.dispose(); //��һ������ر�
			}
		});
		
		oframe.setSize(400,400);
		oframe.setLocation(150,100);
		oframe.setVisible(true);
	}
	
	
}
