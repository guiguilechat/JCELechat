
package fr.guiguilechat.eveonline.model.sde.items.types.deployable;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Deployable;
import org.yaml.snakeyaml.Yaml;

public class MobileWarpDisruptor
    extends Deployable
{

    /**
     * Maximum range objects can be warp scrambled from.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double WarpScrambleRange;
    /**
     * How long it takes to anchor or unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double AnchoringDelay;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DisallowInEmpireSpace;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1Level;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2;
    /**
     * The ranking of the module within its tech level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MetaLevel;
    /**
     * meta group of type
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MetaGroupID;
    public final static String RESOURCE_PATH = "SDE/items/deployable/MobileWarpDisruptor.yaml";
    private static LinkedHashMap<String, MobileWarpDisruptor> cache = (null);

    @Override
    public int getGroupId() {
        return  361;
    }

    @Override
    public Class<?> getGroup() {
        return MobileWarpDisruptor.class;
    }

    public static LinkedHashMap<String, MobileWarpDisruptor> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MobileWarpDisruptor.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MobileWarpDisruptor> items;

    }

}
