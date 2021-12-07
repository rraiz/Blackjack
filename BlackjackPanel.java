import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class BlackjackPanel extends JPanel implements ActionListener
{
   private JPanel westPanel, eastPanel; //panels
   private JPanel grid0, grid1, grid2, grid3, grid4; //grid layout placements
   private List<JLabel> playerPics;
   private List<JLabel> dealerPics;
   private JLabel bjDealer;
   private JLabel pText, dText, msg; //labels
   private JButton hit, stand, play, home; //buttons
   private int pCount, dCount; //ints
   private Blackjack bj; //blackjack object instance
   private Stack<Card> pCards; //list of player cards
   private Stack<Card> dCards; //list of dealer cards
   private boolean finishedGame;


   public BlackjackPanel() 
   {
      //instance of deck and player/dealer hands
      bj = new Blackjack(); 
      pCards = new Stack(); 
      dCards = new Stack(); 
      //card pictures container
      playerPics = new ArrayList();
      dealerPics = new ArrayList();
      
      //counter variables
      pCount = 0;
      dCount = 0;
      finishedGame = false;
   
      //initialize panel
      setLayout(new BorderLayout()); 
      westPanel = new JPanel(); //west main panel
      eastPanel = new JPanel(); //east main panel 
      
      //initialize grids for gridlayout
      
      grid0 = new JPanel(); 
      grid1 = new JPanel();
      grid2 = new JPanel();
      grid3 = new JPanel();
      grid4 = new JPanel();
      
   
      //setting size
      westPanel.setPreferredSize(new Dimension(365, 684)); //380 orig
      eastPanel.setPreferredSize(new Dimension(570, 684));
      
      //setting background color
      westPanel.setBackground(Color.gray);  
      eastPanel.setBackground(Color.getHSBColor(155,155,80));
   
   
      //adding panels
      add(westPanel, BorderLayout.WEST);
      add(eastPanel, BorderLayout.EAST);
      
      eastPanel.setLayout(new GridLayout(5,0)); //setting eastPanel as a grid
      westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
      grid1.setLayout(new BoxLayout(grid1, BoxLayout.X_AXIS)); //Boxlayout for specific panels in grid
      grid3.setLayout(new BoxLayout(grid3, BoxLayout.X_AXIS));
      grid4.setLayout(new BoxLayout(grid4, BoxLayout.X_AXIS));
      
      //adding the panels for boxlayout
      eastPanel.add(grid0);
      eastPanel.add(grid1);
      eastPanel.add(grid2);
      eastPanel.add(grid3);
      eastPanel.add(grid4);
   
   
      //initializing button
      hit = new JButton("Hit");
      stand = new JButton("Stand");
      play = new JButton("Play");
      home = new JButton("Home");
      pText = new JLabel("Your total: " + pCount);    //player text     
      dText = new JLabel("Dealer total: " + dCount); //dealer text
      msg = new JLabel("Test your skill!");
      bjDealer = new JLabel(new ImageIcon("bjDealer.jpg"));
      //player/dealer cards
      for(int i = 0; i<5; i++)
      {
         playerPics.add(new JLabel(""));
         dealerPics.add(new JLabel(""));
      }
   
      //disabling buttons      
      hit.setEnabled(false);
      stand.setEnabled(false);
      //action listeners
      hit.addActionListener(this);
      stand.addActionListener(this);
      play.addActionListener(this);    
      home.addActionListener(this);   
       //setting fonts
      hit.setFont(new Font("Serif", Font.BOLD, 30));
      stand.setFont(new Font("Serif", Font.BOLD, 30));
      play.setFont(new Font("Serif", Font.BOLD, 30));
      home.setFont(new Font("Serif", Font.BOLD, 30));
      pText.setFont(new Font("Serif", Font.BOLD, 30)); 
      dText.setFont(new Font("Serif", Font.BOLD, 30));
      msg.setFont(new Font("Serif", Font.BOLD, 25));
      
       
     //adding labels  
     //west panel boxlayout   
      westPanel.add(Box.createRigidArea(new Dimension(0, 50))); //buffer
      westPanel.add(bjDealer);
      westPanel.add(Box.createRigidArea(new Dimension(0, 20)));
      westPanel.add(msg);
      westPanel.add(Box.createRigidArea(new Dimension(0, 100)));
      westPanel.add(play);
      westPanel.add(Box.createRigidArea(new Dimension(0, 35))); //buffer
      westPanel.add(home);
      bjDealer.setAlignmentX(CENTER_ALIGNMENT);
      play.setAlignmentX(CENTER_ALIGNMENT); 
      home.setAlignmentX(CENTER_ALIGNMENT); 
      msg.setAlignmentX(CENTER_ALIGNMENT); 
   
      //east panel grid
      grid0.add(pText);
      grid2.add(dText);
      grid4.add(Box.createRigidArea(new Dimension(150, 0))); //buffer
      grid4.add(hit);
      grid4.add(Box.createRigidArea(new Dimension(50, 0))); //buffer
      grid4.add(stand);
      grid4.add(Box.createRigidArea(new Dimension(50, 0))); //buffer
   
   
   //image section    //initial buffer of 25 with 10 inbetween
      grid1.add(Box.createRigidArea(new Dimension(25, 0)));
      for(int i = 0; i<5; i++)
      {
         grid1.add(playerPics.get(i));
         grid1.add(Box.createRigidArea(new Dimension(10, 0)));
      }
      
      grid3.add(Box.createRigidArea(new Dimension(25, 0)));
      for(int i = 0; i<5; i++)
      {
         grid3.add(dealerPics.get(i));
         grid3.add(Box.createRigidArea(new Dimension(10, 0)));
      }
      
     
   
   }
   
   public void goBlackjack() 
   {
      try
      {
         CasinoDriver.switchBlackjack();
      }
      catch(Exception e)
      {
         return;
      }
      
   }
   
   public void actionPerformed(ActionEvent e)  
   {
   
      if(e.getSource() == home) //if home is clicked
      {
         CasinoDriver.switchHome(); //goes home
      }
      
      if(e.getSource() == play) //if play is clicked
      {  
         if(finishedGame == true)
         {
            goBlackjack();
         }
         //enable buttons
         play.setEnabled(false);       
         hit.setEnabled(true);
         stand.setEnabled(true);
         
         //adds random card to players hand
         int rand = (int)(Math.random()*(bj.deckSize())); //rng of deck size
         pCards.push(bj.removeCard(rand)); //adds to stack
         playerPics.get(0).setIcon(new ImageIcon(pCards.peek().getImage()));//sets image
         pCount = pCount + pCards.peek().getVal();//adds val 
            
         rand = (int)(Math.random()*(bj.deckSize())); 
         pCards.push(bj.removeCard(rand)); 
         playerPics.get(1).setIcon(new ImageIcon(pCards.peek().getImage())); 
         pCount = pCount + pCards.peek().getVal(); 
      
         pText.setText("Your Total: " + pCount);  //total value
         
         //adds random card to dealer hand
         rand = (int)(Math.random()*(bj.deckSize()));
         dCards.push(bj.removeCard(rand));
         //setting dealers hand on board
         dealerPics.get(0).setIcon(new ImageIcon(dCards.peek().getImage()));
         dealerPics.get(1).setIcon(new ImageIcon("Blackjack Cards/cardback.png"));
      
         //changes dealer total value
         dCount = dCount + dCards.peek().getVal();
         dText.setText("Dealer total: " + dCount + " + ?");
         
         return;
      }
      
      if(e.getSource() == hit) //if player wants another card
      {
         //gets random card and puts it on hand
         int rand = (int)(Math.random()*(bj.deckSize())); 
         pCards.push(bj.removeCard(rand)); 
      
         for(int i = 0; i<5; i++)
         {
            if(playerPics.get(i).getIcon() == null) //if there is an open card slot
            {
               playerPics.get(i).setIcon(new ImageIcon(pCards.peek().getImage() )); //sets card image in place
               break;
            }
         }
      
         //changes and sets value of player
         pCount = pCount + pCards.peek().getVal(); 
         pText.setText("Your total: " + pCount);
         
      }
      
      if(e.getSource() == stand) //if player chooses to stand
      {
          
         for(int i = 1; i<5; i++)
         {
         
            int rand = (int)(Math.random()*(bj.deckSize())); //random card
            dCards.push(bj.removeCard(rand));  //dealer gets cards
            dealerPics.get(i).setIcon(new ImageIcon(dCards.peek().getImage() )); //setting icon
            dCount = dCount + dCards.peek().getVal(); //getting value of icon
            
            if(dCards.get(0).getVal() == 11 && dCount > 21) // if first card has ace and hand > 21
            {
               dCards.get(0).setVal(1); //sets value of ace to 1 
               dCount = dCount - 10; //sets count to appropriate value
            }
            
            
            if(dCards.get(i).getVal() == 11 && dCount > 21) //if current card has ace and hand > 21
            {
               dCards.get(i).setVal(1); //sets value of ace to 1 
               dCount = dCount - 10; //sets count to appropriate value
            }
            
            if(dCount > pCount && dCount < 22) //if dealer has higher value then player while < 22
            {
               msg.setText("Dealer Wins!");
               finishedGame = true;
               break;
            }
         
            if(dCount > 21 ) //if dealer folds( > 21)
            {
               msg.setText("You win, Dealer folds");
               finishedGame = true;
               break;
            }
         
            if(dCount == pCount && pCount > 16 ) //draws if dealer and player have same val over 16
            {
               msg.setText("Its a draw");
               finishedGame = true;
               break;
            }         
         }
          //sets text for dealer val
         dText.setText("Dealer total: " + dCount); 
      
      }
      
      //player wins if they have 
      if(pCards.size() == 5 && pCount < 22) 
      {
         msg.setText("You Won (5 Cards 21 or less)");
         finishedGame = true;
      }
     //if player val > 21          
      if(pCount > 21)
      {
      
         for(int i = 0; i<pCards.size(); i++) //checks if any card in hand is an ace
         {
            //sets value of ace to 1 if found
            if(pCards.get(i).getVal() == 11) 
            {
               pCards.get(i).setVal(1);
               pCount = pCount - 10;
            }
         }
         
         if(pCount < 22) //executes when theres an ace
         {
            pText.setText("Player total: " + pCount);
         }
         else //executes when no ace is found
         {
            msg.setText("You fold!");
            play.setText("Reset");
            finishedGame = true;
         }
          
      
      }
      //when player/dealer wins or ties
      if(finishedGame == true)
      {
         //disables and enables buttons
         hit.setEnabled(false);
         stand.setEnabled(false);
         play.setEnabled(true);      
      }
   
   
      
   }
   
   
   
   
   
   
   
   
}