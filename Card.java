public class Card
{
   private String suit;
   private String name;
   private int val;

   public Card(String s, String n, int v)
   {
      suit = s;
      name = n;
      val = v;
   } 


   public String getSuit()
   {
      return suit;
   }

   public String getName()
   {
      return name;
   }

   public int getVal()
   {
      return val;
   }
   
   public void setVal(int n)
   {
      val = n;
   }
   
   public String getImage()
   {
      return "Blackjack Cards/" + suit + "_" + name + ".png";
   }

   public String toString()
   {
   
      return suit + " " +  name + " " + val;
   
   }













}