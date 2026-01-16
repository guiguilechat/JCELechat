package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class QuantumCores
    extends StructureModule
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final QuantumCores.MetaGroup METAGROUP = new QuantumCores.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<QuantumCores> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<QuantumCores>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/QuantumCores.yaml";
        private Map<Integer, QuantumCores> cache = (null);

        @Override
        public IMetaCategory<? super QuantumCores> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4086;
        }

        @Override
        public String getName() {
            return "QuantumCores";
        }

        @Override
        public synchronized Map<Integer, QuantumCores> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(QuantumCores.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, QuantumCores> types;
        }
    }
}
