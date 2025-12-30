package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;

public class MutaplasmidBlueprint
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final MutaplasmidBlueprint.MetaGroup METAGROUP = new MutaplasmidBlueprint.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<MutaplasmidBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MutaplasmidBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/MutaplasmidBlueprint.yaml";
        private Map<Integer, MutaplasmidBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super MutaplasmidBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4820;
        }

        @Override
        public String getName() {
            return "MutaplasmidBlueprint";
        }

        @Override
        public synchronized Map<Integer, MutaplasmidBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MutaplasmidBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MutaplasmidBlueprint> types;
        }
    }
}
