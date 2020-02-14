package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
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
    public static final AbyssalFilaments.MetaGroup METAGROUP = new AbyssalFilaments.MetaGroup();

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
        public static final String RESOURCE_PATH = "SDE/types/commodity/AbyssalFilaments.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AbyssalFilaments.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AbyssalFilaments> types;
        }
    }
}
