package fr.guiguilechat.jcelechat.model.sde.items.types.asteroid;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Plagioclase
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
    public final static Plagioclase.MetaGroup METAGROUP = new Plagioclase.MetaGroup();

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
    public IMetaGroup<Plagioclase> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Plagioclase>
    {
        public final static String RESOURCE_PATH = "SDE/items/asteroid/Plagioclase.yaml";
        private Map<String, Plagioclase> cache = (null);

        @Override
        public IMetaCategory<? super Plagioclase> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  458;
        }

        @Override
        public String getName() {
            return "Plagioclase";
        }

        @Override
        public synchronized Map<String, Plagioclase> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Plagioclase.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Plagioclase> items;
        }
    }
}