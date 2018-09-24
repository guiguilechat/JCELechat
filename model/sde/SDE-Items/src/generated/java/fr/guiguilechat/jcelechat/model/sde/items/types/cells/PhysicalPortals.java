package fr.guiguilechat.jcelechat.model.sde.items.types.cells;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Cells;
import org.yaml.snakeyaml.Yaml;

public class PhysicalPortals
    extends Cells
{
    public final static PhysicalPortals.MetaGroup METAGROUP = new PhysicalPortals.MetaGroup();

    @Override
    public IMetaGroup<PhysicalPortals> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhysicalPortals>
    {
        public final static String RESOURCE_PATH = "SDE/items/cells/PhysicalPortals.yaml";
        private Map<String, PhysicalPortals> cache = (null);

        @Override
        public IMetaCategory<? super PhysicalPortals> category() {
            return Cells.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1126;
        }

        @Override
        public String getName() {
            return "PhysicalPortals";
        }

        @Override
        public synchronized Map<String, PhysicalPortals> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PhysicalPortals.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PhysicalPortals> items;
        }
    }
}
