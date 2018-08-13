package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class FuelBlock
    extends Material
{
    public final static FuelBlock.MetaGroup METAGROUP = new FuelBlock.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/material/FuelBlock.yaml";
    private static Map<String, FuelBlock> cache = (null);

    @Override
    public int getGroupId() {
        return  1136;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<FuelBlock> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, FuelBlock> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<FuelBlock>
    {

        @Override
        public MetaCategory<? super FuelBlock> category() {
            return Material.METACAT;
        }

        @Override
        public String getName() {
            return "FuelBlock";
        }

        @Override
        public Collection<FuelBlock> items() {
            return (load().values());
        }
    }
}
