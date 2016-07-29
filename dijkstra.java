// Disjkstra algorithm
	// ----------------------------------------------------------------------------------------------------

	@Override
	public void populateDijkstraFrom(Node startNode) {

		this.resetState();
		PriorityQueue<Node> distanceQueue = new PriorityQueue<Node>();

		// check if graph is empty or startNode is not existent
		
		if ((nodes.isEmpty()) || !(nodes.containsValue(startNode)))
			throw new RuntimeException();
		
		// Initialization
		
		for (Node node : nodes.values()) {
			node.distance = Integer.MAX_VALUE; 				// represents  infinity
			node.predecessor = null;
			distanceQueue.add(node);
		}
		
		startNode.distance = 0;				// dist[startNode] = 0
		distanceQueue.add(startNode);
		
		// main loop
		while (!(distanceQueue.isEmpty())){
			Node u = distanceQueue.remove();
			if (u.status == Node.WHITE) {
				u.status = Node.BLACK;				// finished node
			}
			
			for (Node neighbour : u.getAdjacentNodes()) {								// for every neighbour 
				if (distanceQueue.contains(neighbour)) {								// if he is in queue
					int relax = u.distance + getWeight(u, neighbour);						// calculate alternative distance 
					if (Integer.MAX_VALUE - u.distance < u.getWeight(neighbour)) {		// throws exception if integer overflow
						throw new RuntimeException("integer overflow");
					}
					if (relax < neighbour.distance) {					    				// if its less than neighbours distance 
						neighbour.distance = relax;										// change distance to alt 
						neighbour.predecessor = u;										// and predecessor to u 
					}
	
				}
			}
		}
	
	}
	
	/**
	 * Calculates a List of Node indices that describe the shortet path from
	 * start node to target node. The Dijkstra-Algorithmn is used for
	 * calculation.
	 * 
	 * @param targetNodeIndex
	 *            the index of the target node, as returned by addNode()
	 * @return the list of nodes, or null if no path exists
	 */
	@Override
	public List<Node> getShortestPathDijkstra(Node startNode, Node targetNode) {
	
		LinkedList<Node> l = new LinkedList<Node>();
		
		if (!(nodes.containsValue(startNode)) || !(nodes.containsValue(targetNode)))
			return null;
		
		populateDijkstraFrom(startNode);
		
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
