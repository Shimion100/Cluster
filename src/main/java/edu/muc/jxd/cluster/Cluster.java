package edu.muc.jxd.cluster;

import java.util.List;

import javax.imageio.ImageTranscoder;

import edu.muc.jxd.distance.DistenceInter;
import edu.muc.jxd.item.Item;
import edu.muc.jxd.vo.Delta;
import edu.muc.jxd.vo.P;

public class Cluster {

	private List<Item<Number>> itemList;

	/**
	 * 用来计算小兵的肉
	 */
	private P p;

	/**
	 * 默认距离dc
	 */
	private int dc = 0;

	/**
	 * 用来计算帮主之间距离的德尔塔。
	 */
	private Delta delta;

	/**
	 * 构造函数
	 * @param items
	 */
	public Cluster(List<Item<Number>> items) {
		this.itemList = items;
		this.p = new P(items.size());
		this.delta = new Delta(items.size());
		
	}
	
	/**
	 * 初始化P
	 */
	public void initP() {
		for(int i = 0; i < this.itemList.size(); i++) {
			for (int j = 0; j < i; j++) {
				
			}
		}
	}

	/**
	 * 计算从Itemi到Itemj之间的距离.
	 * @param i
	 * @param j
	 * @param distance
	 * @return
	 */
	public int diff(int i, int j, DistenceInter distance) {
		return distance.getDistence(itemList.get(i), itemList.get(j));
	}

	public List<Item<Number>> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item<Number>> itemList) {
		this.itemList = itemList;
	}

	public P getP() {
		return p;
	}

	public void setP(P p) {
		this.p = p;
	}

	public int getDc() {
		return dc;
	}

	public void setDc(int dc) {
		this.dc = dc;
	}

	public Delta getDelta() {
		return delta;
	}

	public void setDelta(Delta delta) {
		this.delta = delta;
	}

}
