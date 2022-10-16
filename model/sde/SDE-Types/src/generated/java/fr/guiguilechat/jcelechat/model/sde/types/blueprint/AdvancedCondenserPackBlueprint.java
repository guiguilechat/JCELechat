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

public class AdvancedCondenserPackBlueprint
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final AdvancedCondenserPackBlueprint.MetaGroup METAGROUP = new AdvancedCondenserPackBlueprint.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<AdvancedCondenserPackBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AdvancedCondenserPackBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/AdvancedCondenserPackBlueprint.yaml";
        private Map<String, AdvancedCondenserPackBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super AdvancedCondenserPackBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4066;
        }

        @Override
        public String getName() {
            return "AdvancedCondenserPackBlueprint";
        }

        @Override
        public synchronized Map<String, AdvancedCondenserPackBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AdvancedCondenserPackBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AdvancedCondenserPackBlueprint> types;
        }
    }
}
