package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Supplier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeDogma;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeDogma.EAttributes;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeDogma.Eeffects;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.EffectService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect.TypeEffect;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect.TypeEffectService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * update the {@link TypeAttribute} and {@link TypeEffect} from the typeDogma
 * entry.
 */
@RequiredArgsConstructor
@Service
@ConfigurationProperties(prefix = "sde.items.typedogma")
@Slf4j
public class TypeDogmaUpdater implements SdeListener {

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final AttributeService attributeService;

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final EffectService effectService;

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final TypeAttributeService typeAttributeService;

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final TypeEffectService typeEffectService;

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final TypeService typeService;

	private static final String FILENAME = EtypeDogma.LOADER.yamlFileName();

	private boolean receivedFile = false;

	@Getter
	@Setter
	protected boolean skip = false;

	@Override
	public void beforeSdeUpdate() {
		if (skip) {
			return;
		}
		receivedFile = false;
	}

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		if (skip) {
			return;
		}
		if (entryName.equals(FILENAME)) {
			log.debug("{} processing file {}",
					getClass().getSimpleName(),
					FILENAME);
			long startTime = System.currentTimeMillis();
			LinkedHashMap<Integer, EtypeDogma> sources = EtypeDogma.LOADER.yaml().from(fileContent.get());
			typeAttributeService().delete();
			typeEffectService().delete();

			List<TypeAttribute> typeAttributes = new ArrayList<>();
			List<TypeEffect> typeEffects = new ArrayList<>();
			for (Entry<Integer, EtypeDogma> e : sources.entrySet()) {
				int typeId = e.getKey();
				for (EAttributes da : e.getValue().dogmaAttributes) {
					typeAttributes.add(TypeAttribute.of(typeId, da.attributeID, da.value));
				}
				for (Eeffects eff : e.getValue().dogmaEffects) {
					typeEffects.add(TypeEffect.of(typeId, eff.effectID, eff.isDefault));
				}
			}
			log.trace(" saving " + typeAttributes.size() + " typeAttributes");
			typeAttributeService().saveAll(typeAttributes);
			log.trace(" saving " + typeEffects.size() + " typeEffects");
			typeEffectService().saveAll(typeEffects);
			receivedFile = true;
			log.info("{} processed {} typeAttribute and {} typeEffect in {} ms",
					getClass().getSimpleName(),
					typeAttributes.size(),
					typeEffects.size(),
					System.currentTimeMillis() - startTime);
		}
	}

	@Override
	public void afterSdeUpdate() {
		if (skip) {
			return;
		}
		if (!receivedFile) {
			log.warn("service {} did not receive file {}",
					getClass().getSimpleName(),
					FILENAME);
		}
	}

}
