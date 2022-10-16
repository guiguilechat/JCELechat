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
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class MicroJumpFieldGeneratorBlueprint
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final MicroJumpFieldGeneratorBlueprint.MetaGroup METAGROUP = new MicroJumpFieldGeneratorBlueprint.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<MicroJumpFieldGeneratorBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MicroJumpFieldGeneratorBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/MicroJumpFieldGeneratorBlueprint.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(MicroJumpFieldGeneratorBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, MicroJumpFieldGeneratorBlueprint> types;
        }
    }
}
