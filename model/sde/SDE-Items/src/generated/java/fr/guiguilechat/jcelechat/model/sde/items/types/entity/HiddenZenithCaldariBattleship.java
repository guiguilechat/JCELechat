package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithCaldariBattleship
    extends Entity
{
    public static final HiddenZenithCaldariBattleship.MetaGroup METAGROUP = new HiddenZenithCaldariBattleship.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithCaldariBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithCaldariBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/HiddenZenithCaldariBattleship.yaml";
        private Map<String, HiddenZenithCaldariBattleship> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithCaldariBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1792;
        }

        @Override
        public String getName() {
            return "HiddenZenithCaldariBattleship";
        }

        @Override
        public synchronized Map<String, HiddenZenithCaldariBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithCaldariBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithCaldariBattleship> items;
        }
    }
}
