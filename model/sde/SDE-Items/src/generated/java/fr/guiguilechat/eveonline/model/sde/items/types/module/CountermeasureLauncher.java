
package fr.guiguilechat.eveonline.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class CountermeasureLauncher
    extends Module
{

    /**
     * One of the groups of charge this launcher can be loaded with.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeGroup4;
    /**
     * reload time (ms)
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(10000.0D)
    public double ReloadTime;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int Slots;
    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Cpu;
    /**
     * Time in milliseconds between possible activations
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int Speed;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Number of charges consumed per activation
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ChargeRate;
    /**
     * One of the groups of charge this launcher can be loaded with.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeGroup1;
    /**
     * One of the groups of charge this launcher can be loaded with.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeGroup2;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * One of the groups of charge this launcher can be loaded with.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeGroup3;
    public final static String RESOURCE_PATH = "SDE/items/module/CountermeasureLauncher.yaml";
    private static LinkedHashMap<String, CountermeasureLauncher> cache = (null);

    @Override
    public int getGroupId() {
        return  308;
    }

    @Override
    public Class<?> getGroup() {
        return CountermeasureLauncher.class;
    }

    public static LinkedHashMap<String, CountermeasureLauncher> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CountermeasureLauncher.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, CountermeasureLauncher> items;

    }

}
