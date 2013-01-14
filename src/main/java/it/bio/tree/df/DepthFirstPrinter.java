package it.bio.tree.df;

import it.bio.tree.node.Node;

import java.util.concurrent.LinkedBlockingQueue;

public class DepthFirstPrinter {
	
	private Node node;

	public DepthFirstPrinter(Node n) {
		this.node = n;
	}
	
	public StringBuilder print() {
		final StringBuilder sb = new StringBuilder();
		
		final LinkedBlockingQueue<Node> q = new LinkedBlockingQueue<Node>();
		q.add(this.node);

		while (true) {
			final LinkedBlockingQueue<Node> subQueue = new LinkedBlockingQueue<Node>();

			while (!q.isEmpty()) {
				final Node aNode = q.remove();
				sb.append(aNode.getValue() + ", ");

				subQueue.addAll(aNode.getChilds());
			}

			sb.append("\n");

			if (subQueue.isEmpty()) {
				break;
			} else {
				q.addAll(subQueue);
			}
		}		
		
		return sb;
	}

}
