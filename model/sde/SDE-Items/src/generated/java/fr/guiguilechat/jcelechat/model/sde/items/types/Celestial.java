package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
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
    public final static Celestial.MetaCat METACAT = new Celestial.MetaCat();

    @Override
    public int getCategoryId() {
        return  2;
    }

    @Override
    public MetaCategory<Celestial> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Celestial> loadCategory() {
        return Stream.of(AuditLogSecureContainer.load(), Biomass.load(), CargoContainer.load(), EffectBeacon.load(), FreightContainer.load(), HarvestableCloud.load(), LargeCollidableObject.load(), OrbitalTarget.load(), SecureCargoContainer.load(), StationImprovementPlatform.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Celestial>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Celestial> [] groups = new MetaGroup[] {CargoContainer.METAGROUP, Biomass.METAGROUP, LargeCollidableObject.METAGROUP, SecureCargoContainer.METAGROUP, AuditLogSecureContainer.METAGROUP, FreightContainer.METAGROUP, HarvestableCloud.METAGROUP, StationImprovementPlatform.METAGROUP, EffectBeacon.METAGROUP, OrbitalTarget.METAGROUP };

        @Override
        public String getName() {
            return "Celestial";
        }

        public Collection<MetaGroup<? extends Celestial>> groups() {
            return Arrays.asList(groups);
        }
    }
}
