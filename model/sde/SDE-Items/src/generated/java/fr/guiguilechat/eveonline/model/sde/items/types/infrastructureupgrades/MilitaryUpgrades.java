
package fr.guiguilechat.eveonline.model.sde.items.types.infrastructureupgrades;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.InfrastructureUpgrades;
import org.yaml.snakeyaml.Yaml;

public class MilitaryUpgrades
    extends InfrastructureUpgrades
{

    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDRogueDrone04;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDRogueDrone05;
    /**
     * Distribution ID for sov upgrades in Sansha space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSansha01;
    /**
     * Distribution ID for sov upgrades in Sansha space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSansha02;
    /**
     * Distribution ID for sov upgrades in Sansha space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSansha03;
    /**
     * Distribution ID for sov upgrades in Sansha space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSansha04;
    /**
     * Distribution ID for sov upgrades in Sansha space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSansha05;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSerpentis01;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSerpentis02;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSerpentis03;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSerpentis04;
    /**
     * Distribution ID for sov upgrades in Serpentis space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDSerpentis05;
    /**
     * Distribution ID for global sov upgrades
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionID01;
    /**
     * Distribution ID for sov upgrades in Angel space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDAngel01;
    /**
     * Distribution ID for sov upgrades in Angel space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDAngel02;
    /**
     * Distribution ID for sov upgrades in Angel space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDAngel03;
    /**
     * Distribution ID for sov upgrades in Angel space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDAngel04;
    /**
     * Distribution ID for sov upgrades in Angel space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDAngel05;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDBlood01;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDBlood02;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDBlood03;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDBlood04;
    /**
     * Distribution ID for sov upgrades in Blood Raider space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDBlood05;
    /**
     * The required minimum military development index level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DevIndexMilitary;
    /**
     * Distribution ID for sov upgrades in Guristas space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDGurista01;
    /**
     * Distribution ID for sov upgrades in Guristas space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDGurista02;
    /**
     * Distribution ID for sov upgrades in Guristas space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDGurista03;
    /**
     * Distribution ID for sov upgrades in Guristas space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDGurista04;
    /**
     * Distribution ID for sov upgrades in Guristas space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDGurista05;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDRogueDrone01;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDRogueDrone02;
    /**
     * The typeID of the upgrade that is required for this upgrade to be installed. 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SovUpgradeRequiredUpgradeID;
    /**
     * Distribution ID for sov upgrades in Rogue Drone space
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DistributionIDRogueDrone03;
    public final static String RESOURCE_PATH = "SDE/items/infrastructureupgrades/MilitaryUpgrades.yaml";
    private static LinkedHashMap<String, MilitaryUpgrades> cache = (null);

    @Override
    public int getGroupId() {
        return  1021;
    }

    @Override
    public Class<?> getGroup() {
        return MilitaryUpgrades.class;
    }

    public static LinkedHashMap<String, MilitaryUpgrades> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MilitaryUpgrades.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MilitaryUpgrades> items;

    }

}
