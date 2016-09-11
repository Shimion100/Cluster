package edu.muc.jxd.item;

import org.apache.log4j.Logger;

import java.io.Serializable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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

	/**
	 * This is used to return true data of Item
	 * @return
     */
	public List<Element<T>> getItemData() {
		return data;
	}

	public T[] getData(){
		//TODO for getNumberArrays N[] problem
		//data.toArray()
		return (T[])getNumberArrays();
	}

	//Object[] =>  N[] ?
	protected <N extends Number> Object[] getNumberArrays(){
		if(data.isEmpty())
			return null;
		else{
		    List<Number> numbers=new ArrayList<>();
			Iterator<Element<T>> elementIterator=data.iterator();
			Logger logger= Logger.getLogger(Item.class);
			logger.debug(data.get(0));
			logger.debug(data.get(0).getClassType());
			logger.debug(data.get(0).getClassType().isAssignableFrom(Object.class));
			/*
			if(!(data.get(0).getClassType().isAssignableFrom(Number.class))){
				throw new ClassCastException("Type is not extends Number...");
			}
			*/
			while (elementIterator.hasNext()){
				Element<Number> numberElement;
				try{
					numberElement=(Element<Number>)(elementIterator.next());
					numbers.add(numberElement.plastic(new ElmentNumeralization<Number>() {
						@Override
						public int numberalization(Number number) {
							return number.intValue()+22;
						}
					}));
				}catch (Exception e){
					System.err.println("Type Convert Number Error.");
					e.printStackTrace();
				}
			}
			// this is to return a father type about Number
			return numbers.toArray();
		}

	}

	/**
	 * To set the data
	 * @param data
     */
	public void setData(List<Element<T>> data){
		this.data = data;
		this.size=this.data.size();
	}

	public void setData(T[] data){
		List<Element<T>> datas=new ArrayList<>();

		for(int i=0;i<data.length;i++){
			datas.add(new Element<T>(data[i]));
		}

		this.data=datas;
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
