package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.AuditLogSecureContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.Biomass;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.CargoContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.FreightContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.HarvestableCloud;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.OrbitalTarget;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.SecureCargoContainer;
import fr.guiguilechat.jcelechat.model.sde.items.types.celestial.StationImprovementPlatform;

public abstract class Celestial
    extends Item
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
