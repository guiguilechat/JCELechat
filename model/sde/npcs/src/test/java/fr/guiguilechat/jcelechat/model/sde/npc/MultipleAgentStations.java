package fr.guiguilechat.jcelechat.model.sde.npc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.exports.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent.AGENT_DIVISION;
import fr.guiguilechat.jcelechat.model.sde.npcs.Agent.AGENT_TYPE;

public class MultipleAgentStations {

	public static class StationAgentsSummary{
		int L3S=0, L4S=0, L3M=0,L4M=0, L3D=0, L4D=0;
		SolarSystem solsyst;

		public int missions(AGENT_DIVISION division, int level) {
			if (level < 3 || level > 4) {
				return 0;
			}
			switch (division) {
			case Distribution:
				return level == 3 ? L3D : L4D;
			case Mining:
				return level == 3 ? L3M : L4M;
			case Security:
				return level == 3 ? L3S : L4S;
			default:
				throw new UnsupportedOperationException("case not handled " + division);
			}
		}
	}

	static final Set<AGENT_DIVISION> validDivisions = new HashSet<>(
			Arrays.asList(AGENT_DIVISION.Distribution, AGENT_DIVISION.Mining, AGENT_DIVISION.Security));

	public static boolean csv = true;
	public static String csvSeparator = "; ";

	public static void main(String[] args) {
		HashMap<String, StationAgentsSummary> summsHS = new HashMap<>();
		HashMap<String, StationAgentsSummary> summsLS = new HashMap<>();
		HashMap<String, StationAgentsSummary> summsNS = new HashMap<>();
		for( Agent a : Agent.load().values()) {
			if (a.level >= 3 && a.level < 5 && a.type == AGENT_TYPE.Basic && validDivisions.contains(a.division)) {
				SolarSystem solsyst = SolarSystem.load().get(a.system) ;
				Map<String, StationAgentsSummary> dataholder=null;
				if(solsyst.isHS()) {
					dataholder=summsHS;
				}
				if(solsyst.isLS()) {
					dataholder=summsLS;
				}
				if(solsyst.isNS()) {
					dataholder=summsNS;
				}
				StationAgentsSummary sum = dataholder.get(a.station);
				if (sum == null) {
					sum = new StationAgentsSummary();
					sum.solsyst = SolarSystem.load().get(a.system);
					dataholder.put(a.station, sum);
				}
				switch (a.division) {
				case Distribution:
					if (a.level == 3) {
						sum.L3D++;
					} else {
						sum.L4D++;
					}
					break;
				case Mining:
					if (a.level == 3) {
						sum.L3M++;
					} else {
						sum.L4M++;
					}
					break;
				case Security:
					if (a.level == 3) {
						sum.L3S++;
					} else {
						sum.L4S++;
					}
					break;
				default:
					throw new UnsupportedOperationException("case not handled " + a.division);
				}
			}
		}
		if (csv) {
			System.out.println("region" + csvSeparator + "constellation" + csvSeparator + "solar system" + csvSeparator
					+ "security status"
					+ csvSeparator + "true sec" + csvSeparator + "station" + csvSeparator + "division" + csvSeparator + "level"
					+ csvSeparator + "number");
		}
		for (HashMap<String, StationAgentsSummary> mmap : Arrays.asList(summsHS, summsLS, summsNS)) {
			String secstatus = mmap == summsHS ? "HS" : mmap == summsLS ? "LS" : "NS";
			if (!csv) {
				System.out.println(secstatus+":");
			}
			for (AGENT_DIVISION division : validDivisions) {
				for (int level : new int[] { 3, 4 }) {
					if (!csv) {
						System.out.println("  " + division + " level " + level);
					}
					mmap.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).forEach(e -> {
						StationAgentsSummary val = e.getValue();
						int nb = val.missions(division, level);
						if (nb > 1) {
							if (!csv) {
								System.out.println("      " + e.getKey() + " : " + nb);
							} else {
								System.out.println(val.solsyst.region + csvSeparator + val.solsyst.constellation + csvSeparator
										+ val.solsyst.name + csvSeparator
										+ secstatus + csvSeparator
										+ val.solsyst.truesec + csvSeparator + e.getKey() + csvSeparator + division + csvSeparator + level
										+ csvSeparator + nb);
							}
						}
					});
				}
			}
		}
	}

}
