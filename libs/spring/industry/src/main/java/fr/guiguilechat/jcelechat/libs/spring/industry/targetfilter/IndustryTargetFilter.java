package fr.guiguilechat.jcelechat.libs.spring.industry.targetfilter;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "EveIndustryActivityFilter")
@Table(name = "eve_industry_targetfilter", indexes = {
		@Index(columnList = "id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IndustryTargetFilter implements Serializable {

	@Id
	private int id;
	private String name;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<Integer> groupIds;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<Integer> categoryIds;

}
