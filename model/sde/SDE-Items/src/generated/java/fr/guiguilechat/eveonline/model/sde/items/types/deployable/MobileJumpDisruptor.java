
package fr.guiguilechat.eveonline.model.sde.items.types.deployable;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Deployable;
import org.yaml.snakeyaml.Yaml;

public class MobileJumpDisruptor
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
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
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
    public double RequiredSkill2;
    /**
     * Required skill level for skill 3
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill3Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill3;
    /**
     * The ranking of the module within its tech level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MetaLevel;
    public final static String RESOURCE_PATH = "SDE/items/deployable/MobileJumpDisruptor.yaml";
    private static LinkedHashMap<String, MobileJumpDisruptor> cache = (null);

    @Override
    public int getGroupId() {
        return  1149;
    }

    @Override
    public Class<?> getGroup() {
        return MobileJumpDisruptor.class;
    }

    public static LinkedHashMap<String, MobileJumpDisruptor> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MobileJumpDisruptor.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MobileJumpDisruptor> items;

    }

}
