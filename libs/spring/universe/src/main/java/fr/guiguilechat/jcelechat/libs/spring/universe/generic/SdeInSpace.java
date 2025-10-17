package fr.guiguilechat.jcelechat.libs.spring.universe.generic;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SdeInSpace extends SdeEntity<Integer> {

	private BigDecimal posX, posY, posZ;

	protected void update(InSpace source) {
		receivedSource();
		setPosX(source.position.x);
		setPosY(source.position.y);
		setPosZ(source.position.z);
	}

}
