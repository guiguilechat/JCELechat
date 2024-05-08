package fr.guiguilechat.jcelechat.jcesi.disconnected;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import fr.lelouet.tools.holders.impl.collections.SetHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.SetHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * singleton for access to static (disconnected) calls.
 *
 */
public class ESIRawPublic extends ConnectedImpl implements G_IDCAccess {

	public static final ESIRawPublic INSTANCE = new ESIRawPublic();

	@Getter
	@Accessors(fluent = true)
	private final transient CacheStatic cache = new CacheStatic(this);

	@Getter(lazy=true)
	private final SetHolder<String> roles=SetHolderImpl.of();

}
