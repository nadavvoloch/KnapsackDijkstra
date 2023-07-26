// link to the system: http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JOptionPane;
public class Main{
	 private static List<Vertex> vertices;
	 private static List<Edge> edges;

 public static void main(String[] args){
	    JOptionPane.showMessageDialog(null, "Welcome to th Knapsack-shortest path program, press OK to start.");
	    
	    testExcute();
	    
	
 }

  public static void testExcute() {
    vertices = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();
    
    String vectorChoiceStr=JOptionPane.showInputDialog(null, "For Vector-Vertices algorithm - Enter 1, For regular vertex - Enter 2:");
    int vectorChoice=Integer.parseInt(vectorChoiceStr); 
//////////////////////////////////////////////// vector vertices ////////////////////////////////////////////////////////////

    if(vectorChoice==1){
    	String verticesSizeStr=JOptionPane.showInputDialog(null, "How many vertices?");
        int verticesSize=Integer.parseInt(verticesSizeStr);
        String  verticesStr="Vertices:\n";
        
        for (int i = 0; i < verticesSize; i++) {
            Vertex location = new Vertex("Vertex " + i, "Vertex " + (char)('a'+i),0, 0);
            location.vecItems=new Vector<Item>(); 
            verticesStr+="Vertex " + (char)('a'+i)+":\n";
        	String itemVecSizeStr=JOptionPane.showInputDialog(null, "How many Items in vertex "+(char)('a'+i)+"?");
            int itemVecSize=Integer.parseInt(itemVecSizeStr);
            for (int j = 0; j < itemVecSize; j++){
                String wgtStr=JOptionPane.showInputDialog(null,"Weight of Vertex "+(char)('a'+i)+" Item "+(j+1)+":");
                int wgt=Integer.parseInt(wgtStr);
                String valStr=JOptionPane.showInputDialog(null,"Value of Vertex "+(char)('a'+i)+" Item "+(j+1)+":");
                int val=Integer.parseInt(valStr);
                Item it=new Item("Vertex "+(char)('a'+i)+" Item "+(j+1)+"",val,wgt);
                verticesStr+=it+"  |||  ";
                location.vecItems.add(it);
            }
            vertices.add(location);
            verticesStr+="\n";
        }
        System.out.println(verticesStr);
        String edgesSizeStr=JOptionPane.showInputDialog(null, "How many edges?");
        int edgesSize=Integer.parseInt(edgesSizeStr);
        String  edgesStr="Edges:\n";
        for (int i = 0; i < edgesSize; i++) {
            String src=JOptionPane.showInputDialog(null,"Edge "+(i+1)+ " Source Vertex:");
            String trgt=JOptionPane.showInputDialog(null, "Edge "+(i+1)+" Target Vertex:");
            String edgWgtStr=JOptionPane.showInputDialog(null, "Edge "+(i+1)+" weight:");
            int edgWgt=Integer.parseInt(edgWgtStr);
            edgesStr+= "Edge "+src+"-"+trgt+", Edge weight:"+ edgWgt+"\n";
            addLane("Edge "+src+"-"+trgt, (int)(src.charAt(0)-'a'),  (int)(trgt.charAt(0)-'a'), edgWgt);
            }
        System.out.println(edgesStr);
        Graph graph = new Graph(vertices, edges); 
        String srcVertex=JOptionPane.showInputDialog(null, "Source Vertex:");
        String trgtVertex=JOptionPane.showInputDialog(null, "Target Vertex:");
    	String edgesFileStr="";
  	  for (int i = 0; i < edgesSize; i++) {
  	    	edgesFileStr+=graph.getEdges().get(i).toString().substring(7, 8)+" "+graph.getEdges().get(i).toString().substring(16, 17)+"\n";
  	   }
  	  String allPathsStr="\n"+AllPaths.execute(edgesFileStr,srcVertex, trgtVertex);
  	 // allPathsStr+='$';
  	  Vector<String> pathStrings=new Vector<String>();
  	
  	  for (int i=1;i<allPathsStr.length();i++){
  		  if(allPathsStr.charAt(i)=='\n'){
  		      pathStrings.add(allPathsStr.substring(0,i));
  			  allPathsStr=allPathsStr.substring(i);
  			  i=i-pathStrings.lastElement().length();
  		  }
  	  }
  	  String maxKnapsackWeightStr=JOptionPane.showInputDialog(null,"Enter max weight for knapsack:");
  	   int maxKnapsackWeight=Integer.parseInt(maxKnapsackWeightStr);
  	 Vector<Path> allPathsVec=new Vector<Path>();
  	 Path pt=new Path();
  	 System.out.println("Number of paths: "+pathStrings.size()+", max weight for knapsack: "+maxKnapsackWeight);
  	  for(int i=0;i<pathStrings.size();i++){
  	//	  System.out.println(pathStrings.elementAt(i).substring(1,pathStrings.elementAt(i).length() ));
  		  pt=new Path();
  		  pt=createPathForVec(pathStrings.elementAt(i).substring(1,pathStrings.elementAt(i).length() ),maxKnapsackWeight,(i+1));
  		  allPathsVec.add(pt);
  		  System.out.println(allPathsVec.elementAt(i));
  
  	  }

  	                    ////////////// priority choice /////////////////
  	  
//  	  String priorityChoiceStr=JOptionPane.showInputDialog(null,"What element gets the priority? For minimal edge weight- Enter 1, For maximal knapsack vertices value - Enter 2,  For eqaul priority of both elements - Enter 3:");
//  	  int priorityChoice=Integer.parseInt(priorityChoiceStr);
  	  System.out.println();
  	  
  		 System.out.println("A. Minimal edge weight priority:");
  	     System.out.println("This is simply Dijksta's algorithm....");
  	     int minEdgeWeight=Integer.MAX_VALUE;
  	     int minEdgeWeightIndex=Integer.MAX_VALUE;
  	     for (int i=0;i<allPathsVec.size();i++){
  	    	 if(allPathsVec.elementAt(i).totalEdgeWeight<minEdgeWeight ||(allPathsVec.elementAt(i).totalEdgeWeight==minEdgeWeight &&allPathsVec.elementAt(i).knapsackTotalValue>allPathsVec.elementAt(minEdgeWeightIndex).knapsackTotalValue )){
  	    		 minEdgeWeight=allPathsVec.elementAt(i).totalEdgeWeight;
  	    		 minEdgeWeightIndex=i;
  	    	 }
  	     }
  	     System.out.println("Chosen Path:");
  	     System.out.println(allPathsVec.elementAt(minEdgeWeightIndex));
  	     
	 
        
      	 System.out.println("B. Maximal knapsack vertices value priority:");
      	 int maxVerticesValue=Integer.MIN_VALUE;
   	     int maxVerticesValueIndex=Integer.MIN_VALUE;
   	     for (int i=0;i<allPathsVec.size();i++){
   	    	 if(allPathsVec.elementAt(i).knapsackTotalValue> maxVerticesValue || (allPathsVec.elementAt(i).knapsackTotalValue== maxVerticesValue&&allPathsVec.elementAt(i).totalEdgeWeight<allPathsVec.elementAt(maxVerticesValueIndex).totalEdgeWeight)){
   	    		maxVerticesValue=allPathsVec.elementAt(i).knapsackTotalValue;
   	    		maxVerticesValueIndex=i;
   	    	 }
   	     }
   	     System.out.println("Chosen Path:");
   	     System.out.println(allPathsVec.elementAt(maxVerticesValueIndex)); 
  	  
      
      	 System.out.println("C. Eqaul priority of both elements:");
           int maxCombinedTotal=Integer.MIN_VALUE;
       	 int maxCombinedTotalIndex=Integer.MIN_VALUE;
       	 for (int i=0;i<allPathsVec.size();i++){
       	      if((allPathsVec.elementAt(i).knapsackTotalValue-allPathsVec.elementAt(i).totalEdgeWeight)> maxCombinedTotal ||((allPathsVec.elementAt(i).knapsackTotalValue-allPathsVec.elementAt(i).totalEdgeWeight)== maxCombinedTotal)&&allPathsVec.elementAt(i).knapsackTotalValue>allPathsVec.elementAt(maxCombinedTotalIndex).knapsackTotalValue){
       	    	 maxCombinedTotal=allPathsVec.elementAt(i).knapsackTotalValue-allPathsVec.elementAt(i).totalEdgeWeight;
       	    	 maxCombinedTotalIndex=i;
       	    	 }
       	     }
       	  System.out.println("Chosen Path:");
       	  System.out.println(allPathsVec.elementAt(maxCombinedTotalIndex)); 	  
  	 
  	  
  
    }
////////////////////////////////////////////////User input for regular vertices ////////////////////////////////////////////////////////////
    if(vectorChoice==2){
	String verticesSizeStr=JOptionPane.showInputDialog(null, "How many vertices?");
    int verticesSize=Integer.parseInt(verticesSizeStr);
    String  verticesStr="Vertices:\n";
    for (int i = 0; i < verticesSize; i++) {
      String wgtStr=JOptionPane.showInputDialog(null,"Weight of Vertex "+(char)('a'+i)+":");
      int wgt=Integer.parseInt(wgtStr);
      String valStr=JOptionPane.showInputDialog(null,"Value of Vertex "+(char)('a'+i)+":");
      int val=Integer.parseInt(valStr);
      verticesStr+= "Vertex "+ (char)('a'+i)+"- Weight:"+wgt+", Value:"+val+"\n";
      Vertex location = new Vertex("Vertex " + i, "Vertex " + (char)('a'+i),wgt, val);
      vertices.add(location);
    }
    System.out.println(verticesStr);
    String edgesSizeStr=JOptionPane.showInputDialog(null, "How many edges?");
    int edgesSize=Integer.parseInt(edgesSizeStr);
    String  edgesStr="Edges:\n";
    for (int i = 0; i < edgesSize; i++) {
        String src=JOptionPane.showInputDialog(null,"Edge "+(i+1)+ " Source Vertex:");
        String trgt=JOptionPane.showInputDialog(null, "Edge "+(i+1)+" Target Vertex:");
        String edgWgtStr=JOptionPane.showInputDialog(null, "Edge "+(i+1)+" weight:");
        int edgWgt=Integer.parseInt(edgWgtStr);
        edgesStr+= "Edge "+src+"-"+trgt+", Edge weight:"+ edgWgt+"\n";
        addLane("Edge "+src+"-"+trgt, (int)(src.charAt(0)-'a'),  (int)(trgt.charAt(0)-'a'), edgWgt);
        }
    System.out.println(edgesStr);
    Graph graph = new Graph(vertices, edges); 
  //  System.out.println("Vertex name:"+vertices.get(0).getName());
  //  System.out.println("Edge id:"+edges.get(0).getId());

    String srcVertex=JOptionPane.showInputDialog(null, "Source Vertex:");
    String trgtVertex=JOptionPane.showInputDialog(null, "Target Vertex:");
    String pathsChoiceStr=JOptionPane.showInputDialog(null, "For two-step algorithm - Enter 1, For combined all paths algorithm - Enter 2:");
    int pathsChoice=Integer.parseInt(pathsChoiceStr);
    
//////////////////////////////  Old two-step Algorithms //////////////////////////////////////////////////////////////////    
    
    if (pathsChoice==1){
    	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
       dijkstra.execute(vertices.get((int)(srcVertex.charAt(0)-'a')));
       LinkedList<Vertex> path = dijkstra.getPath(vertices.get((int)(trgtVertex.charAt(0)-'a')));
       String pathStr="Path: ";
       for (Vertex vertex : path) {
           pathStr+=vertex+" - ";
        }
       pathStr=pathStr.substring(0, pathStr.length()-3);
       System.out.println(pathStr+ "\n Total weight of Edges:"+dijkstra.getShortestDistance(vertices.get((int)(trgtVertex.charAt(0)-'a'))));
       //Knapsack
    Vector<Item> VertexItems=new Vector<Item>();
    for (int i=0;i<path.size();i++){
    	Item VertexItem=new Item(path.get(i).getName(),path.get(i).getValue(),path.get(i).getWeight());
    	VertexItems.add(VertexItem);
    }
    JOptionPane.showMessageDialog(null,"Now Knapsack!");
    String maxWeightStr=JOptionPane.showInputDialog(null,"Enter max weight for knapsack:");
    int maxWeight=Integer.parseInt(maxWeightStr);
    int totalValue=0;
    String chosenStr="Chosen items: \n";
    String choiceStr=JOptionPane.showInputDialog(null,"For 0/1 knapsack algorithm - Enter 1, For Dantzig's algorithm - Enter 2:");
    int choice=Integer.parseInt(choiceStr);
    //Dantzig
    if(choice==2){
	ItemGraph dnz= new ItemGraph (VertexItems, maxWeight);
	dnz.sortItemsByEfficiency();
	String sortedStr="Sorted items: \n";
	for (int i=0;i<dnz.items.size();i++)
		sortedStr+=dnz.items.elementAt(i)+"\n";
	System.out.println(sortedStr);
    dnz.chooseItems();
	for (int i=0;i<dnz.chosenItems.size();i++){
		chosenStr+=dnz.chosenItems.elementAt(i)+"\n";
		totalValue+=dnz.chosenItems.elementAt(i).value;
	}
    }
    //0/1
    if(choice==1){
    	Knapsack knpsk = new Knapsack(VertexItems, maxWeight);
    	Vector<Item> chosenItems=new Vector<Item>();
    	chosenItems=knpsk.chooseItems();
    	for (int i=0;i<chosenItems.size();i++){
    		chosenStr+=chosenItems.elementAt(i)+"\n";
    		totalValue+=chosenItems.elementAt(i).value;
    	}
    }
    chosenStr+="Total Value:"+totalValue+"\n";
	System.out.println(chosenStr);
    }
    
//////////////////////////////New all paths Algorithms //////////////////////////////////////////////////////////////////    
    
    if(pathsChoice==2){
    	String edgesFileStr="";
    	  for (int i = 0; i < edgesSize; i++) {
    	    	edgesFileStr+=graph.getEdges().get(i).toString().substring(7, 8)+" "+graph.getEdges().get(i).toString().substring(16, 17)+"\n";
    	   }
    	  String allPathsStr="\n"+AllPaths.execute(edgesFileStr,srcVertex, trgtVertex);
    	 // allPathsStr+='$';
    	  Vector<String> pathStrings=new Vector<String>();
    	
    	  for (int i=1;i<allPathsStr.length();i++){
    		  if(allPathsStr.charAt(i)=='\n'){
    		      pathStrings.add(allPathsStr.substring(0,i));
    			  allPathsStr=allPathsStr.substring(i);
    			  i=i-pathStrings.lastElement().length();
    		  }
    	  }
    	  String maxKnapsackWeightStr=JOptionPane.showInputDialog(null,"Enter max weight for knapsack:");
    	   int maxKnapsackWeight=Integer.parseInt(maxKnapsackWeightStr);
    	 Vector<Path> allPathsVec=new Vector<Path>();
    	 Path pt=new Path();
    	 System.out.println("Number of paths: "+pathStrings.size()+", max weight for knapsack: "+maxKnapsackWeight);
    	  for(int i=0;i<pathStrings.size();i++){
    	//	  System.out.println(pathStrings.elementAt(i).substring(1,pathStrings.elementAt(i).length() ));
    		  pt=new Path();
    		  pt=createPath(pathStrings.elementAt(i).substring(1,pathStrings.elementAt(i).length() ),maxKnapsackWeight,(i+1));
    		  allPathsVec.add(pt);
    		  System.out.println(allPathsVec.elementAt(i));
    
    	  }
    	                    ////////////// priority choice /////////////////
    	  
  //  	  String priorityChoiceStr=JOptionPane.showInputDialog(null,"What element gets the priority? For minimal edge weight- Enter 1, For maximal knapsack vertices value - Enter 2,  For eqaul priority of both elements - Enter 3:");
  //  	  int priorityChoice=Integer.parseInt(priorityChoiceStr);
    	  System.out.println();
    
    		 System.out.println("A. Minimal edge weight priority:");
    	     System.out.println("This is simply Dijksta's algorithm....");
    	     int minEdgeWeight=Integer.MAX_VALUE;
    	     int minEdgeWeightIndex=Integer.MAX_VALUE;
    	     for (int i=0;i<allPathsVec.size();i++){
    	    	 if(allPathsVec.elementAt(i).totalEdgeWeight<minEdgeWeight ||(allPathsVec.elementAt(i).totalEdgeWeight==minEdgeWeight &&allPathsVec.elementAt(i).knapsackTotalValue>allPathsVec.elementAt(minEdgeWeightIndex).knapsackTotalValue )){
    	    		 minEdgeWeight=allPathsVec.elementAt(i).totalEdgeWeight;
    	    		 minEdgeWeightIndex=i;
    	    	 }
    	     }
    	     System.out.println("Chosen Path:");
    	     System.out.println(allPathsVec.elementAt(minEdgeWeightIndex));
    	     
    	 
          
        	 System.out.println("B. Maximal knapsack vertices value priority:");
        	 int maxVerticesValue=Integer.MIN_VALUE;
     	     int maxVerticesValueIndex=Integer.MIN_VALUE;
     	     for (int i=0;i<allPathsVec.size();i++){
     	    	 if(allPathsVec.elementAt(i).knapsackTotalValue> maxVerticesValue || (allPathsVec.elementAt(i).knapsackTotalValue== maxVerticesValue&&allPathsVec.elementAt(i).totalEdgeWeight<allPathsVec.elementAt(maxVerticesValueIndex).totalEdgeWeight)){
     	    		maxVerticesValue=allPathsVec.elementAt(i).knapsackTotalValue;
     	    		maxVerticesValueIndex=i;
     	    	 }
     	     }
     	     System.out.println("Chosen Path:");
     	     System.out.println(allPathsVec.elementAt(maxVerticesValueIndex)); 
    	  
        
        	 System.out.println("C. Eqaul priority of both elements:");
             int maxCombinedTotal=Integer.MIN_VALUE;
         	 int maxCombinedTotalIndex=Integer.MIN_VALUE;
         	 for (int i=0;i<allPathsVec.size();i++){
         	      if((allPathsVec.elementAt(i).knapsackTotalValue-allPathsVec.elementAt(i).totalEdgeWeight)> maxCombinedTotal ||((allPathsVec.elementAt(i).knapsackTotalValue-allPathsVec.elementAt(i).totalEdgeWeight)== maxCombinedTotal)&&allPathsVec.elementAt(i).knapsackTotalValue>allPathsVec.elementAt(maxCombinedTotalIndex).knapsackTotalValue){
         	    	 maxCombinedTotal=allPathsVec.elementAt(i).knapsackTotalValue-allPathsVec.elementAt(i).totalEdgeWeight;
         	    	 maxCombinedTotalIndex=i;
         	    	 }
         	     }
         	  System.out.println("Chosen Path:");
         	  System.out.println(allPathsVec.elementAt(maxCombinedTotalIndex)); 	  
    	 
    	  
    }
      }//vector or regular-choice 2
    }
  
