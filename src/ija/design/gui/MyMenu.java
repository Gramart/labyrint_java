package ija.design.gui;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import ija.board.MazeBoard;
import ija.board.MazeCard;
import ija.board.MazeField;
import ija.gameinfo.BoardPlayersTreasures;

import java.awt.FlowLayout;
import java.awt.Graphics; 
import java.awt.Graphics2D;


import java.awt.Image;

import javax.swing.ImageIcon;











import javax.swing.JLabel;
import javax.imageio.ImageIO;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Arrays; 
import java.util.UUID;
import java.io.ObjectInput;

import javax.swing.JFileChooser; //pro ulozeni hry


public class MyMenu extends JFrame {
	/**
	 * 
	 */
	
	ChangeManager manager = new ChangeManager(); //manager for undo
	private int undocounter = 0;
	
	
	private static final long serialVersionUID = 7668925508699611633L;
	private MenuBar nav;
	private Menu options, window;
	private MenuItem NewGame, SaveGame, LoadGame,Undo;
	private MenuItem petset, setset, sedmset, osmset, dark, light;
	private JPanel hlavni; // pro hru? 
	
	
	private JLabel picLabel;
	JPanel freepan = new JPanel();
	JPanel freeimage = new JPanel();
	JPanel want_it = new JPanel();
	JPanel color_on_turn = new JPanel();
	JPanel my_button = new JPanel();
	JPanel sipky = new JPanel();
	JPanel end_window = new JPanel(); //zobrazi se na konci hry
	private JButton applyButton;
	public JButton rotate = new JButton();
	
	private JButton dva_leva = new JButton();
	private JButton ctyri_leva = new JButton();
	private JButton sest_leva = new JButton();
	private JButton osm_leva = new JButton();
	private JButton deset_leva = new JButton();
	
	private JButton dva_horni = new JButton();
	private JButton ctyri_horni = new JButton();
	private JButton sest_horni = new JButton();
	private JButton osm_horni = new JButton();
	private JButton deset_horni = new JButton();
	
	private JButton dva_prava = new JButton();
	private JButton ctyri_prava = new JButton();
	private JButton sest_prava = new JButton();
	private JButton osm_prava = new JButton();
	private JButton deset_prava = new JButton();
	
	private JButton dva_dolni = new JButton();
	private JButton ctyri_dolni = new JButton();
	private JButton sest_dolni = new JButton();
	private JButton osm_dolni = new JButton();
	private JButton deset_dolni = new JButton();
	
	private JButton up_arrow = new JButton();
	private JButton left_arrow = new JButton();
	private JButton right_arrow = new JButton();
	private JButton down_arrow = new JButton();
	public int player_on_turn = 0;
	
	JButton next_player = new JButton();
	JPanel next_player_space = new JPanel();
	//JPanel next_player = new JPanel();

	
	private JComboBox horGapComboBox;
	private JComboBox verGapComboBox;
	private JComboBox karGapComboBox;
	
	private JButton actcard;
	
	int fieldsize;
	public static int gamernum;
	public static int cardnum;
	
	private boolean shift = false;
	
	
	 private JLabel[][] GameBoardSquares;
	 //private JLabel[][] GameBoardSquares = new JLabel[7][7];
	 public static JPanel GameBoard;
	 GridLayout experimentLayout = new GridLayout(0,2);
	 
	 
	 private static final String COLS = "VVVVVVV";
	 static final String gapList[] = {"7", "5", "9", "11"};
	 static final String playerList[] = {"2", "3", "4"};
	 static final String kartList[] = {"12", "24"};
	
	 // zemezeni revertovani shiftu pri nasledujicim tahu
	 private int before_x=0; 
	 private int before_y=0;
	 
	private MazeBoard mb;
	
	private int k = 1;  // koeficient udava velikost komponenty
	private Container pane;

	
	public MyMenu()
	{
	        this.setSize(900, 700);
	        this.setTitle("Labyrint");
	        setLayout(null); // bcs layoutmanager set location without it for us
	        //setLayout(new GridBagLayout());
	        this.setLocationRelativeTo(null);
	        this.setBackground(Color.BLACK);
	        this.setKomponents();
	        this.setListeners(freepan);
	        //this.mb = MazeBoard.createMazeBoard(7);
	}

	public static Frame nastav()
	{
	        MyMenu m = new MyMenu();
	        m.setLocationRelativeTo(null);
	        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        m.setVisible(true);
	        return m;
	}

