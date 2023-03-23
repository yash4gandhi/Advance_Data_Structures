
package graphify;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;


public class Algorithms {

    private GraphifyGUI GG,GG1;
    HashMap<Integer, Integer> connectionCache = new HashMap<>();
    private HashMap<Integer, Integer> glowMap = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> nodes;
    private Queue<Integer> queue;
    private Stack<Integer> stack;
    private HashMap<Integer, Integer> distTo;
    private HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> visited;
    private HashMap<Integer, Integer> p = new HashMap<>();

	private HashMap<Integer, Boolean> spl;
    private HashMap<Integer, Integer> color;
    private HashMap<Integer, Integer> greedyresult;
    private HashSet<Integer> _colors2;
    private ArrayList<Integer> conn;
    private ArrayList<Integer> bconn;
    private ArrayList<Integer> cutV;
    private Color[] vertexColors;
    int _selectedNode = -1;
    int _SIZE_OF_NODE = 20;
    int id = 0;
    int time = 0;
    Integer maxColors = 0;
    int _source;
    int _dest;

    public Algorithms(GraphifyGUI GG) {
        this.GG = GG;
        this.nodes = new HashMap<>();
        this.queue = new LinkedList<>();
        this.stack = new Stack<>();
        this.cutV = new ArrayList<>();
        this._colors2 = new HashSet<>();
        this.visited = new HashMap<>();
        this.set = new HashMap<>();
        this.visited = new HashMap<>();
        this.color = new HashMap<>();
        this.greedyresult = new HashMap<>();
        this.vertexColors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green, Color.magenta, Color.orange};
    }

    public HashSet<Integer> getEdge(int source) {
        nodes = GraphifyGUI.getNode();
        return nodes.get(source);
    }

    

    void dfs(int source) {
        nodes = GG.getNode();
        distTo = new HashMap<>();
        visited = new HashMap<>();

        Iterator<Integer> allNodes = nodes.keySet().iterator();
        while (allNodes.hasNext()) {
            int key = allNodes.next();
            visited.put(key, -1);
            distTo.put(key, 0);
        }

        bconn = new ArrayList<>();
        int element;
        visited.put(source, 0); // start vertex
        stack.push(source);
        while (!stack.isEmpty()) {
            element = stack.peek();
            GG.printlnConsole("Considering element " + element);
            if (!bconn.contains(element)) {
                bconn.add(element);
            }
            HashSet<Integer> iList = getEdge(element);
            Iterator<Integer> l = iList.iterator();
            while (l.hasNext()) {
                int n = l.next();
                if (visited.get(n) == -1) {
                    GG.printlnConsole("Pushing " + n);
                    stack.push(n);
                    visited.put(n, element);
                    distTo.put(n, distTo.get(element) + 1);
                    break;
                }
                if (l.hasNext() == false) {
                    int backEdge = stack.pop();
                    GG.printlnConsole("Back edge " + backEdge);
                }
            }
        }
        GG.printlnConsole("order is " + bconn);
    }

    void bfs(int source) {
        nodes = GG.getNode();
        distTo = new HashMap<>();
        visited = new HashMap<>();
        Iterator<Integer> allNodes = nodes.keySet().iterator();
        while (allNodes.hasNext()) {
            int key = allNodes.next();
            visited.put(key, -1);
            distTo.put(key, 0);
        }

        conn = new ArrayList<Integer>();
        int i, element;
        visited.put(source, 0);
        queue.add(source);
        while (!queue.isEmpty()) {
            element = queue.remove();
            GG.printlnConsole(element + " removed");
            i = element; // what is the point of i = element here ?
            conn.add(element);
            HashSet<Integer> iList = getEdge(i);
            Iterator<Integer> l = iList.iterator();
            while (l.hasNext()) {
                int n = l.next();
                if (visited.get(n) == -1) {
                    queue.add(n);
                    visited.put(n, i);
                    distTo.put(n, distTo.get(i) + 1);
                }
            }
        }
        GG.printlnConsole("Order is " + conn);
        GG.printlnConsole("DistTo is " + distTo);
        GG.printlnConsole("DistTo is " + visited);
    }

    
    void dijkstra(int source)
    {
    	   int i=0;
		   nodes = GG.getNode();
           distTo = new HashMap<>();
           visited = new HashMap<>();
		   spl = new HashMap<>();
           Iterator<Integer> allNodes1 = nodes.keySet().iterator();
		    while (allNodes1.hasNext()) {
            int key = allNodes1.next();
            visited.put(key, -1);
			spl.put(key, false);
            distTo.put(key, Integer.MAX_VALUE);
			i++;
               }
		    int [][] arr = new int[i][i];
			for(int j=0;j<i;j++){
				 HashSet<Integer> iList = getEdge(j);
                 Iterator<Integer> l = iList.iterator();
                 while (l.hasNext()) {
                int n = l.next();
				arr[j][n]=1;
				arr[n][j]=1;
            
            }
			}			
		   distTo.put(source, 0);
		   int path[] = new int [i];
		   path[source]=-1;
		   for (int count = 0; count < i; count++) 
            { 
            int min = Integer.MAX_VALUE, u=-1; 
            for (int v = 0; v < i; v++) {
            if (spl.get(v) == false && distTo.get(v) <= min) 
            { 
                min = distTo.get(v); 
                u = v; 
            } 
			}
            spl.put(u,true);
            for (int v = 0; v < i; v++) {
                        if (spl.get(v)==false && arr[u][v]==1 &&
                        distTo.get(u) != Integer.MAX_VALUE && 
                        distTo.get(u)+ arr[u][v] < distTo.get(v)) 
						{ /*int z = distTo.get(u) + arr[u][v];*/
						distTo.put(v,distTo.get(u)+arr[u][v]);
						path[v]=u;
						}					
        }
		}
		   GG.printlnConsole("DistTo is " + distTo);
           GG.printlnConsole("Vertex Visited  " + spl);
		   print1(i,source,path);
           
	}
    void print1(int n,int src,int[] path){
		 GG.printlnConsole("Paths:");
		 for(int i=0;i<n;i++){
			 if(i!=src){
				 GG.printlnConsole(src+"->"+i+":");
				 print2(i,path);
				 GG.printlnConsole(p.toString().replaceAll("=", "-->"));
				 p.clear();
			 }
		 }
		
	}
    void print2(int v, int[] path){
		
		if (path[v] == -1) 
        { 
            return; 
        } 
        print2(path[v], path); 
        p.put(path[v],v); 
	}
    
   
    

    void greedyColoring(int nc) {
        nodes = GG.getNode();
        HashMap<Integer, Integer> available = new HashMap<Integer, Integer>();
        Iterator<Integer> allNode = nodes.keySet().iterator();
        while (allNode.hasNext()) {
            int key = allNode.next();
            greedyresult.put(key, -1);
            available.put(key, 0); //set all to false
        }
        greedyresult.put(_source, 0);
        allNode = nodes.keySet().iterator();
        while (allNode.hasNext()) {
            int key = allNode.next();
            HashSet<Integer> kList = getEdge(key);
            Iterator<Integer> u = kList.iterator();
            while (u.hasNext()) {
                int k = u.next();
                if (greedyresult.get(k) != -1) {
                    available.put(greedyresult.get(k), 1);
                }
            }
            Integer nColor = 0;
            Iterator<Integer> allNodes = nodes.keySet().iterator();
            while (allNodes.hasNext()) {
                nColor = allNodes.next();
                if (available.get(nColor) == 0) {
                    break;
                }
            }
            greedyresult.put(key, nColor);
            if (greedyresult.get(key) > maxColors) {
                maxColors = nColor;
            }
            u = kList.iterator();
            while (u.hasNext()) {
                int k = u.next();
                if (greedyresult.get(k) != -1) {
                    available.put(greedyresult.get(k), 0);
                }
            }
        }

        Iterator<Integer> allNodes = nodes.keySet().iterator();
        while (allNodes.hasNext()) {
            int key = allNodes.next();
            GG.printlnConsole("Vertex " + key + " ---> Color " + greedyresult.get(key));
        }
    }

    

    public int hasPath(int v) {
        return visited.get(v);
    }

    public int distTo(int v) {
        return distTo.get(v);
    }


    public void shortestPath(int v, int e) {
        if (e == v) {
            GG.printlnConsole(v + "-->" + v);
            return;
        }
        for (int i = e; i >= 0; i = visited.get(i)) {
            if (i == v) {
                break;
            }
            if (visited.get(i) != -1) {
                set.put(visited.get(i), i);
            }
        }
        // removed rset
        GG.printlnConsole(set.toString().replaceAll("=", "-->"));
        glowMap.clear();
        for (int i : set.keySet()) {
            glowMap.put(i, set.get(i));
        }
        GG.graph();
    }
    //[0, 2, 19, 5, 7, 9, 14]

    public Queue getQueue() {
        return this.queue;
    }

    public Stack getStack() {
        return this.stack;
    }

    public HashMap getGlowMap() {
        return this.glowMap;
    }

    public HashMap distTo() {
        return this.distTo;
    }

    public HashMap getSet() {
        return this.set;
    }

    public HashMap getVisited() {
        return this.visited;
    }

    public HashMap getColor() {
        return this.color;
    }

    public HashMap getGreedyResult() {
        return this.greedyresult;
    }

    public HashSet getColors2() {
        return this._colors2;
    }

    public ArrayList getConn() {
        return this.conn;
    }

    public ArrayList getBConn() {
        return this.bconn;
    }

    public ArrayList getCutV() {
        return this.cutV;
    }

    public Color[] getVertexColors() {
        return this.vertexColors;
    }

}
