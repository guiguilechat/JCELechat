package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class BiochemicalMaterial
    extends Material
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonMiningAmount;
    public final static BiochemicalMaterial.MetaGroup METAGROUP = new BiochemicalMaterial.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  726 :
            {
                return MoonMiningAmount;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<BiochemicalMaterial> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BiochemicalMaterial>
    {
        public final static String RESOURCE_PATH = "SDE/items/material/BiochemicalMaterial.yaml";
        private Map<String, BiochemicalMaterial> cache = (null);

        @Override
        public IMetaCategory<? super BiochemicalMaterial> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  712;
        }

        @Override
        public String getName() {
            return "BiochemicalMaterial";
        }

        @Override
        public synchronized Map<String, BiochemicalMaterial> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(BiochemicalMaterial.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, BiochemicalMaterial> items;
        }
    }
}