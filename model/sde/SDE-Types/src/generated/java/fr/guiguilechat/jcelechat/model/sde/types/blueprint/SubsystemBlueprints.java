package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class SubsystemBlueprints
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SubsystemBlueprints.MetaGroup METAGROUP = new SubsystemBlueprints.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SubsystemBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SubsystemBlueprints>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/SubsystemBlueprints.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(SubsystemBlueprints.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SubsystemBlueprints> types;
        }
    }
}
