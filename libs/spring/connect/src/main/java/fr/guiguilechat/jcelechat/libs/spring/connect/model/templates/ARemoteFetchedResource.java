package fr.guiguilechat.jcelechat.libs.spring.connect.model.templates;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/** representation of a fetched resource that is linked to a remote entity. The
 * actual implementation + {@link #getRemoteId()} should identify the remote
 * resource completely.
 * 
 * @param <RemoteId> type of remote id used, typically int or long.
 * @param <Fetched>  {@link AFetchedResource} */
@AllArgsConstructor
@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class ARemoteFetchedResource<RemoteId, Fetched> extends AFetchedResource {

	public abstract RemoteId getRemoteId();

	public abstract void update(Fetched data);

}