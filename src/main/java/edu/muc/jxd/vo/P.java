package edu.muc.jxd.vo;

public class P {

	private int[][] value;

	public P(int length) {
		this.value = new int[length][length];
	}

	public int get(int i, int j) {
		if (i >= value.length || j >= value[i].length) {
			return -1;
		} else {
			return this.value[i][j];
		}
	}
	
	public void set(int i, int j, int value) {
		if (i >= this.value.length || j >= this.value[i].length || i < 0 || j < 0) {
			return ;
		} else {
			this.value[i][j] = value;
		}
	}
}
