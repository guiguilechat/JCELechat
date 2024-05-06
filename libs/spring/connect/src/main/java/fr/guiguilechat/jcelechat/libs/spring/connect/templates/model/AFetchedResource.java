package fr.guiguilechat.jcelechat.libs.spring.connect.templates.model;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * abstract class that represent a local representation of a fetched
 * information
 * 
 * @param <Fetched> the fetched class, used to update this class' fields
 */
@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
@SuperBuilder
public abstract class AFetchedResource {

	private Instant created, lastUpdate, expires, lastModified;

	private String lastEtag;

	@Builder.Default
	boolean fetched = false;

	@Builder.Default
	boolean active = true;

	protected void updateMeta(Instant lastModified, Instant expires, String etag) {
		setExpires(expires);
		setFetched(true);
		setLastEtag(etag);
		setLastModified(lastModified);
	}

	public void updateMeta(Requested<?> response) {
		updateMeta(response.getLastModifiedInstant(), response.getExpiresInstant(), response.getETag());
	}

}
