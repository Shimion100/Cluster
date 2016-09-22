package edu.muc.jxd.vo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import edu.muc.jxd.distance.DistenceInter;
import edu.muc.jxd.distance.ImageDistence;
import edu.muc.jxd.item.ImageItemVector;

public class P {

	/**
	 * 数据的引用
	 */
	private List<ImageItemVector<Number>> itemList;

	/**
	 * 计算距离的函数
	 */
	private DistenceInter distence;

	/**
	 * 用来存储p的数组
	 */

	private int[] p;

	/**
	 * 距离
	 */

	private int dc;

	/**
	 * 为了计算这个dc，要遍历很多个dc，要将每次遍历结果存储于一个List,最终取list中的最优值。
	 *
	 */

	private List<Entropy> list4Dc;

	/**
	 * 距离矩阵
	 */
	private int[][] distanceMatrix;

	private Logger logger = Logger.getLogger(ImageDistence.class.getName());

	/**
	 * *************************************************************************
	 * *************************************** 构造方法
	 * 
	 * @param length
	 * @param itemList
	 * @param distence
	 */

	public P(int length, List<ImageItemVector<Number>> itemList, DistenceInter distence) {
		this.distanceMatrix = new int[length][length];
		this.p = new int[length];
		this.itemList = itemList;
		this.distence = distence;
		this.list4Dc = new ArrayList<Entropy>();
		this.optimizeDc(784, 2);
	}

	public void optimizeDc(int max, int step) {

		// 条件要改,
		for (int i = 1; i < max; i = i + step) {
			this.dc = i;
			logger.debug("dc = " + dc);
			this.initP();
			// 开始计算熵
			double z = 0.0;

			for (int j = 0; j < this.p.length; j++) {
				z = z + this.p[j];
			}

			// 熵
			double H = 0.0;
			// 概率
			double p = 0.0;
			for (int j = 0; j < this.p.length; j++) {
				p = (this.p[j] / z);
				H = H + p * Math.log(p);
			}
			H = -H;
			Entropy e = new Entropy(this.dc, H);
			this.list4Dc.add(e);
		}

		/*
		 * 取熵最小的dc值作为最终的dc值。
		 */
		Collections.sort(this.list4Dc);
		Entropy entropy = this.list4Dc.get(0);
		this.dc = entropy.dc;
		this.initP();
	}

	/**
	 * *************************************************************************
	 * *************************************** 初始化P，包括初始化距离矩阵和p
	 */
	public void initP() {
		this.computeDistanceMatrix();
		this.printDistanceMatrix();
		this.computeP();
		this.printP();
	}

	/**
	 * 將距离矩阵输出
	 */

	public void printDistanceMatrix() {
		StringBuilder builder = new StringBuilder();
		builder.append("DistanceMatrix：\n");
		for (int i = 0; i < this.distanceMatrix.length; i++) {
			for (int j = 0; j < this.distanceMatrix[i].length; j++) {
				builder.append(this.distanceMatrix[i][j] + " ");
			}
			builder.append("\n");
		}
		System.out.println(builder.toString());
	}

	/**
	 * 输出p
	 */
	public void printP() {
		StringBuilder builder = new StringBuilder();
		builder.append("p：\n");
		for (int i = 0; i < this.p.length; i++) {
			builder.append(this.p[i] + " ");
		}
		builder.append("\n");
		System.out.println(builder.toString());
	}

	/**
	 * 计算Distance
	 */
	public void computeDistanceMatrix() {
		/*
		 * 计算Distance
		 */
		for (int i = 0; i < this.itemList.size(); i++) {
			for (int j = i + 1; j < this.itemList.size(); j++) {
				int dis = diff(i, j);
				this.setValue(i, j, dis);
				this.setValue(j, i, dis);
			}
			// 设置对角线
			this.setValue(i, i, 0);
		}
	}

	/**
	 * 通过Distance来计算p
	 */
	public void computeP() {
		/*
		 * 计算p，如果i与j的距离小于dc，则Pi+1
		 */
		for (int i = 0; i < distanceMatrix.length; i++) {
			int aP = 0;
			for (int j = 0; j < distanceMatrix[i].length; j++) {
				if (distanceMatrix[i][j] < this.dc) {
					aP++;
				}
			}
			this.p[i] = aP;
		}
		// p计算完成。
	}

	/**
	 * *************************************************************************
	 * *************************************** 计算从Itemi到Itemj之间的距离.
	 */
	public int diff(int i, int j) {
		int dis = this.distence.getDistence(itemList.get(i), itemList.get(j));
		// logger.debug("disstance between "+i+" and "+j +" is "+dis);
		return dis;
	}

	/**
	 * *************************************************************************
	 * ***************************************
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int get(int i, int j) {
		if (i >= distanceMatrix.length || j >= distanceMatrix[i].length) {
			return -1;
		} else {
			return this.distanceMatrix[i][j];
		}
	}

	public void setValue(int i, int j, int value) {
		if (i >= this.distanceMatrix.length || j >= this.distanceMatrix[i].length || i < 0 || j < 0) {
			return;
		} else {
			this.distanceMatrix[i][j] = value;
		}
	}

	/**
	 * *************************************************************************
	 * *************************************** write to file
	 * 
	 */

	public void writetoFile(File fileName) {

		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write("dc :" + this.dc + "\n");
			writer.write("entropy: " + Arrays.toString(this.getList4Dc().toArray()));
			writer.write("p :" + Arrays.toString(this.getP()) + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * *************************************************************************
	 * *************************************** getters and setters
	 * 
	 * @return
	 */
	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}

	public int[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public void setDistanceMatrix(int[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	public int getDc() {
		return dc;
	}

	public void setDc(int dc) {
		this.dc = dc;
	}

	public List<Entropy> getList4Dc() {
		return list4Dc;
	}

	public void setList4Dc(List<Entropy> list4Dc) {
		this.list4Dc = list4Dc;
	}

}
