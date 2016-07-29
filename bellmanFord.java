// Bellman-Ford algorithm
	// ----------------------------------------------------------------------------------------------------

	public void populateBellmanFordFrom(Node startNode) {

		this.resetState();
		
		// check if graph empty or startNode not valid
		if ((nodes.isEmpty()) || !(nodes.containsValue(startNode)))
				throw new RuntimeException("Empty Graph or invalid startNode");
		
		// Initialization
		for (Node node : nodes.values()) {
			node.distance = Integer.MAX_VALUE; 				// set distance to INF
			node.predecessor = null;						// and predecessor to null
			
		}
		startNode.distance = 0;								// startNode is 0
		
		// main loop
		for (Node node : nodes.values()) {														// for every node in nodes get the corresponding edges
			LinkedList<Edge> edges = node.edges;
			if (node.status != Node.GRAY) {														// change color to gray
				node.status = Node.GRAY;
				for (Edge edge : edges) {														// #nodes - 1 times
					int relax = node.distance + getWeight(node, edge.endnode); 					// calculate the relax	
					if (Integer.MAX_VALUE - node.distance < getWeight(node, edge.endnode)) {
						throw new RuntimeException("integer overflow");
					}
					if (relax < edge.endnode.distance) {		//change the distance and predecessor
						edge.endnode.distance = relax;
						edge.endnode.predecessor = node;
					}
				 }
				
			}
			node.status = Node.BLACK;
		}
		
		// check for negative cycles and throw runtimeexception if negative cycle exists
		// if there is no negative cycle than the inner loop shouldnt be executed
		
		for (Node node : nodes.values()) {
			LinkedList<Edge> edges = node.edges;
			for (Edge edge : edges) {						// n-1 times
				int relax = node.distance + getWeight(node, edge.endnode);
				if (relax < edge.endnode.distance) {
					throw new RuntimeException("Graph contains a negative-weight cycle");
				}
			}
				
		}
					
	}

	/**
	 * Calculates a List of Node indices that describe the shortet path from
	 * start node to target node. The Bellman-Ford-Algorithmn is used for
	 * calculation.
	 * 
	 * @param targetNodeIndex
	 *            the index of the target node, as returned by addNode()
	 * @return the list of nodes, or null if no path exists
	 */
	@Override
	public List<Node> getShortestPathBellmanFord(Node startNode, Node targetNode) {
		// TODO: Your implementation here
		// NOTE: you have to run populateBellmanFordFrom first before you can read
		// out the shortest path

		//This line stops program execution until a key is pressed
		stopExecutionUntilSignal();

		//Return an empty list. Replace this line by the shortest path!
		LinkedList<Node> l = new LinkedList<Node>();
		
		if (!(nodes.containsValue(startNode)) || !(nodes.containsValue(targetNode)))
			return null;
		
		populateBellmanFordFrom(startNode);
		
		Node node = targetNode;
		while (node.predecessor != null) {
			l.addFirst(node);
			node = node.predecessor;
		}
		if (l.isEmpty())				// empty
			return null;
		
		l.addFirst(startNode);
	
		return l;
	}
