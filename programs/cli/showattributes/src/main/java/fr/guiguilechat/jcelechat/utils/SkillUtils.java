package fr.guiguilechat.jcelechat.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

public class SkillUtils {

	/**
	 * 250 * skillTimeConstant * sqrt(32)^(skillLevel - 1)
	 *
	 * @param sk
	 * @param level
	 * @return total SP required to train skill from 0 to level
	 */
	public static long totalSP(Skill sk, int level) {
		if (sk == null || level < 1) {
			return 0l;
		}
		return (long) Math.ceil(250.0 * sk.skilltimeconstant * Math.pow(Math.sqrt(32), level - 1));
	}

	/**
	 *
	 * @param sk
	 * @param level
	 * @return the amount of SP required specifically to train that level.
	 */
	public static long levelSP(Skill sk, int level) {
		return totalSP(sk, level) - totalSP(sk, level - 1);
	}

	/**
	 *
	 * @param sk
	 * @return the total amount of SP required to inject the skill
	 */
	public static long totalSPInject(Skill sk) {
		long total = 0l;
		HashMap<Skill, Integer> skillIdToLevel = new HashMap<>();
		addRequiredLevels(sk, skillIdToLevel);
		for (Entry<Skill, Integer> e : skillIdToLevel.entrySet()) {
			total += totalSP(e.getKey(), e.getValue());
		}
		return total;
	}

	private static Map<Attribute, Attribute> requiredToLevel = null;

	public static Map<Attribute, Attribute> requiredToLevel() {
		if (requiredToLevel == null) {
			HashMap<Attribute, Attribute> ret = new HashMap<>();
			ret.put(RequiredSkill1.INSTANCE, RequiredSkill1Level.INSTANCE);
			ret.put(RequiredSkill2.INSTANCE, RequiredSkill2Level.INSTANCE);
			ret.put(RequiredSkill3.INSTANCE, RequiredSkill3Level.INSTANCE);
			ret.put(RequiredSkill4.INSTANCE, RequiredSkill4Level.INSTANCE);
			ret.put(RequiredSkill5.INSTANCE, RequiredSkill5Level.INSTANCE);
			requiredToLevel = Collections.unmodifiableMap(ret);
		}
		return requiredToLevel;
	}

	/**
	 * recursively add all the required skill in a map. Only skills that are not
	 * already present are recursively added ( to avoid cycles)
	 *
	 * @param sk
	 * @param requirements
	 *          existing map.
	 */
	protected static void addRequiredLevels(Skill sk, Map<Skill, Integer> requirements) {
		for (Entry<Attribute, Attribute> e : requiredToLevel().entrySet()) {
			Number skillId = sk.valueSet(e.getKey());
			if (skillId != null && skillId.intValue()!=0) {
				Skill required = Skill.METACAT.load().values().stream().filter(mb -> mb.id == skillId.intValue()).findFirst()
						.get();
				int level = sk.valueSet(e.getValue()).intValue();
				Integer old = requirements.put(required, Math.max(level, requirements.getOrDefault(required, 0)));
				if (old == null || old != level) {
					addRequiredLevels(required, requirements);
				}
			}
		}
	}

	public static void main(String[] args) {
		HashMap<Skill, Integer> skill2SP = new HashMap<>();
		for (Skill sk : Skill.METACAT.load().values()) {
			skill2SP.put(sk, (int) totalSPInject(sk));
		}
		ArrayList<Entry<Skill, Integer>> l = new ArrayList<>(skill2SP.entrySet());
		Collections.sort(l, Comparator.comparing(Entry::getValue));
		for (Entry<Skill, Integer> e : l) {
			System.out.println(e.getKey() + "\t" + e.getValue());
		}
	}

}
