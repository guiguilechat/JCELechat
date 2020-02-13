package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.AuditLogSecureContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.Biomass;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.CargoContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.FreightContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.HarvestableCloud;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.OrbitalTarget;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.SecureCargoContainer;
import fr.guiguilechat.jcelechat.model.sde.types.celestial.StationImprovementPlatform;

public abstract class Celestial
    extends EveType
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    public static final Celestial.MetaCat METACAT = new Celestial.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return Hp;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
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
            return Arrays.asList(CargoContainer.METAGROUP, Biomass.METAGROUP, SecureCargoContainer.METAGROUP, AuditLogSecureContainer.METAGROUP, FreightContainer.METAGROUP, HarvestableCloud.METAGROUP, StationImprovementPlatform.METAGROUP, OrbitalTarget.METAGROUP);
        }
    }
}
