package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class IceProduct
    extends Material
{
    public static final IceProduct.MetaGroup METAGROUP = new IceProduct.MetaGroup();

    @Override
    public IMetaGroup<IceProduct> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IceProduct>
    {
        public static final String RESOURCE_PATH = "SDE/items/material/IceProduct.yaml";
        private Map<String, IceProduct> cache = (null);

        @Override
        public IMetaCategory<? super IceProduct> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  423;
        }

        @Override
        public String getName() {
            return "IceProduct";
        }

        @Override
        public synchronized Map<String, IceProduct> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IceProduct.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IceProduct> items;
        }
    }
}
