package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.function.IntUnaryOperator;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_blueprints_location_flag;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import lombok.Getter;

public class Industry {

	private final ESIAccount con;

	public Industry(ESIAccount con) {
		this.con = con;
	}

	//
	// industry jobs
	//

	/**
	 * the list of active industry jobs for this character, excluding completed
	 * jobs
	 */

	@Getter(lazy = true)
	private final ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = con.connection()
	.cache().characters.industry_jobs(con.characterId(), false);

	public static boolean isManufacture(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 1;
	}

	public static boolean isTE(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 3;
	}

	public static boolean isME(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 4;
	}

	public static boolean isCopy(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 5;
	}

	public static boolean isInvention(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	public static boolean isReaction(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 11;
	}

	//
	// research
	//

	@Getter(lazy = true)
	private final ObsListHolder<R_get_characters_character_id_industry_jobs> researchJobs = getJobs()
	.filter(j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j));

	//
	// invent
	//

	@Getter(lazy = true)
	private final ObsListHolder<R_get_characters_character_id_industry_jobs> inventJobs = getJobs()
	.filter(Industry::isInvention);

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, Long> invent = getInventJobs().toMap(j -> j.product_type_id, j -> (long) j.runs,
			Long::sum);

	//
	// copy
	//

	@Getter(lazy = true)
	private final ObsListHolder<R_get_characters_character_id_industry_jobs> copyJobs = getJobs()
	.filter(Industry::isCopy);

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, Long> copy = getCopyJobs().toMap(j -> j.product_type_id,
			j -> (long) (j.runs * j.licensed_runs), Long::sum);

	//
	// manufacturing
	//

	@Getter(lazy=true)
	private final ObsListHolder<R_get_characters_character_id_industry_jobs> productionJobs = getJobs().filter(Industry::isManufacture);

	private ObsMapHolder<Integer, Long> cacheProduction = null;

	/**
	 * get the cached value of present production
	 *
	 * @param bpoId2ProductQtty
	 *          resolves the production of a bp, in product per run, from the id
	 *          of the bp. is stored inside the cached map if not done already.
	 * @return
	 */
	public ObsMapHolder<Integer, Long> getProduction(IntUnaryOperator bpoId2ProductQtty) {
		if (cacheProduction == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getProductionJobs();
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
	private final ObsListHolder<R_get_characters_character_id_industry_jobs> reactionJobs = getJobs()
	.filter(Industry::isReaction);

	private ObsMapHolder<Integer, Long> cacheReaction = null;

	/**
	 * get the cached value of present production
	 *
	 * @param bpoId2ReactionQtty
	 *          resolves the reaction of a bp, in product per run, from the id of
	 *          the bp. is stored inside the cached map if not done already.
	 * @return
	 */
	public ObsMapHolder<Integer, Long> getReaction(IntUnaryOperator bpoId2ReactionQtty) {
		if (cacheReaction == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getReactionJobs();
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
	// used BP
	//

	@Getter(lazy = true)
	private final ObsSetHolder<Long> usedBPs = getJobs().mapItems(j -> j.blueprint_id).distinct();

	//
	// blueprints
	//

	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> blueprints = con.connection()
	.cache().characters.blueprints(con.characterId()).toMap(b -> b.item_id, Industry::convertBlueprint);

	/**
	 * copy the structure of a character bp to a corporation bp.
	 *
	 * @param source
	 * @return
	 */
	protected static R_get_corporations_corporation_id_blueprints convertBlueprint(
			R_get_characters_character_id_blueprints source) {
		R_get_corporations_corporation_id_blueprints ret = new R_get_corporations_corporation_id_blueprints();
		ret.item_id = source.item_id;
		ret.location_flag = get_corporations_corporation_id_blueprints_location_flag.valueOf(source.location_flag.name());
		ret.location_id = source.location_id;
		ret.material_efficiency = source.material_efficiency;
		ret.quantity = source.quantity;
		ret.runs = source.runs;
		ret.time_efficiency = source.time_efficiency;
		ret.type_id = source.type_id;
		return ret;
	}

}
