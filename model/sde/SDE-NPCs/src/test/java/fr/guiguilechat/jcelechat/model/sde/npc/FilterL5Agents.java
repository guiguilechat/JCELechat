package fr.guiguilechat.jcelechat.model.sde.npc;

import fr.guiguilechat.jcelechat.model.sde.npcs.Agent;

public class FilterL5Agents {

	public static void main(String[] args) {
		System.out.println("name\tsystem\tdivision\ttype");
		for (Agent a : Agent.load().values()) {
			if (a.level == 5) {
				System.out.println(a.name + "\t" + a.system + "\t" + a.division + "\t" + a.type);
			}
		}
	}

}
