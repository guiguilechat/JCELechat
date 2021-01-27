package fr.guiguilechat.jcelechat.jcesi.disconnected;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
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
	private final CacheStatic cache = new CacheStatic(this);

	private ESIStatic() {
	}

	@Override
	public ObservableSet<String> getRoles() {
		return FXCollections.emptyObservableSet();
	};

}
