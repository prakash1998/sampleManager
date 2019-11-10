package com.pra;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import com.pra.utils.view.BackgroundPanel;


//   class - 8


public class LoadingScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912634789435210488L;
	private JPanel contentPane;
	private JLabel lblBackground;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingScreen frame = new LoadingScreen(5);
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
	public LoadingScreen(int n) {
		setResizable(false);
		setTitle("loading");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight=(int) screenSize.getHeight() * 3 /4;
		int screenWidth=(int) screenSize.getWidth() *3/4;
		int posY = (int) (screenSize.getHeight() /2  - screenHeight /2);
		int posX =(int) (screenSize.getWidth() /2  - screenWidth /2);
		
		setType(javax.swing.JFrame.Type.UTILITY);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
		setBounds(posX, posY, screenWidth,screenHeight);
		
		BufferedImage backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("resources/images/loadingBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane = new BackgroundPanel(backgroundImage);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		ImageIcon sampleManagerText=new ImageIcon("resources/images/sampleManagerText.png");
		JLabel lblSampleManager = new JLabel("");
		lblSampleManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblSampleManager.setIcon(sampleManagerText);
		lblSampleManager.setBounds(screenWidth/2 - 400 , screenHeight/2 - 300, 800, 100);
		contentPane.add(lblSampleManager);
		
		ImageIcon loadigGif=new ImageIcon("resources/images/loading.gif");
		JLabel lblLoadingGif = new JLabel("");
		lblLoadingGif.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadingGif.setIcon(loadigGif);
		lblLoadingGif.setBounds(screenWidth/2 - 100 , screenHeight/2 - 100, 200, 200);
		contentPane.add(lblLoadingGif);
		
		ImageIcon loadigText=new ImageIcon("resources/images/loadingText.png");
		JLabel lblLoading = new JLabel("");
		lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoading.setIcon(loadigText);
		lblLoading.setBounds(screenWidth/2 - 200 , screenHeight/2 + 100, 400, 100);
		contentPane.add(lblLoading);
		

		

		
	    SwingWorker<?,?> worker = new SwingWorker<Void,Integer>(){
	        protected Void doInBackground() throws InterruptedException{
	          for (int x = 0; x < n; x++)
	          {
	            publish(x);
	            Thread.sleep(1000);
	          }
	          return null;
	        }

	        protected void done(){
	          dispose();
	   
	        }
	      };
	      worker.execute();
	}
	
	
	private ImageIcon getScaledImageIcon(String imagePath , int w , int h ) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
