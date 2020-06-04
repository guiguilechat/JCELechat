package fr.guiguilechat.jcelechat.libs.refinesolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.EveType;

public class Result {

	public static class Command {
		public double cost;
		public HashMap<EveType, Integer> quantities = new HashMap<>();
		public HashMap<EveType, Double> prices = new HashMap<>();

		@Override
		public String toString() {
			return quantities.toString();
		}

		public String prices() {
			return prices.toString();
		}

		public String toEveBuy() {
			StringBuilder sb = new StringBuilder();
			for (Entry<EveType, Integer> e : quantities.entrySet()) {
				sb.append(e.getValue()).append(" ").append(e.getKey().name).append("\n");
			}
			return sb.toString();
		}
	}

	public List<Command> commands = new ArrayList<>();

	public double cost;

}
