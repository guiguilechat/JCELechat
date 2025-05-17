package fr.guiguilechat.jcelechat.libs.spring.industry.activity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "EveIndustryBlueprintActivity")
@Table(name = "eve_industry_blueprintactivity", indexes = {
		@Index(columnList = "id,name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IndustryActivity implements Serializable {

	@Id
	private int id;
	private String name;
	@Lob
	private String description;
	/**
	 * "/"-separated lower case alias
	 */
	private String aliases;

}
