package bai2;

import java.util.*;

public class HuffmanCode {
	

	private HuffmanNode root;
	private PriorityQueue<HuffmanNode> queue;
	private HashMap<Character, Integer> map;
	private int n;
	
	public HuffmanCode(int n, HashMap<Character, Integer> map) {
		this.n = n;
		this.map = map;
		createQueue();
		createTree();
	}
	
	
	private void createQueue() {
		queue = new PriorityQueue<>(n, new Comparator<HuffmanNode>() {

			@Override
			public int compare(HuffmanNode n1, HuffmanNode n2) {
				// TODO Auto-generated method stub
				return n1.getValue() - n2.getValue();
			}
			
		});
	}
	
	private void createTree() {
		Set<Character> keys = map.keySet();
		Iterator<Character> i = keys.iterator();
		while (i.hasNext()) {
			char c = i.next();
			HuffmanNode n = new HuffmanNode();
			n.setName(c);
			n.setValue(map.get(c));
			n.setLeftChild(null);
			n.setLeftChild(null);
			
			queue.add(n);
		}
		
		root = null;
		
		while (queue.size() > 1) {
			
			HuffmanNode x = queue.peek();
			queue.poll();
			
			HuffmanNode y = queue.peek();
			queue.poll();
			
			HuffmanNode f = new HuffmanNode();
			
			f.setValue(x.getValue() + y.getValue());
			f.setName('-');
			
			f.setLeftChild(x);
			f.setRightChild(y);
			
			root = f;
			
			queue.add(f);
		}
	}
	
	private void printCode(HuffmanNode node, String str) {
		if (node.getLeftChild() == null && node.getRightChild() == null && node.getName() != '-') {
			System.out.println(node.getName() + ": " + str);
			return;
		}
		
		printCode(node.getLeftChild(), str + "0");
		printCode(node.getRightChild(), str + "1");
	}
	
	public void displayResult() {
		System.out.println("\nMa hoa Huffman");
		printCode(root, "");
	}
}
