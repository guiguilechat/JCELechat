package fr.guiguilechat.jcelechat.model.sde.types.cells;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Cells;
import org.yaml.snakeyaml.Yaml;

public class PhysicalPortals
    extends Cells
{
    public static final PhysicalPortals.MetaGroup METAGROUP = new PhysicalPortals.MetaGroup();

    @Override
    public IMetaGroup<PhysicalPortals> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhysicalPortals>
    {
        public static final String RESOURCE_PATH = "SDE/types/cells/PhysicalPortals.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(PhysicalPortals.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PhysicalPortals> types;
        }
    }
}
