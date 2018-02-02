package fr.guiguilechat.eveonline.model.sde.items.types.implant;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Implant;
import org.yaml.snakeyaml.Yaml;

public class CyberBiology
    extends Implant
{
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BoosterChanceBonus;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BoosterAttributeModifier;
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Implantness;
    public final static String RESOURCE_PATH = "SDE/items/implant/CyberBiology.yaml";
    private static LinkedHashMap<String, CyberBiology> cache = (null);

    @Override
    public int getGroupId() {
        return  1231;
    }

    @Override
    public Class<?> getGroup() {
        return CyberBiology.class;
    }

    public static synchronized LinkedHashMap<String, CyberBiology> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CyberBiology.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CyberBiology> items;
    }
}
