package it.bio.tree;

import it.bio.builder.PhasesCounter;
import it.bio.cases.AlgorithmCases;
import it.bio.cases.FirstCase;
import it.bio.cases.SecondCase;
import it.bio.cases.SecondCaseImplicit;
import it.bio.log.MyBuffer;
import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.ExplicitNode;
import it.bio.tree.node.LeafNode;
import it.bio.tree.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class ImprovedTree extends AbstractTree {
		
	private final PhasesCounter phasesCounter = new PhasesCounter();
	private final Map<String, Node> explicitNodes = new HashMap<String, Node>();
	private final MyBuffer sb = new MyBuffer();
	
	private Node previous;

	public ImprovedTree(String text) {
		super(text);		
		
		explicitNodes.put(StringUtils.EMPTY, this.root);

		for (int i = 0; i < text.length(); i++) {
			System.out.println("i=" + i);
			for (int j = 1; j < i + 2; j++) {
				String suffix = text.substring(j - 1, i);
				final char phase = text.charAt(i);				
				
				sb.append("\tj=" + j + " -> if exist edge [" + suffix + "] ? add [" + phase + "] ... case " + O);
				
				if (j == 1 && phasesCounter.getLeaf(1) != null) {
					appendToLeaf(null, phase, 1); //il primo prefisso è sempre il più lungo
					sb.append("f");
				} else if (j <= phasesCounter.getCount(i-1).intValue()){
					appendToLeaf(null, phase, j); //i primi passi della fase successiva ...
					sb.append("p");
				} else {
					Node current = this.root;
					
					if (previous != null && previous.isExplicit()) {
						final ExplicitNode en = (ExplicitNode)previous;
						
						if (!en.hasSL()) {
							updateSL();
						}		
						
						if (en.hasSL()) {
							current = en.getSL();
							
							if (StringUtils.isNotBlank(suffix)) {
								suffix = suffix.substring(1); //rimuovo il 1° char xè seguirò un SL	
							}
						}
					}
					
					processCase(suffix, phase, String.valueOf(j), current);
				}
					
				sb.append(C);				
				System.out.println(sb.toString());
				
				updateMax(i, j);
				
				boolean shouldBreak = sb.indexOf(O + AlgorithmCases.THREE.getNumber() + C)  != -1 && j <= i;				
				sb.delete(0, sb.length());
				
				if (shouldBreak) { //se viene eseguito il caso 3 ... skip other
					break;
				}
			}							
		}	
		
		System.out.println(sb.printStatisticsInformation());
	}
	
	private void updateMax(int i, int j) {
		if (
			sb.indexOf("(1)") > -1 || 
			sb.indexOf("(1p)") > -1 || 
			sb.indexOf("(1f)") > -1 ||
			sb.indexOf(O + AlgorithmCases.TWO.getNumber() + C) > -1 || 
			sb.indexOf(O + AlgorithmCases.TWO_PLUS.getNumber() + C) > -1
		) {
			phasesCounter.updateMax(i, j);
		}
	}

	private void processCase(final String suffix, final char phase, final String jS, final Node p) {
		Node find = p.existPath(suffix);
		
		if (find == null && p.isExplicit()) {
			find = p;
		}
		
		if (find.isLeaf() && find.getLabel().equals(jS)) {
			appendToLeaf(find, phase, null);
		} else if (find.isInternal()) {
			final String phaseS = String.valueOf(phase);
			final Node find2;
			
			if (find.isImplicit()) {
				find2 = ((EdgeNode)find).existPath(suffix, phaseS);
				
				if (find2 == null) {
					sb.append(AlgorithmCases.TWO_PLUS.getNumber());
					updatePrevious(
						new SecondCaseImplicit((EdgeNode) find, suffix, jS, phase).getLeaf()
					);
				}
			} else {
				find2 = find.existPath(phaseS);
				
				if (find2 == null) {
					sb.append(AlgorithmCases.TWO.getNumber());
					updatePrevious(
						new SecondCase(find, jS, phase).getLeaf()
					);
				}
			}			
			
			if (find2 != null) {
				sb.append(AlgorithmCases.THREE.getNumber());
				previous = null;
			}				
		}		
	}
	
	private void appendToLeaf(final Node n, final char phase, final Integer i) {
		sb.append("1");
		previous = null;
		
		if (n != null) {
			new FirstCase((LeafNode)n, phase);
		} else {
			new FirstCase(phasesCounter.getLeaf(i), phase);			
		}		
	}	

	private void updateSL() {
		final ExplicitNode en = (ExplicitNode)previous;
		final String label = en.getFullLabel().toString();
		
		for (Entry<String, Node> entry : explicitNodes.entrySet()) {
			final String key = entry.getKey();
			if (key.length() + 1 == label.length() && label.endsWith(key)) {
				en.setSL(explicitNodes.get(key));
				break;
			}
		}		
	}

	private void updatePrevious(final Node n) {
		previous = n.getPreviousNode().getPreviousNode();			
		explicitNodes.put(previous.getFullLabel().toString(), previous);	
		
		phasesCounter.addLeafNode((LeafNode)n);
	}
}
