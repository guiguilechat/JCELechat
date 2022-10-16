package fr.guiguilechat.jcelechat.model.sde.types.celestial;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.IgnoreMiningWaste;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class HarvestableCloud
    extends Celestial
{
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ignoreminingwaste;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, IgnoreMiningWaste.INSTANCE, Capacity.INSTANCE })));
    public static final HarvestableCloud.MetaGroup METAGROUP = new HarvestableCloud.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  3236 :
            {
                return ignoreminingwaste;
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
    public IMetaGroup<HarvestableCloud> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HarvestableCloud>
    {
        public static final String RESOURCE_PATH = "SDE/types/celestial/HarvestableCloud.yaml";
        private Map<String, HarvestableCloud> cache = (null);

        @Override
        public IMetaCategory<? super HarvestableCloud> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  711;
        }

        @Override
        public String getName() {
            return "HarvestableCloud";
        }

        @Override
        public synchronized Map<String, HarvestableCloud> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HarvestableCloud.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HarvestableCloud> types;
        }
    }
}
