package edu.muc.jxd.mian;

import java.io.File;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.jxd.distance.DistenceInter;
import edu.muc.jxd.distance.MixDistance;
import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.tools.ToImageVec;

public class MainTest {

	public static void main(String[] args) {

		DistenceInter distance = new MixDistance();
		//DistenceInter distance = new ImageDistence();
		List<ImageItemVector<Number>> itemList = ToImageVec.getImageVec();
		Cluster cluster = new Cluster(itemList, distance);
		System.out.println("ItemList");
		for (ImageItemVector<Number> imageItemVector : itemList) {
			System.out.println(imageItemVector);
		}
		System.out.println("p");
		System.out.println(cluster.getP().toString());
		cluster.getP().writetoFile(new File("E:\\project\\cluster\\p.txt"));
		System.out.println("Delta");
		System.out.println(cluster.getDelta().toString());
		cluster.getDelta().writetoFile(new File("E:\\project\\cluster\\delta.txt"));
		System.out.println("dc="+cluster.getP().getDc());
		cluster.printResult();
	
	}
}
