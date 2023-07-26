import java.util.Vector;

public class ItemGraph {
    int maxWeight;
    Vector<Item> items;
  //  Vector<ItemEdge> itemEdges=new Vector<ItemEdge>();
    Vector<Item> chosenItems=new Vector<Item>();
    public ItemGraph(Vector<Item> _items, int _maxWeight){
    	maxWeight=_maxWeight;
    	items=new Vector<Item>(_items.size());
    	for (int i = 0; i < _items.size(); i++) 
    		items.addElement(_items.elementAt(i));	
    }
 /*   public void updateNeighbours(){
    	for (int i=0;i<items.size();i++){
    		for (int j=0;j<items.size();j++){
    			if(isEdge(items.elementAt(i),items.elementAt(j))){
    				items.elementAt(i).neighbours.addElement(items.elementAt(j));
    			}
    		}
    	}
    }
    */
    public void sortItemsByEfficiency() {

    	    int n = items.size();
    	    Item temp1= null,temp2 = null;
    	    for (int i = 0; i < n; i++) {
    	        for (int j = 1; j < (n-i) ; j++) {

    	            if (items.elementAt(j).getEfficiency() > items.elementAt(j - 1).getEfficiency()) {
    	                temp1 = new Item(items.elementAt(j - 1));
    	                temp2 = new Item(items.elementAt(j));
    	                items.setElementAt(temp2, j-1);
    	                items.setElementAt(temp1, j);
    	            }

    	        }
    	    }
    	}
    public void chooseItems(){
    	sortItemsByEfficiency();
    	int totalWeight=0,i=0;
    	while (i<items.size()){
    		totalWeight+=items.elementAt(i).weight;
    		if(totalWeight>maxWeight)
    			totalWeight-=items.elementAt(i).weight;
    		else
    			chosenItems.addElement(items.elementAt(i));
    		i++;
    		   
    	}
    }
 /*   public boolean isEdge(Item src, Item trg){
    	for (int i=0;i<itemEdges.size();i++){
    		if(itemEdges.elementAt(i).source.symbol.equals(src.symbol)&&itemEdges.elementAt(i).target.symbol.equals(trg.symbol))
    			return true;
    	}
    	return false;
    	
    }
 */
   
}
