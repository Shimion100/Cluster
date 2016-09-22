package edu.muc.jxd.vo;

public class Entropy implements Comparable<Entropy> {

	public int dc;

	public double entropy;

	public Entropy() {
	}

	public Entropy(int dc, double entropy) {
		this.dc = dc;
		this.entropy = entropy;
	}

	@Override
	public int compareTo(Entropy o) {
		// TODO Auto-generated method stub
		return (int) ((this.entropy - o.entropy) * 1000);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " dc =" + this.dc + " entropy = " + this.entropy +" ";
	}

}
