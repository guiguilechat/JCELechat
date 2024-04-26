package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import java.time.Instant;

import jakarta.persistence.Id;
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
public abstract class ACharData<F> {

	@Id
	private int characterId;

	private Instant created, lastUpdate, expires;

	private String lastEtag;

	@Builder.Default
	boolean fetched = false;

	@Builder.Default
	boolean active = true;

	public void update(Instant expires, String etag) {
		setExpires(expires);
		setFetched(true);
		setLastEtag(etag);
	}

	public abstract void update(F data);

}
