package fr.guiguilechat.jcelechat.libs.spring.sde.space.stargate;

import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.generic.SdeInSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeSpaceStargate")
@Table(name = "sde_space_stargate", indexes = {
    @Index(columnList = "solar_system_id"),
		@Index(columnList = "type_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Stargate extends SdeInSystem {


	/**
	 * destination object
	 */
	@OneToOne
	private Stargate destination;

	public void update(EmapStargates source,
			Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems,
			Function<Integer, Stargate> stargates) {
		super.update(source, null, null);
		setDestination(stargates.apply(source.destination.stargateID));
	}

}
