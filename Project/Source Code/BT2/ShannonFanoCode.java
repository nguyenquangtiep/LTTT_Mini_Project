package bai2;

import java.util.*;

public class ShannonFanoCode {
	
	private int n;
	private HashMap<Character, Integer> map;
	private ShannonFanoNode[] nodeArr;
	private double perform;
	
	public ShannonFanoCode(int n, HashMap<Character, Integer> map) {
		this.n = n;
		this.map = map;
		nodeArr = new ShannonFanoNode[map.size()];
		createArr();
		sortArr();
		generateCode(0, nodeArr.length-1);
	}
	
	private void createArr() {
		Set<Character> set = map.keySet();
		Iterator<Character> i = set.iterator();
		int index = 0;
		while (i.hasNext()) {
			ShannonFanoNode node = new ShannonFanoNode();
			char name = i.next();
			node.setName(name);
			node.setValue(map.get(name));
			nodeArr[index] = node;
			index++;
		}
	}
	
	private void sortArr() {
		for (int i = 0; i < nodeArr.length - 1; i++) {
			for (int j = i + 1; j < nodeArr.length; j++) {
				if (nodeArr[i].getValue() < nodeArr[j].getValue())
				{
					ShannonFanoNode tmp = new ShannonFanoNode();
					
					tmp.setName(nodeArr[i].getName());
					tmp.setValue(nodeArr[i].getValue());
					
					nodeArr[i].setName(nodeArr[j].getName());
					nodeArr[i].setValue(nodeArr[j].getValue());
					
					nodeArr[j].setName(tmp.getName());
					nodeArr[j].setValue(tmp.getValue());
				}
			}
		}
	}
	
	private void generateCode(int l, int r) {
		if (l == r) return;
		int sumLeft = 0, sum = 0;
		int mid = l;
		for (int i = l; i <= r; i++) {
			sum += nodeArr[i].getValue();
		}
		int beforeDif = 0;
		int afterDif = sum;
		while (mid < r) {
			sumLeft += nodeArr[mid].getValue();
			sum -= nodeArr[mid].getValue();
			beforeDif = Math.abs(afterDif);
			afterDif = Math.abs(sum - sumLeft);
			if (afterDif >= beforeDif) {
				break;
			}
			mid++;
		}
		for (int i = l; i < mid; i++) {
			nodeArr[i].setCode(nodeArr[i].getCode() + "0");
		}
		for (int i = mid; i <= r; i++) {
			nodeArr[i].setCode(nodeArr[i].getCode() + "1");
		}
		generateCode(l, mid - 1);
		generateCode(mid, r);
	}
	
	public void printCode() {
		System.out.println("\nMa hoa Shannon Fano");
		for (int i = 0; i < nodeArr.length; i++) {
			System.out.println(nodeArr[i].getName() + ": " + nodeArr[i].getCode());
		}
	}
	
	public void showPerform() {
		double a = 0.0, b = 0.0;
		for (int i = 0; i < nodeArr.length; i++) {
			double p = (double)nodeArr[i].getValue() / n;
			a -= p * (Math.log(p) / Math.log(2));
			b += p * nodeArr[i].getCode().length();
		}
		perform = a/b;
		System.out.println("\nHieu suat: " + perform);
	}
	
	public void showRedundancy() {
		System.out.println("\nTinh du thua R = " + (1 - perform));
	}
}