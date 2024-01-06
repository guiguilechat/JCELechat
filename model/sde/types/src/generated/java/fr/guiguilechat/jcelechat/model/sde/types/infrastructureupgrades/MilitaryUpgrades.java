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
import fr.guiguilechat.jcelechat.model.sde.attributes.DevIndexMilitary;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionID01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDAngel01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDAngel02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDAngel03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDAngel04;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDAngel05;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDBlood01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDBlood02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDBlood03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDBlood04;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDBlood05;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDGurista01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDGurista02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDGurista03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDGurista04;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDGurista05;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDRogueDrone01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDRogueDrone02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDRogueDrone03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDRogueDrone04;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDRogueDrone05;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSansha01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSansha02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSansha03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSansha04;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSansha05;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSerpentis01;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSerpentis02;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSerpentis03;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSerpentis04;
import fr.guiguilechat.jcelechat.model.sde.attributes.DistributionIDSerpentis05;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SovUpgradeRequiredUpgradeID;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class MilitaryUpgrades
    extends InfrastructureUpgrades
{
    /**
     * The required minimum military development index level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int devindexmilitary;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionid01;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidangel01;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidangel02;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidangel03;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidangel04;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidangel05;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidblood01;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidblood02;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidblood03;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidblood04;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidblood05;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidgurista01;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidgurista02;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidgurista03;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidgurista04;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidgurista05;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidroguedrone01;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidroguedrone02;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidroguedrone03;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidroguedrone04;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidroguedrone05;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidsansha01;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidsansha02;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidsansha03;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidsansha04;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidsansha05;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidserpentis01;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidserpentis02;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidserpentis03;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidserpentis04;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int distributionidserpentis05;
    /**
     * The typeID of the upgrade that is required for this upgrade to be installed. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int sovupgraderequiredupgradeid;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DistributionIDRogueDrone04 .INSTANCE, DistributionIDRogueDrone05 .INSTANCE, DistributionIDSansha01 .INSTANCE, DistributionIDSansha02 .INSTANCE, DistributionIDSansha03 .INSTANCE, DistributionIDSansha04 .INSTANCE, DistributionIDSansha05 .INSTANCE, DistributionIDSerpentis01 .INSTANCE, DistributionIDSerpentis02 .INSTANCE, DistributionIDSerpentis03 .INSTANCE, DistributionIDSerpentis04 .INSTANCE, DistributionIDSerpentis05 .INSTANCE, DistributionID01 .INSTANCE, DistributionIDAngel01 .INSTANCE, DistributionIDAngel02 .INSTANCE, DistributionIDAngel03 .INSTANCE, DistributionIDAngel04 .INSTANCE, Radius.INSTANCE, DistributionIDAngel05 .INSTANCE, Capacity.INSTANCE, DistributionIDBlood01 .INSTANCE, DistributionIDBlood02 .INSTANCE, DistributionIDBlood03 .INSTANCE, DistributionIDBlood04 .INSTANCE, DistributionIDBlood05 .INSTANCE, DevIndexMilitary.INSTANCE, DistributionIDGurista01 .INSTANCE, DistributionIDGurista02 .INSTANCE, DistributionIDGurista03 .INSTANCE, DistributionIDGurista04 .INSTANCE, DistributionIDGurista05 .INSTANCE, DistributionIDRogueDrone01 .INSTANCE, DistributionIDRogueDrone02 .INSTANCE, DistributionIDRogueDrone03 .INSTANCE, SovUpgradeRequiredUpgradeID.INSTANCE })));
    public static final MilitaryUpgrades.MetaGroup METAGROUP = new MilitaryUpgrades.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1583 :
            {
                return devindexmilitary;
            }
            case  1755 :
            {
                return distributionid01;
            }
            case  1695 :
            {
                return distributionidangel01;
            }
            case  1696 :
            {
                return distributionidangel02;
            }
            case  1697 :
            {
                return distributionidangel03;
            }
            case  1698 :
            {
                return distributionidangel04;
            }
            case  1699 :
            {
                return distributionidangel05;
            }
            case  1705 :
            {
                return distributionidblood01;
            }
            case  1706 :
            {
                return distributionidblood02;
            }
            case  1707 :
            {
                return distributionidblood03;
            }
            case  1708 :
            {
                return distributionidblood04;
            }
            case  1709 :
            {
                return distributionidblood05;
            }
            case  1715 :
            {
                return distributionidgurista01;
            }
            case  1716 :
            {
                return distributionidgurista02;
            }
            case  1717 :
            {
                return distributionidgurista03;
            }
            case  1718 :
            {
                return distributionidgurista04;
            }
            case  1719 :
            {
                return distributionidgurista05;
            }
            case  1725 :
            {
                return distributionidroguedrone01;
            }
            case  1726 :
            {
                return distributionidroguedrone02;
            }
            case  1727 :
            {
                return distributionidroguedrone03;
            }
            case  1728 :
            {
                return distributionidroguedrone04;
            }
            case  1729 :
            {
                return distributionidroguedrone05;
            }
            case  1735 :
            {
                return distributionidsansha01;
            }
            case  1736 :
            {
                return distributionidsansha02;
            }
            case  1737 :
            {
                return distributionidsansha03;
            }
            case  1738 :
            {
                return distributionidsansha04;
            }
            case  1739 :
            {
                return distributionidsansha05;
            }
            case  1745 :
            {
                return distributionidserpentis01;
            }
            case  1746 :
            {
                return distributionidserpentis02;
            }
            case  1747 :
            {
                return distributionidserpentis03;
            }
            case  1748 :
            {
                return distributionidserpentis04;
            }
            case  1749 :
            {
                return distributionidserpentis05;
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
    public IMetaGroup<MilitaryUpgrades> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MilitaryUpgrades>
    {
        public static final String RESOURCE_PATH = "SDE/types/infrastructureupgrades/MilitaryUpgrades.yaml";
        private Map<Integer, MilitaryUpgrades> cache = (null);

        @Override
        public IMetaCategory<? super MilitaryUpgrades> category() {
            return InfrastructureUpgrades.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1021;
        }

        @Override
        public String getName() {
            return "MilitaryUpgrades";
        }

        @Override
        public synchronized Map<Integer, MilitaryUpgrades> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MilitaryUpgrades.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, MilitaryUpgrades> types;
        }
    }
}