 //////////////////////////////////////////////////// Assisting methods //////////////////////////////////////////////////////////
  
  public static Path createPath(String pathStr, int knapsackWGTLimit, int pid){
	  Vector<Vertex> pathVertices=new Vector<Vertex>();
	  Vector<Edge> pathEdges=new Vector<Edge>();
	  for (int i=1;i<pathStr.length()-2;i=i+3){
//	    System.out.println(findVertexByName(vertices,""+pathStr.charAt(i)));
	      Vertex v=findVertexByName(vertices,""+pathStr.charAt(i));
	      pathVertices.add(v);
	  }
	  for (int i=1;i<pathStr.length()-3;i=i+3){
//		  System.out.println(findEdgeByTargetAndSourceName(edges,""+pathStr.charAt(i), ""+pathStr.charAt(i+3)));
		  Edge e=findEdgeByTargetAndSourceName(edges,""+pathStr.charAt(i), ""+pathStr.charAt(i+3));
		  pathEdges.add(e);
	  }
	   Path p=new Path(pid, pathVertices, pathEdges, knapsackWGTLimit,false);
	   return p;

  }

  public static Path createPathForVec(String pathStr, int knapsackWGTLimit, int pid){
	  Vector<Vertex> pathVertices=new Vector<Vertex>();
	  Vector<Edge> pathEdges=new Vector<Edge>();
	  for (int i=1;i<pathStr.length()-2;i=i+3){
//	    System.out.println(findVertexByName(vertices,""+pathStr.charAt(i)));
	      Vertex v=findVertexByName(vertices,""+pathStr.charAt(i));
	      pathVertices.add(v);
	  }
	  for (int i=1;i<pathStr.length()-3;i=i+3){
//		  System.out.println(findEdgeByTargetAndSourceName(edges,""+pathStr.charAt(i), ""+pathStr.charAt(i+3)));
		  Edge e=findEdgeByTargetAndSourceName(edges,""+pathStr.charAt(i), ""+pathStr.charAt(i+3));
		  pathEdges.add(e);
	  }
	   Path p=new Path(pid, pathVertices, pathEdges, knapsackWGTLimit, true);
	   return p;

  }

  public static Vertex findVertexByName(List<Vertex> verticesList, String name){
	  Vertex result = null;
	  for(int i=0;i<verticesList.size();i++){
		  if(verticesList.get(i).getName().equals("Vertex "+name)){
			  result=new Vertex(verticesList.get(i));
		  }
	  }
	  return result;
	  
  }
  public static Edge findEdgeByTargetAndSourceName(List<Edge> edgesList, String src, String trgt){
	  Edge result = null;
	  for(int i=0;i<edgesList.size();i++){
		  if(edgesList.get(i).getId().equals("Edge "+src+"-"+trgt)){
			  result=new Edge(edgesList.get(i));
		  }
	  }
	  return result;
	  
  }

  private static void addLane(String laneId, int sourceLocNo, int destLocNo,
      int duration) {
	
	  Edge lane = new Edge(laneId,vertices.get(sourceLocNo), vertices.get(destLocNo), duration);
    edges.add(lane);
    
  }
} 