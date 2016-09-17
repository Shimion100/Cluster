package edu.muc.jxd.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.item.ImageItemXml;
import edu.muc.jxd.item.ImageItemXmlElement;

public class ToImageVec {

	public static List<ImageItemVector<Number>> getImageVec() {

		List<ImageItemVector<Number>> itemList = new ArrayList<ImageItemVector<Number>>();

		ImageItemXml object = (ImageItemXml) XmlUtil.convertXmlFileToObject(ImageItemXml.class,
				PathKit.getRootClassPath() + "\\image.xml");
		Iterator<ImageItemXmlElement> images = object.getImagesData().iterator();
		while (images.hasNext()) {
			ImageItemXmlElement imageItemXmlElement = images.next();
			itemList.add(imageItemXmlElement.getDataToImageItemVector());
		}
		return itemList;
		
		
	}
	
	public static void main(String[] args) {
		List<ImageItemVector<Number>> itemList  = ToImageVec.getImageVec();
		
		for (ImageItemVector<Number> imageItemVector : itemList) {
			System.out.println(imageItemVector.toString());
		}
	}

}
