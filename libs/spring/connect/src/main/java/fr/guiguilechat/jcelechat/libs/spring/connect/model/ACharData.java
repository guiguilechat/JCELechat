package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
@SuperBuilder
public abstract class ACharData<Fetched> extends AFetchedResource<Integer, Fetched> {

	@Id
	private int characterId;

	@Override
	public Integer getRemoteId() {
		return getCharacterId();
	}

}
