package edu.muc.jxd.item;

import org.apache.log4j.Logger;

import java.io.ObjectStreamException;
import java.io.Serializable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Item<T> implements Serializable,ItemInter {

	//private T data[];

	protected List<Element<T>> data=new ArrayList<>();

	protected int size=0;
	private Logger logger= Logger.getLogger(Item.class);

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

	public <N extends Number> N[] getData(){
		//TODO for getNumberArrays N[] problem
		//data.toArray()		
		return null;
	}

	//Object[] =>  N[] ?
	@SuppressWarnings("unchecked")
	protected <N extends Number> Object[] getNumberArrays(){
		if(data.isEmpty())
			return null;
		else{
		    List<N> numbers=new ArrayList<>();
			Iterator<Element<T>> elementIterator=data.iterator();
			
			logger.debug(data.get(0));
			logger.debug(data.get(0).getClassType());
			logger.debug(data.get(0).getClassType().isAssignableFrom(Object.class));
			/*
			if(!(data.get(0).getClassType().isAssignableFrom(Number.class))){
				throw new ClassCastException("Type is not extends Number...");
			}
			*/		
			while (elementIterator.hasNext()){
				Element<N> numberElement;
				try{
					numberElement=(Element<N>)(elementIterator.next());
					//TODO this is an error to convert it to save the model.
					/*
					numbers.add((N)(new Double(numberElement.plastic(new ElmentNumeralization<N>() {
						@Override
						public int numberalization(Number number) {
							return number.intValue()+22;
						}
					}))));
						*/
				}catch (Exception e){
					System.err.println("Type Convert Number Error.");
					e.printStackTrace();
				}
			}
			// this is to return a father type about Number
			logger.debug(Arrays.toString(numbers.toArray()));
			//numbers.toArray(T[] a)
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
