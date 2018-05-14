package fr.guiguilechat.eveonline.model.sde.items.types.orbitals;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;
import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class OrbitalInfrastructure
    extends Orbitals
{
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInEmpireSpace;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityFactionLoss;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.1)
    public double NpcCustomsOfficeTaxRate;
    /**
     * The number of seconds that the structure will be in reinforcement time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(172800)
    public int ReinforcementDuration;
    /**
     * The number of seconds that the reinforcement exit time will be adjusted by. exitTime +- attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10800)
    public int ReinforcementVariance;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * Capacity of material bay
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SpecialMaterialBayCapacity;
    public final static String RESOURCE_PATH = "SDE/items/orbitals/OrbitalInfrastructure.yaml";
    private static LinkedHashMap<String, OrbitalInfrastructure> cache = (null);

    public int attributeInt(IntAttribute attribute) {
        switch (attribute.getId()) {
            case  1074 :
            {
                return DisallowInEmpireSpace;
            }
            case  562 :
            {
                return EntityFactionLoss;
            }
            case  1612 :
            {
                return ReinforcementDuration;
            }
            case  1613 :
            {
                return ReinforcementVariance;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  1770 :
            {
                return SpecialMaterialBayCapacity;
            }
            default:
            {
                return super.attributeInt((attribute));
            }
        }
    }

    public double attributeDouble(DoubleAttribute attribute) {
        switch (attribute.getId()) {
            case  1780 :
            {
                return NpcCustomsOfficeTaxRate;
            }
            default:
            {
                return super.attributeDouble((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1025;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalInfrastructure.class;
    }

    public static synchronized LinkedHashMap<String, OrbitalInfrastructure> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalInfrastructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, OrbitalInfrastructure> items;
    }
}