	public void CreateGameWindow(){
		
	       this.pane = this.getContentPane();
	       this.pane.setBackground(new Color(66,66,66)); 
	       
	       
	       this.hlavni = new JPanel();
	       this.hlavni.setSize(700, 600);
	       this.hlavni.setBackground(new Color(66,66,66));
	       this.hlavni.setLayout(null);
	       //this.hlavni.setLocation(0, 0);
	       
	       this.hlavni.setBackground(new Color(66,66,66));
		
		  	horGapComboBox = new JComboBox(gapList);
	        verGapComboBox = new JComboBox(playerList);
	        karGapComboBox = new JComboBox(kartList);
	       this.applyButton = new JButton("Create Game");
	       final JPanel compsToExperiment = new JPanel();
	        compsToExperiment.setLayout(experimentLayout);
	        JPanel controls = new JPanel();
	        controls.setLayout(new GridLayout(2,4));
	        controls.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
	         
	        /*
	        //Set up components preferred size
	        JButton b = new JButton("Just fake button");
	        Dimension buttonSize = b.getPreferredSize();
	        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+20,
	                (int)(buttonSize.getHeight() * 3.5)+20 * 2));
	         
	        //Add buttons to experiment with Grid Layout
	        compsToExperiment.add(new JButton("Button 1"));
	        compsToExperiment.add(new JButton("Button 2"));
	        compsToExperiment.add(new JButton("Button 3"));
	        compsToExperiment.add(new JButton("Long-Named Button 4"));
	        compsToExperiment.add(new JButton("5"));
	         */
	        //Add controls to set up horizontal and vertical gaps
	        controls.add(new Label("Velikost pole:"));
	        controls.add(new Label("Pocet hracu:"));
	        controls.add(new Label("Pocet karet:"));
	        controls.add(new Label(" "));
	        controls.add(horGapComboBox);
	        controls.add(verGapComboBox);
	        controls.add(karGapComboBox);
	        controls.add(this.applyButton);
	        
	        
	        
	        applyButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	//Get the field size value
	                fieldsize = Integer.parseInt((String)horGapComboBox.getSelectedItem());
	                //Get the number of games
	                gamernum =  Integer.parseInt((String)verGapComboBox.getSelectedItem());
	              //Get the cards number value
	                cardnum =  Integer.parseInt((String)karGapComboBox.getSelectedItem());
	            
	                if(fieldsize == 5 && cardnum == 24){
	                	return;
	                	//nepovolena kombinace
	                }
		            getContentPane().removeAll();
		            GameBoardSquares = new JLabel[fieldsize][fieldsize];
		            mb = new MazeBoard();
		            mb.createMazeBoard(fieldsize);
		            clear_board();
		           // mb = MazeBoard.createMazeBoard(fieldsize);
		            mb.chci_pokladu = cardnum; // ulozeni poctu pokladu
		            mb.newGame();	
		            shift = false;

		            try{
		            	CreateNewGame(mb,fieldsize,gamernum,cardnum,true);
		            	getContentPane().add(GameBoard);
		            }
		            catch (Exception ex) {
	                    ex.printStackTrace();
	                }
		            
		            repaint();
		            printAll(getGraphics());
		            //this.GameBoardSquares = new JButton[7][7];
		        	mb.print();
	            }
	        });
	        
	        //controls.setSize(400,100);
	        controls.setBorder(BorderFactory.createLineBorder(Color.black));
		    controls.setBounds(180, 100, 500, 100);
	        hlavni.add(compsToExperiment, BorderLayout.NORTH);
	        hlavni.add(new JSeparator(), BorderLayout.CENTER);
	        hlavni.add(controls, BorderLayout.SOUTH);
	        hlavni.setVisible(true);
		    
	        pane.add(this.hlavni,BorderLayout.CENTER);
	        
	        

		       
	}
	
	
	@SuppressWarnings("rawtypes")
	void setKomponents()
	{
	       /**
	        * Layout a komponenta
	        */
	      
	       
	       BufferedImage img = null;
	       try {
	           img = ImageIO.read(new File("lib/resources/img.png"));
	       } 
	       catch (IOException e) {
              // System.out.print("CHYBA!!");

	       }
	       
	       /*--------*/
	       
	     
	       
	       
	       /**
	        * Hlavni cast menu
	        */

	       this.nav = new MenuBar();
	       this.setMenuBar(this.nav);
	       
	       this.options = new Menu("Game menu");
	       //this.window = new Menu("Window menu");
	       this.nav.add(this.options);
	      // this.nav.add(this.window);

	       /**
	        * Polo�ky podmenu
	        */

	       this.NewGame = new MenuItem("New Game");
	       this.SaveGame = new MenuItem("Save Game");
	       this.LoadGame = new MenuItem("Load Game");
	       this.Undo = new MenuItem("Undo");
	       
	       /*this.petset = new MenuItem("500 x 500");
	       this.setset = new MenuItem("600 x 600");
	       this.sedmset = new MenuItem("700 x 700");
	       this.osmset = new MenuItem("800 x 800");
	       this.dark = new MenuItem("Dark colors");
	       this.light = new MenuItem("Light colors");*/

	       
	       this.options.add(this.NewGame);
	       this.options.add(this.SaveGame);
	       this.options.add(this.LoadGame);
	       this.options.add(this.Undo);
	       
	       /*this.window.add(this.petset);
	       this.window.add(this.setset);
	       this.window.add(this.sedmset);
	       this.window.add(this.osmset);
	       this.window.add(this.dark);
	       this.window.add(this.light);*/

	}
	

	
	public void CreateNewGame(MazeBoard mb,int fieldsize, int gamers, int cardsnum, boolean print) throws Exception{
		

		
			//nastaveni rozmeru hraci desky
	       this.GameBoard = new JPanel(new GridLayout(0, fieldsize+2));
	       int gbsize = (fieldsize+2)*62; //velikost GameBoard Panelu
	       this.GameBoard.setSize(gbsize, gbsize);
	       this.GameBoard.setBackground(new Color(77,77,77));
	       

	       	this.next_player.setPreferredSize(new Dimension(173, 30));
	       	//this.next_player.setPreferredSize(new Dimension(160, 25));

			try {
			    //Image img = ImageIO.read(new File("lib/resources/button.png"));
				Image img = ImageIO.read(new File("lib/resources/button.png"));
			    next_player.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
			
				//next_player.setBounds(gbsize+55,300,160, 25);
				//next_player.setBounds(gbsize+55,300,160, 25);
				//next_player.add(next_player);
	       //-------------------------------
	       
	       // bocni panel
	       freepan.setLayout(new BoxLayout(freepan,BoxLayout.PAGE_AXIS ));
	       freepan.setBounds(gbsize+50, 10, 182, 150);
		   freepan.setBackground(new Color(77,77,77));
		   
		   freeimage.setBackground(new Color(77,77,77));
		   want_it.setBackground(new Color(77,77,77));
		   color_on_turn.setBackground(new Color(77,77,77));

		   my_button.setBackground(new Color(77,77,77));

		   sipky.setLayout(new GridLayout(0,3)); 
		   sipky.setBackground(new Color(66,66,66));
		   sipky.setBounds(gbsize+50,200,182,182);
		  
		   sipky.add(new JLabel(""));
		  // this.up_arrow = new JButton("A");
		   try {
			    Image img = ImageIO.read(new File("lib/resources/top_sipka.png"));
			    up_arrow.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
		   sipky.add(up_arrow);
		   sipky.add(new JLabel(""));
		 //  this.left_arrow =  new JButton("<");
		   try {
			    Image img = ImageIO.read(new File("lib/resources/left_sipka.png"));
			    left_arrow.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
		   sipky.add(left_arrow);
		   sipky.add(new JLabel(""));
		//   this.right_arrow =  new JButton(">");
		   try {
			    Image img = ImageIO.read(new File("lib/resources/right_sipka.png"));
			    right_arrow.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
		   sipky.add(right_arrow);
		   sipky.add(new JLabel(""));
		//   this.down_arrow =  new JButton("V");
		   try {
			    Image img = ImageIO.read(new File("lib/resources/down_sipka.png"));
			    down_arrow.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
		   sipky.add(down_arrow);
		   sipky.add(new JLabel(""));
		   
		   
		   next_player_space.setBounds(gbsize+50,400,182,160);  
		   //next_player_space.setLayout(new GridLayout(0,2));
		   next_player_space.setBackground(new Color(66,66,66));
		   next_player_space.setLayout(new FlowLayout());
		   
	       Label ff = new Label("       FREE FIELD: ");
	       ff.setForeground(new Color(105,189,69));
		   this.freepan.add(ff,BorderLayout.CENTER);
		   //this.freepan.add(new Label("<html><font color='#69BD45'>FREE FIELD: </font></html>"),BorderLayout.CENTER);
	       BufferedImage bi1;
	       //URL url1 = return_URL(MazeBoard.freesutr);
	       
	       Image img3 = this.return_URL(MazeBoard.freesutr);
	       bi1 = toBufferedImage(img3);
           /*bi1 = new BufferedImage(
          		    icon.getIconWidth(),
          		    icon.getIconHeight(),
          		    BufferedImage.TYPE_INT_RGB);
          		Graphics g = bi1.createGraphics();
          		// paint the Icon to the BufferedImage.
          		icon.paintIcon(null, g, 0,0);
          		g.dispose();   */        
          // bi1  = new BufferedImage(icon.getIconWidth(),
                         //  icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
           
	      // URL url1 = new URL("http://travelbydream.eu/ija_img/poklad_1.png");
          // bi1 = ImageIO.read(url1);
           //freepan.revalidate();
           //freepan.repaint();

           JLabel picLabel = new JLabel(new ImageIcon(bi1));
           freeimage.add(picLabel);
           
           img3 = this.want_treasure(MazeBoard.players_arr[player_on_turn].looking_for);
           bi1 = toBufferedImage(img3);
           /*bi1 = new BufferedImage(
          		    icon.getIconWidth(),
          		    icon.getIconHeight(),
          		    BufferedImage.TYPE_INT_RGB);
          		g = bi1.createGraphics();
          		// paint the Icon to the BufferedImage.
          		icon.paintIcon(null, g, 0,0);
          		g.dispose(); */
           
           JLabel want_it_pic = new JLabel(new ImageIcon(bi1));
           want_it.add(want_it_pic);
           
           Image img2 = this.want_color(MazeBoard.players_arr[player_on_turn].name);
           bi1= toBufferedImage(img2);
            /*bi1 = new BufferedImage(
           		    icon.getIconWidth(),
           		    icon.getIconHeight(),
           		    BufferedImage.TYPE_INT_RGB);
           		g = bi1.createGraphics();
           		// paint the Icon to the BufferedImage.
           		icon.paintIcon(null, g, 0,0);
           		g.dispose();*/
          		
           JLabel color_on_turn_pic = new JLabel(new ImageIcon(bi1));
           //color_on_turn_pic.setPreferredSize(new Dimension(15,15));
           color_on_turn.add(color_on_turn_pic);
           //color_on_turn.setBounds(gbsize+50, 300, 15, 15);
           //color_on_turn.setPreferredSize(new Dimension(15,15));
           
	       this.rotate.setPreferredSize(new Dimension(60, 25));
	       try {
			    Image img = ImageIO.read(new File("lib/resources/rotuj.png"));
			    rotate.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
           my_button.add(rotate);
           
           this.freepan.add(freeimage);
           this.freepan.add(my_button);
	       this.pane.add(this.GameBoard);
	       freepan.setBackground(new Color(77,77,77));

	       this.pane.add(freepan);
	       sipky.setBackground(new Color(77,77,77));
	       this.pane.add(sipky);
	       
	       Label pot = new Label("PLAYER ON TURN:");
	       pot.setForeground(new Color(105,189,69));
	       Label hc = new Label("HIS CARD:");
	       hc.setForeground(new Color(105,189,69));
	       Label ht = new Label("HIS TREASURES: "+Integer.toString(MazeBoard.players_arr[player_on_turn].get_treasures)+" FROM "+Integer.toString(cardnum/gamernum));
	       ht.setForeground(new Color(105,189,69));
	       //next_player_space.add(new Label("HIS TREASURES: "+Integer.toString(MazeBoard.players_arr[player_on_turn].get_treasures)+" FROM "+Integer.toString(cardnum/gamernum)));
	      // next_player_space.add(ht);
	      // next_player_space.add(new Label(""));
	       //next_player_space.add(next_player);
	      // next_player_space.setBackground(new Color(77,77,77));
	      
	      //this.next_player_space.add(new Label("<html><font color='#69BD45'>PLAYER ON TURN:</font></html>"), BorderLayout.LINE_START);
	       //this.next_player_space.add(new Label("<html><font color='#69BD45'>HIS CARD: </font></html>"));
	     //this.next_player_space.add(new Label("<html><font color='#69BD45'>HIS TREASURES: "+Integer.toString(MazeBoard.players_arr[player_on_turn].get_treasures)+" FROM "+Integer.toString(cardnum/gamernum)+"</font></html>"));
	       this.next_player_space.add(pot, BorderLayout.LINE_START);
	       this.next_player_space.add(color_on_turn,  BorderLayout.CENTER);
	       this.next_player_space.add(hc);
	       this.next_player_space.add(want_it);	       
	       this.next_player_space.add(ht);
	       this.next_player_space.add(next_player);
	       next_player_space.setBackground(new Color(77,77,77));
	      
	       this.pane.add(next_player_space);
	       
	       
	       URL url1 = null;;
		//-------------------------------
	       if (print == true) //pri load game print nechceme
	    	   print_board(mb,fieldsize, gamers, cardsnum, url1 , bi1);
	}
	
	
	
	public void print_board( MazeBoard mb,int fieldsize, int gamers, int cardsnum,  URL url1, BufferedImage bi1){
	       Insets buttonMargin = new Insets(0,0,0,0);
	        for (int ii = 0; ii < GameBoardSquares.length; ii++) {
	            for (int jj = 0; jj < GameBoardSquares[ii].length; jj++) {
	                JLabel b = new JLabel();
	               // b.setMargin(buttonMargin);

	        		MazeCard aktualni_karta = MazeBoard.board[ii][jj].getCard(); //mb.board na MazeBoard
	        		
	                
	                Image img3 = this.return_URL(aktualni_karta);
	                bi1 = toBufferedImage(img3);
	                /*bi1 = new BufferedImage(
	               		    icon.getIconWidth(),
	               		    icon.getIconHeight(),
	               		    BufferedImage.TYPE_INT_RGB);
	               		Graphics g = bi1.createGraphics();
	               		// paint the Icon to the BufferedImage.
	               		icon.paintIcon(null, g, 0,0);
	               		g.dispose();*/

	                
	                /*
	                // zobrazeni prvniho hrace, zobrazuje se vzdy
	                if (ii == 0 & jj == 0){
	                	bi1 = get_player_img(bi1,0);
	                }// druhy hrac, zobrazuje se vzdy
	                if (ii == (GameBoardSquares.length-1) && jj == (GameBoardSquares.length-1)){
	                	bi1 = get_player_img(bi1,1);
	                }// treti hrac
	                if (ii == 0 && jj == (GameBoardSquares.length-1) && gamers >= 3){
	                	bi1 = get_player_img(bi1,2);
	                }
	                // ctvrty hrac
	                if (ii == (GameBoardSquares.length-1) && jj == 0 && gamers == 4){
	                	bi1 = get_player_img(bi1,3);
	                }*/
	                
	                
	                
	             	//MazeBoard.setRotation(MazeBoard.getRotation(-1, false) + 1,  ((ii+1)*GameBoardSquares.length + (jj+1)), false);//get - 1. - poloha, 2. - freecard? || set 1. - o kolik orotovat, 2. - poloha, 3. - freecard?
	    	        
	                bi1 = rotateComponent(MazeBoard.getRotation(((ii)*GameBoardSquares.length + (jj)), false)*Math.PI/2, bi1);

	                if (aktualni_karta.player_on[0] != -1){
	                	bi1 = get_player_img(bi1,aktualni_karta.player_on[0]);
	                }
	                
	                if (aktualni_karta.player_on[1] != -1){
	                	bi1 = get_player_img(bi1,aktualni_karta.player_on[1]);
	    				/*System.out.print("\n\n\n\nnasel jsem playera\n");
	    				System.out.print("\nradek: ");
	    				System.out.print(ii);

	    				System.out.print("\nsloupec");
	    				System.out.print(jj);*/


	                }
	                
	                if (aktualni_karta.player_on[2] != -1){
	                	bi1 = get_player_img(bi1,aktualni_karta.player_on[2]);
	                }
	                
	                if (aktualni_karta.player_on[3] != -1){
	                	bi1 = get_player_img(bi1,aktualni_karta.player_on[3]);
	                }
	                
	                b.setIcon(new ImageIcon(bi1));
	                
	                
	                b.setBackground(Color.BLACK);

	                GameBoardSquares[jj][ii] = b;
	            }
	        }
	        
	        fill_GameBoard(GameBoardSquares);
			

		
	}
	
    
    protected BufferedImage  rotateComponent(double radians, BufferedImage image) {
    	   AffineTransform transform = new AffineTransform();
    	    transform.rotate(radians, image.getWidth()/2, image.getHeight()/2);
    	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
    	    image = op.filter(image, null);
    	    return image;

    }
    
    public void print_freesutr(boolean rotate){
    	 Image url = return_URL(MazeBoard.freesutr);
         
         //BufferedImage image = null;
         //ImageIcon icon = url;
         /*BufferedImage image = new BufferedImage(
        		    icon.getIconWidth(),
        		    icon.getIconHeight(),
        		    BufferedImage.TYPE_INT_RGB);
        		Graphics g = image.createGraphics();
        		// paint the Icon to the BufferedImage.
        		icon.paintIcon(null, g, 0,0);
        		g.dispose();*/
         
         /*try{
         	image = ImageIO.read(url);	                
         	}
         catch (IOException p) {
             System.out.print("CHYBA!!");

	       	}*/
       BufferedImage image = toBufferedImage(url);
         	if (rotate == true) MazeBoard.setRotation(MazeBoard.getRotation(-1, true) + 1, -1, true);//get - 1. - poloha, 2. - freecard? || set 1. - o kolik orotovat, 2. - poloha, 3. - freecard?
	        image = rotateComponent(MazeBoard.getRotation(-1, true)*Math.PI/2, image);
	      
	        freeimage.removeAll();
	        
	        JLabel picLabel = new JLabel(new ImageIcon(image));
	        freeimage.add(picLabel);
	        
	           			       
		   freeimage.revalidate();	
		   freeimage.repaint();
    }
    
    private void refresh_board(int i, int j){
   	 	
    	MazeField RC = mb.get(i, j);
   	 	if (RC == null){
   	 		//System.err.println("Pozice mimo platny rozsah");
   	 	}else
   	 	{
         mb.shift(RC);
   	 	}
   	 	
    	GameBoard.removeAll();
		URL url1 = null;
		BufferedImage obr = null;
		//System.out.print("\nrefreshuju\n");

	    print_board(mb,fieldsize, gamernum, cardnum, url1, obr);
		GameBoard.revalidate();	
		GameBoard.repaint();
		
    	next_player_space.removeAll();
    	want_it.removeAll();
    	color_on_turn.removeAll();
    	
    	
    	Image img3 = this.want_treasure(MazeBoard.players_arr[player_on_turn].looking_for);
       BufferedImage bi1 = toBufferedImage(img3);/*new BufferedImage(
       		    icon.getIconWidth(),
       		    icon.getIconHeight(),
       		    BufferedImage.TYPE_INT_RGB);
       		Graphics g = bi1.createGraphics();
       		// paint the Icon to the BufferedImage.
       		icon.paintIcon(null, g, 0,0);
       		g.dispose(); */
        
        JLabel want_it_pic = new JLabel(new ImageIcon(bi1));
        want_it.add(want_it_pic);
        
        Image img2 = this.want_color(MazeBoard.players_arr[player_on_turn].name);
       bi1= toBufferedImage(img2);
        /*bi1 = new BufferedImage(
       		    icon.getIconWidth(),
       		    icon.getIconHeight(),
       		    BufferedImage.TYPE_INT_RGB);
       		g = bi1.createGraphics();
       		// paint the Icon to the BufferedImage.
       		icon.paintIcon(null, g, 0,0);
       		g.dispose();*/ 
        
        JLabel color_on_turn_pic = new JLabel(new ImageIcon(bi1));
        color_on_turn.add(color_on_turn_pic);
	       Label pot = new Label("PLAYER ON TURN:");
	       pot.setForeground(new Color(105,189,69));
	       Label hs = new Label("HIS CARD:");
	       hs.setForeground(new Color(105,189,69));
	       Label ht = new Label("HIS TREASURES: "+Integer.toString(MazeBoard.players_arr[player_on_turn].get_treasures)+" FROM "+Integer.toString(cardnum/gamernum));
	       ht.setForeground(new Color(105,189,69));
           this.next_player_space.add(pot, BorderLayout.LINE_START);
	       this.next_player_space.add(color_on_turn,  BorderLayout.CENTER);
	       this.next_player_space.add(hs);
	       this.next_player_space.add(want_it);	       
	       this.next_player_space.add(ht);
	       this.next_player_space.add(next_player);
		next_player_space.revalidate();	
		next_player_space.repaint();
		
		print_freesutr(false);
    }
	
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    
	public void setListeners(final JPanel freepan)
	{
	       //prvni vyber
		
	        this.NewGame.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	          
	        getContentPane().removeAll();
	        freepan.removeAll(); //remove panel with freecard and board
	        if (GameBoard != null)
	        	GameBoard.removeAll();
	        shift = false;//
	        before_x=0; //
	   	 	before_y=0;//
	        MazeBoard.poklady_na_plose = new int[24];
	        MazeBoard.cislo_ukolu = 0;
	        mb = new MazeBoard();//
	        sipky.removeAll();
	        next_player_space.removeAll();
	        color_on_turn.removeAll();
	        want_it.removeAll();
	        next_player.removeAll();
	        freeimage.removeAll();
	        CreateGameWindow();
	        repaint();
            printAll(getGraphics());
	        }});
	        
	        rotate.addActionListener(new ActionListener() {
	        	 
				public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                MazeBoard.turnRight();
	                
	               print_freesutr(true);
			           
	            }
				
			//////////////////NEXT PLAYER BUTTON///////////////////////
	        }); 
	        this.next_player.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	shift = false;
	        		manager.addChangeable(new CommandLineChanger(mb,player_on_turn,GameBoardSquares,true));
		        	switch (player_on_turn){
		        	case 0:
		        		player_on_turn = 1;
			        	//System.out.println("next player");
			        	//System.out.println(player_on_turn);
		        		break;
		        	case 1:
		        		if (gamernum>2){
		        			player_on_turn = 2;
		        		}
		        		else{
		        			player_on_turn = 0;
		        		}
			        	//System.out.println("next player");
			        	//System.out.println(player_on_turn);
		        		break;
		        	case 2:
		        		if (gamernum>3){
		        			player_on_turn = 3;
		        		}
		        		else{
		        			player_on_turn = 0;
		        		}
			        	//System.out.println("next player");
			        	//System.out.println(player_on_turn);
		        		break;
		        	case 3:
		        		player_on_turn = 0;
			        	//System.out.println("next player");
			        	//System.out.println(player_on_turn);
		        		break;
		        	}
		        	
		        	next_player_space.removeAll();
		        	want_it.removeAll();
		        	color_on_turn.removeAll();
		        	
		        	Image img3 = want_treasure(MazeBoard.players_arr[player_on_turn].looking_for);
		            BufferedImage bi1 = toBufferedImage(img3);/*new BufferedImage(
		            		    icon.getIconWidth(),
		            		    icon.getIconHeight(),
		            		    BufferedImage.TYPE_INT_RGB);
		            		Graphics g = bi1.createGraphics();
		            		// paint the Icon to the BufferedImage.
		            		icon.paintIcon(null, g, 0,0);
		            		g.dispose(); */
		             
		             JLabel want_it_pic = new JLabel(new ImageIcon(bi1));
		             want_it.add(want_it_pic);
		             Image img2 = want_color(MazeBoard.players_arr[player_on_turn].name);
		             bi1 = toBufferedImage(img2);
		            /* bi1 = new BufferedImage(
		            		    icon.getIconWidth(),
		            		    icon.getIconHeight(),
		            		    BufferedImage.TYPE_INT_RGB);
		            		g = bi1.createGraphics();
		            		// paint the Icon to the BufferedImage.
		            		icon.paintIcon(null, g, 0,0);
		            		g.dispose(); */
		             
		             JLabel color_on_turn_pic = new JLabel(new ImageIcon(bi1));
		             color_on_turn.add(color_on_turn_pic);		        	
		  	       Label pot = new Label("PLAYER ON TURN:");
			       pot.setForeground(new Color(105,189,69));
			       Label hs = new Label("HIS CARD:");
			       hs.setForeground(new Color(105,189,69));
			       Label ht = new Label("HIS TREASURES: "+Integer.toString(MazeBoard.players_arr[player_on_turn].get_treasures)+" FROM "+Integer.toString(cardnum/gamernum));
			       ht.setForeground(new Color(105,189,69));
		           next_player_space.add(pot, BorderLayout.LINE_START);
			       next_player_space.add(color_on_turn,  BorderLayout.CENTER);
			       next_player_space.add(hs);
			       next_player_space.add(want_it);	       
			       next_player_space.add(ht);
			       next_player_space.add(next_player);
		    		next_player_space.revalidate();	
		    		next_player_space.repaint();
		        	
		        }});
	        ///////////////////////////////////////////////////////////
	        this.Undo.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	manager.undo();
	        	
			   
	        }});
	        
	        /////////////POHYBY PANACKU////////////////////////////////
	        this.up_arrow.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	boolean konec;
	        	if (shift == true){
	        		//System.out.print(player_on_turn);   
        			manager.addChangeable(new CommandLineChanger(mb,player_on_turn,GameBoardSquares,true));
	        		konec = MazeBoard.move_players(player_on_turn, 0);	
	        		refresh_board(-1,-1);
	        		//System.out.println("pohyb up");
	        		if (konec == true) {
		        		//System.out.println("KONEC HRY UP");
		        		manager = new ChangeManager(); //reset undo
		        		disable_board();
	        			//pane.removeAll();
	        		}
	        		mb.id = mb.id + 1;
	        		//System.out.println("mb id pred undo: "+mb.id);
	        	}		   
	        }});
	        this.left_arrow.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	boolean konec;
	        	if (shift == true){
        			manager.addChangeable(new CommandLineChanger(mb,player_on_turn,GameBoardSquares,true));
	        		konec = MazeBoard.move_players(player_on_turn, 3);
	        		refresh_board(-1,-1);

	        		//System.out.println("pohyb left");
	        		if (konec == true) {
		        		//System.out.println("KONEC HRY, LEFT");
		        		disable_board();
		        		manager = new ChangeManager();
	        			//pane.removeAll();
	        		}

	        	}
			   
	        }});
	        this.right_arrow.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	boolean konec;
	        	if (shift == true){
        			manager.addChangeable(new CommandLineChanger(mb,player_on_turn,GameBoardSquares,true));
	        		konec = MazeBoard.move_players(player_on_turn, 1);
	        		refresh_board(-1,-1);

	        		//System.out.println("pohyb right");
	        		if (konec == true) {
		        		//System.out.println("KONEC HRY, RIGHT");
		        		manager = new ChangeManager();
		        		disable_board();
		        		//pane.removeAll();
	        		}

	        	}
			   
	        }});
	        this.down_arrow.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	boolean konec;
	        	if (shift == true){
        			manager.addChangeable(new CommandLineChanger(mb,player_on_turn,GameBoardSquares,true));
	        		konec = MazeBoard.move_players(player_on_turn, 2);
	        		refresh_board(-1,-1);

	        		//System.out.println("pohyb down");
	        		if (konec == true) {	        		
	        			//System.out.println("KONEC HRY, DOWN");
	        			manager = new ChangeManager();
	        			disable_board();
	        		}

	        	}
	        	
	       // ImageIcon ii = new ImageIcon(this.getClass().getResource("lib/resources/img.png"));
	        //freepan.add(ii);
	        	
			   
	        }});
	        ///////////////////////////////////////////////////////////
	        
	        
	        ////////////////////SHIFTY/////////////////////////////////
	        this.dva_leva.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
		        	if ((before_x == 2) && (before_y == fieldsize)){ // nedovoli revertovat predesly shift
		        		//System.out.println("neplatny shift");
		        		
		        		}
		        	else {
		        		test_refresh(2,1);//zabranuje vicenasobnemu shiftovani pri jednom tahu 	   
		        	}
			   
	        }});
	        
	        this.ctyri_leva.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	if ((before_x == 4) && (before_y == fieldsize)){
		        		System.out.println(before_x+" "+before_y);
		        		 //System.out.println("neplatny shift");
		        	}
		        	else test_refresh(4,1);
		        		

		        }});
	        
	        this.sest_leva.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	if ((before_x == 6) && (before_y == fieldsize)){
		        		System.out.println(before_x+" "+before_y);
		        		 //System.out.println("neplatny shift");
		        	}
		        	else test_refresh(6,1);
		        }});
	        
	        this.osm_leva.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	if ((before_x == 8) && (before_y == fieldsize)){
		        		System.out.println(before_x+" "+before_y);
		        		 //System.out.println("neplatny shift");
		        	}
		        	else test_refresh(8,1);

		        }});
	        
	        this.deset_leva.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	if ((before_x == 10) && (before_y == fieldsize)){
		        		System.out.println(before_x+" "+before_y);
		        		 //System.out.println("neplatny shift");
		        	}
		        	else test_refresh(10,1);

		        }});
	        
	        
	        
	        this.dva_prava.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	if (before_x == 2 && before_y == 1){
		        	System.out.println(before_x+" "+before_y);
		        	//System.out.println("neplatny shift");
		        	}
		        	else test_refresh(2,fieldsize);
		        }});
		        
		        this.ctyri_prava.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	if (before_x == 4 && before_y == 1){
				        	System.out.println(before_x+" "+before_y);
				        	//System.out.println("neplatny shift");
				        	}
				        	else test_refresh(4,fieldsize);
			        }});
		        
		        this.sest_prava.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	if (before_x == 6 && before_y == 1){
				        	System.out.println(before_x+" "+before_y);
				        	//System.out.println("neplatny shift");
				        	}
				        	else test_refresh(6,fieldsize);
			        }});
		        
		        this.osm_prava.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	if (before_x == 8 && before_y == 1){
				        	System.out.println(before_x+" "+before_y);
				        	//System.out.println("neplatny shift");
				        	}
				        	else test_refresh(8,fieldsize);
			        }});
		        
		        this.deset_prava.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	if (before_x == 10 && before_y == 1){
				        	System.out.println(before_x+" "+before_y);
				        	//System.out.println("neplatny shift");
				        	}
				        	else test_refresh(10,fieldsize);
			        }});
		        
		        
		        this.dva_dolni.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	if (before_x == 1 && before_y == 2){
				        	System.out.println(before_x+" "+before_y);
				        	//System.out.println("neplatny shift");
				        	}
				        	else test_refresh(fieldsize,2);

			        }});
			        
			        this.ctyri_dolni.addActionListener(new ActionListener(){
				        public void actionPerformed(ActionEvent e){
				        	if (before_x == 1 && before_y == 4){
					        	System.out.println(before_x+" "+before_y);
					        	//System.out.println("neplatny shift");
					        	}
					        	else test_refresh(fieldsize,4);;
				        }});
			        
			        this.sest_dolni.addActionListener(new ActionListener(){
				        public void actionPerformed(ActionEvent e){
				        	if (before_x == 1 && before_y == 6){
					        	System.out.println(before_x+" "+before_y);
					        	//System.out.println("neplatny shift");
					        	}
					        	else test_refresh(fieldsize,6);
				        }});
			        
			        this.osm_dolni.addActionListener(new ActionListener(){
				        public void actionPerformed(ActionEvent e){
				        	if (before_x == 1 && before_y == 8){
					        	System.out.println(before_x+" "+before_y);
					        	//System.out.println("neplatny shift");
					        	}
					        	else test_refresh(fieldsize,8);
				        }});
			        
			        this.deset_dolni.addActionListener(new ActionListener(){
				        public void actionPerformed(ActionEvent e){
				        	if (before_x == 1 && before_y == 10){
					        	System.out.println(before_x+" "+before_y);
					        	//System.out.println("neplatny shift");
					        	}
					        	else test_refresh(fieldsize,10);
				        }});
			        
			        
			        
			        this.dva_horni.addActionListener(new ActionListener(){
				        public void actionPerformed(ActionEvent e){
				        	if (before_x == fieldsize && before_y == 2){
					        	System.out.println(before_x+" "+before_y);
					        	//System.out.println("neplatny shift");
					        	}
					        	else test_refresh(1,2);
				        }});
				        
				        this.ctyri_horni.addActionListener(new ActionListener(){
					        public void actionPerformed(ActionEvent e){

					        	if (before_x == fieldsize && before_y == 4){
						        	System.out.println(before_x+" "+before_y);
						        	//System.out.println("neplatny shift");
						        	}
						        	else test_refresh(1,4);
					        }});
				        
				        this.sest_horni.addActionListener(new ActionListener(){
					        public void actionPerformed(ActionEvent e){

					        	if (before_x == fieldsize && before_y == 6){
						        	System.out.println(before_x+" "+before_y);
						        	//System.out.println("neplatny shift");
						        	}
						        	else test_refresh(1,6);
					        }});
				        
				        this.osm_horni.addActionListener(new ActionListener(){
					        public void actionPerformed(ActionEvent e){

					        	if (before_x == fieldsize && before_y == 8){
						        	System.out.println(before_x+" "+before_y);
						        	//System.out.println("neplatny shift");
						        	}
						        	else test_refresh(1,8);;
					        }});
				        
				        this.deset_horni.addActionListener(new ActionListener(){
					        public void actionPerformed(ActionEvent e){

					        	if (before_x == fieldsize && before_y == 10){
						        	System.out.println(before_x+" "+before_y);
						        	//System.out.println("neplatny shift");
						        	}
						        	else test_refresh(1,10);
					        }});
				   ///////////////////////////////////////////////////////////
				   ////////////////ULOZENI HRY////////////////////////////////       
				   
				   this.SaveGame.addActionListener(new ActionListener(){
				        private ObjectOutputStream out;

						public void actionPerformed(ActionEvent e){
				           File selectedFile = null;
				           mb.id = 100000;
						  // System.out.println("ulozeni hry");
						   JFileChooser fileChooser = new JFileChooser();
						   fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
						   int result = fileChooser.showSaveDialog(freepan); // okno pro ulozeni
						   if (result == JFileChooser.APPROVE_OPTION) {
						       selectedFile = fileChooser.getSelectedFile();
						       //System.out.println("Selected file: " + selectedFile.getAbsolutePath());

								   try{
									//System.out.println("ukladany mazeboard:");
									mb.print();
									out = new ObjectOutputStream( new FileOutputStream( selectedFile ));
								    
									out.writeObject(fieldsize);
									mb.writeObject(out);
								    out.writeObject(gamernum);
								    out.writeObject(cardnum);
								    out.close();
								   }
								   catch (IOException ioe) {
								       // know if something went wrong
								      ioe.printStackTrace();
								   }
						   }
				        }});
				   this.LoadGame.addActionListener(new ActionListener(){
					    private ObjectInputStream saved;
				        public void actionPerformed(ActionEvent e){
				        	   //System.out.println("nacteni hry");;
					           File selectedFile = null;
					          //mb = null;
							   JFileChooser fileChooser = new JFileChooser();
							   fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
							   int result = fileChooser.showOpenDialog(freepan); // okno pro ulozeni
							   if (result == JFileChooser.APPROVE_OPTION) {
							       selectedFile = fileChooser.getSelectedFile();
							       //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
									   try{
										saved = new ObjectInputStream( new FileInputStream( selectedFile ));
										fieldsize = (int) saved.readObject();
										mb = new MazeBoard();
										mb.createMazeBoard(fieldsize);
									    mb.readObject(saved);
									    
									    gamernum = (int) saved.readObject();
									    cardnum = (int) saved.readObject();								   
									    saved.close();
									   // System.out.println("print desky");
									    //System.out.println("nacteny mazeboard:");
									    mb.print();
									   // System.out.println(mb.id);
									    // TISK DESKY//////////////
									    pane = getContentPane();
									    pane.setBackground(new Color(66,66,66)); 
									    pane.removeAll();
									    clear_board(); //clear game components
							            try{
							            	GameBoardSquares = new JLabel[fieldsize][fieldsize];
							            	CreateNewGame(mb,fieldsize,gamernum,cardnum,true);
							            	//fill_GameBoard(GameBoardsqu);
							            	pane.add(GameBoard);
							            	print_freesutr(false);	
							            }
							            catch (Exception ex) {
						                    ex.printStackTrace();
						                }
							            
							            repaint();
							            printAll(getGraphics());
									    
							        	
									   }
									   catch (Exception ioe) {
									       // do something if there is an error, at least this so you
									       // know if something went wrong
									      ioe.printStackTrace();
									   }
							   }
				        }});
	        
	    }
	public BufferedImage get_player_img(BufferedImage image,int player){
		/**
		 * get_player_img - funkce pro zobrazeni hrace na danem kameni
		 **/
		
        /////////////////merging images///////////////
        BufferedImage overlay = null;
        int position_x = 0; // umisteni panacka x ose
        int position_y = 0; // umsiteni panacka na y ose
        if (player == 0){
        	try{
        	overlay = ImageIO.read(new File("lib/resources/cerveny.png"));
        	/*ImageIcon picLabel = new ImageIcon(this.getClass().getResource("lib/resources/cerveny.png"));
        	 overlay = new BufferedImage(
        			 picLabel.getIconWidth(),
        			 picLabel.getIconHeight(),
        		    BufferedImage.TYPE_INT_RGB);
        		Graphics g = image.createGraphics();
        		// paint the Icon to the BufferedImage.
        		picLabel.paintIcon(null, g, 0,0);
        		g.dispose();
        	
        	position_x = 0;
    		position_y = 0;
        	*/}
        	catch (IOException e){
        		System.out.println("URLError in get_player_img function");
    		}
        }
        else if(player == 1){
        	try{
            	overlay = ImageIO.read(new File("lib/resources/zluty.png"));
            	/*ImageIcon picLabel = new ImageIcon(this.getClass().getResource("lib/resources/zluty.png"));
            	 overlay = new BufferedImage(
            			 picLabel.getIconWidth(),
            			 picLabel.getIconHeight(),
            		    BufferedImage.TYPE_INT_RGB);
            		Graphics g = image.createGraphics();
            		// paint the Icon to the BufferedImage.
            		picLabel.paintIcon(null, g, 0,0);
            		g.dispose();
        	position_x = 0;
    		position_y = 0;
        	*/}
        	catch (IOException e){
        		System.out.println("URLError in get_player_img function");
        	}
        }
        else if(player == 2){
        	try{
            	overlay = ImageIO.read(new File("lib/resources/zeleny.png"));
            	/*ImageIcon picLabel = new ImageIcon(this.getClass().getResource("lib/resources/zeleny.png"));
            	 overlay = new BufferedImage(
            			 picLabel.getIconWidth(),
            			 picLabel.getIconHeight(),
            		    BufferedImage.TYPE_INT_RGB);
            		Graphics g = image.createGraphics();
            		// paint the Icon to the BufferedImage.
            		picLabel.paintIcon(null, g, 0,0);
            		g.dispose();
        	position_x = 0;
    		position_y = 0;
        	*/}
        	catch (IOException e){
        		System.out.println("URLError in get_player_img function");
        	}
        }
        else if(player == 3){
        	try{
            	overlay = ImageIO.read(new File("fialovy/cerveny.png"));
            	/*ImageIcon picLabel = new ImageIcon(this.getClass().getResource("lib/resources/fialovy.png"));
            	 overlay = new BufferedImage(
            			 picLabel.getIconWidth(),
            			 picLabel.getIconHeight(),
            		    BufferedImage.TYPE_INT_RGB);
            		Graphics g = image.createGraphics();
            		// paint the Icon to the BufferedImage.
            		picLabel.paintIcon(null, g, 0,0);
            		g.dispose();
            	position_x = 0;
        		position_y = 0;
        	*/}
        	catch (IOException e){
        		System.out.println("URLError in get_player_img function");
        	}
        }
        
        // create the new image, canvas size is the max. of both image sizes
        int w = Math.max(image.getWidth(), overlay.getWidth());
        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics(); 
        g.drawImage(image, 0, 0, null);
        g.drawImage(overlay, 0, 0, null); //jeden hrac
        //g.drawImage(overlay, 0, 0, null); //treba druhy

        // Save as new image
        //////////////////////////////////////////////////////////////////
        return combined; //vraci kamen s panackem
        //b.setIcon(new ImageIcon(combined));		
		
	}
	
	
	public Image want_color(int cislo_pokladu){
		Image picLabel = null;

        switch(cislo_pokladu){
        case 0:
            try {
				picLabel = ImageIO.read(new File("lib/resources/barva_cervena.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 1:
            try {
				picLabel = ImageIO.read(new File("lib/resources/barva_zluta.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 2:
            try {
				picLabel = ImageIO.read(new File("lib/resources/barva_zelena.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 3:
            try {
				picLabel = ImageIO.read(new File("lib/resources/barva_fialova.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        }
        return picLabel;
	}
	
	public Image want_treasure(int cislo_pokladu){
		Image picLabel = null;

        switch(cislo_pokladu){
        case 1:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 2:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 3:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 4:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    	break;
        case 5:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_5.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 6:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_6.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 7:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_7.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 8:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_8.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 9:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_9.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 10:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_10.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 11:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_11.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 12:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_12.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 13:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_13.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 14:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_14.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 15:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_15.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 16:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_16.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 17:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_17.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 18:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_18.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 19:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_19.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 20:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_20.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 21:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_21.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 22:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_22.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 23:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_23.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 24:
            try {
				picLabel = ImageIO.read(new File("lib/resources/treasures/treasure_24.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	
        }
        return picLabel;
	}
	
	
	
	
	public Image return_URL(MazeCard aktualni_karta){
		Image picLabel = null;

        switch(aktualni_karta.name){
        case 'x':
            try {
				picLabel = ImageIO.read(new File("lib/resources/2_top_down.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
        case 'z':
            try {
				picLabel = ImageIO.read(new File("lib/resources/2_left_right.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'A':
            try {
				picLabel = ImageIO.read(new File("lib/resources/2_top_left.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	break;
    	case 'B':
            try {
				picLabel = ImageIO.read(new File("lib/resources/2_down_right.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'C':
            try {
				picLabel = ImageIO.read(new File("lib/resources/2_top_right.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'D':
            try {
				picLabel = ImageIO.read(new File("lib/resources/2_down_left.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'E':
            try {
				picLabel = ImageIO.read(new File("lib/resources/3_left_right_down.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'F':
            try {
				picLabel = ImageIO.read(new File("lib/resources/3_left_right_top.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'G':
            try {
				picLabel = ImageIO.read(new File("lib/resources/3_top_down_left.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'H':
            try {
				picLabel = ImageIO.read(new File("lib/resources/3_top_down_right.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'I':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'J':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'K':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'L':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'M':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_5.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'N':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_6.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'O':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_7.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'P':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_8.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'Q':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_9.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'R':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_10.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'S':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_11.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'T':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_12.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'U':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_13.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'V':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_14.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'W':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_15.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'X':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_16.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'Y':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_17.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'Z':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_18.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'a':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_19.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'b':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_20.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'c':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_21.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'd':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_22.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'e':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_23.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	case 'f':
            try {
				picLabel = ImageIO.read(new File("lib/resources/poklad_24.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	break;
    	
        } 
    	return picLabel;


	}
	public void fill_GameBoard(JLabel[][]GameBoardSquares){
        GameBoard.add(new JLabel("")); //first corner
        try {
		    Image img = ImageIO.read(new File("lib/resources/right_sipka.png"));
		    dva_leva.setIcon(new ImageIcon(img));
		    ctyri_leva.setIcon(new ImageIcon(img));
		    sest_leva.setIcon(new ImageIcon(img));
		    osm_leva.setIcon(new ImageIcon(img));
		    deset_leva.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
        
        try {
		    Image img = ImageIO.read(new File("lib/resources/left_sipka.png"));
		    dva_prava.setIcon(new ImageIcon(img));
		    ctyri_prava.setIcon(new ImageIcon(img));
		    sest_prava.setIcon(new ImageIcon(img));
		    osm_prava.setIcon(new ImageIcon(img));
		    deset_prava.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
        
        try {
		    Image img = ImageIO.read(new File("lib/resources/down_sipka.png"));
		    dva_horni.setIcon(new ImageIcon(img));
		    ctyri_horni.setIcon(new ImageIcon(img));
		    sest_horni.setIcon(new ImageIcon(img));
		    osm_horni.setIcon(new ImageIcon(img));
		    deset_horni.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
        
        try {
		    Image img = ImageIO.read(new File("lib/resources/top_sipka.png"));
		    dva_dolni.setIcon(new ImageIcon(img));
		    ctyri_dolni.setIcon(new ImageIcon(img));
		    sest_dolni.setIcon(new ImageIcon(img));
		    osm_dolni.setIcon(new ImageIcon(img));
		    deset_dolni.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
        // fill the top row
        // for (int ii = 0; ii < 7; ii++) {
        for (int ii = 0; ii < GameBoardSquares.length; ii++) {
            		if(ii%2 == 1){
            			if (ii == 1) GameBoard.add(dva_horni);
            			if (ii == 3) GameBoard.add(ctyri_horni);
            			if (ii == 5) GameBoard.add(sest_horni);
            			if (ii == 7) GameBoard.add(osm_horni);
            			if (ii == 9) GameBoard.add(deset_horni);
            		}
            		else{
            			 GameBoard.add(new JLabel("")); // free field
            		}
            		//new JButton(COLS.substring(ii, ii + 1)));
                    //new JLabel(COLS.substring(ii, ii + 1),
                    //SwingConstants.CENTER));
        }
        GameBoard.add(new JLabel("")); //second corner
        // fill the black non-pawn piece row
        //for (int ii = 0; ii < 7; ii++) {
         //   for (int jj = 0; jj < 9; jj++) {
        int rightshift_pos = GameBoardSquares.length+1; //pozice praveho shift
        
        for (int ii = 0; ii < GameBoardSquares.length; ii++) {
            for (int jj = 0; jj < GameBoardSquares.length+2; jj++) {
                //switch (jj) {
                    //case 0:
            	if (jj == 0){
                    	if(ii%2 == 1){
	            			if (ii == 1) GameBoard.add(dva_leva);
	            			if (ii == 3) GameBoard.add(ctyri_leva);
	            			if (ii == 5) GameBoard.add(sest_leva);
	            			if (ii == 7) GameBoard.add(osm_leva);
	            			if (ii == 9) GameBoard.add(deset_leva);
                    	}
                    	else{
                    		GameBoard.add(new JLabel("")); // prazdne pole
                    	}
                 //   break;
            	}
            	else if(jj == rightshift_pos){
                //    case rightshift:
                    	if(ii%2 == 1){ //shift jen u sudych, test na liche protoze prvni radek pole jsou shifty
	            			if (ii == 1) GameBoard.add(dva_prava);
	            			if (ii == 3) GameBoard.add(ctyri_prava);
	            			if (ii == 5) GameBoard.add(sest_prava);
	            			if (ii == 7) GameBoard.add(osm_prava);
	            			if (ii == 9) GameBoard.add(deset_prava);
                    	}
                    	else{
                    		GameBoard.add(new JLabel("")); // prazdne pole
                    	}
                //    break;
            	}
            	else{
                  //  default:
                    	//System.out.println("jj: " + jj +" ii: "+ ii);
                    	// jj-1 kvuli prvnimu shiftu (case 0)
                        GameBoard.add(GameBoardSquares[jj-1][ii]);
                }
            }
        }
        //last row
        GameBoard.add(new JLabel("")); //third corner
        // fill the top row
        for (int ii = 0; ii < GameBoardSquares.length; ii++) {
            		if(ii%2 == 1){
            			if (ii == 1) GameBoard.add(dva_dolni);
            			if (ii == 3) GameBoard.add(ctyri_dolni);
            			if (ii == 5) GameBoard.add(sest_dolni);
            			if (ii == 7) GameBoard.add(osm_dolni);
            			if (ii == 9) GameBoard.add(deset_dolni);
            		}
            		else{
            			 GameBoard.add(new JLabel("")); // free field
            		}
        }
        GameBoard.add(new JLabel("")); //fourth corner
		
	}
	public void test_refresh(int x,int y){
		// funkce zamezujici vicenasobnemu shiftovani pri jednom tahu hrace
    	if (shift == false){
    		manager.addChangeable(new CommandLineChanger(mb,player_on_turn,GameBoardSquares,false));
    		refresh_board(x,y);
        	before_x = x;
        	before_y = y;
        	//System.out.println("test_refr "+ before_x+" "+before_y);
    	}    	
    	shift = true;
	}
	public void clear_board(){
		//remove components from main panel
        freepan.removeAll(); 
        sipky.removeAll();
        next_player_space.removeAll();
        color_on_turn.removeAll();
        want_it.removeAll();
        next_player.removeAll();
        freeimage.removeAll();
	}
	public static void setContainerAndChildrenEnabled(Container c, boolean b)
	{
	    Component[] allComps = c.getComponents();
	    for(Component com : allComps)
	    {
	        com.setEnabled(b);
	        if(com instanceof Container)
	            setContainerAndChildrenEnabled((Container) com, b);
	    }
	}
	public void disable_board(){
		//remove components from main panel
		setContainerAndChildrenEnabled(pane, false);
		/*
        freepan.setEnabled(false);
        sipky.setEnabled(false);
        next_player_space.setEnabled(false);
        color_on_turn.setEnabled(false);
        want_it.setEnabled(false);
        next_player.setEnabled(false);
        freeimage.setEnabled(false);
        GameBoard.setEnabled(false);
       // clear_board();
        GameBoard.removeAll();
        */
        end_window.setSize(200,200);
        //getContentPanel.removeall();
		//pane.removeAll();
        end_window.setBounds(0, ((fieldsize+2)*62)+10, (fieldsize+2)*62, 100);
        end_window.setBackground(new Color(77,77,77));
        Label end_info = new Label ("GAME OVER, YOU ARE THE WINNER");
        end_info.setForeground(new Color(105,189,69));
        Font font = new Font("Courier", Font.BOLD,16);
        end_info.setFont(font);
        
        Image img2 = this.want_color(MazeBoard.players_arr[player_on_turn].name);
        BufferedImage bi1= toBufferedImage(img2);
         /*bi1 = new BufferedImage(
        		    icon.getIconWidth(),
        		    icon.getIconHeight(),
        		    BufferedImage.TYPE_INT_RGB);
        		g = bi1.createGraphics();
        		// paint the Icon to the BufferedImage.
        		icon.paintIcon(null, g, 0,0);
        		g.dispose();*/
       		
       		
       	color_on_turn.setBackground(new Color(77,77,77));
        JLabel color_on_turn_pic = new JLabel(new ImageIcon(bi1));
        color_on_turn.add(color_on_turn_pic);
        this.add(color_on_turn);
        end_window.add(end_info);
        end_window.setVisible(true);
        this.add(end_window,BorderLayout.CENTER);
        repaint();
        printAll(getGraphics());
        
	}
	public static void createEmployee(int number){

		   //Scanner input = new Scanner(System.in);
		   //System.out.printf("Enter full name of employee %d, separated by spaces: ", number);
		   String fName = "Undo_file";
		   String lName = Integer.toString(number);
		  // return String(fName,lName);
		}
	
	public class CommandLineChanger implements Changeable{

		//private final MazeBoard val;
		private final int val;
		private final MazeField[][] undo_board;
		private final MazeCard undo_freesutr;
		private final BoardPlayersTreasures[] undo_players_arr;
		private int[] undo_poklady_na_plose;
		private int undo_player_on_turn;
		private JLabel[][] undo_GameBoardSquares;
		File file = new File("lib/"+Integer.toString(undocounter));
		//File file = new File("undo/"+UUID.randomUUID().toString());
		private ObjectInputStream in;
		private boolean undo_shift;
		
		public CommandLineChanger(MazeBoard v, int player_on_t, JLabel[][] GameBoardSquares,boolean shft){
			/**
			 * funkce pro undo
			 * funkce for saving data, ktere slouzi k vraceni k predeslemu stavu
			 * 
			 * 
			 * 
			 * */
			super();
			try{
				FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				mb.writeObject(out);
			}
			catch (IOException ioe){
				 ioe.printStackTrace();
			}
			
			//System.out.println("parametr v:" + v);
			this.val = v.id;
			//System.out.println("\nboard 1 1 pred :\n"+MazeBoard.board[1][1].getCard().name);
	        this.undo_board = mb.getboard();
	        this.undo_freesutr = MazeBoard.freesutr;
	        this.undo_players_arr = MazeBoard.players_arr;
	        this.undo_poklady_na_plose = MazeBoard.poklady_na_plose;
	        this.undo_player_on_turn = player_on_t;
	       // System.out.println("\nboard 1 1 po:\n"+undo_board[1][1].getCard().name);
	        this.undo_GameBoardSquares = GameBoardSquares;
	        this.undo_shift = shft;
			if (undocounter == 20){
				undocounter = 0;
			}
			else {
				undocounter++;
			}
			
		}
		@Override
		public void undo() {
			//znovu nacteni objektu a znovu vykresleni
			//System.out.println(val + " undone");
			shift = undo_shift;
			mb = new MazeBoard();
			mb.createMazeBoard(fieldsize);
			mb.id = val;
			mb.setboard(undo_board);
			//MazeBoard.board = undo_board;
			MazeBoard.freesutr = undo_freesutr;
			MazeBoard.players_arr = undo_players_arr;
			MazeBoard.poklady_na_plose = undo_poklady_na_plose;
			//System.out.println("mb id po undo: "+mb.id);
			player_on_turn = undo_player_on_turn;
			
			try{
				FileInputStream fileIn = new FileInputStream(file);
				in = new ObjectInputStream(fileIn);
				mb.readObject(in);
			}
			catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
			
			//refresh_board(-1,-1);
			//GameBoard.removeAll();
			//fill_GameBoard(undo_GameBoardSquares);
	        //repaint();
	        //printAll(getGraphics());
		    pane = getContentPane();
		    pane.setBackground(new Color(66,66,66)); 
		    pane.removeAll();
		    clear_board(); //clear game components
            try{
            	GameBoardSquares = new JLabel[fieldsize][fieldsize];
            	CreateNewGame(mb,fieldsize,gamernum,cardnum,true);
            	//fill_GameBoard(GameBoardsqu);
            	pane.add(GameBoard);
            	print_freesutr(false);	
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            setContainerAndChildrenEnabled(pane, true);
            repaint();
           // printAll(getGraphics());
			
		}

		@Override
		public void redo() {
			//System.out.println(val + " redone");
			
		}
		
		
	}
}
