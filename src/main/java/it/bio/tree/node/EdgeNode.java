package it.bio.tree.node;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class EdgeNode extends Node {
	
	protected Node child;

	public EdgeNode(String label) {
		super(label);
	}

	@Override
	public void addChildNode(Node node) {
		child = node;
	}

	@Override
	public List<Node> getChilds() {
		return Collections.unmodifiableList(Collections.singletonList(child));
	}

	@Override
	public Node innerExistPath(String path) {
		if (StringUtils.indexOfDifference(getLabel(), path) == -1) {
			return child;
		} else {
			for (int i = 1; i <= path.length(); i++) {
				boolean r = getLabel().startsWith(
					StringUtils.substring(path, 0, i)
				);
				
				if (!r) {
					return null;
				}
			}
			
			return this;
		}
	}	
	
	public void append(char edgeLabel) {
		super.append(edgeLabel);
	}
	
	public void resetLabel(String label) {
		super.resetLabel(label);
	}
	
	public Node existPath(String suffix, String phaseS) {		
		final StringBuilder sb = getFullLabel();
		
		return sb.toString().startsWith(suffix + phaseS) ? this : null;
	}
}
