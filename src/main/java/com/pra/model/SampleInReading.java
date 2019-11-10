package com.pra.model;

import static com.pra.utils.view.DataFormatUtils.formatDate;
import static com.pra.utils.view.DataFormatUtils.formatKey;
import static com.pra.utils.view.DataFormatUtils.formatNum;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pra.reports.beans.SampleInReadingReportBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SAMPLEIN_READING")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleInReading implements BaseModel<Integer> {
	
	@Id
	private Integer id;
	private LocalDate date;
	@ManyToOne
    @JsonIgnoreProperties("readings")
	private SampleIn sample;
	private String detailReport;
	private Double strength1;
	private Double strength2;
	private Double de1;
	private Double de2;
	private Double da1;
	private Double da2;
	private Double db1;
	private Double db2;
	private Double dc1;
	private Double dc2;
	
	public SampleInReadingReportBean sampleInReadingReportBean() {
		return SampleInReadingReportBean.builder()
				.key(this.id)
				.id(formatKey(this.id,"SI"))
				.date(formatDate(this.date))
				.productName(this.sample == null ? "-" :this.sample.getProduct().toString())
				.sampleInRef(this.sample == null ? "-" :formatKey(this.sample.getRefId(),"CI"))
				.detailReport(this.detailReport)
				.strength1(formatNum(this.strength1))
				.strength2(formatNum(this.strength2))
				.da1(formatNum(this.da1))
				.da2(formatNum(this.da2))
				.db1(formatNum(this.db1))
				.db2(formatNum(this.db2))
				.dc1(formatNum(this.dc1))
				.dc2(formatNum(this.dc2))
				.de1(formatNum(this.de1))
				.de2(formatNum(this.de2))
				.build();
	}

	@Override
	public Integer primaryKey() {
		return this.id;
	}

}
