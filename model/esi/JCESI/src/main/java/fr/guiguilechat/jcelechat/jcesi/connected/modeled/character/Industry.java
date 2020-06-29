package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_blueprints_location_flag;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Industry {

	private final ESIAccount con;

	public Industry(ESIAccount con) {
		this.con = con;
	}

	//
	// industry jobs
	//

	/**
	 * fetch the list of active industry jobs for this character. Completed jobs
	 * are ignored.
	 *
	 * @return the internal cache of the jobs for this character. successive calls
	 *         will return the same value.
	 *
	 */

	private ObsListHolder<R_get_characters_character_id_industry_jobs> cacheJobs = null;

	public ObsListHolder<R_get_characters_character_id_industry_jobs> getJobs() {
		if (cacheJobs == null) {
			synchronized (this) {
				if (cacheJobs == null) {
					cacheJobs = con.raw.cache.characters.industry_jobs(con.characterId(), false);
				}
			}
		}
		return cacheJobs;
	}

	private ObsListHolder<R_get_characters_character_id_industry_jobs> cacheResearchJobs = null;

	public ObsListHolder<R_get_characters_character_id_industry_jobs> getResearchJobs() {
		if (cacheResearchJobs == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheResearchJobs == null) {
					cacheResearchJobs = jobs.filter(j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j));
				}
			}
		}
		return cacheResearchJobs;
	}

	private ObsListHolder<R_get_characters_character_id_industry_jobs> cacheInventJobs = null;

	public ObsListHolder<R_get_characters_character_id_industry_jobs> getInventJobs() {
		if (cacheInventJobs == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheInventJobs == null) {
					cacheInventJobs = jobs.filter(Industry::isInvention);
				}
			}
		}
		return cacheInventJobs;
	}

	private ObsListHolder<R_get_characters_character_id_industry_jobs> cacheCopyJobs = null;

	public ObsListHolder<R_get_characters_character_id_industry_jobs> getCopyJobs() {
		if (cacheCopyJobs == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheCopyJobs == null) {
					cacheCopyJobs = jobs.filter(Industry::isCopy);
				}
			}
		}
		return cacheCopyJobs;
	}

	private ObsListHolder<R_get_characters_character_id_industry_jobs> cacheProductionJobs = null;

	public ObsListHolder<R_get_characters_character_id_industry_jobs> getProductionJobs() {
		if (cacheProductionJobs == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheProductionJobs == null) {
					cacheProductionJobs = jobs.filter(Industry::isManufacture);
				}
			}
		}
		return cacheProductionJobs;
	}

	private ObsSetHolder<Long> cachedUsedBPs = null;

	public ObsSetHolder<Long> getUsedBPs() {
		if (cachedUsedBPs == null) {
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = getJobs();
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedUsedBPs == null) {
					cachedUsedBPs = jobs.mapItems(j -> j.blueprint_id).distinct();
				}
			});
		}
		return cachedUsedBPs;
	}

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

	//
	// blueprints
	//

	private ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> blueprints = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> getBlueprints() {
		if (blueprints == null) {
			synchronized (this) {
				if (blueprints == null) {
					blueprints = con.raw.cache.characters.blueprints(con.characterId())
							.toMap(b -> b.item_id,
									this::convertBlueprint);
				}
			}
		}
		return blueprints;
	}

	/**
	 * copy the structure of a character bp to a corporation bp.
	 *
	 * @param source
	 * @return
	 */
	protected R_get_corporations_corporation_id_blueprints convertBlueprint(
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
