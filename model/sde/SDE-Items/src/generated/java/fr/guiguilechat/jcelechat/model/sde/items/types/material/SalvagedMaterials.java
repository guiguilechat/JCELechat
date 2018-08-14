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

public class SalvagedMaterials
    extends Material
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    public final static SalvagedMaterials.MetaGroup METAGROUP = new SalvagedMaterials.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return Hp;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<SalvagedMaterials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SalvagedMaterials>
    {
        public final static String RESOURCE_PATH = "SDE/items/material/SalvagedMaterials.yaml";
        private Map<String, SalvagedMaterials> cache = (null);

        @Override
        public IMetaCategory<? super SalvagedMaterials> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  754;
        }

        @Override
        public String getName() {
            return "SalvagedMaterials";
        }

        @Override
        public synchronized Map<String, SalvagedMaterials> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SalvagedMaterials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SalvagedMaterials> items;
        }
    }
}
