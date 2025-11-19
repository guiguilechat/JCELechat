package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.MoonMiningAmount;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import org.yaml.snakeyaml.LoaderOptions;
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
    public int moonminingamount;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MoonMiningAmount.INSTANCE })));
    public static final BiochemicalMaterial.MetaGroup METAGROUP = new BiochemicalMaterial.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  726 :
            {
                return moonminingamount;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<BiochemicalMaterial> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BiochemicalMaterial>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/BiochemicalMaterial.yaml";
        private Map<Integer, BiochemicalMaterial> cache = (null);

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
        public synchronized Map<Integer, BiochemicalMaterial> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(BiochemicalMaterial.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, BiochemicalMaterial> types;
        }
    }
}
