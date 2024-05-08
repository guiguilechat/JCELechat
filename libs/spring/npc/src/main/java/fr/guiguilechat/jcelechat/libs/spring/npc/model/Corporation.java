package fr.guiguilechat.jcelechat.libs.spring.npc.model;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "EsiNpcCorporation")
@Table(name = "esi_npc_corporation")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Corporation extends ARemoteFetchedResource<Integer, R_get_corporations_corporation_id> {

	@Id
	private int corporationId;

	@Override
	public Integer getRemoteId() {
		return getCorporationId();
	}

	private int ceoId;

	private int creatorId;

	private int factionId;

	/**
	 * the full name of the corporation
	 */
	private String name;

	@Override
	public void update(R_get_corporations_corporation_id data) {
		setCeoId(data.ceo_id);
		setCreatorId(data.creator_id);
		setFactionId(data.faction_id);
		setName(data.name);
	}

}
