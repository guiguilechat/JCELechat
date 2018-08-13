package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class Refinery
    extends Structure
{
    /**
     * Delay for exploding moon mining chunk into asteroid field
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10800)
    public int AutoFractureDelay;
    /**
     * This attribute doesn't directly impact the asteroid decay, but is used to expose the decay time to the show-info window
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(48)
    public int MoonAsteroidDecayDisplayValue;
    /**
     * Decay time for asteroid created from moon spew
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonAsteroidDecayTimeMultiplier;
    /**
     * Time bonus for Refinery Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrReactionTimeMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StrRefiningYieldBonus;
    public final static String RESOURCE_PATH = "SDE/items/structure/Refinery.yaml";
    private static Map<String, Refinery> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2698 :
            {
                return AutoFractureDelay;
            }
            case  2728 :
            {
                return MoonAsteroidDecayDisplayValue;
            }
            case  2706 :
            {
                return MoonAsteroidDecayTimeMultiplier;
            }
            case  2721 :
            {
                return StrReactionTimeMultiplier;
            }
            case  2722 :
            {
                return StrRefiningYieldBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1406;
    }

    @Override
    public Class<?> getGroup() {
        return Refinery.class;
    }

    public static synchronized Map<String, Refinery> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Refinery.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Refinery> items;
    }
}
