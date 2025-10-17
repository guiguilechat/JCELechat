package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcCharacter")
@Table(name = "sde_npc_character", indexes = {
// @Index(columnList = "activity_id,type_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NpcCharacter extends SdeEntity<Integer> {

	@ManyToOne
	private Division division;
	@ManyToOne
	private AgentType agentType;
	private int corporationId;
	private boolean isLocator;
	private int level;
	private int locationId;
	private String name;

}
