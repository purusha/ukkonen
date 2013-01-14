package it.bio.tree;

import it.bio.cases.FirstCase;
import it.bio.cases.SecondCase;
import it.bio.cases.SecondCaseImplicit;
import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.LeafNode;
import it.bio.tree.node.Node;

public class Tree extends AbstractTree {
	
	public Tree(String text) {
		super(text);
		
		for (int i = 0; i < text.length(); i++) {
			System.out.println("i=" + i);
			for (int j = 1; j < i + 2; j++) {
				final String suffix = text.substring(j - 1, i);
				final char phase = text.charAt(i);
				
				processCase(suffix, phase, String.valueOf(j));
			}
		}	
	}

	private void processCase(final String suffix, final char phase, final String jS) {
		final StringBuffer sb = new StringBuffer("\tj=" + jS + " -> if exist edge [" + suffix + "] ? add [" + phase + "] ... case (");
		final Node find = this.root.existPath(suffix);
				
		if (find.isLeaf() && find.getLabel().equals(jS)) {
			sb.append(1);
			new FirstCase((LeafNode)find, phase);
		} else if (find.isInternal()) {
			final String phaseS = String.valueOf(phase);
			final Node find2;
			
			if (find.isImplicit()) {
				find2 = ((EdgeNode)find).existPath(suffix, phaseS);
				
				if (find2 == null) {
					sb.append("2+");
					new SecondCaseImplicit((EdgeNode) find, suffix, jS, phase);
				}
			} else {
				find2 = find.existPath(phaseS);
				
				if (find2 == null) {
					sb.append(2);
					new SecondCase(find, jS, phase);
				}
			}			
			
			if (find2 != null) {
				sb.append(3);				
			}
		}
		
		sb.append(")");
		System.out.println(sb.toString());
	}
}
