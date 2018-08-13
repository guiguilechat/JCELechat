package fr.guiguilechat.jcelechat.model.sde.items.types.asteroid;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Ice
    extends Asteroid
{
    /**
     * Number of items needed to be able to compress it
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CompressionQuantityNeeded;
    /**
     * What type this type can be compressed into
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CompressionTypeID;
    public final static String RESOURCE_PATH = "SDE/items/asteroid/Ice.yaml";
    private static Map<String, Ice> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1941 :
            {
                return CompressionQuantityNeeded;
            }
            case  1940 :
            {
                return CompressionTypeID;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  465;
    }

    @Override
    public Class<?> getGroup() {
        return Ice.class;
    }

    public static synchronized Map<String, Ice> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Ice.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Ice> items;
    }
}
