package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class SubsystemBlueprints
    extends Blueprint
{
    public static final SubsystemBlueprints.MetaGroup METAGROUP = new SubsystemBlueprints.MetaGroup();

    @Override
    public IMetaGroup<SubsystemBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SubsystemBlueprints>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/SubsystemBlueprints.yaml";
        private Map<String, SubsystemBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super SubsystemBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  973;
        }

        @Override
        public String getName() {
            return "SubsystemBlueprints";
        }

        @Override
        public synchronized Map<String, SubsystemBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SubsystemBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SubsystemBlueprints> items;
        }
    }
}
