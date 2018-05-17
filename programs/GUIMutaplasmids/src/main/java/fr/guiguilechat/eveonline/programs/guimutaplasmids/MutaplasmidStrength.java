package fr.guiguilechat.eveonline.programs.guimutaplasmids;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.attributes.CapacitorNeed;
import fr.guiguilechat.eveonline.model.sde.items.attributes.Cpu;
import fr.guiguilechat.eveonline.model.sde.items.attributes.Power;
import fr.guiguilechat.eveonline.model.sde.items.attributes.SignatureRadiusBonus;
import fr.guiguilechat.eveonline.model.sde.items.attributes.SpeedFactor;

public enum MutaplasmidStrength {

	DECAYED(
			// fitting
			range(Cpu.INSTANCE, 0.95, 1.25), range(Power.INSTANCE, 0.95, 1.25),
			// propmod
			range(SpeedFactor.INSTANCE, 0.97, 1.035), range(CapacitorNeed.INSTANCE, 0.85, 1.2),
			// MWD
			range(SignatureRadiusBonus.INSTANCE, 0.9, 1.05));

	public final Map<Integer, AttributeRange> modifiers;

	private MutaplasmidStrength(AttributeRange... attributeModifiers) {
		modifiers = Collections
				.unmodifiableMap(Stream.of(attributeModifiers).collect(Collectors.toMap(ar -> ar.attribute.getId(), ar -> ar)));
	}

	public static class AttributeRange {
		public final double min;
		public final double max;
		public final Attribute attribute;

		public AttributeRange(Attribute attribute, double min, double max) {
			this.attribute = attribute;
			this.min = min;
			this.max = max;
		}

	}

	public static AttributeRange range(Attribute attribute, double min, double max) {
		return new AttributeRange(attribute, min, max);
	}
}
