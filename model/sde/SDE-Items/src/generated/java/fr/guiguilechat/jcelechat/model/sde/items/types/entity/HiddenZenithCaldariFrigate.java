package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithCaldariFrigate
    extends Entity
{
    public final static HiddenZenithCaldariFrigate.MetaGroup METAGROUP = new HiddenZenithCaldariFrigate.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithCaldariFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithCaldariFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithCaldariFrigate.yaml";
        private Map<String, HiddenZenithCaldariFrigate> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithCaldariFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1794;
        }

        @Override
        public String getName() {
            return "HiddenZenithCaldariFrigate";
        }

        @Override
        public synchronized Map<String, HiddenZenithCaldariFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithCaldariFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithCaldariFrigate> items;
        }
    }
}