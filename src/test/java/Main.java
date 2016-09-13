import java.util.ArrayList;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.jxd.distance.ImageDistence;
import edu.muc.jxd.item.ImageItemVector;
import edu.muc.jxd.item.Item;
import org.junit.Test;

public class Main {

	@Test
	public void domain() {
		ImageItemVector<Number> item1 = new ImageItemVector<>();
		Integer [] data1 = {0,1,2,3,4,5,6,7,8,9};
		item1.setData(data1);
		Integer [] data2 = {0,2,1,3,4,3,6,7,1,9};
		ImageItemVector<Number> item2 = new ImageItemVector<>();
		item2.setData(data2);
		
		List<ImageItemVector<Number>> list = new ArrayList<>();
		list.add(item1);
		list.add(item2);
				
		ImageDistence distance = new ImageDistence();
		Cluster cluster = new Cluster(list,distance);
		System.out.println(cluster.diff(0, 1, distance));
		
		
	}
}
