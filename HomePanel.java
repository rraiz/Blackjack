import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class HomePanel extends JPanel implements ActionListener
{
   private JPanel westPanel, eastPanel, northPanel; //panels
   private JButton bjButton; //buttons
   private JLabel title, heading, pic1, bj1, bj2; //labels


   public HomePanel()
   {
    
      
      setLayout(new BorderLayout()); //made border layout
      westPanel = new JPanel(); //west main panel
      eastPanel = new JPanel(); //east main panel
      northPanel = new JPanel(); //north main panel
      
      //setting sizes
      westPanel.setPreferredSize(new Dimension(475, 292)); 
      eastPanel.setPreferredSize(new Dimension(475, 292));
      northPanel.setPreferredSize(new Dimension(950, 100));
   
      //setting background colors
      westPanel.setBackground(Color.black);  
      eastPanel.setBackground(Color.getHSBColor(155,155,80));
      northPanel.setBackground(Color.white);
   
      //adds  panels in a border layout
      add(northPanel, BorderLayout.NORTH);  
      add(westPanel, BorderLayout.WEST);
      add(eastPanel, BorderLayout.EAST); 
      
      //box layout for all panels
      northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS)); 
      eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
      westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
   
      
      //initializing buttons/labels/pictures
      
      bjButton = new JButton("Blackjack");
      bjButton.setFont(new Font("Serif", Font.BOLD, 30));
      bjButton.addActionListener(this);       
   
      title = new JLabel("Welcome to Rev's Table!");
      title.setFont(new Font("Serif", Font.BOLD, 50));
      heading = new JLabel("Test your skill in Blackjack!");
      heading.setFont(new Font("Serif", Font.BOLD, 18));
      
      pic1 = new JLabel(new ImageIcon("blackjack-switch.png"));
      bj1 = new JLabel(new ImageIcon("bjPic1.png"));  
      bj2 = new JLabel(new ImageIcon("bjPic2.png"));    
   
      
      //making the box layout center
      heading.setAlignmentX(CENTER_ALIGNMENT); 
      title.setAlignmentX(CENTER_ALIGNMENT);
      bjButton.setAlignmentX(CENTER_ALIGNMENT);
      pic1.setAlignmentX(CENTER_ALIGNMENT);
      bj1.setAlignmentX(CENTER_ALIGNMENT);
      bj2.setAlignmentX(CENTER_ALIGNMENT);
   
      
       //buffer room for box layout
      eastPanel.add(Box.createRigidArea(new Dimension(0, 50))); 
      westPanel.add(Box.createRigidArea(new Dimension(0, 25)));
      
      
      //adding buttons/labels to the panels
      //west
      westPanel.add(bj1);
      westPanel.add(Box.createRigidArea(new Dimension(0, 30))); //buffer
   
      westPanel.add(bj2);
      
      //north
      northPanel.add(title);   
      northPanel.add(heading);
     
     //east
      eastPanel.add(bjButton); 
      eastPanel.add(Box.createRigidArea(new Dimension(0, 50))); //buffer
      eastPanel.add(pic1);
   
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
      if(e.getSource() == bjButton)
      {         
         goBlackjack();
         
         return;
      }
   
   
   }

}