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
import org.yaml.snakeyaml.Yaml;

public class Mineral
    extends Material
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Mineral.MetaGroup METAGROUP = new Mineral.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Mineral> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Mineral>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/Mineral.yaml";
        private Map<String, Mineral> cache = (null);

        @Override
        public IMetaCategory<? super Mineral> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  18;
        }

        @Override
        public String getName() {
            return "Mineral";
        }

        @Override
        public synchronized Map<String, Mineral> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Mineral.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Mineral> types;
        }
    }
}
