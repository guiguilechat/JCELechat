package fr.guiguilechat.jcelechat.libs.refinesolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression;
import org.chocosolver.solver.expression.discrete.arithmetic.NaArExpression;
import org.chocosolver.solver.variables.IntVar;
import org.slf4j.Logger;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;
import fr.guiguilechat.jcelechat.libs.refinesolver.Result.Command;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;

public class RefineSolver {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RefineSolver.class);

	protected IPricing market;

	protected Model model;

	protected Params params;

	/**
	 * Prices are expressed in cents, because that's the limit of Eve.
	 *
	 * @param params
	 * @return
	 */
	public Result solve(Params params) {
		this.params = params;
		market = ESIAccess.INSTANCE.markets.getLocalMarket(params.marketLocation);

		//
		// analyze on the mineral we want
		//

		requiredTypeIds = params.requiredQuantities.entrySet().stream()
				.filter(e -> e.getValue() > 0 && IndustryUsage.of(e.getKey()) != null
				&& !IndustryUsage.of(e.getKey()).reprocessedFrom.isEmpty())
				.mapToInt(e -> e.getKey()).toArray();

		if (requiredTypeIds.length == 0) {
			return new Result();
		}
		makeStaticData();

		// start the modeling

		model = new Model();

		makeQuantities();
		makeCosts();
		makeRefine();

		// solve

		Solver solver = model.getSolver();
		// solver.showDecisions();
		// solver.showContradiction();
		Solution solution = solver.findOptimalSolution(totalCost, false);
		if (solution.exists()) {
			return convertSolution(solution);
		} else {
			if (params.debug) {
				logger.info("could not find a solution");
			}
			return new Result();
		}
	}

	/**
	 * the ids of the types we want
	 */
	protected int[] requiredTypeIds;

	/** for each id of mineral we want, the quantity we want. */
	protected int[] requiredTypeQttys;

	/**
	 * for each id we want, the maximum price IN CENTS we are paying per unit, if
	 * we buy that item from market. eg if we need 100, the price of the 100th
	 * item.
	 */
	protected int[] requiredTypeMaxPrices;

	/**
	 * if set to true, that mean we can buy everything form the market for given
	 * required type
	 */
	protected boolean[] requiredTypeCanFulfill;

	/**
	 * the types we can purchase. The first one are the required types.
	 */
	protected EveType[] purchasedTypes;

	/**
	 * for each type we can purchase, the price in cents(rounded up) that such a
	 * unit requires to pay.
	 */
	protected int[] purchasedVolumicPrice;

	/**
	 * for each item we can buy, the max quantity we actually may need to buy
	 */
	protected int[] purchasedMaxQuantity;

	/**
	 * one unit of purchased type pi refines into
	 * {@link #purchasedRefineInto}[pi][rj] units of required type rj
	 */
	protected double[][] purchasedRefineInto;

	protected void makeStaticData() {

		requiredTypeQttys = new int[requiredTypeIds.length];

		requiredTypeMaxPrices = new int[requiredTypeIds.length];

		requiredTypeCanFulfill = new boolean[requiredTypeIds.length];

		IntStream.range(0, requiredTypeQttys.length).parallel().forEach(index -> {
			int id = requiredTypeIds[index];
			long qtty = requiredTypeQttys[index] = params.requiredQuantities.get(id);
			List<R_get_markets_region_id_orders> orders = market.getMarketOrders(id).listSellOrders().get();
			for (R_get_markets_region_id_orders order : orders) {
				qtty -= order.volume_remain;
				if (qtty <= 0) {
					requiredTypeMaxPrices[index] = (int) Math.ceil(order.price * 100);
					requiredTypeCanFulfill[index] = true;
					break;
				}
			}
			if (qtty > 0) {
				requiredTypeMaxPrices[index] = Integer.MAX_VALUE;
				requiredTypeCanFulfill[index] = false;
			}
		});

		if (params.debug) {
			logger.info("made target basket of " + requiredTypeIds.length + " types");
		}

		//
		// then get all the ore that can be processed into at least one of the
		// required minerals.
		//

		Set<Integer> allowedGroups = params.groupsLimit == null || params.groupsLimit.length == 0 ? null
				: IntStream.of(params.groupsLimit).boxed().collect(Collectors.toSet());

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
			// remove roids that are not compressed if required
			if (usage.compressFrom == 0 && params.onlyCompressed) {
				return false;
			}
			// remove roid that is not in the correct group
			if (allowedGroups != null && !allowedGroups.contains(type.getGroupId())) {
				return false;
			}

			// remove roids that do not reprocess in a required mineral
			if (!IntStream.of(requiredTypeIds).filter(mineralid -> usage.reprocessInto.getOrDefault(mineralid, 0.0) > 0)
					.findAny().isPresent()) {
				return false;
			}
			// TODO remove roids that have WORSE cost (including volumic) for one item
			// than the worst cost of the items (including volumic) they would refine
			// into.It's specially interesting if we don't care about several of the
			// reprocessed items.
			return true;
		}).toArray(Asteroid[]::new);

		if (params.debug) {
			logger.info("filtered roids : using " + purchasedRoids.length + " of them");
		}

		purchasedTypes = new EveType[requiredTypeIds.length + purchasedRoids.length];
		purchasedRefineInto = new double[purchasedTypes.length][requiredTypeIds.length];
		purchasedVolumicPrice = new int[purchasedTypes.length];
		purchasedMaxQuantity = new int[purchasedTypes.length];

		// then fill those values from minerals
		for (int i = 0; i < requiredTypeIds.length; i++) {
			purchasedTypes[i] = TypeIndex.getType(requiredTypeIds[i]);
			purchasedVolumicPrice[i] = (int) Math.ceil(purchasedTypes[i].volume * params.volumicCost * 100);
			purchasedRefineInto[i][i] = 1.0;
			purchasedMaxQuantity[i] = requiredTypeQttys[i];
		}
		// and from roids.
		for (int i = 0; i < purchasedRoids.length; i++) {
			int idx = i + requiredTypeIds.length;
			EveType purchasedType = purchasedTypes[idx] = purchasedRoids[i];
			purchasedVolumicPrice[idx] = (int) Math.ceil(purchasedTypes[idx].volume * params.volumicCost * 100);
			IndustryUsage usage = IndustryUsage.of(purchasedType.id);
			double maxQtty = 0.0;
			for (Entry<Integer, Double> e : usage.reprocessInto.entrySet()) {
				int mineralid = e.getKey();
				for (int j = 0; j < requiredTypeIds.length; j++) {
					if (mineralid == requiredTypeIds[j]) {
						double refineQtty = purchasedRefineInto[idx][j] = e.getValue() * params.refineRate;
						maxQtty = Double.max(maxQtty, requiredTypeQttys[j] / refineQtty);
						break;
					}
				}
			}
			purchasedMaxQuantity[idx] = (int) Math.ceil(maxQtty);
		}

		// debug static data

		if (params.debug) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < purchasedTypes.length; i++) {
				sb.append("" + i + "\t" + purchasedTypes[i].name);
				sb.append("\tv=" + purchasedVolumicPrice[i]);
				sb.append("\tmax=" + purchasedMaxQuantity[i]);
				for (int j = 0; j < requiredTypeIds.length; j++) {
					sb.append("\t" + purchasedRefineInto[i][j]);
				}
				sb.append("\n");
			}
			logger.info("static data\n" + sb.toString());
		}

	}

	/**
	 * for command ci, type tj, the variable
	 * {@link #commandTypeQuantities}[ci][tj] is the amount of that type we
	 * purchase. If a quantity is set to zero, then next quantities are also zero,
	 * ie quantity[i]!=0 OR quantity[i+1]==0
	 */
	protected IntVar[][] commandTypeQuantities;

	/**
	 * for command ci, type tj, the variable
	 * {@link #commandTypeCumulQuantities}[ci][tj] is the sum of quantities for
	 * that command and previous. This is used to get the unit price for that
	 * command and type.
	 */
	protected ArExpression[][] commandTypeCumulQuantities;

	/**
	 * for each type, the total quantity we buy. Equal to the last command's cumul
	 * quantities
	 */
	protected IntVar[] purchasedQuantities;

	protected void makeQuantities() {
		commandTypeQuantities = new IntVar[params.maxCommands][purchasedTypes.length];
		commandTypeCumulQuantities = new ArExpression[params.maxCommands][purchasedTypes.length];
		purchasedQuantities = new IntVar[purchasedTypes.length];
		for (int pi = 0; pi < purchasedTypes.length; pi++) {
			EveType purchasedType = purchasedTypes[pi];
			int maxQtty = purchasedMaxQuantity[pi];
			ArExpression cumulQuantity = model.intVar(0);
			for (int ci = 0; ci < params.maxCommands; ci++) {
				IntVar commandQuantity = commandTypeQuantities[ci][pi] = model
						.intVar(purchasedType.name + ".command" + ci + ".quantity", 0, maxQtty);
				// if a command is set to 0, next command is also set to 0
				if (ci > 0) {
					commandTypeQuantities[ci - 1][pi].gt(0).or(commandQuantity.eq(0)).post();
				}
				cumulQuantity = commandTypeCumulQuantities[ci][pi] = cumulQuantity.add(commandQuantity);
			}
			purchasedQuantities[pi] = model.intVar(purchasedType.name + ".purchased", 0, maxQtty);
			purchasedQuantities[pi].eq(cumulQuantity).post();
		}
	}

	/**
	 * for command ci, type tj, the variable {@link #commandTypePrices}[ci][tj] is
	 * the unit price (in cents) for that command and that type, including the
	 * volumic cost.
	 * <p>
	 * for example, for a type ti given, if I there are two sell orders, one of
	 * 100 items for 10 cents and the other of 100 items for 15 cents, if the
	 * first command takes the full 100 first then the price will be 10 cents, if
	 * it takes more the price will be 15 cents.<br />
	 * A constraint requires that either a command takes the full order, or the
	 * next commands quantity is set to 0.
	 * </p>
	 */
	protected IntVar[][] commandTypePrices;

	/**
	 * for command ci, type tj, the variable {@link #commandTypeCosts}[ci][tj] is
	 * the total cost to pay (in cents) for that command and that type, including
	 * the volumic cost.<br />
	 * it's just the product of the price for chosen quantity and that quantity.
	 */
	protected ArExpression[][] commandTypeCosts;

	/** for each command, the sum of the costs of the types we purchase */
	protected IntVar[] commandCosts;

	/** total cost */
	protected IntVar totalCost;

	protected void makeCosts() {
		commandTypePrices = new IntVar[params.maxCommands][purchasedTypes.length];
		commandCosts = new IntVar[params.maxCommands];
		for (int pi = 0; pi < purchasedTypes.length; pi++) {
			EveType purchasedType = purchasedTypes[pi];
			int maxQuantity = purchasedMaxQuantity[pi];
			List<R_get_markets_region_id_orders> orders = market.getMarketOrders(purchasedType.id).listSellOrders().get();
			// make the cumulated quantities and corresponding price of the order,
			// starting with 0
			// they are used to deduce the price of a command from the cumulative
			// quantity of that command.
			ArrayList<Integer> orderCumulQtty = new ArrayList<>();
			orderCumulQtty.add(0);
			int cumul = 0;
			for (R_get_markets_region_id_orders order : orders) {
				cumul += order.volume_remain;
				orderCumulQtty.add(cumul);
				if (cumul >= maxQuantity) {
					break;
				}
			}
			int[] cumulQtty = orderCumulQtty.stream().mapToInt(i -> i).toArray();
			int purchasedIndex = pi;
			int[] cumulPrice = IntStream.range(0, cumulQtty.length).map(cumuli -> {
				if (cumuli == 0) {
					return 0;
				} else {
					return (int) Math.ceil(purchasedVolumicPrice[purchasedIndex] + orders.get(cumuli + 1).price * 100);
				}
			}).toArray();
			for (int ci = 0; ci < params.maxCommands; ci++) {
				ArExpression qtty = commandTypeCumulQuantities[ci][pi];
				// create the table of variables that say whether that quantity is &le;
				// the order's cumulated quantity
				ArExpression[] lowerThanQtty = IntStream.of(cumulQtty).mapToObj(orderqtty -> qtty.le(orderqtty))
						.toArray(ArExpression[]::new);
				ArExpression priceIndex = model.intVar(0).add(lowerThanQtty);
				IntVar commandPrice = model.intVar(purchasedType.name + ".command_" + ci + ".price", 0,
						cumulPrice[cumulPrice.length - 1]);
				model.element(commandPrice, cumulPrice, priceIndex.intVar()).post();
				commandTypePrices[ci][pi] = commandPrice;
				// prevent a command price to be the same as previous one, unless
				// previous is set to 0
				// ie : previous>0 OR price==0
				if (ci > 0) {
					commandTypePrices[ci - 1][pi].gt(0).or(commandPrice.eq(0)).post();
				}
			}
		}

		commandTypeCosts = new ArExpression[params.maxCommands][purchasedTypes.length];
		for (int ci = 0; ci < params.maxCommands; ci++) {
			for (int pi = 0; pi < purchasedTypes.length; pi++) {
				commandTypeCosts[ci][pi] = commandTypePrices[ci][pi].mul(commandTypeQuantities[ci][pi]);
			}
			commandCosts[ci] = new NaArExpression(ArExpression.Operator.ADD, commandTypeCosts[ci]).intVar();
		}

		totalCost = new NaArExpression(ArExpression.Operator.ADD, commandCosts).intVar();
	}

	/**
	 * granularity for refining. Some types can produce decimal value on average,
	 * so we increase the values to get correct int.
	 */
	int grainMultiplier = 1000;

	/**
	 * for each required type, the amount we can get after refine - multiplied by
	 * the granularity
	 */
	protected IntVar[] requiredGrainRefined;

	protected void makeRefine() {
		requiredGrainRefined = new IntVar[requiredTypeIds.length];
		for (int ri = 0; ri < requiredTypeIds.length; ri++) {
			EveType requiredType = purchasedTypes[ri];
			int requiredGrainQtty = requiredTypeQttys[ri] * grainMultiplier;
			// max quantity , granularized, we can get from any ONE purchased type
			int maxGrainDelta = 0;
			ArExpression sum = model.intVar(0);
			for (int pi = 0; pi < purchasedTypes.length; pi++) {
				int purchasedGrainQtty = (int) Math.floor(grainMultiplier * purchasedRefineInto[pi][ri]);
				maxGrainDelta = Math.max(maxGrainDelta, purchasedGrainQtty);
				sum = sum.add(purchasedQuantities[pi].mul(purchasedGrainQtty));
			}
			IntVar obtained = requiredGrainRefined[ri] = model.intVar(requiredType.name + ".obtained_grain" + grainMultiplier,
					requiredGrainQtty, requiredGrainQtty + maxGrainDelta - 1);
			obtained.eq(obtained).post();
		}
	}

	protected Result convertSolution(Solution solution) {
		Result ret = new Result();
		for (int ci = 0; ci < params.maxCommands; ci++) {
			Command command = new Command();
			for (int pi = 0; pi < purchasedTypes.length; pi++) {
				EveType purchase = purchasedTypes[pi];
				int qtty = solution.getIntVal(commandTypeQuantities[ci][pi]);
				double price = 0.01 * solution.getIntVal(commandTypePrices[ci][pi]);
				if (qtty > 0) {
					command.quantities.put(purchase, qtty);
					command.prices.put(purchase, price);
				}
			}
			if (command.quantities.isEmpty()) {
				break;
			} else {
				command.cost = 0.01 * solution.getIntVal(commandCosts[ci]);
				ret.commands.add(command);
			}
		}
		ret.cost = 0.01 * solution.getIntVal(totalCost);
		return ret;

	}

}
