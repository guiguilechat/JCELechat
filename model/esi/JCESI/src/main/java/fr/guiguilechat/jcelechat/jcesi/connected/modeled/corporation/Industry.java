package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Industry {

	private final ESIAccount con;

	public Industry(ESIAccount con) {
		this.con = con;
	}

	private ObsListHolder<R_get_corporations_corporation_id_industry_jobs> cachedJobs = null;

	public ObsListHolder<R_get_corporations_corporation_id_industry_jobs> getJobs() {
		if (cachedJobs == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedJobs == null) {
					cachedJobs = con.raw.cache.corporations.industry_jobs(con.corporation.getId(), false);
				}
			});
		}
		return cachedJobs;
	}

	//
	// research
	//

	private ObsListHolder<R_get_corporations_corporation_id_industry_jobs> cacheResearchJobs = null;

	public ObsListHolder<R_get_corporations_corporation_id_industry_jobs> getResearchJobs() {
		if (cacheResearchJobs == null) {
			ObsListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheResearchJobs == null) {
					cacheResearchJobs = jobs.filter(j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j));
				}
			}
		}
		return cacheResearchJobs;
	}

	//
	// invent
	//

	private ObsListHolder<R_get_corporations_corporation_id_industry_jobs> cacheInventJobs = null;

	public ObsListHolder<R_get_corporations_corporation_id_industry_jobs> getInventJobs() {
		if (cacheInventJobs == null) {
			ObsListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheInventJobs == null) {
					cacheInventJobs = jobs.filter(Industry::isInvention);
				}
			}
		}
		return cacheInventJobs;
	}

	//
	// copy
	//

	private ObsListHolder<R_get_corporations_corporation_id_industry_jobs> cacheCopyJobs = null;

	public ObsListHolder<R_get_corporations_corporation_id_industry_jobs> getCopyJobs() {
		if (cacheCopyJobs == null) {
			ObsListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getJobs();
			synchronized (jobs) {
				if (cacheCopyJobs == null) {
					cacheCopyJobs = jobs.filter(Industry::isCopy);
				}
			}
		}
		return cacheCopyJobs;
	}

	private ObsMapHolder<Integer, Long> cacheCopy = null;

	public ObsMapHolder<Integer, Long> getCopy() {
		if (cacheCopy == null) {
			ObsListHolder<R_get_corporations_corporation_id_industry_jobs> copyjobs = getCopyJobs();
			synchronized (copyjobs) {
				if (cacheCopy == null) {
					cacheCopy = copyjobs.toMap(j -> j.product_type_id, j -> (long) j.runs, Long::sum);
				}
			}
		}
		return cacheCopy;
	}

	//
	// manufacturing
	//

	private ObsListHolder<R_get_corporations_corporation_id_industry_jobs> cacheProductionJobs = null;

	public ObsListHolder<R_get_corporations_corporation_id_industry_jobs> getProductionJobs() {
		if (cacheProductionJobs == null) {
			ObsListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getJobs();
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
			ObsListHolder<R_get_corporations_corporation_id_industry_jobs> jobs = getJobs();
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedUsedBPs == null) {
					cachedUsedBPs = jobs.mapItems(j -> j.blueprint_id).distinct();
				}
			});
		}
		return cachedUsedBPs;
	}

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

	// get the blueprints

	private ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> blueprints = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> getBlueprints() {
		if (blueprints == null) {
			synchronized (this) {
				if (blueprints == null) {
					blueprints = con.raw.cache.corporations.blueprints(con.corporation.getId()).toMap(b -> b.item_id, b -> b);
				}
			}
		}
		return blueprints;
	}

}
