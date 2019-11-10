package com.pra.reports.beans;

import com.pra.annotations.TableCol;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SampleInReportBean implements ReportBean<Integer> {

	public Integer key;
	@TableCol(displayName = "Ref No" ,width=10)
	public String refId;
	@TableCol(displayName = "Date" , width=30)
	public String date;
	@TableCol(displayName = "Product" , filterable = true,width = 70)
	public String productName;
	@TableCol(displayName = "Party Name" , filterable = true , width = 70)
	public String partyName;
	@TableCol(displayName = "P Ref No" , filterable = true , width = 10)
	public String partyRefNo;
	@TableCol(displayName = "Detail Report" , filterable = true ,width = 500)
	public String detailReport;
	@TableCol(displayName = "Price" , filterable = true , width=30)
	public String cost;
	@TableCol(displayName = "Cost" , filterable = true, width=30)
	public String price;
	
	@Override
	public Integer getPrimaryKey() {
		return this.key;
	}

}
