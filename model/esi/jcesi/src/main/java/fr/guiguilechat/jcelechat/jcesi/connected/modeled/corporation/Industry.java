package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.function.IntUnaryOperator;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.collections.SetHolder;
import lombok.Getter;

public class Industry {

	private final ESIAccount con;

	public Industry(ESIAccount con) {
		this.con = con;
	}

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> allJobs = con.connection()
	    .cache().corporations.industry_jobs(con.corporation.getId(), true);

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> activeJobs = con.connection()
	.cache().corporations.industry_jobs(con.corporation.getId(), false);

	public static boolean isManufacture(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 1;
	}

	public static boolean isTE(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 3;
	}

	public static boolean isME(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 4;
	}

	public static boolean isCopy(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 5;
	}

	public static boolean isInvention(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	public static boolean isReaction(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 11;
	}

	//
	// research
	//

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> researchJobs = getActiveJobs()
	.filter(j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j));

	//
	// invent
	//

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> inventJobs = getActiveJobs()
	.filter(Industry::isInvention);

	/**
	 * map from type id to the invention amount for that type. Since we can only
	 * invent bpc, it means the key is blueprint ids.
	 */
	@Getter(lazy = true)
	private final MapHolder<Integer, Long> invention = getInventJobs().toMap(j -> j.product_type_id,
	    j -> (long) (j.runs * j.licensed_runs), Long::sum);

	//
	// copy
	//

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> copyJobs = getActiveJobs()
	.filter(Industry::isCopy);

	@Getter(lazy = true)
	private final MapHolder<Integer, Long> copy = getCopyJobs().toMap(j -> j.product_type_id, j -> (long) j.runs,
			Long::sum);

	//
	// manufacturing
	//

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> productionJobs = getActiveJobs()
	.filter(Industry::isManufacture);

	private MapHolder<Integer, Long> cacheProduction = null;

	/**
	 * get the cached value of present production
	 *
	 * @param bpoId2ProductQtty
	 *          resolves the production of a bp, in product per run, from the id
	 *          of the bp. is stored inside the cached map if not done already.
	 * @return
	 */
	public MapHolder<Integer, Long> getProduction(IntUnaryOperator bpoId2ProductQtty) {
		if (cacheProduction == null) {
			ListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getProductionJobs();
			synchronized (jobs) {
				if (cacheProduction == null) {
					cacheProduction = jobs.toMap(j -> j.product_type_id,
							j -> (long) (j.runs * bpoId2ProductQtty.applyAsInt(j.blueprint_type_id)), Long::sum);
				}
			}
		}
		return cacheProduction;
	}

	//
	// reactions
	//

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_industry_jobs> reactionJobs = getActiveJobs()
	.filter(Industry::isReaction);

	private MapHolder<Integer, Long> cacheReaction = null;

	/**
	 * get the cached value of present production
	 *
	 * @param bpoId2ReactionQtty
	 *          resolves the reaction of a bp, in product per run, from the id of
	 *          the bp. is stored inside the cached map if not done already.
	 * @return
	 */
	public MapHolder<Integer, Long> getReaction(IntUnaryOperator bpoId2ReactionQtty) {
		if (cacheReaction == null) {
			ListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getReactionJobs();
			synchronized (jobs) {
				if (cacheReaction == null) {
					cacheReaction = jobs.toMap(j -> j.product_type_id,
							j -> (long) (j.runs * bpoId2ReactionQtty.applyAsInt(j.blueprint_type_id)), Long::sum);
				}
			}
		}
		return cacheReaction;
	}

	//
	// bp usage
	//

	@Getter(lazy = true)
	private final SetHolder<Long> usedBPs = getActiveJobs().mapItems(j -> j.blueprint_id).distinct();

	/** all the blueprints of the corporation */
	@Getter(lazy = true)
	private final MapHolder<Long, R_get_corporations_corporation_id_blueprints> blueprints = con.connection()
	.cache().corporations.blueprints(con.corporation.getId()).toMap(b -> b.item_id, b -> b);

}
