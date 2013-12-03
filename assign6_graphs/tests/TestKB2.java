/**
 * TestKB2 -- Dijkstra's Algorithm with Kevin Bacon (over 15 nodes).
 * <p>
 * Creates the Kevin Bacon graph and uses Dijkstra's Algorithm to find a
 * path between Kevin Bacon and Joe Pesci.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 6
 * @since       2013-11-30
 */

import java.util.LinkedList;
import java.util.List;

public class TestKB2 extends TestHarness {

    public TestKB2(String s) { super(s); }

    public boolean test() {
	Graph<String,Integer> g = new Graph<String,Integer>();

	//save participants strings for easy access
	String kb = "Kevin Bacon";
	String th = "Tom Hanks";
	String bp = "Bill Paxton";
	String kw = "Kate Winslet";
	String bz = "Billy Zane";
	String cl = "Christopher Lloyd";
	String mj = "Michael J. Fox";
	String mg = "Michael Gross";
	String fw = "Fred Ward";
	String ce = "Clint Eastwood";
	String bv = "Bee Vang";
	String cc = "Christopher Carley";
	String zb = "Zach Braff";
	String mw = "Michelle Williams";
	String jf = "James Franco";
	
	//starting point will be kbNode
	//they'll be defined after the graph is created
	Node<String,Integer> kbNode = null;
	Node<String,Integer> jfNode = null;
		
	try {
		g.addNode(kb);
		g.addNode(th);
		g.addNode(bp);
		g.addNode(kw);
		g.addNode(bz);
		g.addNode(cl);
		g.addNode(mj);
		g.addNode(mg);
		g.addNode(fw);
		g.addNode(ce);
		g.addNode(bv);
		g.addNode(cc);
		g.addNode(zb);
		g.addNode(mw);
		g.addNode(jf);
		
		//all links of equal weight
		g.addEdge(kb, 1, th);
		g.addEdge(th, 1, bp);
		g.addEdge(bp, 1, kw);
		g.addEdge(kw, 1, bz);
		g.addEdge(bz, 1, cl);
		g.addEdge(cl, 1, mj);
		g.addEdge(mj, 1, mg);
		g.addEdge(mg, 1, fw);
		g.addEdge(fw, 1, ce);
		g.addEdge(ce, 1, bv);
		g.addEdge(bv, 1, cc);
		g.addEdge(cc, 1, zb);
		g.addEdge(zb, 1, mw);
		g.addEdge(mw, 1, jf);
		
		//add in extra nodes to challenge the algorithm
		//should have no impact if the algorithm works
		String b = "Batman";
		String j = "Joker";
		String p = "Peter Parker";
		String n = "Nice Guy";
		g.addNode(b);
		g.addNode(j);
		g.addNode(p);
		g.addNode(n);
		g.addEdge(jf, 1, b);
		g.addBiEdge(j, 1, cc);
		g.addEdge(p, 1, cc);
		g.addBiEdge(bz, 1, n);
	
		
	} catch (InvalidOperationException e) {
		//why was an exception thrown?
		return false;
	}

	//run Dijkstra's Algorithm
	DijkstrasAlgorithm da = new DijkstrasAlgorithm(g);
	
	//get the nodes in a list to initialize the starting and ending nodes
	List<Node<String,Integer>> list = g.getNodes();
	for(Node<String,Integer> Node:list){
		//starting node is kevin bacon
		if(Node.getLabel().equals(kb)){
			kbNode = Node;
			//execute the algorithm from the starting point
			da.execute(kbNode);
		}
		//save the finishing node (which is james franco)
		else if(Node.getLabel().equals(jf)){
			jfNode = Node;
		}
	}

	LinkedList<Node<String,Integer>> ll = da.getPath(jfNode);
	System.out.println("path: "+ll.toString());
	
	
	// Confirm that this path is what it should be
	if(! ll.pop().getLabel().equals(kb)) return false;
	if(! ll.pop().getLabel().equals(th)) return false;
	if(! ll.pop().getLabel().equals(bp)) return false;
	if(! ll.pop().getLabel().equals(kw)) return false;
	if(! ll.pop().getLabel().equals(bz)) return false;
	if(! ll.pop().getLabel().equals(cl)) return false;
	if(! ll.pop().getLabel().equals(mj)) return false;
	if(! ll.pop().getLabel().equals(mg)) return false;
	if(! ll.pop().getLabel().equals(fw)) return false;
	if(! ll.pop().getLabel().equals(ce)) return false;
	if(! ll.pop().getLabel().equals(bv)) return false;
	if(! ll.pop().getLabel().equals(cc)) return false;
	if(! ll.pop().getLabel().equals(zb)) return false;
	if(! ll.pop().getLabel().equals(mw)) return false;
	if(! ll.pop().getLabel().equals(jf)) return false;
	
	if(  ll.size() != 0) return false;

	System.out.println(g.toString());
	return true;
    }

}