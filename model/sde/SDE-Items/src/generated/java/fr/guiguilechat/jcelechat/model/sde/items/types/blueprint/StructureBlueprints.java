package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class StructureBlueprints
    extends Blueprint
{
    public static final StructureBlueprints.MetaGroup METAGROUP = new StructureBlueprints.MetaGroup();

    @Override
    public IMetaGroup<StructureBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureBlueprints>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/StructureBlueprints.yaml";
        private Map<String, StructureBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super StructureBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1462;
        }

        @Override
        public String getName() {
            return "StructureBlueprints";
        }

        @Override
        public synchronized Map<String, StructureBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StructureBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureBlueprints> items;
        }
    }
}
