package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class LegacyCurrency
    extends Accessories
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AurumConversionRate;
    public final static LegacyCurrency.MetaGroup METAGROUP = new LegacyCurrency.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1818 :
            {
                return AurumConversionRate;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<LegacyCurrency> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LegacyCurrency>
    {
        public final static String RESOURCE_PATH = "SDE/items/accessories/LegacyCurrency.yaml";
        private Map<String, LegacyCurrency> cache = (null);

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
        public synchronized Map<String, LegacyCurrency> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(LegacyCurrency.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, LegacyCurrency> items;
        }
    }
}