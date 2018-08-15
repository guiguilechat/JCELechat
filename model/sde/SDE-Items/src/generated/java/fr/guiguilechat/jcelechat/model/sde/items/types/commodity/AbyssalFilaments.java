package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
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
    public final static AbyssalFilaments.MetaGroup METAGROUP = new AbyssalFilaments.MetaGroup();

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
    public IMetaGroup<AbyssalFilaments> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalFilaments>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/AbyssalFilaments.yaml";
        private Map<String, AbyssalFilaments> cache = (null);

        @Override
        public IMetaCategory<? super AbyssalFilaments> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1979;
        }

        @Override
        public String getName() {
            return "AbyssalFilaments";
        }

        @Override
        public synchronized Map<String, AbyssalFilaments> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AbyssalFilaments.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AbyssalFilaments> items;
        }
    }
}