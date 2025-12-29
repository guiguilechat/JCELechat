package fr.guiguilechat.jcelechat.model.sde.types.bonus;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AccuracyMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;

public class PhysicalHandicap
    extends Bonus
{
    /**
     * Scales the accuracy of some targeted weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double accuracymultiplier;
    /**
     * Scales the capacitor need for fitted modules.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneedmultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CapacitorNeedMultiplier.INSTANCE, AccuracyMultiplier.INSTANCE })));
    public static final PhysicalHandicap.MetaGroup METAGROUP = new PhysicalHandicap.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  205 :
            {
                return accuracymultiplier;
            }
            case  216 :
            {
                return capacitorneedmultiplier;
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
    public IMetaGroup<PhysicalHandicap> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhysicalHandicap>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/PhysicalHandicap.yaml";
        private Map<Integer, PhysicalHandicap> cache = (null);

        @Override
        public IMetaCategory<? super PhysicalHandicap> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  192;
        }

        @Override
        public String getName() {
            return "PhysicalHandicap";
        }

        @Override
        public synchronized Map<Integer, PhysicalHandicap> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PhysicalHandicap.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PhysicalHandicap> types;
        }
    }
}
