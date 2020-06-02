package fr.guiguilechat.jcelechat.libs.refinesolver;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;

public class RefineSolver {


	public Result solve(Params params) {
		IPricing market = ESIAccess.INSTANCE.markets.getLocalMarket(params.marketLocation);

		//
		// analyze on the mineral we want
		//

		/**
		 * the array of the required ids we want. We just keep the ids for which we
		 * have a quantity >0, and that can be the result of a reprocess.
		 */
		int[] requiredTypeIds = params.requiredQuantities.entrySet().stream()
				.filter(e -> e.getValue() > 0 && IndustryUsage.of(e.getKey()) != null
				&& !IndustryUsage.of(e.getKey()).reprocessedFrom.isEmpty())
				.mapToInt(e -> e.getKey()).toArray();

		/** for each id of mineral we want, the quantity we want. */
		int[] requiredTypeQttys = new int[requiredTypeIds.length];

		/**
		 * for each id we want, the maximum price we are paying if we buy that item
		 * from market. eg if we need 100, the price of the 100th item.
		 */
		double[] requiredTypeMaxPrices = new double[requiredTypeIds.length];

		/**
		 * if set to true, that mean we can buy everything form the market for given
		 * id.
		 */
		boolean[] requiredTypeCanFulfill = new boolean[requiredTypeIds.length];

		IntStream.range(0, requiredTypeQttys.length).parallel().forEach(index -> {
			int id = requiredTypeIds[index];
			long qtty = requiredTypeQttys[index] = params.requiredQuantities.get(id);
			List<R_get_markets_region_id_orders> orders =
					market
					.getMarketOrders(id)
					.listSellOrders()
					.get();
			for (R_get_markets_region_id_orders order : orders) {
				qtty -= order.volume_remain;
				if (qtty <= 0) {
					requiredTypeMaxPrices[index] = order.price;
					requiredTypeCanFulfill[index] = true;
					break;
				}
			}
			if (qtty > 0) {
				requiredTypeMaxPrices[index] = Double.POSITIVE_INFINITY;
				requiredTypeCanFulfill[index] = false;
			}
		});

		//
		// then get all the ore that can be processed into at least one of the
		// required minerals.
		//

		// first list all the roids that can be reprocessedinto the mineral we
		// need.
		Asteroid[] purchasedRoids = Asteroid.METACAT.load().values().parallelStream().filter(type -> {
			// don't keep roids that are not on sale.
			if (market.getMarketOrders(type.id).listSellOrders().isEmpty().get()) {
				return false;
			}
			IndustryUsage usage = IndustryUsage.of(type.id);
			// remove roids that do not reprocess into anything
			if (usage == null || usage.reprocessInto.isEmpty()) {
				return false;
			}
			// remove roids that do not reprocess in a required mineral
			if (!IntStream.of(requiredTypeIds).filter(mineralid -> usage.reprocessInto.getOrDefault(mineralid, 0.0) > 0)
					.findAny().isPresent()) {
				return false;
			}
			return true;
		}).toArray(Asteroid[]::new);

		// then make static data about both minerals and asteroids we can purchase.
		// first index them together.
		EveType[] types = new EveType[requiredTypeIds.length + purchasedRoids.length];

		/** one unit of type i refines into refineinto[i][j] units of type j */
		double[][] refineInto = new double[types.length][requiredTypeIds.length];

		/** for each item we can buy, the added price due to volume, per unit. */
		double[] volumicPrice = new double[types.length];

		/**
		 * for each item we can buy, the max quantity we actually may need to buy
		 */
		int[] maxQuantity = new int[types.length];
		// then fill those values from minerals
		for (int i = 0; i < requiredTypeIds.length; i++) {
			types[i] = TypeIndex.getType(requiredTypeIds[i]);
			volumicPrice[i] = types[i].volume * params.volumicCost;
			refineInto[i][i] = 1.0;
			maxQuantity[i] = requiredTypeQttys[i];
		}
		// and from roids.
		for (int i = 0; i < purchasedRoids.length; i++) {
			int idx = i + requiredTypeIds.length;
			EveType type = types[idx] = purchasedRoids[i];
			volumicPrice[idx] = types[idx].volume * params.volumicCost;
			IndustryUsage usage = IndustryUsage.of(type.id);
			double maxQtty = 0.0;
			for (Entry<Integer, Double> e : usage.reprocessInto.entrySet()) {
				int mineralid = e.getKey();
				for (int j = 0; j < requiredTypeIds.length; j++) {
					if (mineralid == requiredTypeIds[j]) {
						double refineQtty = refineInto[idx][j] = e.getValue() * params.refineRate;
						maxQtty = Double.max(maxQtty, requiredTypeQttys[j] / refineQtty);
						break;
					}
				}
			}
			maxQuantity[idx] = (int) Math.ceil(maxQtty);
		}

		// debug satic data

		for (int i = 0; i < types.length; i++) {
			System.err.print("" + i + "\t" + types[i].name);
			System.err.print("\tv=" + volumicPrice[i]);
			System.err.print("\tmax=" + maxQuantity[i]);
			for (int j = 0; j < requiredTypeIds.length; j++) {
				System.err.print("\t" + refineInto[i][j]);
			}
			System.err.println();
		}
		return null;
	}

}
