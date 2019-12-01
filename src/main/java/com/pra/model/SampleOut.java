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

import com.pra.reports.beans.SampleOutReportBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SAMPLEOUT")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"readings"})
public class SampleOut implements BaseModel<Integer>{
	@Transient
	private final String PREFIX = "CI";
	
	@Id
	private Integer refId;
	private LocalDate date;
	@ManyToOne
	private Product product;
	@ManyToOne
	private PartyOut party;
	private String detailReport;
	private Double cost;
	private Double price;
	@OneToMany(mappedBy = "sample",cascade = CascadeType.ALL)
	private List<SampleOutReading> readings;
	
	public String formattedKey() {
		return formatKey(this.refId,PREFIX);
	}
	
	public SampleOutReportBean sampleOutReportBean() {
		return SampleOutReportBean.builder()
				.key(this.refId)
				.refId(this.formattedKey())
				.date(formatDate(this.date))
				.productName(this.product == null ? "-" :this.product.toString())
				.partyName(this.party == null ? "-" :this.party.toString())
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
