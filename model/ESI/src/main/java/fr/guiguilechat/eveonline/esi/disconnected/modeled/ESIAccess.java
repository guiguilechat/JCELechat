package fr.guiguilechat.eveonline.esi.disconnected.modeled;

import fr.guiguilechat.eveonline.esi.disconnected.ESIStatic;

public class ESIAccess {

	public final ESIStatic connection;

	private ESIAccess(ESIStatic connection) {
		this.connection = connection;
		markets = new Markets(connection);
	}

	public static final ESIAccess INSTANCE = new ESIAccess(ESIStatic.INSTANCE);

	public final Markets markets;

}
