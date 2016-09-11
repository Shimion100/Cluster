package edu.muc.jxd.item;

import java.io.Serializable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item<T> implements Serializable,ItemInter {

	//private T data[];

	protected List<Element<T>> data=new ArrayList<>();

	protected int size=0;


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4519457391951715668L;
	/**
	 * 标志ID
	 */
	private int id;
	/**
	 * 真实的数据
	 */
	//private T data[];

	/**
	 * 构造方法
	 */
	public Item() {
		super();
	}

	public List<Element<T>> getData() {
		return data;
	}

	/**
	 * To set the data
	 * @param data
     */
	public void setData(List<Element<T>> data){
		this.data = data;
		this.size=this.data.size();
	}

	/**
	 * To get size of item
	 * @return size
     */
	public int getLength() {
		if (null != this.data) {
			return this.data.size();
		} else {
			return 0;
		}
	}


	public int getItemSize(){
		return size;
	}


	public boolean add(Element<T> e){
		data.add(e);
		size=data.size();
		return true;
	}


	public void setLength(int length) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
