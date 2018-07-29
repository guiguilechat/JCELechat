package fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.InfrastructureUpgrades;

public class MilitaryUpgrades
    extends InfrastructureUpgrades
{
    /**
     * The required minimum military development index level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DevIndexMilitary;
    /**
     * Distribution ID for global sov upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionID01;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDAngel01;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDAngel02;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDAngel03;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDAngel04;
    /**
     * Distribution ID for sov upgrades in Angel space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDAngel05;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDBlood01;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDBlood02;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDBlood03;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDBlood04;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDBlood05;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDGurista01;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDGurista02;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDGurista03;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDGurista04;
    /**
     * Distribution ID for sov upgrades in Guristas space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDGurista05;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDRogueDrone01;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDRogueDrone02;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDRogueDrone03;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDRogueDrone04;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDRogueDrone05;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSansha01;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSansha02;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSansha03;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSansha04;
    /**
     * Distribution ID for sov upgrades in Sansha space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSansha05;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSerpentis01;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSerpentis02;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSerpentis03;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSerpentis04;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DistributionIDSerpentis05;
    /**
     * The typeID of the upgrade that is required for this upgrade to be installed. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SovUpgradeRequiredUpgradeID;
    public final static String RESOURCE_PATH = "SDE/items/infrastructureupgrades/MilitaryUpgrades.yaml";
    private static LinkedHashMap<String, MilitaryUpgrades> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1583 :
            {
                return DevIndexMilitary;
            }
            case  1755 :
            {
                return DistributionID01;
            }
            case  1695 :
            {
                return DistributionIDAngel01;
            }
            case  1696 :
            {
                return DistributionIDAngel02;
            }
            case  1697 :
            {
                return DistributionIDAngel03;
            }
            case  1698 :
            {
                return DistributionIDAngel04;
            }
            case  1699 :
            {
                return DistributionIDAngel05;
            }
            case  1705 :
            {
                return DistributionIDBlood01;
            }
            case  1706 :
            {
                return DistributionIDBlood02;
            }
            case  1707 :
            {
                return DistributionIDBlood03;
            }
            case  1708 :
            {
                return DistributionIDBlood04;
            }
            case  1709 :
            {
                return DistributionIDBlood05;
            }
            case  1715 :
            {
                return DistributionIDGurista01;
            }
            case  1716 :
            {
                return DistributionIDGurista02;
            }
            case  1717 :
            {
                return DistributionIDGurista03;
            }
            case  1718 :
            {
                return DistributionIDGurista04;
            }
            case  1719 :
            {
                return DistributionIDGurista05;
            }
            case  1725 :
            {
                return DistributionIDRogueDrone01;
            }
            case  1726 :
            {
                return DistributionIDRogueDrone02;
            }
            case  1727 :
            {
                return DistributionIDRogueDrone03;
            }
            case  1728 :
            {
                return DistributionIDRogueDrone04;
            }
            case  1729 :
            {
                return DistributionIDRogueDrone05;
            }
            case  1735 :
            {
                return DistributionIDSansha01;
            }
            case  1736 :
            {
                return DistributionIDSansha02;
            }
            case  1737 :
            {
                return DistributionIDSansha03;
            }
            case  1738 :
            {
                return DistributionIDSansha04;
            }
            case  1739 :
            {
                return DistributionIDSansha05;
            }
            case  1745 :
            {
                return DistributionIDSerpentis01;
            }
            case  1746 :
            {
                return DistributionIDSerpentis02;
            }
            case  1747 :
            {
                return DistributionIDSerpentis03;
            }
            case  1748 :
            {
                return DistributionIDSerpentis04;
            }
            case  1749 :
            {
                return DistributionIDSerpentis05;
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
    public int getGroupId() {
        return  1021;
    }

    @Override
    public Class<?> getGroup() {
        return MilitaryUpgrades.class;
    }

    public static synchronized LinkedHashMap<String, MilitaryUpgrades> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MilitaryUpgrades.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MilitaryUpgrades> items;
    }
}
