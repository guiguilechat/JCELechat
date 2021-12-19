package fr.guiguilechat.jcelechat.model.sde.types.infrastructureupgrades;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DevIndexIndustrial;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionID01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionID02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionID03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDAngel01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDBlood01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDGurista01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDRogueDrone01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSansha01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSerpentis01;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SovUpgradeRequiredUpgradeID;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;
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
    public int devindexindustrial;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionid01;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionid02;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionid03;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidangel01;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidblood01;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidgurista01;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidroguedrone01;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidsansha01;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidserpentis01;
    /**
     * The typeID of the upgrade that is required for this upgrade to be installed. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int sovupgraderequiredupgradeid;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE, DistributionIDSansha01 .INSTANCE, DistributionIDBlood01 .INSTANCE, DevIndexIndustrial.INSTANCE, DistributionIDSerpentis01 .INSTANCE, DistributionIDGurista01 .INSTANCE, DistributionID01 .INSTANCE, DistributionID02 .INSTANCE, DistributionIDRogueDrone01 .INSTANCE, DistributionID03 .INSTANCE, DistributionIDAngel01 .INSTANCE, SovUpgradeRequiredUpgradeID.INSTANCE })));
    public static final IndustrialUpgrades.MetaGroup METAGROUP = new IndustrialUpgrades.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1584 :
            {
                return devindexindustrial;
            }
            case  1755 :
            {
                return distributionid01;
            }
            case  1756 :
            {
                return distributionid02;
            }
            case  1757 :
            {
                return distributionid03;
            }
            case  1695 :
            {
                return distributionidangel01;
            }
            case  1705 :
            {
                return distributionidblood01;
            }
            case  1715 :
            {
                return distributionidgurista01;
            }
            case  1725 :
            {
                return distributionidroguedrone01;
            }
            case  1735 :
            {
                return distributionidsansha01;
            }
            case  1745 :
            {
                return distributionidserpentis01;
            }
            case  1599 :
            {
                return sovupgraderequiredupgradeid;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<IndustrialUpgrades> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IndustrialUpgrades>
    {
        public static final String RESOURCE_PATH = "SDE/types/infrastructureupgrades/IndustrialUpgrades.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IndustrialUpgrades.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IndustrialUpgrades> types;
        }
    }
}
