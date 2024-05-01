package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import java.time.Instant;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public abstract class AFetchedResource<RemoteId, Fetched> {

	private Instant created, lastUpdate, expires;

	private String lastEtag;

	public abstract RemoteId getRemoteId();

	@Builder.Default
	boolean fetched = false;

	@Builder.Default
	boolean active = true;

	public void updateMeta(Instant expires, String etag) {
		setExpires(expires);
		setFetched(true);
		setLastEtag(etag);
	}

	public abstract void update(Fetched data);

}
