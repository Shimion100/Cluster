package edu.muc.jxd.distance;

import org.apache.log4j.Logger;

import edu.muc.jxd.item.ImageItemVector;

public class ImageDistence implements DistenceInter {

	private Logger logger=Logger.getLogger(ImageDistence.class.getName());
	@Override
	public <T extends Number> int getDistence(ImageItemVector<T> a, ImageItemVector<T> b) {
		//logger.debug("Distance"+a.getId()+"-"+b.getId());	
		T [] dataA = (T[]) a.getData();
		T [] dataB = (T[]) b.getData();
		int distance = 0;
		for (int i = 0; i < dataB.length; i++) {
			int ta = dataA[i].intValue();
			int tb =dataB[i].intValue();
			distance = distance + Math.abs(( ta - tb));
		}
		return distance;
	}
}
