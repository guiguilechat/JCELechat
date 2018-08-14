package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class Processors
    extends PlanetaryInteraction
{
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuLoad;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PlanetRestriction;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    public final static Processors.MetaGroup METAGROUP = new Processors.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return CpuLoad;
            }
            case  1632 :
            {
                return PlanetRestriction;
            }
            case  15 :
            {
                return PowerLoad;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Processors> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Processors>
    {
        public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/Processors.yaml";
        private Map<String, Processors> cache = (null);

        @Override
        public IMetaCategory<? super Processors> category() {
            return PlanetaryInteraction.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1028;
        }

        @Override
        public String getName() {
            return "Processors";
        }

        @Override
        public synchronized Map<String, Processors> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Processors.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Processors> items;
        }
    }
}
