package fr.guiguilechat.jcelechat.model.sde.types.accessories;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AurumConversionRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.NumDays;
import fr.guiguilechat.jcelechat.model.sde.types.Accessories;

public class LegacyCurrency
    extends Accessories
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aurumconversionrate;
    /**
     * Number of days that this PLEX adds to your account
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int numdays;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {AurumConversionRate.INSTANCE, NumDays.INSTANCE })));
    public static final LegacyCurrency.MetaGroup METAGROUP = new LegacyCurrency.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1818 :
            {
                return aurumconversionrate;
            }
            case  1551 :
            {
                return numdays;
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
    public IMetaGroup<LegacyCurrency> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LegacyCurrency>
    {
        public static final String RESOURCE_PATH = "SDE/types/accessories/LegacyCurrency.yaml";
        private Map<Integer, LegacyCurrency> cache = (null);

        @Override
        public IMetaCategory<? super LegacyCurrency> category() {
            return Accessories.METACAT;
        }

        @Override
        public int getGroupId() {
            return  943;
        }

        @Override
        public String getName() {
            return "LegacyCurrency";
        }

        @Override
        public synchronized Map<Integer, LegacyCurrency> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(LegacyCurrency.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, LegacyCurrency> types;
        }
    }
}
