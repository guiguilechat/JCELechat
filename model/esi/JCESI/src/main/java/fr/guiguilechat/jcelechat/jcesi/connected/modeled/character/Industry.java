package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_blueprints_location_flag;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;

public class Industry {

	private final ESIAccount con;

	public Industry(ESIAccount con) {
		this.con = con;
	}

	//
	// industry jobs
	//

	/**
	 * fetch the list of industry jobs for this character. Completed jobs are
	 * ignored.
	 *
	 * @return the internal cache of the jobs for this character. successive calls
	 *         will return the same value.
	 *
	 */

	private ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> industryJobs = null;

	public ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> getIndustryJobs() {
		if (industryJobs == null) {
			synchronized (this) {
				if (industryJobs == null) {
					industryJobs = con.raw.cache.characters.industry_jobs(con.characterId(), false).toMap(j -> j.job_id);
				}
			}
		}
		return industryJobs;
	}

	private ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> researchJobs = null;

	public ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> getResearchJobs() {
		ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> industryJobs = getIndustryJobs();
		if (researchJobs == null) {
			synchronized (industryJobs) {
				if (researchJobs == null) {
					researchJobs = industryJobs.filter(null, j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j));
				}
			}
		}
		return researchJobs;
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
					blueprints = ObsMapHolderImpl.toMap(con.raw.cache.characters.blueprints(con.characterId()), b -> b.item_id,
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
