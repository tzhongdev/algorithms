	/**
	 * Traverses the Graph using breadth-first search
	 * @param startNode the node to start the search with
	 * @return a list containing the reachable nodes, ordered as visited during the search
	 */
	@Override
	public List<Node> breadthFirstSearch(Node startNode){
		LinkedList<Node> nodeList = new LinkedList<Node>();
		resetState();
		// TODO

		// return an empty list if startnode is null or graph is empty
		if (startNode == null || nodes.isEmpty()) {
			return null;
		}

		// create a queue and add the start node to queue and nodeList

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(startNode);
		startNode.status = Node.GRAY; 										// visited so change status to 1 (grey)

		while (!queue.isEmpty()) {
			Node parent = queue.remove();								   // while queue is not empty remove parent from queue
			nodeList.add(parent);										   // add parent to nodeList

			List<Node> adjacentList = parent.getAdjacentNodes();	   		   // create adjacent list to parent
			for (Node node : adjacentList) {								   // for every node in the adjacent list
				if (node.status == Node.WHITE) {					       // if node is white
					node.status = Node.GRAY;							   // is visited change to grey
					queue.add(node);							   			// and add to queue
				}
			}
			// mark parent black 2
			parent.status = Node.BLACK;
		}
		return nodeList;
	}

	/**
	 * Traverses the Graph using depth-first search
	 * @param startNode the node to start the search with
	 * @return a list containing the reachable nodes, ordered as visited during the search
	 */
	@Override
	public List<Node> depthFirstSearch(Node startNode){
		LinkedList<Node> nodeList = new LinkedList<Node>();
		resetState();
		// TODO

		// return empty list if startNode is not existent or graph is empty
		if (startNode == null || nodes.isEmpty()) {
			return null;
		}

		Stack<Node> stack = new Stack<Node>();						// create and empty stack to store nodes
		Node parent = startNode;
		stack.push(parent);											// push startNode to stack



		while (!stack.isEmpty()) {
			Node node = stack.pop();								// while stack not empty pop node from stack
			if (node.status == Node.WHITE) { 						// and if node is not visited (white)
				nodeList.add(node);									// add node to nodelist
				node.status = Node.GRAY;							// mark as visited (grey)

				List<Node> adjacentList = node.getAdjacentNodes();		// create corresponding adjacentList to node
				for (Node element : adjacentList) {						// for every element in adjacentList
					if (element.status == Node.WHITE)					// if element is not visited
						stack.push(element);							// push it on the stack
				}

			}
			node.status = Node.BLACK;									// finished node (black)
		}
		return nodeList;
	}
