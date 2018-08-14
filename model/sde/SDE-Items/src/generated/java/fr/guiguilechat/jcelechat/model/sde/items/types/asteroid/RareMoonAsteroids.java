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

public class RareMoonAsteroids
    extends Asteroid
{
    /**
     * max visual size for asteroids to fit moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(16255)
    public int AsteroidMaxRadius;
    public final static RareMoonAsteroids.MetaGroup METAGROUP = new RareMoonAsteroids.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2727 :
            {
                return AsteroidMaxRadius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<RareMoonAsteroids> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RareMoonAsteroids>
    {
        public final static String RESOURCE_PATH = "SDE/items/asteroid/RareMoonAsteroids.yaml";
        private Map<String, RareMoonAsteroids> cache = (null);

        @Override
        public IMetaCategory<? super RareMoonAsteroids> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1922;
        }

        @Override
        public String getName() {
            return "RareMoonAsteroids";
        }

        @Override
        public synchronized Map<String, RareMoonAsteroids> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RareMoonAsteroids.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RareMoonAsteroids> items;
        }
    }
}
