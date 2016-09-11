package edu.muc.jxd.Main;

import java.util.ArrayList;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.jxd.distance.ImageDistence;
import edu.muc.jxd.item.Item;

public class Main {

	public static void main(String[] args) {
		Item<Number> item1 = new Item<>();
		Integer [] data1 = {0,1,2,3,4,5,6,7,8,9};
		item1.setData(data1);
		Integer [] data2 = {0,1,2,3,4,5,6,7,8,9};
		Item<Number> item2 = new Item<>();
		item2.setData(data2);
		
		List<Item<Number>> list = new ArrayList<>();
		list.add(item1);
		list.add(item2);
		Cluster cluster = new Cluster(list);
		
		ImageDistence distance = new ImageDistence();
		
		System.out.println(cluster.diff(0, 1, distance));
		
		
	}
}
