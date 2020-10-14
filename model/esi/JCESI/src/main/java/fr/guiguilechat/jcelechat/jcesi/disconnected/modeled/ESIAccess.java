package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;

/** modeled access to the esi disconnected operation. */
public class ESIAccess {

	public final ESIStatic connection;

	private ESIAccess(ESIStatic connection) {
		this.connection = connection;
		contracts = new Contracts(connection);
		industry = new Industry(connection);
		markets = new Markets(connection);
		route = new Route(connection);
		universe = new Universe(connection);
		wars = new Wars(connection);
	}

	public static final ESIAccess INSTANCE = new ESIAccess(ESIStatic.INSTANCE);

	public final Contracts contracts;

	public final Industry industry;

	public final Markets markets;

	public final Route route;

	public final Universe universe;

	public final Wars wars;

}
