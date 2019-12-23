package com.theta.test;

public class Foo {

	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}


	/**
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}


	/**
	 * @return the second
	 */
	public String getSecond() {
		return second;
	}


	/**
	 * @param second the second to set
	 */
	public void setSecond(String second) {
		this.second = second;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Foo [first=");
		builder.append(first);
		builder.append(", second=");
		builder.append(second);
		builder.append("]");
		return builder.toString();
	}


	private String first = null;
	private String second = null;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
