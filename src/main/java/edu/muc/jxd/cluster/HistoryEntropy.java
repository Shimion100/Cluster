package edu.muc.jxd.cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.muc.jxd.vo.PDelta;

public class HistoryEntropy {

	/**
	 * 所有的点数
	 */
	private int numberOfPoints;

	/**
	 * 窗口大小，用來計算entropy
	 */
	private int windowSize = 7;

	/**
	 * 聚类结果，主要是用于获得P和Delta
	 */
	private Cluster cluster;

	/**
	 * 使用pv进行排序
	 */
	private List<PDelta> pDeltaList;

	/**
	 * 已经在结果中的点
	 */
	private HashMap<Integer, List<Number>> tempPoints = new HashMap<>();

	/**
	 * 已经在结果中的点
	 */
	private HashMap<Integer, List<Number>> allPoints = new HashMap<>();

	/**
	 * 已经处理过的点
	 */
	private Set<Number> tempProcessePoints;

	/**
	 * 已经处理过的点
	 */
	private Set<Number> processePoints;

	/**
	 * 已经找到的中心点
	 */

	private List<Integer> points = new ArrayList<>();

	private HashMap<Integer, Double> clusterCenter;

	public HistoryEntropy(Cluster cluster) {
		this.cluster = cluster;
		this.numberOfPoints = this.cluster.getP().getP().length;
		System.out.println("共有" + this.numberOfPoints + "个点");
		this.pDeltaList = new ArrayList<>();

		/*
		 * 初始化pv
		 */
		for (int i = 0; i < this.numberOfPoints; i++) {
			Integer aPv = this.cluster.getP().getP()[i] * this.cluster.getDelta().getValue()[i];
			PDelta aPDelta = new PDelta(i, aPv);
			this.pDeltaList.add(aPDelta);
			Collections.sort(this.pDeltaList);
		}

		// 初始化完成,飯轉
		List<PDelta> reversePD = new ArrayList<>();
		for (int i = 0; i < this.pDeltaList.size(); i++) {
			reversePD.add(this.pDeltaList.get(this.pDeltaList.size() - i - 1));
		}
		this.pDeltaList = reversePD;

		/*
		 * for (int i = 0; i < this.pDeltaList.size(); i++) {
		 * System.out.println(this.pDeltaList.get(i)); }
		 */

		this.clusterCenter = new HashMap<>();
		this.tempPoints = new HashMap<>();
		this.processePoints = new HashSet<>();
		this.tempProcessePoints = new HashSet<>();
		this.tempPoints = new HashMap<>();
		this.allPoints = new HashMap<>();

		this.initAllPoints();
	}

	public void initAllPoints() {
		/*
		 * 初始化allPoints
		 */
		for (int i = 0; i < this.numberOfPoints; i++) {
			List<Number> aList = new ArrayList<>();
			aList.add(i);
			this.allPoints.put(i, aList);
		}

		double H = this.computeEntropy();
		System.out.println("初始化之后的熵:" + H);
		this.clusterCenter.put(-1, H);
	}

	public void printAllPoints(HashMap<Integer, List<Number>> points, String title) {
		/*
		 * 当前要处理的List，大小为window
		 */
		/*
		 * System.out.println(title + ":"); for (Integer key : points.keySet())
		 * { List<Number> list = points.get(key); System.out.print(" " + key +
		 * ": "); for (Number number : list) { System.out.print(number + " "); }
		 * System.out.println(); }
		 * 
		 */
	}

	/**
	 * 计算当前系统的熵
	 * 
	 * @return
	 */

	public double computeEntropy() {
		/*
		 * 开始计算熵
		 */
		double H = 0.0;

		double sum = 0.0;

		for (Integer key : this.allPoints.keySet()) {
			sum = sum + this.allPoints.get(key).size();
		}
		System.out.println("當前確定的狀態集合中共有" + sum + "个点");
		for (Integer aKey : this.allPoints.keySet()) {
			int numberOfKey = this.allPoints.get(aKey).size();
			double gailv = numberOfKey / sum;
			H = H + (gailv * Math.log(gailv));
		}

		H = -H;

		return H;
	}

