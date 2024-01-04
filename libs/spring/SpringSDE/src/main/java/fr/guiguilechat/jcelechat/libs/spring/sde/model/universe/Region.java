package fr.guiguilechat.jcelechat.libs.spring.sde.model.universe;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Region {

	@Id
	private int regionID;

	public void updateFrom(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region source) {

	}

}
