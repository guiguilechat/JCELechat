package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class AbyssalMaterials
    extends Material
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final AbyssalMaterials.MetaGroup METAGROUP = new AbyssalMaterials.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<AbyssalMaterials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalMaterials>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/AbyssalMaterials.yaml";
        private Map<Integer, AbyssalMaterials> cache = (null);

        @Override
        public IMetaCategory<? super AbyssalMaterials> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1996;
        }

        @Override
        public String getName() {
            return "AbyssalMaterials";
        }

        @Override
        public synchronized Map<Integer, AbyssalMaterials> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AbyssalMaterials.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, AbyssalMaterials> types;
        }
    }
}
