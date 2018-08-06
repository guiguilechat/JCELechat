package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AuditLogSecureContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Biomass;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.CargoContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.EffectBeacon;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.FreightContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.HarvestableCloud;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.LargeCollidableObject;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.OrbitalTarget;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.SecureCargoContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.StationImprovementPlatform;

public abstract class Celestial
    extends Item
{

    @Override
    public int getCategoryId() {
        return  2;
    }

    @Override
    public Class<?> getCategory() {
        return Celestial.class;
    }

    public static Map<String, ? extends Celestial> loadCategory() {
        return Stream.of(AuditLogSecureContainer.load(), Biomass.load(), CargoContainer.load(), EffectBeacon.load(), FreightContainer.load(), HarvestableCloud.load(), LargeCollidableObject.load(), OrbitalTarget.load(), SecureCargoContainer.load(), StationImprovementPlatform.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
