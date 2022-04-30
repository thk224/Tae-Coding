//CS102 - Data Structures, Dr.Anasse Bari
//This code was adopted from Data Structures and Algorithms in Java / Edition 2 by Robert Lafore
// tree.java
// demonstrates binary search tree

//QUIZ 3 QUESTION: provide the implementation of the methods below + TEST all your methods in the main by using the menu in the main (see main method)
//Make sure your code works (either compiled in command line (terminal) or in Eclipse



import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   }  // end class Node  
////////////////////////////////////////////////////////////////
class Tree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {
	   Node current = root;
	   
	   while(current.iData != key) {
		   if(key < current.iData) {
			   current = current.leftChild;
		   } else {
			   current = current.rightChild;
		   }
		   if(current == null) {
			   return null;
		   }
	   }
	   
	   return current;
	   
      }  // end find()
// -------------------------------------------------------------
   public void insert(int id, double dd) //this method inserts a node of (id and dd) into the tree. (We are consider a BINARY SEARCH TREE by iData)
      {
	   Node newNode = new Node();
	   newNode.iData = id;
	   newNode.dData = dd;

	   if (root == null) {
		   root = newNode;
	   } else {
		   Node current = root;
		   Node parent;
		   
		   while(true) {
			   parent = current;
			   if (id < current.iData) {
				   current = current.leftChild;
				   if (current == null) {
					   parent.leftChild = newNode;
					   return;
				   }
			   } else {
				   current = current.rightChild;
				   if (current == null) {
					   parent.rightChild = newNode;
					   return;
				   }
			   }
		   }
		   
	   }



      }  // end insert()
//////////////////////////////////////////////////////

   public void traverse(int traverseType) //this method is full implemented see below 
      {
       switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot) //implement preOrder traversal
      {
	   if (localRoot == null) {
		   return;
	   }
	   
	   System.out.print(localRoot.iData + " "); //visit the root
	   
	   preOrder(localRoot.leftChild); //visit the left subtree
	   
	   preOrder(localRoot.rightChild); //visit the right subtree

      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot) //implement in Order traversal
      {
	   if (localRoot == null) {
		   return;
	   }
	   
	   inOrder(localRoot.leftChild);
	   
	   System.out.print(localRoot.iData + " ");
	   
	   inOrder(localRoot.rightChild);

      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot) //implement postOrder traversal
      {
	   if (localRoot == null) {
		   return;
	   }
	   
	   postOrder(localRoot.leftChild);
	   
	   postOrder(localRoot.rightChild);
	   
	   System.out.print(localRoot.iData + " ");
	   
      }


///////////////////////////////////////////////////////////////
    public void isBST(Node localRoot) //this method will take a tree as an input and will PRINT to the screen if the tree is a BST or NOT.
    {	
    	
    	if (localRoot == null) {
    		System.out.println("This tree is a BST");
    		
    	} else if(validBST(localRoot)) {
    		System.out.println("This tree is a BST");
    		
    	} else {
    		System.out.println("This tree is NOT a BST");
    		
    	}
    	
    	
    	
    }
    
    private boolean validBST(Node localRoot) {
    	if (localRoot == null) {
    		return true;
    	}
    	
    	//if left child is greater than the parent
    	if (localRoot.leftChild != null && localRoot.leftChild.iData > localRoot.iData) {
    		return false;
    	}
    	
    	if (localRoot.rightChild != null && localRoot.rightChild.iData < localRoot.iData) {
    		return false;
    	}
    	
    	if (validBST(localRoot.leftChild) || validBST(localRoot.rightChild)) {
    		return false;
    	}
    	
    	return true;
    }

// -------------------------------------------------------------
   public boolean delete(int key) // delete node with given key (iData) (if there are multiple nodes match key with iData you have to delete all of them.
      {
	   Node current = root;
	   Node parent = root;
	   
	   boolean isLeftChild = false;
	   
	   //search for node to delete
	   while(key != current.iData) {
		   parent = current;
		   
		   if (key < current.iData) {
			   isLeftChild = true;
			   current = current.leftChild;
			   
		   } else {
			   isLeftChild = false;
			   current = current.rightChild;
			   
		   }
		   
		   if(current == null) {
			   return false;
		   }
	   }
	   
	   //node without any child
	   if (current.leftChild == null && current.rightChild == null) {
		   
		   if (current == root) {
			   root = null;
		   } else if(isLeftChild) {
			   parent.leftChild = null;
		   } else {//if right child
			   parent.rightChild = null;
		   }
		   
	   } else if (current.leftChild == null) { //if no left child
		   
		   if (current == root) {
			   root = current.rightChild;
		   } else if(isLeftChild) {
			   parent.leftChild = current.rightChild;
		   } else {
			   parent.rightChild = current.rightChild;
		   }
		   
	   } else if (current.rightChild == null) { //if no right child
		   
		   if (current == root) {
			   root = current.leftChild;
		   } else if(isLeftChild) {
			   parent.leftChild = current.leftChild;
		   } else {
			   parent.rightChild = current.leftChild;
		   }
	   } else {
		   Node predec = getPredecessor(current);
		   
		   if (current == root) {
			   root = predec;
		   } else if(isLeftChild) {
			   parent.leftChild = predec;
		   } else {
			   parent.rightChild = predec;
		   }
			
		   predec.rightChild = current.rightChild;
	   }
      

	   return true;
                    



      }  // end delete()

   
   public Node getPredecessor(Node delNode) {
	   Node predecParent = delNode;
	   Node predec = delNode;
	   Node current = delNode.leftChild;
	   
	   while (current != null) {
		   predecParent = predec;
		   predec = current;
		   current = current.rightChild;
	   }
	   
	   if (predec != delNode.leftChild) {
		   predecParent.rightChild = predec.leftChild;
		   predec.leftChild = delNode.leftChild;
	   }
	   
	   return predec;
   }
