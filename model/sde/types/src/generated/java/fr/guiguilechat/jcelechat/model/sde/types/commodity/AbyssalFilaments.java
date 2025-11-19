package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.DifficultyTier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeatherID;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
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
    public int difficultytier;
    /**
     * sets the weather effect type for abyssal deadspace keys
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int weatherid;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {WeatherID.INSTANCE, DifficultyTier.INSTANCE })));
    public static final AbyssalFilaments.MetaGroup METAGROUP = new AbyssalFilaments.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2761 :
            {
                return difficultytier;
            }
            case  2760 :
            {
                return weatherid;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<AbyssalFilaments> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalFilaments>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/AbyssalFilaments.yaml";
        private Map<Integer, AbyssalFilaments> cache = (null);

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
        public synchronized Map<Integer, AbyssalFilaments> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AbyssalFilaments.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, AbyssalFilaments> types;
        }
    }
}
