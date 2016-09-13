package edu.muc.jxd.item;


public class ImageItemVector<T extends Number> implements ItemInter {
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
	private T data[];
	
	/**
	 * 构造方法
	 */
	public ImageItemVector() {
		super();
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
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
