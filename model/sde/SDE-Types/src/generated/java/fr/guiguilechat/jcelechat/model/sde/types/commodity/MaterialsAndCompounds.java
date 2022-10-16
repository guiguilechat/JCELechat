package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class MaterialsAndCompounds
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final MaterialsAndCompounds.MetaGroup METAGROUP = new MaterialsAndCompounds.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<MaterialsAndCompounds> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MaterialsAndCompounds>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/MaterialsAndCompounds.yaml";
        private Map<String, MaterialsAndCompounds> cache = (null);

        @Override
        public IMetaCategory<? super MaterialsAndCompounds> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  530;
        }

        @Override
        public String getName() {
            return "MaterialsAndCompounds";
        }

        @Override
        public synchronized Map<String, MaterialsAndCompounds> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MaterialsAndCompounds.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, MaterialsAndCompounds> types;
        }
    }
}
