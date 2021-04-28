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
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.numbers.BoolHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class Skills {

	@Getter
	@Accessors(fluent = true)
	private final ESIAccount con;

	//
	// access to skills
	//

	/**
	 *
	 * the list of skills of this character. To have the access to the skills
	 * values, use instead {@link #ID2Level()} or {@link #name2Level()}, which are
	 * cached.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ListHolder<get_characters_character_id_skills_skills> list = con.connection().cache().characters
	.skills(con.characterId()).toList(c -> Arrays.asList(c.skills));

	/**
	 * character skill ids to active level
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final MapHolder<Integer, Integer> ID2Level = list().toMap(s -> s.skill_id, s -> s.active_skill_level);

	/**
	 * character skill names to active level
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final MapHolder<String, Integer> name2Level = list()
	.toMap(s -> ESIStatic.INSTANCE.cache().universe.types(s.skill_id).get().name, s -> s.active_skill_level);

	//
	// training
	//

	@Getter(lazy = true)
	private final ListHolder<R_get_characters_character_id_skillqueue> queue = con().connection().cache().characters
	.skillqueue(con().characterId());

	@Getter(lazy = true)
	private final ObjHolder<R_get_characters_character_id_skillqueue> training = makeTraining();

	protected ObjHolder<R_get_characters_character_id_skillqueue> makeTraining() {
		return getQueue().map(l -> {
			LocalDateTime now = LocalDateTime.now();
			R_get_characters_character_id_skillqueue ret = l.stream()
					.filter(sq -> sq.finish_date != null && now.isBefore(ESITools.convertLocalDateTime(sq.finish_date))).findFirst()
					.orElse(null);
			if (ret == null) {
				ret = new R_get_characters_character_id_skillqueue();
			}
			return ret;
		});
	}

	@Getter(lazy = true)
	private final ObjHolder<R_get_universe_types_type_id> trainingSkill = getTraining()
	.map(sk -> sk.skill_id == 0 ? new R_get_universe_types_type_id()
			: ESIAccess.INSTANCE.universe.cache().types(sk.skill_id).get());

	/**
	 * the acquisition rate, in SP/hour, for the current skill using estimated
	 * completion
	 */
	@Getter(lazy = true)
	private final DoubleHolder currentSkillAvgAcquisitionRate = getTraining().mapDouble(sk -> {
		if (sk.start_date == null || sk.finish_date == null) {
			return 0.0;
		}
		LocalDateTime start = ESITools.convertLocalDateTime(sk.start_date);
		LocalDateTime end = ESITools.convertLocalDateTime(sk.finish_date);
		long deltaTime = ChronoUnit.MINUTES.between(start, end);
		double ret = 60.0 * (sk.level_end_sp - sk.training_start_sp) / deltaTime;
		return ret;
	});

	/**
	 *
	 * @return a variable that is set to true if the character contains at least
	 *         one skill with a different active and trained level
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder hasLimitedskill = list()
	.test(l -> l.stream().filter(s -> s.active_skill_level != s.trained_skill_level).findAny().isPresent());;


}
