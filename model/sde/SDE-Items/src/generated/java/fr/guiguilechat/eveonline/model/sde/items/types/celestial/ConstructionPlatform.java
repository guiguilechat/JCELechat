package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class ConstructionPlatform
    extends Celestial
{
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * The type of station this platform can be used to build.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StationTypeID;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * How long it takes to anchor or unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int AnchoringDelay;
    /**
     * This is a display-only attribute for showinfo
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiresSovereigntyDisplayOnly;
    public final static String RESOURCE_PATH = "SDE/items/celestial/ConstructionPlatform.yaml";
    private static LinkedHashMap<String, ConstructionPlatform> cache = (null);

    @Override
    public int getGroupId() {
        return  307;
    }

    @Override
    public Class<?> getGroup() {
        return ConstructionPlatform.class;
    }

    public static synchronized LinkedHashMap<String, ConstructionPlatform> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ConstructionPlatform.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ConstructionPlatform> items;
    }
}
