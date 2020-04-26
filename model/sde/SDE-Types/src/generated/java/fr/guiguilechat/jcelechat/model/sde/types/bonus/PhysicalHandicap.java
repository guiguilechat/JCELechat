package fr.guiguilechat.jcelechat.model.sde.types.bonus;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhysicalHandicap
    extends Bonus
{
    /**
     * Scales the accuracy of some targeted weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double AccuracyMultiplier;
    /**
     * Scales the capacitor need for fitted modules.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeedMultiplier;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    public static final PhysicalHandicap.MetaGroup METAGROUP = new PhysicalHandicap.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  205 :
            {
                return AccuracyMultiplier;
            }
            case  216 :
            {
                return CapacitorNeedMultiplier;
            }
            case  38 :
            {
                return Capacity;
            }
            case  4 :
            {
                return Mass;
            }
            case  162 :
            {
                return Radius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<PhysicalHandicap> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhysicalHandicap>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/PhysicalHandicap.yaml";
        private Map<String, PhysicalHandicap> cache = (null);

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
        public synchronized Map<String, PhysicalHandicap> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PhysicalHandicap.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PhysicalHandicap> types;
        }
    }
}