	/**
	 * 计算当前系统的熵
	 * 
	 * @return
	 */
	public double computeTempEntropy() {
		/*
		 * 开始计算熵
		 */
		double H = 0.0;

		double sum = 0.0;

		for (Integer key : this.tempPoints.keySet()) {
			sum = sum + this.tempPoints.get(key).size();
		}
		System.out.println("集合中共有" + sum + "个点");
		for (Integer aKey : this.tempPoints.keySet()) {
			int numberOfKey = this.tempPoints.get(aKey).size();
			double gailv = numberOfKey / sum;
			H = H + (gailv * Math.log(gailv));
		}

		H = -H;

		/*
		 * int n = this.tempPoints.keySet().size();
		 * 
		 * double h = 1.0 / n; double xxx = -h * Math.log(h); H = n * xxx;
		 * System.out.println("开始计算熵 n"+n+" h"+h+" 單個熵"+xxx+ " 熵"+H);
		 */
		return H;
	}

	/**
	 * 计算熵，将这个点加入之后熵的变化情况
	 * 
	 * @param aPoints
	 */
	public Double getEntropy(PDelta aPoint) {
		this.tempReSetAllPoints(aPoint);
		return this.computeTempEntropy();
	}

	public void copyPoints() {
		this.tempProcessePoints = new HashSet<>();
		for (Number integer : this.processePoints) {
			this.tempProcessePoints.add(integer);
		}

		this.tempPoints = new HashMap<>();

		for (Integer key : this.allPoints.keySet()) {
			List<Number> aList = new ArrayList<>();
			for (Number number : this.allPoints.get(key)) {
				aList.add(number);
			}
			this.tempPoints.put(key, aList);
		}
	}

	public void tempReSetAllPoints(PDelta aPoint) {

		this.copyPoints();

		// 当前点的ID
		int id = aPoint.getID();
		// 当前点下属的点
		List<Number> pListOfCurrentPoint = this.cluster.getP().getpDetail().get(id);

		System.out.print("当前处理的点" + id + "点的List大小" + pListOfCurrentPoint.size() + ": ");
		for (Number number : pListOfCurrentPoint) {
			System.out.print(number + " ");
		}

		System.out.println();

		// 移走单独的点
		for (int i = 0; i < pListOfCurrentPoint.size(); i++) {
			Number aID = pListOfCurrentPoint.get(i);
			if (this.tempPoints.get(aID) != null && this.tempPoints.get(aID).size() == 1
					&& this.tempPoints.get(aID).get(0) == aID) {
				this.tempPoints.remove(aID);
				this.tempProcessePoints.remove(aID);
			}
		}

		this.printAllPoints(this.tempPoints, "移走单独的点tempPoints");

		/*
		 * 将非单独的点中的已经有的点去掉
		 */
		List<Number> usedPoints = new ArrayList<>();
		for (Integer centerPoint : this.tempPoints.keySet()) {
			List<Number> pointsOfThisCenterPoint = this.tempPoints.get(centerPoint);

			for (Number i : pointsOfThisCenterPoint) {
				if (pListOfCurrentPoint.contains(i)) {
					usedPoints.add(i);
				}
			}
		}
		pListOfCurrentPoint.removeAll(usedPoints);

		this.printAllPoints(this.tempPoints, "将非单独的点中的已经有的点去掉tempPoints");

		System.out.print("剩下的点的List");
		for (Number number : pListOfCurrentPoint) {
			System.out.print(number + " ");
		}

		// 已经处理的点
		this.tempProcessePoints.addAll(pListOfCurrentPoint);
		this.tempProcessePoints.add(id);

		if (pListOfCurrentPoint.size() > 0) {
			// 结果
			this.tempPoints.put(id, pListOfCurrentPoint);
		}

		System.out.println("countSize" + pListOfCurrentPoint.size());
		this.printAllPoints(this.tempPoints, "处理完成tempPoints");

	}

