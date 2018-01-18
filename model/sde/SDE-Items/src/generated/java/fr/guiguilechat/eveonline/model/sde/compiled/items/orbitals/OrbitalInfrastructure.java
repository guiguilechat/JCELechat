
package fr.guiguilechat.eveonline.model.sde.compiled.items.orbitals;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class OrbitalInfrastructure
    extends Orbitals
{

    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(100.0D)
    public double SignatureRadius;
    /**
     * Capacity of material bay
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SpecialMaterialBayCapacity;
    /**
     * The number of seconds that the structure will be in reinforcement time
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(172800.0D)
    public double ReinforcementDuration;
    /**
     * The number of seconds that the reinforcement exit time will be adjusted by. exitTime +- attribute
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(10800.0D)
    public double ReinforcementVariance;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DisallowInEmpireSpace;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EntityFactionLoss;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.1D)
    public double NpcCustomsOfficeTaxRate;
    public final static String RESOURCE_PATH = "SDE/items/orbitals/OrbitalInfrastructure.yaml";
    private static LinkedHashMap<String, OrbitalInfrastructure> cache = (null);

    @Override
    public int getGroupId() {
        return  1025;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalInfrastructure.class;
    }

    public static LinkedHashMap<String, OrbitalInfrastructure> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalInfrastructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, OrbitalInfrastructure> items;

    }

}
