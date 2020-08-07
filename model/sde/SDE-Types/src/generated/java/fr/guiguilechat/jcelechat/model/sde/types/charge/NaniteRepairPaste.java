package fr.guiguilechat.jcelechat.model.sde.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class NaniteRepairPaste
    extends Charge
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double hp;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE })));
    public static final NaniteRepairPaste.MetaGroup METAGROUP = new NaniteRepairPaste.MetaGroup();

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
    public IMetaGroup<NaniteRepairPaste> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NaniteRepairPaste>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/NaniteRepairPaste.yaml";
        private Map<String, NaniteRepairPaste> cache = (null);

        @Override
        public IMetaCategory<? super NaniteRepairPaste> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  916;
        }

        @Override
        public String getName() {
            return "NaniteRepairPaste";
        }

        @Override
        public synchronized Map<String, NaniteRepairPaste> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NaniteRepairPaste.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NaniteRepairPaste> types;
        }
    }
}
