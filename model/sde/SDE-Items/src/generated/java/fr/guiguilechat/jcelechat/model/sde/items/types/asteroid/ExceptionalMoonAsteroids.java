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

public class ExceptionalMoonAsteroids
    extends Asteroid
{
    /**
     * max visual size for asteroids to fit moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(16255)
    public int AsteroidMaxRadius;
    public final static ExceptionalMoonAsteroids.MetaGroup METAGROUP = new ExceptionalMoonAsteroids.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/asteroid/ExceptionalMoonAsteroids.yaml";
    private static Map<String, ExceptionalMoonAsteroids> cache = (null);

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
    public int getGroupId() {
        return  1923;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ExceptionalMoonAsteroids> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ExceptionalMoonAsteroids> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ExceptionalMoonAsteroids.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ExceptionalMoonAsteroids> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ExceptionalMoonAsteroids>
    {

        @Override
        public MetaCategory<? super ExceptionalMoonAsteroids> category() {
            return Asteroid.METACAT;
        }

        @Override
        public String getName() {
            return "ExceptionalMoonAsteroids";
        }

        @Override
        public Collection<ExceptionalMoonAsteroids> items() {
            return (load().values());
        }
    }
}
