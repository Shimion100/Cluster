package edu.muc.jxd.mian;

import java.io.File;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.jxd.distance.DistenceInter;
import edu.muc.jxd.distance.SimpleDistance;
import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.tools.ToImageVec;

public class Main {

	public static void main(String[] args) {

		DistenceInter distance = new SimpleDistance();
		// DistenceInter distance = new ImageDistence();
		String path = "3";
		// String path = "test";
		// String path = "all";
		String filePath = path + File.separator;
		List<ImageItemVector<Number>> itemList = ToImageVec.getImageVec(filePath + "image.xml");

		System.out.println("数据--------------------------------------------------------------------------------");
		for (ImageItemVector<Number> imageItemVector : itemList) {
			System.out.println(imageItemVector.printImage());

		}

		System.out.println("开始聚类-------------------------------------------------------------------------------");
		Cluster cluster = new Cluster(itemList, distance, 1, 784, 1);
		// System.out.println("ItemList");

		System.out.println("保存结果-------------------------------------------------------------------------------");
		System.out.println(cluster.getP().toString());
		cluster.getP().writetoFile(new File("E:\\project\\cluster\\" + filePath + "p.txt"));
		System.out.println(cluster.getDelta().toString());
		cluster.getDelta().writetoFile(new File("E:\\project\\cluster\\" + filePath + "delta.txt"));

		System.out.println("---------------------------------------------------");
		System.out.println("dc=" + cluster.getP().getDc());
		cluster.printResult();

	}
}
