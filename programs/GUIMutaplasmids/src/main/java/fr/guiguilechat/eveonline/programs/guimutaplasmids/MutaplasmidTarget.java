package fr.guiguilechat.eveonline.programs.guimutaplasmids;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.attributes.CapacitorNeed;
import fr.guiguilechat.eveonline.model.sde.items.attributes.Cpu;
import fr.guiguilechat.eveonline.model.sde.items.attributes.Power;
import fr.guiguilechat.eveonline.model.sde.items.attributes.SpeedFactor;
import fr.guiguilechat.eveonline.model.sde.items.types.module.PropulsionModule;

/**
 * each mutaplasmid applies to a given list of types.
 */
public enum MutaplasmidTarget {
	AB_1MN(PropulsionModule.load().entrySet().stream().filter(e -> e.getKey().contains(" 1mn ")).map(Entry::getValue),
			SpeedFactor.INSTANCE, Power.INSTANCE, Cpu.INSTANCE, CapacitorNeed.INSTANCE);

	public final Set<Item> allowedItems;
	public final Set<Attribute> modifiedAttributes;

	private MutaplasmidTarget(Stream<Item> allowedItems, Attribute... modifiedAttribute) {
		this.allowedItems = Collections.unmodifiableSet(allowedItems.collect(Collectors.toSet()));
		modifiedAttributes = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(modifiedAttribute)));
	}

}
