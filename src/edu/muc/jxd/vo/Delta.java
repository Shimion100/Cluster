package edu.muc.jxd.vo;

public class Delta {

	private int[] value;

	public Delta(int length) {
		this.value = new int[length];

	}

	public int getI(int i) {
		if (i >= 0 && i < this.value.length) {
			return this.value[i];
		} else {
			return -1;
		}
	}
}
