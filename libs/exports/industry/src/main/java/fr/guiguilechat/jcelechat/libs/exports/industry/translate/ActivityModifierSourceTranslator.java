package fr.guiguilechat.jcelechat.libs.exports.industry.translate;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.exports.industry.ActivityModifierSource;
import fr.guiguilechat.jcelechat.libs.exports.industry.ActivityModifierSource.ModifiedActivity;
import fr.guiguilechat.jcelechat.libs.exports.industry.ActivityModifierSource.ModifiedActivity.AttributeFilter;
import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.attribute.FilteredAttributeReference;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivityModifierSources;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivityModifierSources.IndustryActivityModifier;

public class ActivityModifierSourceTranslator {

	public void translate(ClientCache cc,
			LinkedHashMap<Integer, ActivityModifierSource> activityModifierSources)
			throws JsonMappingException, JsonProcessingException, SQLException {
		List<KeyValTime<EindustryActivityModifierSources>> loaded = EindustryActivityModifierSources.getLoader()
				.load(cc);
		loaded.stream()
				.sorted(Comparator.comparing(KeyValTime::getKey))
				.map(this::translate)
				.forEach(ams -> activityModifierSources.put(ams.typeId, ams));
	}

	ActivityModifierSource translate(KeyValTime<EindustryActivityModifierSources> source) {
		if (source == null) {
			return null;
		}
		ActivityModifierSource ret = new ActivityModifierSource();
		EindustryActivityModifierSources val = source.getVal();
		ret.typeId = source.getKey();
		ret.copying = translate(val.copying);
		ret.invention = translate(val.invention);
		ret.manufacturing = translate(val.manufacturing);
		ret.reaction = translate(val.reaction);
		ret.researchMaterial = translate(val.researchMaterial);
		ret.researchTime = translate(val.researchTime);
		return ret;
	}

	ModifiedActivity translate(IndustryActivityModifier source) {
		if (source == null) {
			return null;
		}
		ModifiedActivity ret = new ModifiedActivity();
		ret.cost = source.cost.stream()
				.sorted(Comparator.comparing(far -> far.attributeId))
				.map(this::translate)
				.toList();
		ret.material = source.material.stream()
				.sorted(Comparator.comparing(far -> far.attributeId))
				.map(this::translate)
				.toList();
		ret.time = source.time.stream()
				.sorted(Comparator.comparing(far -> far.attributeId))
				.map(this::translate)
				.toList();
		return ret;
	}

	AttributeFilter translate(FilteredAttributeReference source) {
		if (source == null) {
			return null;
		}
		AttributeFilter ret = new AttributeFilter();
		ret.attributeId = source.attributeId;
		ret.filterId = source.filterId;
		return ret;
	}

}
