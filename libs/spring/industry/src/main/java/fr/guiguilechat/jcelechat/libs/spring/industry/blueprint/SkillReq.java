package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * a skill required to start a blueprint activity
 */
@SuppressWarnings("serial")
@Entity(name = "SdeBlueprintSkillreq")
@Table(name = "sde_blueprint_skillreq", indexes = {
    @Index(columnList = "activity_id,type_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SkillReq implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private BlueprintActivity activity;

	@ManyToOne
	private Type type;

	private int level;

	public static SkillReq of(BlueprintActivity bp, Type type, int level) {
		return builder()
				.activity(bp)
				.type(type)
				.level(level)
				.build();
	}
}
