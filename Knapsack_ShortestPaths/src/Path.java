import java.util.Vector;

import javax.swing.JOptionPane;

public class Path{
   int id;
   Vector<Vertex> pathVertices=new Vector<Vertex>();
   Vector<Edge> pathEdges=new Vector<Edge>();
   Vector<Item> chosenItems=new Vector<Item>();
   static Vector<Item> allVerticesItems=new Vector<Item>();
   int totalEdgeWeight=0;
   int knapsackWeightLimit=0;
   int knapsackTotalValue=0;
   int knapsackTotalVerticesWeight=0;
   String chosenStr="Chosen items: \n";
   public Path(){
	   
   }

   public Path(int _id, Vector<Vertex> _pathVertices, Vector<Edge> _pathEdges, int _knapsackWeightLimit, boolean isVectorVertices){
	   id=_id;
	   knapsackWeightLimit=_knapsackWeightLimit;
	   for (int i=0;i<_pathVertices.size();i++)
		  pathVertices.add(_pathVertices.elementAt(i));
	   for (int i=0;i<_pathEdges.size();i++){
		  pathEdges.add(_pathEdges.elementAt(i));
		  totalEdgeWeight+=pathEdges.elementAt(i).getWeight();
	  }
	   Vector<Item> VertexItems=new Vector<Item>();
	   if (!isVectorVertices){
	   for (int i=0;i<pathVertices.size();i++){
	    	Item VertexItem=new Item(pathVertices.get(i).getName(),pathVertices.get(i).getValue(),pathVertices.get(i).getWeight());
	    	VertexItems.add(VertexItem);
	   }
	   }
	   else{	   
		   for (int i=0;i<pathVertices.size();i++){	
			   for (int j=0;j<pathVertices.elementAt(i).vecItems.size();j++){
		    	Item VertexItem=new Item(pathVertices.elementAt(i).vecItems.elementAt(j).symbol, pathVertices.elementAt(i).vecItems.elementAt(j).value, pathVertices.elementAt(i).vecItems.elementAt(j).weight);
		    	VertexItems.add(VertexItem);
		    	
			   }
			   }
	//   for (int i=0;i<VertexItems.size();i++)
	//	         System.out.println(VertexItems.elementAt(i));
		

	   }
	
	    Knapsack knpsk = new Knapsack(VertexItems, knapsackWeightLimit);
   	    chosenItems=knpsk.chooseItems();
   	    for (int i=0;i<chosenItems.size();i++){
   		    chosenStr+=chosenItems.elementAt(i)+"\n";
   		    knapsackTotalValue+=chosenItems.elementAt(i).value;
   		    knapsackTotalVerticesWeight+=chosenItems.elementAt(i).weight;
  }
   	    chosenStr+="Total Value:"+knapsackTotalValue+"\n"+"Total Edge weight:"+totalEdgeWeight+"\n"+"Total Vertices weight:"+knapsackTotalVerticesWeight;
   	//	System.out.println(chosenStr);
	  
   }
   public String toString(){
	   String res="Path "+id+": ";
	   res+="Knapsack weight limit: "+knapsackWeightLimit+"\n";
	   for (int i=0;i<pathVertices.size();i++){
		   res+=pathVertices.elementAt(i);
		   if (i<pathVertices.size()-1)
			   res+=" - ";
	   }
	   res+="\n"+chosenStr;
	   return res;
   }

   public static void main(String[] args){
	   Item a=new Item("a",13,4);
	   Item b=new Item("b",3,41);
	   Item c=new Item("c",3,14);
	   Item d=new Item("d",23,4);
	   Item e=new Item("e",3,54);
	   Item f=new Item("f",63,14);
	   Item g=new Item("g",23,74);
	   Item h=new Item("h",73,4);
	   Vertex va=new Vertex("va","va", 9,8);
	   Vertex vb=new Vertex("vb","vb", 9,8);
	   Vertex vc=new Vertex("vc","vc", 9,8);
	   va.vecItems.add(a);
	   va.vecItems.add(b);
	   va.vecItems.add(c);
	   vb.vecItems.add(d);
	   vb.vecItems.add(e);
	   vb.vecItems.add(f);
	   vc.vecItems.add(g);
	   vc.vecItems.add(h);
	
       for (int i=0;i<allVerticesItems.size();i++)
    	   System.out.println(allVerticesItems.elementAt(i));
   }
}