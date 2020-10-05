package fr.guiguilechat.jcelechat.libs.anomgroup;

import java.util.Collection;

import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class HSPirateGroups {

	public PirateGroup guristas = new PirateGroup(), angels = new PirateGroup(), sanshas = new PirateGroup(),
			serpentis = new PirateGroup(), bloods = new PirateGroup();

	public HSPirateGroups() {
		add(angels, Region.EMPIRE_ANGELS);
		add(bloods, Region.EMPIRE_BLOODS);
		add(guristas, Region.EMPIRE_GURISTAS);
		add(sanshas, Region.EMPIRE_SANSHAS);
		add(serpentis, Region.EMPIRE_SERPENTIS);
	}

	protected void add(PirateGroup guristas2, Collection<String> empireGuristas) {
		for (String rn : empireGuristas) {
			Region r = Region.getRegion(rn);
			r.systems().map(SolarSystem::getSystem).forEach(guristas2::accept);
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
