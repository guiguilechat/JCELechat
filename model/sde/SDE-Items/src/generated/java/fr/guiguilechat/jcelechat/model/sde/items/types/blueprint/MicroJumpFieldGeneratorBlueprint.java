package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MicroJumpFieldGeneratorBlueprint
    extends Blueprint
{
    public static final MicroJumpFieldGeneratorBlueprint.MetaGroup METAGROUP = new MicroJumpFieldGeneratorBlueprint.MetaGroup();

    @Override
    public IMetaGroup<MicroJumpFieldGeneratorBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MicroJumpFieldGeneratorBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/MicroJumpFieldGeneratorBlueprint.yaml";
        private Map<String, MicroJumpFieldGeneratorBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super MicroJumpFieldGeneratorBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1543;
        }

        @Override
        public String getName() {
            return "MicroJumpFieldGeneratorBlueprint";
        }

        @Override
        public synchronized Map<String, MicroJumpFieldGeneratorBlueprint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MicroJumpFieldGeneratorBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MicroJumpFieldGeneratorBlueprint> items;
        }
    }
}
