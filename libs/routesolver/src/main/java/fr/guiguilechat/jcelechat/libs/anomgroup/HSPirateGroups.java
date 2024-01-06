package fr.guiguilechat.jcelechat.libs.anomgroup;

import fr.guiguilechat.jcelechat.model.sde.locations.Region.EMPIRE_FACTIONS;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class HSPirateGroups {

	public PirateGroup guristas = new PirateGroup(), angels = new PirateGroup(), sanshas = new PirateGroup(),
			serpentis = new PirateGroup(), bloods = new PirateGroup();

	public HSPirateGroups() {
		add(angels, EMPIRE_FACTIONS.angels);
		add(bloods, EMPIRE_FACTIONS.bloods);
		add(guristas, EMPIRE_FACTIONS.guristas);
		add(sanshas, EMPIRE_FACTIONS.sanshas);
		add(serpentis, EMPIRE_FACTIONS.serpentis);
	}

	protected void add(PirateGroup pirategroup, EMPIRE_FACTIONS pirats) {
		for (SolarSystem ss : pirats.systems()) {
			pirategroup.accept(ss);
		}
	}

	public static void main(String[] args) {
		HSPirateGroups group = new HSPirateGroups();
		print("angel", group.angels);
		print("blood", group.bloods);
		print("gurista", group.guristas);
		print("sansha", group.sanshas);
		print("serpentis", group.serpentis);
	}

	public static void print(String name, PirateGroup group) {
		System.out.println(name);
		System.out.println("\tburrow\t" + group.burrow.size() + "\t" + group.burrow);
		System.out.println("\thideway\t" + group.hideway.size() + "\t" + group.hideway);
		System.out.println("\trefuge\t" + group.refuge.size() + "\t" + group.refuge);
		System.out.println("\tdenHS\t" + group.denHS.size() + "\t" + group.denHS);
	}

}
