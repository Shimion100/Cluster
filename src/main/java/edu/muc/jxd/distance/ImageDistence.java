package edu.muc.jxd.distance;

import edu.muc.jxd.item.Item;

public class ImageDistence implements DistenceInter {

	@Override
	public <T extends Number> int getDistence(Item<T> a, Item<T> b) {
		T [] dataA = (T[]) a.getData();
		T [] dataB = (T[]) b.getData();
		int distance = 0;
		for (int i = 0; i < dataB.length; i++) {
			int ta = dataA[i].intValue();
			int tb =dataB[i].intValue();
			distance = distance + ( ta - tb);
		}
		return distance;
	}
}
