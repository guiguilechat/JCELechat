package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import org.yaml.snakeyaml.Yaml;

public class FuelBlock
    extends Material
{
    public static final FuelBlock.MetaGroup METAGROUP = new FuelBlock.MetaGroup();

    @Override
    public IMetaGroup<FuelBlock> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FuelBlock>
    {
        public static final String RESOURCE_PATH = "SDE/items/material/FuelBlock.yaml";
        private Map<String, FuelBlock> cache = (null);

        @Override
        public IMetaCategory<? super FuelBlock> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1136;
        }

        @Override
        public String getName() {
            return "FuelBlock";
        }

        @Override
        public synchronized Map<String, FuelBlock> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(FuelBlock.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, FuelBlock> items;
        }
    }
}
