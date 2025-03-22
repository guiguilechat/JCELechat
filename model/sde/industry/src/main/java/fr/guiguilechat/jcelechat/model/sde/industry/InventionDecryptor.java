package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.exports.common.ArchiveManager;
import fr.guiguilechat.jcelechat.libs.exports.common.MapIntSerializer;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * decryptors used in invention.
 *
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class InventionDecryptor {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<InventionDecryptor> storage = new MapIntSerializer<>(
			"SDE/industry/decryptors.yaml",
			InventionDecryptor.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, InventionDecryptor>> archives = new ArchiveManager<>(
			"SDE/industry/decryptors/",
			storage()::load);

	/**
	 * load the archived list for given date.
	 */
	public static Map<Integer, InventionDecryptor> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing ids once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static InventionDecryptor of(int id, Instant date) {
		InventionDecryptor ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && missingIds.add(id)) {
			log.warn("unknown id " + id);
		}
		return ret;
	}

	public static InventionDecryptor of(int id) {
		return of(id, null);
	}

	//
	// structure
	//

	//

	/**
	 * artificial null decryptor to use when no decryptor actually used.
	 */

	public static InventionDecryptor NULLDECRYPTOR = new InventionDecryptor();

	public int typeId = 0;
	public String name = "no decryptor";
	public int maxRuns = 0;
	public int me = 0;
	public int te = 0;
	public double probMult = 1.0;

	////
	// interaction with invention
	////

	/**
	 * get the effective ME of an invented blueprint from a copy of given one
	 *
	 * @param target
	 *          the blueprint copy, can(should) be null
	 * @return the effective ME, if success
	 */
	public int getMe(Blueprint... target) {
		return 2 + me();
	}

	/**
	 * get the effective TE of an invented blueprint from a copy of given one
	 *
	 * @param target
	 *          the blueprint copy, can(should) be null
	 * @return the effective TE, if success
	 */
	public int getTe(Blueprint... target) {
		return 4 + te();
	}

	/**
	 * get the effective number of runs of an invented blueprint from a copy of
	 * given one
	 *
	 * @param target
	 *          the blueprint copy t1
	 * @return the effective number of runs, if success
	 */
	public int getMaxRuns(Blueprint target) {
		return target.invention.products.get(0).quantity + maxRuns();
	}

	/**
	 * get the probability of success of inventing a product bpc from a copy of a
	 * blueprint
	 *
	 * @param target
	 *          the blueprint copy
	 * @param invented
	 *          one of the products of the invention from target
	 * @param skills
	 *          the skills of the inventor
	 * @return the probability (base 1) to success based only on the parameters
	 *         given.
	 */
	public double getProbability(Blueprint target, MaterialProd<?> invented, Map<Integer, Integer> skills) {
		if (skills == null) {
			skills = Collections.emptyMap();
		}
		int skillsProbaPoints_base120 = 120;

		for (TypeRef<Skill> skillRef : target.invention.skills.keySet()) {
			Skill sk = skillRef.type();
			int skillMult = inventionProbaIncr_base120(sk);
			if (skillMult != 0) {
				skillsProbaPoints_base120 += skillMult * skills.getOrDefault(sk.id, 0);
			}
		}

		float skillsProbaMult = 1.0f * skillsProbaPoints_base120 / 120;
		double ret = Math.min(1.0, invented.probability * skillsProbaMult * probMult());
		log.trace(
		    " invent from " + target.name() + "with dec=" + name() + " gives success probability=" + ret + " bp value="
						+ invented.probability + " skills=" + skillsProbaMult + " decryptormult=" + probMult());
		return ret;
	}

	@SuppressWarnings("serial")
	public static final Map<Integer, Integer> ALL_5 = new HashMap<>() {
		public Integer get(Object key) {
			return 5;
		}
	};

	/**
	 * get the increase, in base 120 points and per skill level, of the invention
	 * probability when using a BP whose invention activity requires that skill.
	 *
	 * @param skill
	 *          the skill required
	 * @return the increase in points base 120
	 */
	public static int inventionProbaIncr_base120(Skill skill) {
		// encryption methods require hacking
		if (skill.requiredskill1 == 21718) {
			return 3;
		}
		// hardcoded sleeper encryption methods.
		if (skill.id == 3408) {
			return 3;
		}
		return 4;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null && obj.getClass() == this.getClass()) {
			InventionDecryptor o = (InventionDecryptor) obj;
			return o.typeId == typeId;
		}
		return false;
	}

}
