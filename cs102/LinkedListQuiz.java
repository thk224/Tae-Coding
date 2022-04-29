//Tae Kim (tk2686)
import java.util.NoSuchElementException;

////////////////////////////////////////////////////////////////
class Link
   {
   public int iData;              // data item
   public double dData;           // data item
   public Link next;              // next link in list
// -------------------------------------------------------------
   public Link(int id, double dd) // constructor
      {
      iData = id;                 // initialize data
      dData = dd;                 // ('next' is automatically
      }                           //  set to null)
// -------------------------------------------------------------
   public void displayLink()      // display ourself
      {
      System.out.print("{" + iData + ", " + dData + "} ");
      }
   }  // end class Link
////////////////////////////////////////////////////////////////
class LinkList
   {
   private Link first;            // ref to first link on list

// -------------------------------------------------------------
   public LinkList()              // constructor
      {
      first = null;               // no links on list yet
      }
// -------------------------------------------------------------
   public boolean isEmpty()       // true if list is empty
      {
      return (first==null);
      }
// -------------------------------------------------------------
// insert at start of list
   public void insertFirst(int id, double dd)
      {                           
	   Link nlink = new Link(id, dd);
	   nlink.next = first;
	   first = nlink;
	   
      }  
// -------------------------------------------------------------
   public Link deleteFirst()     
      {                           
	   if (first == null) {
		   throw new NoSuchElementException();
	   }
	   
	   Link link = first;
	   first = first.next;
	   return link;
      }	  
// -------------------------------------------------------------
   public void displayList()
      {
      
      //display all the elements in the lists
	   Link current = first;
	   
	   while (current != null) {
		   current.displayLink();
		   current = current.next;
	   }
	   System.out.println();
      }
	 
	// ------------------------------------------------------------- 
	 
// -------------------------------------------------------------
   public void displaytheLastNODEiData()
      {
      
      //display the last node's iData
	   Link current = first;

	   
	   if(first == null) {
		  
	   } else {
		   while(current.next != null) {
			   current = current.next;
		   }
		   
		   System.out.println("Last node iData: " + current.iData);
	   }
	   
      }
    
// ------------------------------------------------------------- 
    
	public int findMin(){


    //returns the minimum iData in the list
		int min = first.iData;
		Link current = first.next;
		
		while(current != null) {
			if (current.iData < min) {
				min = current.iData;
			}
			
			current = current.next;
		}
		
		return min;
   } 
	 
// ------------------------------------------------------------- 


// ------------------------------------------------------------- 
    
   public void DeleteElementwithiData(int a){

    //this method will delete any node with iData == a. 
    //if there are many, the method will have to delete all of them.
	   Link current = first;
	   Link previous = null;
	   
	   while(current != null) {
		   if(current.iData == a) {
			   if(current == first) {
				   deleteFirst();
			   } else {
				   previous.next = current.next;
			   }
		   }
		   
		   previous = current;
		   current = current.next;
		   
	   }
   } 
    
// ------------------------------------------------------------- 
    	 
   public void removeDuplicates(){


    //this method will scan through the list and remove 
    //duplicates. (if the list is: 2->3->2->4->3->5->90 the method should
    //keep on copy of each duplicate, so the list become:
    //2->3->4->5-90
    //if there are no duplicates then the method does not need do anything
	   Link current = first;
	   Link cNext = null;
	   
	   while(current != null) {
		   cNext= current;
		   
		   while(cNext.next != null) {
			   if(current.iData == cNext.next.iData) {
				   cNext.next = cNext.next.next;
				   
			   } else {
				   cNext = cNext.next;
			   }
		   }
		   
		   current = current.next;
	   }
   } 
	 
// -------------------------------------------------------------

public void sortList(){

  
//write an algorithm that I will sort the list in-place
//do not use arrays. 
	Link current = first;
	Link cNext = null;
	
	//bubble sort
	while (current != null) {
		cNext = current.next;
		
		while(cNext != null) {
			if(cNext.iData < current.iData && cNext.dData < current.dData) {
				int temp1 = cNext.iData;
				cNext.iData = current.iData;
				current.iData = temp1;
				
				double temp2 = cNext.dData;
				cNext.dData = current.dData;
				current.dData = temp2;
			}
			cNext = cNext.next;
		}
		
		current = current.next;
	}
}

   }  // end class LinkList
////////////////////////////////////////////////////////////////
class Quiz3
   {
   public static void main(String[] args)
      {
      LinkList theList = new LinkList();  // make new list
      //this code is for you to use to test you code
      //feel free to modify it. 

      theList.insertFirst(22, 2.99);      // insert four items
      theList.insertFirst(44, 4.99);
      theList.insertFirst(66, 6.99);
      theList.insertFirst(88, 8.99);
      theList.insertFirst(22, 2.99);      // insert four items
      theList.insertFirst(44, 4.99);
      theList.insertFirst(66, 6.99);
      theList.insertFirst(88, 8.99);
      //... add more elements if you want..

      //CALL and TEST YOUR METHODS HERE 
      theList.displaytheLastNODEiData();
      
      System.out.println(theList.findMin());
      
      theList.displayList();

      theList.removeDuplicates();
      theList.displayList();
      
      theList.sortList();
      theList.displayList();
      
      theList.DeleteElementwithiData(22);
      theList.displayList();

                 
      }  // end main()
   }  // end class LinkListApp
////////////////////////////////////////////////////////////////
