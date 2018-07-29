package fr.guiguilechat.jcelechat.model.sde.items.types.subsystem;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Subsystem;

public class DefensiveSystems
    extends Subsystem
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHPBonusAdd;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorCapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CargoCapacityAdd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CloakingCpuNeedBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CovertCloakCPUPenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(30000)
    public int CovertOpsAndReconOpsCloakModuleDelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpHarmonicsModifier;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureHPBonusAdd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusAmarrDefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusAmarrDefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusAmarrDefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusCaldariDefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusCaldariDefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusCaldariDefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusGallenteDefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusGallenteDefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusGallenteDefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusMinmatarDefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusMinmatarDefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusMinmatarDefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int VirusStrengthBonus;
    public final static String RESOURCE_PATH = "SDE/items/subsystem/DefensiveSystems.yaml";
    private static LinkedHashMap<String, DefensiveSystems> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1159 :
            {
                return ArmorHPBonusAdd;
            }
            case  482 :
            {
                return CapacitorCapacity;
            }
            case  2689 :
            {
                return CargoCapacityAdd;
            }
            case  649 :
            {
                return CloakingCpuNeedBonus;
            }
            case  1871 :
            {
                return CovertCloakCPUPenalty;
            }
            case  1034 :
            {
                return CovertOpsAndReconOpsCloakModuleDelay;
            }
            case  1541 :
            {
                return JumpHarmonicsModifier;
            }
            case  263 :
            {
                return ShieldCapacity;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  2688 :
            {
                return StructureHPBonusAdd;
            }
            case  1433 :
            {
                return SubsystemBonusAmarrDefensive;
            }
            case  1507 :
            {
                return SubsystemBonusAmarrDefensive2;
            }
            case  2680 :
            {
                return SubsystemBonusAmarrDefensive3;
            }
            case  1443 :
            {
                return SubsystemBonusCaldariDefensive;
            }
            case  1516 :
            {
                return SubsystemBonusCaldariDefensive2;
            }
            case  2682 :
            {
                return SubsystemBonusCaldariDefensive3;
            }
            case  1438 :
            {
                return SubsystemBonusGallenteDefensive;
            }
            case  1517 :
            {
                return SubsystemBonusGallenteDefensive2;
            }
            case  2684 :
            {
                return SubsystemBonusGallenteDefensive3;
            }
            case  1448 :
            {
                return SubsystemBonusMinmatarDefensive;
            }
            case  1526 :
            {
                return SubsystemBonusMinmatarDefensive2;
            }
            case  2686 :
            {
                return SubsystemBonusMinmatarDefensive3;
            }
            case  1918 :
            {
                return VirusStrengthBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  954;
    }

    @Override
    public Class<?> getGroup() {
        return DefensiveSystems.class;
    }

    public static synchronized LinkedHashMap<String, DefensiveSystems> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DefensiveSystems.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DefensiveSystems> items;
    }
}
