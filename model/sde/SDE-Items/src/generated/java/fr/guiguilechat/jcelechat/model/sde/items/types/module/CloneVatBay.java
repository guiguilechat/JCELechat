package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class CloneVatBay
    extends Module
{
    /**
     * If this module is active and the ship supports it, the ship can serve as a destination for clone jumps.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AllowsCloneJumpsWhenActive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int CanCloak;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup02;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionQuantity;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionType;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Modules with this attribute set to 1 can not be used in deadspace. Modules with this attribute set to 2 can not be used in deadspace even where "disableModuleBlocking" is selected
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DeadspaceUnsafe;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowEarlyDeactivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupActive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
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
    @DefaultIntValue(0)
    public int SiegeModeWarpStatus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double SpeedFactor;
    public final static String RESOURCE_PATH = "SDE/items/module/CloneVatBay.yaml";
    private static Map<String, CloneVatBay> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  981 :
            {
                return AllowsCloneJumpsWhenActive;
            }
            case  1163 :
            {
                return CanCloak;
            }
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  1299 :
            {
                return CanFitShipGroup02;
            }
            case  714 :
            {
                return ConsumptionQuantity;
            }
            case  713 :
            {
                return ConsumptionType;
            }
            case  50 :
            {
                return Cpu;
            }
            case  801 :
            {
                return DeadspaceUnsafe;
            }
            case  906 :
            {
                return DisallowEarlyDeactivation;
            }
            case  73 :
            {
                return Duration;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  852 :
            {
                return SiegeModeWarpStatus;
            }
            case  20 :
            {
                return SpeedFactor;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  815;
    }

    @Override
    public Class<?> getGroup() {
        return CloneVatBay.class;
    }

    public static synchronized Map<String, CloneVatBay> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CloneVatBay.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, CloneVatBay> items;
    }
}
