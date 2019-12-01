package com.pra.model;

import static com.pra.utils.view.DataFormatUtils.formatDate;
import static com.pra.utils.view.DataFormatUtils.formatKey;
import static com.pra.utils.view.DataFormatUtils.formatNum;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pra.reports.beans.SampleInReportBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SAMPLEIN")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"readings"})
public class SampleIn implements BaseModel<Integer>{
	@Transient
	private final String PREFIX = "IW";
	
	@Id
	private Integer refId;
	private LocalDate date;
	@ManyToOne
	private Product product;
	@ManyToOne
	private PartyIn party;
	private String partyRefNo;
	private String detailReport;
	private Double cost;
	private Double price;
	@OneToMany(mappedBy = "sample",cascade = CascadeType.ALL)
	private List<SampleInReading> readings;
	
	public String formattedKey() {
		return formatKey(this.refId,PREFIX);
	}
	
	public SampleInReportBean sampleInReportBean() {
		return SampleInReportBean.builder()
				.key(this.refId)
				.refId(this.formattedKey())
				.date(formatDate(this.date))
				.productName(this.product == null ? "-" :this.product.toString())
				.partyName(this.party == null ? "-" :this.party.toString())
				.partyRefNo(this.partyRefNo)
				.detailReport(this.detailReport)
				.cost(formatNum(this.cost))
				.price(formatNum(this.price))
				.build();
	}

	@Override
	public Integer primaryKey() {
		return this.refId;
	}

}
