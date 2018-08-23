package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;

public class ESIAccess {

	public final ESIStatic connection;

	private ESIAccess(ESIStatic connection) {
		this.connection = connection;
		markets = new Markets(connection);
		industry = new Industry(connection);
		universe = new Universe(connection);
		route = new Route(connection);
	}

	public static final ESIAccess INSTANCE = new ESIAccess(ESIStatic.INSTANCE);

	public final Markets markets;

	public final Industry industry;

	public final Universe universe;

	public final Route route;

}
