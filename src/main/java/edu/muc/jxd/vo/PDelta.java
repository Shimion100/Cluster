package edu.muc.jxd.vo;

public class PDelta implements Comparable<PDelta> {

	private Integer ID;

	private Integer pv;
	
	
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID:"+this.ID+"\t"+pv;
	}



	public PDelta(Integer iD, Integer pv) {
		ID = iD;
		this.pv = pv;
	}



	@Override
	public int compareTo(PDelta arg0) {
		// TODO Auto-generated method stub
		return (int) (this.pv - arg0.pv);
	}



	public Integer getID() {
		return ID;
	}



	public void setID(Integer iD) {
		ID = iD;
	}



	public Integer getPv() {
		return pv;
	}



	public void setPv(Integer pv) {
		this.pv = pv;
	}

	
	
}
