package fr.guiguilechat.jcelechat.libs.spring.update.resolve.id;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.NumberEntity;
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
 * name and type of a resolved id
 */
@Entity(name = "EsiRemoteIdResolution")
@Table(name = "esi_remote_idresolution", indexes = {
		@Index(columnList = "fetch_priority,next_fetch"),
		@Index(columnList = "name"),
		@Index(columnList = "category")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdResolution extends NumberEntity<Integer> {

	/**
	 * category string
	 */
	@Enumerated(EnumType.STRING)
	private post_universe_names_category category;

	/**
	 * name string
	 */
	private String name;

	@ColumnDefault("current_timestamp")
	private Instant nextFetch = null;

	@ColumnDefault("100")
	private int fetchPriority = 100;

	public void update(R_post_universe_names data) {
		setCategory(data.category);
		setName(data.name);
		setFetchPriority(0);
		updateMetaOk();
	}

}
