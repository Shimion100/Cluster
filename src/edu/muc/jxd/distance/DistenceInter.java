package edu.muc.jxd.distance;

import edu.muc.jxd.item.ItemInter;

/**
 * 计算距离的接口
 * @author Simon
 *
 */
public interface DistenceInter {

	/**
	 * 计算距离的
	 * @param a
	 * @param b
	 * @return
	 */
	public <T extends ItemInter<T>> T getDistence(ItemInter<T> a, ItemInter<T> b);

}
