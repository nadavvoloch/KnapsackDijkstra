/**
link to site: http://www.sanfoundry.com/java-program-knapsack-algorithm/ 
** Java Program to Implement Knapsack Algorithm
 **/

 
import java.util.Scanner;
import java.util.Vector;
 
/** Class Knapsack **/
public class Knapsack
{
	Vector<Item> items=new Vector<Item>();
	int maxWeight;
	public Knapsack(){
		
	}
    public Knapsack(Vector<Item> _items, int maxWgt){
    	for(int i=0;i<_items.size();i++)
    		items.addElement(_items.elementAt(i));
		maxWeight=maxWgt;
	}
    public Vector<Item> chooseItems(){
    	int[] weights= new int[items.size()+1];
    	int[] values= new int[items.size()+1];
    	for(int i=1;i<items.size()+1;i++){
    		weights[i]=items.elementAt(i-1).weight;
    		values[i]=items.elementAt(i-1).value;
   // 		System.out.println("wgt"+i+":"+weights[i]+", val"+i+":"+values[i]);
    	}
    	String result=solve(weights,values, maxWeight,items.size());
    	System.out.println(result);
    	Vector<Item> chosenItems=new Vector<Item>();
    	int temp=0;
    	String curNumStr="";
    	for(int i=0;i<result.length();i++){
    	//	for(int j=i;j<result.length();j++){
    		if(result.charAt(i)=='$'){
    			curNumStr=result.substring(temp, i);
    			chosenItems.addElement(items.elementAt(Integer.parseInt(curNumStr)-1));
    			temp=i+1;
    		}
    		
    	//	}
    	
    	}
    	return chosenItems;
    }
    public String solve(int[] wt, int[] val, int W, int N)
    {
        int NEGATIVE_INFINITY = Integer.MIN_VALUE;
        int[][] m = new int[N + 1][W + 1];
        int[][] sol = new int[N + 1][W + 1];
 
        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j <= W; j++)
            {
                int m1 = m[i - 1][j];
                int m2 = NEGATIVE_INFINITY; 
                if (j >= wt[i])
                    m2 = m[i - 1][j - wt[i]] + val[i];
                /** select max of m1, m2 **/
                m[i][j] = Math.max(m1, m2);
                sol[i][j] = m2 > m1 ? 1 : 0;
            }
        }        
        /** make list of what all items to finally select **/
        int[] selected = new int[N + 1];
        for (int n = N, w = W; n > 0; n--)
        {
            if (sol[n][w] != 0)
            {
                selected[n] = 1;
                w = w - wt[n];
            }
            else
                selected[n] = 0;
        }
        /** Print finally selected items **/
      //  System.out.println("\nItems selected : ");
        String res="";    
        for (int i = 1; i < N + 1; i++){
            if (selected[i] == 1){
       //         System.out.print(i +" ");
                res+=i+"$";
            }
        }
         
        System.out.println();
        return res;
     
    }
    /** Main function **/
    public static void main (String[] args) 
    {
    	/*
        Scanner scan = new Scanner(System.in);
        System.out.println("Knapsack Algorithm Test\n");
        /** Make an object of Knapsack class **/
      /*
    	Knapsack ks = new Knapsack();
 
        System.out.println("Enter number of elements ");
        int n = scan.nextInt();
 
        int[] wt = new int[n + 1];
        int[] val = new int[n + 1];
 
        System.out.println("\nEnter weight for "+ n +" elements");
        for (int i = 1; i <= n; i++)
            wt[i] = scan.nextInt();
        System.out.println("\nEnter value for "+ n +" elements");
        for (int i = 1; i <= n; i++)
            val[i] = scan.nextInt();
 
        System.out.println("\nEnter knapsack weight ");
        int W = scan.nextInt();
 
        ks.solve(wt, val, W, n);
       */
    	Item a=new Item("a",15,3);
    	Item b=new Item("b",121,11);
    	Item c=new Item("c",72,10);
    	Item d=new Item("d",16,9);
     	Item e=new Item("e",22,4);
    	Item f=new Item("f",13,11);
    	Item g=new Item("g",76,10);
    	Item h=new Item("h",19,9);
     	Item ii=new Item("ii",18,3);
    	Item jj=new Item("jj",44,11);
    	Item k=new Item("k",123,4);
    	Item l=new Item("l",116,2);
    	Vector<Item> checkItems=new Vector<Item>();
    	checkItems.addElement(a);
    	checkItems.addElement(b);
    	checkItems.addElement(c);
    	checkItems.addElement(d);
     	checkItems.addElement(e);
    	checkItems.addElement(f);
    	checkItems.addElement(g);
    	checkItems.addElement(h);
     	checkItems.addElement(ii);
    	checkItems.addElement(jj);
    	checkItems.addElement(k);
    	checkItems.addElement(l);

    	Knapsack knpsk = new Knapsack(checkItems,32);
    	Vector<Item> chosenCheckItems=new Vector<Item>();
    	chosenCheckItems=knpsk.chooseItems();
    	for(int i=0;i<chosenCheckItems.size();i++)
    		System.out.println(chosenCheckItems.elementAt(i)); 
    }
}