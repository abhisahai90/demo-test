package com.nisum.demotest.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ZipCodeRange {

	private int startRange;
	private int endRange;
	
	@JsonCreator
	public ZipCodeRange(@JsonProperty("startRange") int startRange, @JsonProperty("endRange") int endRange) {
		if (startRange <= endRange) {
			this.startRange = startRange;
			this.endRange = endRange;
		} else {
			this.startRange = endRange;
			this.endRange = startRange;
		}
	}

	public int getStartRange() {
		return startRange;
	}

	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}

	public int getEndRange() {
		return endRange;
	}

	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}

	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof ZipCodeRange) {
			ZipCodeRange that = (ZipCodeRange) other;
			result = (this.getStartRange() == that.getStartRange() && this.getEndRange() == that.getEndRange());
		}
		return result;
	}
}
