package fr.guiguilechat.jcelechat.libs.spring.remotefetching.resolve;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * name and type of a resolve id
 */
@Entity(name = "EsiRemoteIdResolution")
@Table(name = "esi_remote_idresolution", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "name"),
    @Index(columnList = "category")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class IdResolution extends ARemoteFetchedResource<Integer, R_post_universe_names> {

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

}
