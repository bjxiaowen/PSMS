package com.PSMS.pojo;

import java.math.BigDecimal;

public class PSTotal {
	
	private int totalPS;//电站总数
	
	private BigDecimal totalCapacity;//总容量
	
	private BigDecimal totalHistoryQ;//历史发电量

	public int getTotalPS() {
		return totalPS;
	}

	public void setTotalPS(int totalPS) {
		this.totalPS = totalPS;
	}

	public BigDecimal getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(BigDecimal totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public BigDecimal getTotalHistoryQ() {
		return totalHistoryQ;
	}

	public void setTotalHistoryQ(BigDecimal totalHistoryQ) {
		this.totalHistoryQ = totalHistoryQ;
	}
	
}
