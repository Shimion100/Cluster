package edu.muc.jxd.cluster;

import java.util.List;

import edu.muc.jxd.distance.DistenceInter;
import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.vo.Delta;
import edu.muc.jxd.vo.P;

public class Cluster {

	private List<ImageItemVector<Number>> itemList;

	/**
	 * 用来计算小兵的肉
	 */
	private P p;


	/**
	 * 用来计算帮主之间距离的德尔塔。
	 */
	private Delta delta;

	/**
	 * 构造函数
	 * 
	 * @param items
	 */
	public Cluster(List<ImageItemVector<Number>> items, DistenceInter distance, int dc) {
		this.itemList = items;
		/*
		 * 初始化 p和Delta。
		 */
		this.p = new P(items.size(), items, distance);
		this.delta = new Delta(items.size(), this.p);
	}


	public List<ImageItemVector<Number>> getItemList() {
		return itemList;
	}

	public void setItemList(List<ImageItemVector<Number>> itemList) {
		this.itemList = itemList;
	}

	public P getP() {
		return p;
	}

	public void setP(P p) {
		this.p = p;
	}


	public Delta getDelta() {
		return delta;
	}

	public void setDelta(Delta delta) {
		this.delta = delta;
	}

}
