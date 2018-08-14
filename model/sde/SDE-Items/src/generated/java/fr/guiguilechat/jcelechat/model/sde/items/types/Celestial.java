package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
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
    public final static Celestial.MetaCat METACAT = new Celestial.MetaCat();

    @Override
    public IMetaCategory<Celestial> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Celestial>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Celestial> [] groups = new IMetaGroup[] {CargoContainer.METAGROUP, Biomass.METAGROUP, LargeCollidableObject.METAGROUP, SecureCargoContainer.METAGROUP, AuditLogSecureContainer.METAGROUP, FreightContainer.METAGROUP, HarvestableCloud.METAGROUP, StationImprovementPlatform.METAGROUP, EffectBeacon.METAGROUP, OrbitalTarget.METAGROUP };

        @Override
        public int getCategoryId() {
            return  2;
        }

        @Override
        public String getName() {
            return "Celestial";
        }

        @Override
        public Collection<IMetaGroup<? extends Celestial>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Celestial> load() {
            HashMap<String, Celestial> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
