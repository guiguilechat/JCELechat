package fr.guiguilechat.eveonline.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class CapacitorPowerRelay
    extends Module
{
    /**
     * Multiplier to the capacitors recharge rate.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CapacitorRechargeRateMultiplier;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldBoostMultiplier;
    public final static String RESOURCE_PATH = "SDE/items/module/CapacitorPowerRelay.yaml";
    private static LinkedHashMap<String, CapacitorPowerRelay> cache = (null);

    @Override
    public int getGroupId() {
        return  767;
    }

    @Override
    public Class<?> getGroup() {
        return CapacitorPowerRelay.class;
    }

    public static synchronized LinkedHashMap<String, CapacitorPowerRelay> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CapacitorPowerRelay.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CapacitorPowerRelay> items;
    }
}
