package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
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
        public static final String RESOURCE_PATH = "SDE/types/material/IceProduct.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IceProduct.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IceProduct> types;
        }
    }
}
