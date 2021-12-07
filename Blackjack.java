import java.io.*;
import java.util.*;

public class Blackjack
{

   private List<Card> cards;


   public Blackjack() throws IOException
   {
      cards = new ArrayList();
      buildDeck();
   }


   private void buildDeck() throws IOException
   {
      Scanner input = new Scanner(new FileReader("cards.txt"));
      while(input.hasNextLine())
      {
         String[] parts = input.nextLine().split(" ");
         cards.add(new Card(parts[0], parts[1], Integer.parseInt(parts[2])));
      }
   }
   
   public  List<Card> getDeck() //returns full deck
   {
      return cards;
   }
   
   public Card getCard(int num) //returns card at index
   {
      return cards.get(num);
   }
   
   public Card removeCard(int num) //removes card at index
   {
      return cards.remove(num);
   }
   
   public int deckSize() //return size of deck
   {
      return cards.size();
   }
   
   public String getPicture(int num) //gets card picture at index
   {
      return cards.get(num).getImage();
      
   }
   
   public int getValue(int num) //gets card value at index
   {
      return cards.get(num).getVal();
   }
   
   public String getTitle(int num) //gets card name at index
   {
      return cards.get(num).getName();
   }
   
   

   public static void main(String[] args) throws IOException
   {
      Blackjack deck = new Blackjack();
      System.out.println(deck.getPicture(0));
   
   }


}