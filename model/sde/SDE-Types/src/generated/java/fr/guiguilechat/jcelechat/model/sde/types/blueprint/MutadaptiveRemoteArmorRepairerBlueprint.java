package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MutadaptiveRemoteArmorRepairerBlueprint
    extends Blueprint
{
    public static final MutadaptiveRemoteArmorRepairerBlueprint.MetaGroup METAGROUP = new MutadaptiveRemoteArmorRepairerBlueprint.MetaGroup();

    @Override
    public IMetaGroup<MutadaptiveRemoteArmorRepairerBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MutadaptiveRemoteArmorRepairerBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/MutadaptiveRemoteArmorRepairerBlueprint.yaml";
        private Map<String, MutadaptiveRemoteArmorRepairerBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super MutadaptiveRemoteArmorRepairerBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2019;
        }

        @Override
        public String getName() {
            return "MutadaptiveRemoteArmorRepairerBlueprint";
        }

        @Override
        public synchronized Map<String, MutadaptiveRemoteArmorRepairerBlueprint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MutadaptiveRemoteArmorRepairerBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MutadaptiveRemoteArmorRepairerBlueprint> items;
        }
    }
}
