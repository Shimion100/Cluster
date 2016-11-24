package edu.muc.jxd.mian;

import java.io.File;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.jxd.distance.DistenceInter;
import edu.muc.jxd.distance.ImageDistence;
import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.tools.ToImageVec;

public class SecondCluster {
	
	public static void main(String[] args) {
		//DistenceInter distance = new MixDistance();
		DistenceInter distance = new ImageDistence();
		String path = "second3";
		String filePath = path + File.separator;

		List<ImageItemVector<Number>> itemList = ToImageVec.inItemVectors();
		System.out.println(itemList.size());
		Cluster cluster = new Cluster(itemList, distance, 1, 784, 1);
		System.out.println(cluster.getP().toString());
		cluster.getP().writetoFile(new File("E:\\project\\cluster\\" + filePath + "p.txt"));
		System.out.println(cluster.getDelta().toString());
		cluster.getDelta().writetoFile(new File("E:\\project\\cluster\\" + filePath + "delta.txt"));
		System.out.println("dc=" + cluster.getP().getDc());
		cluster.printResult();
	}

	


}
