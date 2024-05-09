package fr.guiguilechat.jcelechat.libs.spring.templates.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a fetched resource that relates to a remote character.
 * 
 * @param <Fetched> data representing that resource on the remote server.
 */
@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public abstract class ACharData<Fetched> extends ARemoteFetchedResource<Integer, Fetched> {

	@Id
	private int characterId;

	@Override
	public Integer getRemoteId() {
		return getCharacterId();
	}

}
