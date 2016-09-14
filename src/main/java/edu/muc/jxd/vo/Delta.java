package edu.muc.jxd.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Delta {

	private P p;
	
	private int[] value;

	public Delta(int length, P p) {
		this.value = new int[length];
		this.p = p;
		this.initDelta();
	}

	public void initDelta() {
		for (int i = 0; i <  this.value.length; i++){
			int pValue = this.p.getP()[i];
			List<Integer> Ps = new ArrayList<>();
			/*
			 * 求pi < pj
			 */
			for (int j = 0; j < this.p.getP().length; j++) {
				if (pValue < this.p.getP()[j]) {
					Ps.add(this.p.getP()[j]);
				}
			}
			/*
			 *求Min(pj)
			 */
			if (Ps.size() > 0 ) {
				Collections.sort(Ps);	
				this.value[i] = Ps.get(0);
			}else {
				this.value[i] =0 ;
			}
		}
		
	}
	
	
	public int getI(int i) {
		if (i >= 0 && i < this.value.length) {
			return this.value[i];
		} else {
			return -1;
		}
	}

	public P getP() {
		return p;
	}

	public void setP(P p) {
		this.p = p;
	}

	public int[] getValue() {
		return value;
	}

	public void setValue(int[] value) {
		this.value = value;
	}
	
	
}
