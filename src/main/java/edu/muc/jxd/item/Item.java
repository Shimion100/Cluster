package edu.muc.jxd.item;

public class Item<T> implements ItemInter {
	public T data[];
	public int length;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T[] getData() {
		return data;
	}

	public void setData(T[] data) {
		this.data = data;
	}

	public int getLength() {
		if (null != this.data) {
			return this.data.length;
		} else {
			return 0;
		}
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public Object getDistance() {
		// TODO Auto-generated method stub
		return null;
	}

}
