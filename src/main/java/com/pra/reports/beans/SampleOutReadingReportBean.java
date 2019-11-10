package com.pra.reports.beans;

import com.pra.annotations.TableCol;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SampleOutReadingReportBean implements ReportBean<Integer> {
	
	public Integer key;
	@TableCol(displayName = "ID" ,width=10)
	public String id;
	@TableCol(displayName = "Date" , width=30)
	public String date;
	@TableCol(displayName = "Product" , filterable = true,width = 70)
	public String productName;
	@TableCol(displayName = "Reference" , filterable = true,width = 70)
	public String sampleOutRef;
	@TableCol(displayName = "Detail Report" , filterable = true ,width = 300)
	public String detailReport;
	@TableCol(displayName = "Strnth" , width=15)	
	public String strength1;
	@TableCol(displayName = "DE" , width=15)
	public String de1;
	@TableCol(displayName = "DA" , width=15)
	public String da1;
	@TableCol(displayName = "DB" , width=15)
	public String db1;
	@TableCol(displayName = "DC" , width=15)
	public String dc1;
	@TableCol(displayName = "Strnth" , width=15)
	public String strength2;
	@TableCol(displayName = "DE" , width=15)
	public String de2;
	@TableCol(displayName = "DA" , width=15)
	public String da2;
	@TableCol(displayName = "DB" , width=15)
	public String db2;
	@TableCol(displayName = "DC" , width=15)
	public String dc2;
	
	@Override
	public Integer getPrimaryKey() {
		return this.key;
	}
	

}
