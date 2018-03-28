package fr.guiguilechat.eveonline.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class OverdriveInjectorSystem
    extends Module
{
    /**
     * Multiplier to the amount of cargo capacity for a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CargoCapacityMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ImplantBonusVelocity;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
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
    public final static String RESOURCE_PATH = "SDE/items/module/OverdriveInjectorSystem.yaml";
    private static LinkedHashMap<String, OverdriveInjectorSystem> cache = (null);

    @Override
    public int getGroupId() {
        return  764;
    }

    @Override
    public Class<?> getGroup() {
        return OverdriveInjectorSystem.class;
    }

    public static synchronized LinkedHashMap<String, OverdriveInjectorSystem> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OverdriveInjectorSystem.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, OverdriveInjectorSystem> items;
    }
}
