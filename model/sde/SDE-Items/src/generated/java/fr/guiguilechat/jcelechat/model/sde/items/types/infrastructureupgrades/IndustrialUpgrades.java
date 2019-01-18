package fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.InfrastructureUpgrades;
import org.yaml.snakeyaml.Yaml;

public class IndustrialUpgrades
    extends InfrastructureUpgrades
{
    /**
     * The minimum required industrial development index level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DevIndexIndustrial;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionID01;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionID02;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionID03;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDAngel01;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDBlood01;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDGurista01;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDRogueDrone01;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSansha01;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSerpentis01;
    /**
     * The typeID of the upgrade that is required for this upgrade to be installed. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SovUpgradeRequiredUpgradeID;
    public static final IndustrialUpgrades.MetaGroup METAGROUP = new IndustrialUpgrades.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1584 :
            {
                return DevIndexIndustrial;
            }
            case  1755 :
            {
                return DistributionID01;
            }
            case  1756 :
            {
                return DistributionID02;
            }
            case  1757 :
            {
                return DistributionID03;
            }
            case  1695 :
            {
                return DistributionIDAngel01;
            }
            case  1705 :
            {
                return DistributionIDBlood01;
            }
            case  1715 :
            {
                return DistributionIDGurista01;
            }
            case  1725 :
            {
                return DistributionIDRogueDrone01;
            }
            case  1735 :
            {
                return DistributionIDSansha01;
            }
            case  1745 :
            {
                return DistributionIDSerpentis01;
            }
            case  1599 :
            {
                return SovUpgradeRequiredUpgradeID;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<IndustrialUpgrades> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IndustrialUpgrades>
    {
        public static final String RESOURCE_PATH = "SDE/items/infrastructureupgrades/IndustrialUpgrades.yaml";
        private Map<String, IndustrialUpgrades> cache = (null);

        @Override
        public IMetaCategory<? super IndustrialUpgrades> category() {
            return InfrastructureUpgrades.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1020;
        }

        @Override
        public String getName() {
            return "IndustrialUpgrades";
        }

        @Override
        public synchronized Map<String, IndustrialUpgrades> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IndustrialUpgrades.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IndustrialUpgrades> items;
        }
    }
}
