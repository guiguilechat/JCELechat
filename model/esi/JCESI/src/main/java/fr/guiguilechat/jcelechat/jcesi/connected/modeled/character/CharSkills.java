package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skillqueue;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_characters_character_id_skills_skills;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class CharSkills {

	private final ESIAccount con;

	public CharSkills(ESIAccount con) {
		this.con = con;
	}

	//
	// access to skills
	//

	private ObsListHolder<get_characters_character_id_skills_skills> skillsList = null;

	/**
	 *
	 * @return the list of skills of this character. To have the access to the
	 *         skills values, use instead {@link #ID2Level()} or
	 *         {@link #name2Level()}, which are cached.
	 */
	public ObsListHolder<get_characters_character_id_skills_skills> list() {
		if (skillsList == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (skillsList == null) {
					skillsList = con.raw.cache.characters.skills(con.characterId()).toList(c -> Arrays.asList(c.skills));
				}
			});
		}
		return skillsList;
	}

	private ObsMapHolder<Integer, Integer> ID2Level = null;

	public ObsMapHolder<Integer, Integer> ID2Level() {
		ObsListHolder<get_characters_character_id_skills_skills> skills = list();
		if (ID2Level == null) {
			LockWatchDog.BARKER.syncExecute(skills, () -> {
				if (ID2Level == null) {
					ID2Level = skills.toMap(s -> s.skill_id, s -> s.active_skill_level);
				}
			});
		}
		return ID2Level;
	}

	private ObsMapHolder<String, Integer> name2Level = null;

	public ObsMapHolder<String, Integer> name2Level() {
		ObsListHolder<get_characters_character_id_skills_skills> skills = list();
		if (name2Level == null) {
			LockWatchDog.BARKER.syncExecute(skills, () -> {
				if (name2Level == null) {
					name2Level = skills.toMap(s -> ESIStatic.INSTANCE.cache.universe.types(s.skill_id).get().name,
							s -> s.active_skill_level);
				}
			});
		}
		return name2Level;
	}

	//
	// training
	//

	public ObsListHolder<R_get_characters_character_id_skillqueue> getQueue() {
		return con.raw.cache.characters.skillqueue(con.characterId());
	}

	private ObsObjHolder<R_get_characters_character_id_skillqueue> training = null;

	public ObsObjHolder<R_get_characters_character_id_skillqueue> getTraining() {
		ObsListHolder<R_get_characters_character_id_skillqueue> trainlist = getQueue();
		if (training == null) {
			LockWatchDog.BARKER.syncExecute(trainlist, () -> {
				if(training==null) {
					training = trainlist.map(l -> {
						LocalDateTime now = LocalDateTime.now();
						R_get_characters_character_id_skillqueue ret = l.stream()
								.filter(sq -> sq.finish_date != null && now.isBefore(ESITools.convertDateLocal(sq.finish_date)))
								.findFirst().orElse(null);
						if (ret == null) {
							ret= new R_get_characters_character_id_skillqueue();
						}
						return ret;
					});
				}
			});
		}
		return training;
	}

	private ObsObjHolder<R_get_universe_types_type_id> trainingSkill = null;

	public ObsObjHolder<R_get_universe_types_type_id> getTrainingSkill() {
		ObsObjHolder<R_get_characters_character_id_skillqueue> training = getTraining();
		if (trainingSkill == null) {
			LockWatchDog.BARKER.syncExecute(training, () -> {
				if (trainingSkill == null) {
					trainingSkill = training
							.map(sk -> {
								R_get_universe_types_type_id ret = sk.skill_id == 0 ? new R_get_universe_types_type_id()
										: ESIAccess.INSTANCE.universe.cache.types(sk.skill_id).get();
								return ret;
							});
				}
			});
		}
		return trainingSkill;
	}

	private ObsDoubleHolder acquisitionRate = null;

	/** get the acquisition rate, in SP/hour */
	public ObsDoubleHolder getAcquisitionRate() {
		ObsObjHolder<R_get_characters_character_id_skillqueue> train = getTraining();
		if (acquisitionRate == null) {
			LockWatchDog.BARKER.syncExecute(train, () -> {
				if (acquisitionRate == null) {
					acquisitionRate = train.mapDouble(sk -> {
						if (sk.start_date == null || sk.finish_date == null) {
							return 0.0;
						}
						LocalDateTime start = ESITools.convertDateLocal(sk.start_date);
						LocalDateTime end = ESITools.convertDateLocal(sk.finish_date);
						long deltaTime = ChronoUnit.MINUTES.between(start, end);
						double ret = 60.0 * (sk.level_end_sp - sk.training_start_sp) / deltaTime;
						// System.err.println("training skill " + sk.skill_id + " started on
						// " + sk.start_date + " with "
						// + sk.training_start_sp + "SP, finish on " + sk.finish_date + "
						// with " + sk.level_end_sp
						// + " time in hours is " + deltaTime + " SP/h is " + ret);
						return ret;
					});
				}
			});
		}
		return acquisitionRate;
	}

}
