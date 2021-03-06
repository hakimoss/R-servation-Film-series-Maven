package com.codeboxx.reservationFilm;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import java.awt.event.KeyEvent;

import java.awt.Graphics2D;

import org.json.JSONObject;

import com.codeboxx.reservationFilm.CallApi.CallApi;

public class SuggestionFilm extends JPanel {

	
	
	JLabel labelChercher;
	JTextField textFieldChercher;
	JButton btnChercher;
	
	JLabel labelTitle;
	JLabel labelGenre;
	JLabel labelRuntime;
	JLabel labelRating;
	JLabel labelSummary;
	
	JLabel picLabel;
	
	String stringApiName = "nom du film";
	String stringApiGenre = "genre du film";
	String stringApiRuntime = "dur? du film";
	String stringApiRating = "note du film";
	String imageString = "image";
	Image ImageFilm;
	
	String mc;
	
	public SuggestionFilm() {
		this.setLayout(new BorderLayout());
		
		labelChercher=new JLabel("Chercher un film/s?ries");
		textFieldChercher=new JTextField(12);
		btnChercher=new JButton("Chercher");

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());		
		JPanel panelN=new JPanel();
		this.add(panelN, BorderLayout.NORTH);
		panelN.add(labelChercher);
		panelN.add(textFieldChercher);
		panelN.add(btnChercher);
	
		JPanel panelC=new JPanel();
		this.add(panelC);
		

		labelTitle=new JLabel(stringApiName);
		labelGenre=new JLabel(stringApiGenre);
		labelRuntime=new JLabel(stringApiRuntime);
		labelRating=new JLabel(stringApiRating);
		JPanel panelS=new JPanel();
		
		this.add(panelS, BorderLayout.SOUTH);
		panelS.add(labelTitle);
		panelS.add(labelGenre);
		panelS.add(labelRuntime);
		panelS.add(labelRating);
	
        
        btnChercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				panelC.removeAll();
				String valueChercher = textFieldChercher.getText();
				mc = valueChercher;
				
				CallApi obj = new CallApi();
		        try {
		        	Object apiName = ((JSONObject) obj.getImage(mc)).get("name");
		        	stringApiName = (String) apiName;
		        	labelTitle.setText("| Nom : "+stringApiName+" |");
		        	
		        	// peu avoire plus d'un genre
		        	Object apiGenre = ((JSONObject) obj.getImage(mc)).getJSONArray("genres").get(1);
		        	stringApiGenre = (String) apiGenre;
		        	labelGenre.setText("| Genre : "+stringApiGenre+" |");
		        	
		        	Object apiRuntime = ((JSONObject) obj.getImage(mc)).get("runtime");
		        	labelRuntime.setText("| Dur? : "+String.valueOf(apiRuntime)+ "minutes |");
		        	
		        	Object apiRating = ((JSONObject) obj.getImage(mc)).getJSONObject("rating").get("average");
		        	labelRating.setText("| Rating : "+String.valueOf(apiRating)+"/10 |");
		        	
		        	Object apiImage = ((JSONObject) obj.getImage(mc)).getJSONObject("image").get("original");
		        	imageString = (String) apiImage;
        	
		        	URL url = new URL(imageString);
		            BufferedImage image = ImageIO.read(url);
		            Image tmp = image.getScaledInstance(450, 600, Image.SCALE_SMOOTH);
		            Graphics2D g2d = image.createGraphics();
		            g2d.drawImage(tmp, 0, 0, null);
		            g2d.dispose();
					picLabel = new JLabel(new ImageIcon(tmp));
					panelC.add(picLabel);
        	
		        	/*
		        	Object apiSummary = ((JSONObject) obj.getImage(mc)).get("summary");
		        	*/

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        textFieldChercher.setText("");
				
			}
			
        	
        });

		
	}
	
	
}
