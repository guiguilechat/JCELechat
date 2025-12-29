package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.types.Material;

public class AncientSalvage
    extends Material
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Hp.INSTANCE })));
    public static final AncientSalvage.MetaGroup METAGROUP = new AncientSalvage.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return hp;
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
    public IMetaGroup<AncientSalvage> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AncientSalvage>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/AncientSalvage.yaml";
        private Map<Integer, AncientSalvage> cache = (null);

        @Override
        public IMetaCategory<? super AncientSalvage> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  966;
        }

        @Override
        public String getName() {
            return "AncientSalvage";
        }

        @Override
        public synchronized Map<Integer, AncientSalvage> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AncientSalvage.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, AncientSalvage> types;
        }
    }
}
