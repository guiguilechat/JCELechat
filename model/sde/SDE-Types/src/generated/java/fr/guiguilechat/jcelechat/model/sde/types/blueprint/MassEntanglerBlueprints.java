package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MassEntanglerBlueprints
    extends Blueprint
{
    public static final MassEntanglerBlueprints.MetaGroup METAGROUP = new MassEntanglerBlueprints.MetaGroup();

    @Override
    public IMetaGroup<MassEntanglerBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MassEntanglerBlueprints>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/MassEntanglerBlueprints.yaml";
        private Map<String, MassEntanglerBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super MassEntanglerBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2010;
        }

        @Override
        public String getName() {
            return "MassEntanglerBlueprints";
        }

        @Override
        public synchronized Map<String, MassEntanglerBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MassEntanglerBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MassEntanglerBlueprints> items;
        }
    }
}
