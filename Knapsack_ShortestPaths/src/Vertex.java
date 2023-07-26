import java.util.Vector;

public class Vertex {
   String id="";
   String name="";
  //Nadav-adding knapsack attributes
    int vertWeight=0;
    int vertValue=0;
    Vector<Item> vecItems=new Vector<Item>();
  public Vertex(String id, String name, int vWeight, int vValue) {
    this.id = id;
    this.name = name;
    this.vertWeight=vWeight;
    this.vertValue=vValue;

  }
  public Vertex(Vertex other) {
	    this.id = other.id;
	    this.name = other.name;
	    this.vertWeight=other.vertWeight;
	    this.vertValue=other.vertValue;
	    this.vecItems=new Vector<Item>();
        for (int i=0;i<other.vecItems.size();i++)
         	this.vecItems.add(other.vecItems.elementAt(i));
	  }
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  public int getWeight() {
	    return vertWeight;
	  }
  public int getValue() {
	    return vertValue;
	  }
  public double getEfficiency(){
	   return (double)(vertValue)/vertWeight;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vertex other = (Vertex) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return name;
  }
  
} 
