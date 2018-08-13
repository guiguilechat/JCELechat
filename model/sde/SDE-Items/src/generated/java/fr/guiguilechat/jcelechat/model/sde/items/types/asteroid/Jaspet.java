package fr.guiguilechat.jcelechat.model.sde.items.types.asteroid;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Jaspet
    extends Asteroid
{
    /**
     * max visual size for asteroids to fit moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(16255)
    public int AsteroidMaxRadius;
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
    public final static Jaspet.MetaGroup METAGROUP = new Jaspet.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/asteroid/Jaspet.yaml";
    private static Map<String, Jaspet> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2727 :
            {
                return AsteroidMaxRadius;
            }
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
        return  456;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Jaspet> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Jaspet> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Jaspet.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Jaspet> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Jaspet>
    {

        @Override
        public MetaCategory<? super Jaspet> category() {
            return Asteroid.METACAT;
        }

        @Override
        public String getName() {
            return "Jaspet";
        }

        @Override
        public Collection<Jaspet> items() {
            return (load().values());
        }
    }
}
