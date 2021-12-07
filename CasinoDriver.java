import javax.swing.*;
import java.awt.*;
import java.io.*;


public class CasinoDriver
{

   public static JFrame frame;
   public static HomePanel home;
   public static BlackjackPanel bj;
   
   
   public static void main(String [] args) throws IOException
   {
      bj = new BlackjackPanel();
      home = new HomePanel();
   
      frame = new JFrame("Casino");
      frame.setSize(950, 684);
      frame.setLocation(500, 200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(home);
      frame.setVisible(true);
   
   }
   
   public static void switchHome()
   {
      frame.setContentPane(new HomePanel());
      frame.setVisible(true);
   
   }
   public static void switchBlackjack() throws IOException
   {
      frame.setContentPane(new BlackjackPanel());
      frame.setVisible(true);
   
   }
}