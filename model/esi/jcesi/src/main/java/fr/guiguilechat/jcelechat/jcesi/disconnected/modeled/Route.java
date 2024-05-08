package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag;

public class Route {

	protected final ESIRawPublic con;

	public Route(ESIRawPublic con) {
		this.con = con;
	}

	public Integer[] makeRoute(int origin, int destination, boolean secure) {
		// ignore jita if not required
		int[] ignore = origin != 30000142 && destination != 30000142 ? new int[] { 30000142 } : new int[] {};
		return con.get_route(ignore, null, destination,
				secure ? flag.secure : flag.shortest, origin, null).getOK();
	}

}
