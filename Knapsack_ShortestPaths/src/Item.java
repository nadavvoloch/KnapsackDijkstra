import java.util.Vector;

public class Item {
   String symbol;
   int value;
   int weight;
   boolean visited;
   int distanceFromSource;
   Item previous;
   Vector<Item> neighbours=new Vector<Item>();
   public Item(String symb, int val, int wgt){
	   symbol=symb;
	   value=val;
	   weight=wgt;
	   visited=false;
	   distanceFromSource=Integer.MAX_VALUE;
   }
   public Item(Item other){
	   symbol=other.symbol;
	   value=other.value;
	   weight=other.weight;
   }
   public double getEfficiency(){
	   return (double)(value)/weight;
   }
   public String toString(){
	   return "Name="+symbol+", Value="+value+", Weight="+weight+", Efficiency="+getEfficiency();
   }
}
