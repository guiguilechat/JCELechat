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

public class Composite
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
    public static final Composite.MetaGroup METAGROUP = new Composite.MetaGroup();

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
    public IMetaGroup<Composite> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Composite>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/Composite.yaml";
        private Map<Integer, Composite> cache = (null);

        @Override
        public IMetaCategory<? super Composite> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  429;
        }

        @Override
        public String getName() {
            return "Composite";
        }

        @Override
        public synchronized Map<Integer, Composite> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Composite.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Composite> types;
        }
    }
}
