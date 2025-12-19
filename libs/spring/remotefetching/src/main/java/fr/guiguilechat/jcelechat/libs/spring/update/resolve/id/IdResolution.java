package fr.guiguilechat.jcelechat.libs.spring.update.resolve.id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * name and type of a resolve id
 */
@Entity(name = "EsiRemoteIdResolution")
@Table(name = "esi_remote_idresolution", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires"),
		@Index(columnList = "name"),
		@Index(columnList = "category")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdResolution extends RemoteEntity<Integer, R_post_universe_names> {

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