	public void reSetAllPoints(PDelta aPoint) {

		// 当前点的ID
		int id = aPoint.getID();
		// 当前点下属的点
		List<Number> pListOfCurrentPoint = this.cluster.getP().getpDetail().get(id);

		System.out.print("开始修改系统状态,点" + id + "点的List大小" + pListOfCurrentPoint.size() + ": ");
		for (Number number : pListOfCurrentPoint) {
			System.out.print(number + " ");
		}

		System.out.println();

		// 移走单独的点
		for (int i = 0; i < pListOfCurrentPoint.size(); i++) {
			Number aID = pListOfCurrentPoint.get(i);
			if (this.allPoints.get(aID) != null && this.allPoints.get(aID).size() == 1
					&& this.allPoints.get(aID).get(0) == aID) {
				this.allPoints.remove(aID);
				this.processePoints.remove(aID);
			}
		}

		this.printAllPoints(this.allPoints, "移走单独的点allPoints");

		/*
		 * 将非单独的点中的已经有的点去掉
		 */
		List<Number> usedPoints = new ArrayList<>();
		for (Integer centerPoint : this.allPoints.keySet()) {
			List<Number> pointsOfThisCenterPoint = this.allPoints.get(centerPoint);

			for (Number i : pointsOfThisCenterPoint) {
				if (pListOfCurrentPoint.contains(i)) {
					usedPoints.add(i);
				}
			}
		}
		pListOfCurrentPoint.removeAll(usedPoints);

		this.printAllPoints(this.allPoints, "将非单独的点中的已经有的点去掉allPoints");

		System.out.print("剩下的点的List");
		for (Number number : pListOfCurrentPoint) {
			System.out.print(number + " ");
		}

		// 已经处理的点
		this.processePoints.addAll(pListOfCurrentPoint);
		this.processePoints.add(id);

		if (pListOfCurrentPoint.size() > 0) {
			// 结果
			this.allPoints.put(id, pListOfCurrentPoint);
		}

		System.out.println("countSize" + pListOfCurrentPoint.size());
		this.printAllPoints(this.allPoints, "处理完成allPoints");

	}

	/**
	 * 处理下一个点
	 */
	public void addPoints() {

		this.printAllPoints(this.allPoints, "AllPoints");

		List<PDelta> processingPoints = new ArrayList<>();
		for (int i = 0; i < this.windowSize; i++) {
			int index = i;
			if (index >= this.pDeltaList.size()) {
				continue;
			}
			PDelta pDelta = this.pDeltaList.get(index);
			processingPoints.add(pDelta);
		}

		System.out.println("processingPoints:");
		for (int i = 0; i < processingPoints.size(); i++) {
			System.out.print(" " + processingPoints.get(i));
			System.out.println();
		}
		System.out.println();

		/*
		 * 计算每一个点的平均熵
		 */
		HashMap<Integer, Double> processingEntropy = new HashMap<Integer, Double>();

		for (PDelta pDelta : processingPoints) {
			double entropy = this.getEntropy(pDelta);
			processingEntropy.put(pDelta.getID(), entropy);
		}

		System.out.println("求最小熵---------------------------------");

		for (Integer enIndex : processingEntropy.keySet()) {
			// System.out.println("key " + enIndex + " ---" +
			// processingEntropy.get(enIndex));
		}
		System.out.println("---------------------------------");

		/*
		 * 获得最小的平均熵的点下标。
		 */
		Integer theIndexOFthePointWhichEntropyIsMini = -1;
		Double max = 100000000000.0;

		for (Integer enIndex : processingEntropy.keySet()) {
			Double entropy = processingEntropy.get(enIndex);
			if (entropy <= max) {
				max = entropy;
				theIndexOFthePointWhichEntropyIsMini = enIndex;
			}
		}

		System.out.println("當前最小熵的下標：" + theIndexOFthePointWhichEntropyIsMini);

		/**
		 * 將此点从待处理的点中移除，已经处理完成。确定，是贪心算法
		 */
		int index = -1;
		for (int i = 0; i < this.pDeltaList.size(); i++) {
			if (theIndexOFthePointWhichEntropyIsMini == this.pDeltaList.get(i).getID()) {
				index = i;
			}
		}
		PDelta aPDeltaPoint = pDeltaList.get(index);
		this.pDeltaList.remove(index);

		/*
		 * 更改整个系统的状态，将此点p下的所有点都加入已处理过的点的集合中，修改熵，更新记录熵变化的集合。
		 */

		/**
		 * 将此点加入中心点
		 */

		this.reSetAllPoints(aPDeltaPoint);
		this.clusterCenter.put(theIndexOFthePointWhichEntropyIsMini, this.computeEntropy());
		this.points.add(theIndexOFthePointWhichEntropyIsMini);

	}

	/**
	 * 构造完成之后运行此函数。
	 */
	public void run() {

		while (this.processePoints.size() < this.numberOfPoints) {
			this.addPoints();

			System.err.println("已经处理了" + processePoints.size() + "个点:");
			/*
			 * for (Number poInteger : this.processePoints) { System.out.print(
			 * " " + poInteger); }
			 */
		}

		System.out.println("\n所有的點都處理完成：------------");
		System.out.println("添加的点数是：" + this.clusterCenter.size() + "添加顺序是：");
		for (Integer integer : clusterCenter.keySet()) {
			System.out.println(" " + integer + " " + clusterCenter.get(integer).doubleValue());
		}
		System.out.println("添加点的顺序是：");

		for (Integer integer : this.points) {
			System.out.println(integer);
		}

	}

}
