package fr.guiguilechat.jcelechat.jcesi.disconnected;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import fr.lelouet.collectionholders.impl.collections.ObsSetHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * singleton for access to static (disconnected) calls.
 *
 */
public class ESIStatic extends ConnectedImpl implements G_IDCAccess {

	public static final ESIStatic INSTANCE = new ESIStatic();

	@Getter
	@Accessors(fluent = true)
	private final transient CacheStatic cache = new CacheStatic(this);

	@Getter(lazy=true)
	private final ObsSetHolder<String> roles=ObsSetHolderImpl.of();

}
