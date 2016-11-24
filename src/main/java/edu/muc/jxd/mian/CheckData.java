package edu.muc.jxd.mian;

import java.io.File;
import java.util.List;

import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.tools.ToImageVec;

public class CheckData {
	public static void main(String[] args) {

		// DistenceInter distance = new ImageDistence();
		String path = "3";
		// String path = "test";
		// String path = "all";
		String filePath = path + File.separator;
		List<ImageItemVector<Number>> itemList = ToImageVec.getImageVec(filePath + "image.xml");

		for (ImageItemVector<Number> imageItemVector : itemList) {
			if (imageItemVector != null) {
				String x = imageItemVector.toString();
				x = x.replace("0", " ");
				System.out.println(x);
			}

		}

	}
}
