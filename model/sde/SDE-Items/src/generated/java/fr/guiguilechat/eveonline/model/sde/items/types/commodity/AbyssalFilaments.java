package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class AbyssalFilaments
    extends Commodity
{
    /**
     * sets the difficulty tier for abyssal deadspace keys
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int DifficultyTier;
    /**
     * sets the weather effect type for abyssal deadspace keys
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int WeatherID;
    public final static String RESOURCE_PATH = "SDE/items/commodity/AbyssalFilaments.yaml";
    private static LinkedHashMap<String, AbyssalFilaments> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2761 :
            {
                return DifficultyTier;
            }
            case  2760 :
            {
                return WeatherID;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1979;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalFilaments.class;
    }

    public static synchronized LinkedHashMap<String, AbyssalFilaments> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AbyssalFilaments.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AbyssalFilaments> items;
    }
}