// -------------------------------------------------------------
   public void displayTreeLevels(Node localRoot) // this method will display the nodes at each level in the tree. (The method should print the nodes (id) as: Level1:.... - Level2:... 
      {
	   Node current = localRoot;
	   int lv = 1;
	   
	   if(current == null) {
		   return;
		   
	   }
	   
	   System.out.print("Level " + lv + ": " + current.iData);
	   
	   lv++;
	   displayTreeLevels(current.leftChild);
	   displayTreeLevels(current.leftChild);
	   
	   


      }  // end displayTreeLevels()



// -------------------------------------------------------------

  public void displaymyChilds(int id, double dd) //given a node who idata is id and dd is ddata display it childen in the following way:
  {

    //Left child: idata:  dData: 
    //Right child: idata: dData: 

    //if the node does not have children you display message that the nodes Do not have children. 
    // or if one of the child is null, then you display a message stating that. 
	  Node parent = find(id);
	  
	  if (parent.dData != dd) {
		  System.out.println("There is no such node with the given data set");
		  return;
	  } else {
		  if(parent.leftChild == null && parent.rightChild == null) {
			  System.out.println("This node does not have children");
			  return;
		  }
		  //left child
		  if(parent.leftChild == null) {
			  System.out.println("There is no left child");
		  } else {
			 System.out.println("Left child-> iData: " + parent.leftChild.iData + " dData: " + parent.leftChild.dData);
		  }
		  
		  //right child
		  if(parent.rightChild == null) {
			  System.out.println("There is no left child");
		  } else {
			 System.out.println("Right child-> iData: " + parent.rightChild.iData + " dData: " + parent.rightChild.dData);
		  }
	  }

  }


// -------------------------------------------------------------

public void displayLeaves(Node localRoot) //this method will display all the leaves (iData and dData) of all the leaves)
  {
	if(localRoot == null) {
		return;
	}
    
	if(localRoot.leftChild == null && localRoot.rightChild == null) {
		System.out.println("Leaf-> iData: " + localRoot.iData + " dData: " + localRoot.dData);
		
	} else {
		if(localRoot.leftChild != null) {
			displayLeaves(localRoot.leftChild);
		}
		if(localRoot.rightChild != null) {
			displayLeaves(localRoot.rightChild);
		}
	}
		


  }

// -------------------------------------------------------------



}  // end class Tree





////////////////////////////////////////////////////////////////
class TreeApp
   {
   public static void main(String[] args) throws IOException
      {

      //You can modify this code of the main as much as you want - as longs as  ALL the methods above are being tested and called. 


      int value;

      Tree theTree = new Tree();

       //... you change these inputs to build the tree, and/or can add other inputs to test the program. 
      //The tree is ordered by iData.  


      theTree.insert(50, 1.5);
      theTree.insert(25, 1.2);
      theTree.insert(75, 1.7);
      theTree.insert(12, 1.5);
      theTree.insert(37, 1.2);
      theTree.insert(43, 1.7);
      theTree.insert(30, 1.5);
      theTree.insert(33, 1.2);
      theTree.insert(87, 1.7);
      theTree.insert(93, 1.5);
      theTree.insert(97, 1.5);
     
      /*

      Menu:

      1. Traverse
      2. isBST 
      3. Delete 
      4. Display Tree by Levels
      5. Display my Childs
      6. Insert a New Node
      7. Display All the Leaves
   

      */
      Node root = theTree.find(50);
     
      theTree.isBST(root);
      theTree.displayLeaves(root);
      theTree.traverse(1);
      theTree.traverse(2);
      theTree.traverse(3);
      theTree.delete(12);
      theTree.traverse(1);
      theTree.displaymyChilds(50, 1.5);

// -------------------------------------------------------------
      }
   
   }// end class TreeApp
////////////////////////////////////////////////////////////////
