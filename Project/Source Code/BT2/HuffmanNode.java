package bai2;

public class HuffmanNode {
	private int value;
	private char name;
	private HuffmanNode leftChild;
	private HuffmanNode rightChild;
	
	public HuffmanNode() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public HuffmanNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(HuffmanNode leftChild) {
		this.leftChild = leftChild;
	}

	public HuffmanNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(HuffmanNode rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean isLeaf() {
		return this.getLeftChild() == null && this.getRightChild() == null;
	}
}
