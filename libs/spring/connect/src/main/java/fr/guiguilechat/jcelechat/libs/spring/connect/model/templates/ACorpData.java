package fr.guiguilechat.jcelechat.libs.spring.connect.model.templates;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * a fetched resource that relates to a remote corporation.
 * 
 * @param <Fetched> data representing that resource on the remote server.
 */
@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
@SuperBuilder
public abstract class ACorpData<Fetched> extends ARemoteFetchedResource<Integer, Fetched> {

	@Id
	private int corporationId;

	@Override
	public Integer getRemoteId() {
		return getCorporationId();
	}

}
