package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag;

public class Route {

	protected final ESIStatic con;

	public Route(ESIStatic con) {
		this.con = con;
	}

	public int[] makeRoute(int origin, int destination, boolean secure) {
		return con.get_route(null, null, destination,
				secure ? flag.secure : flag.shortest, origin, null);
	}

}
