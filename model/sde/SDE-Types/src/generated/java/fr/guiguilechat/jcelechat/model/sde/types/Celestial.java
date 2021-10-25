package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.AuditLogSecureContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.Biomass;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.CargoContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.EncounterSurveillanceSystem;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.FreightContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.HarvestableCloud;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.SecureCargoContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.StationImprovementPlatform;

public abstract class Celestial
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Celestial.MetaCat METACAT = new Celestial.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Celestial> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Celestial>
    {

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
            return Arrays.asList(CargoContainer.METAGROUP, Biomass.METAGROUP, SecureCargoContainer.METAGROUP, AuditLogSecureContainer.METAGROUP, FreightContainer.METAGROUP, HarvestableCloud.METAGROUP, StationImprovementPlatform.METAGROUP, EncounterSurveillanceSystem.METAGROUP);
        }
    }
}
