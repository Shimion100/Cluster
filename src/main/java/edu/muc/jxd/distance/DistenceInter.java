package edu.muc.jxd.distance;

import edu.muc.jxd.item.Item;

/**
 * 计算距离的接口
 * @author Simon
 *
 */
public interface DistenceInter {

	/**
	 * 计算距离的
	 * @param <T>
	 * @param <T>
	 * @param a 第一个Item
	 * @param b 第二个Item
	 * @return 两个Item之间的距离
	 */
	public  <T extends Number> int getDistence(Item<T> a, Item<T> b);

}
