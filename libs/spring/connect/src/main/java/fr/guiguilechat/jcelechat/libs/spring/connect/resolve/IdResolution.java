package fr.guiguilechat.jcelechat.libs.spring.connect.resolve;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * name and type of a resolve id
 */
@Entity(name = "EsiConnectIdResolution")
@Table(name = "esi_connect_idresolution", indexes = {
    @Index(columnList = "name"),
    @Index(columnList = "category")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class IdResolution extends ARemoteFetchedResource<Integer, R_post_universe_names> {

	@Id
	private int id;

	/**
	 * category string
	 */
	@Enumerated(EnumType.STRING)
	private post_universe_names_category category;

	/**
	 * name string
	 */
	private String name;

	@Override
	public void update(R_post_universe_names data) {
		setCategory(data.category);
		setName(data.name);
	}

	@Override
	public Integer getRemoteId() {
		return getId();
	}
}
