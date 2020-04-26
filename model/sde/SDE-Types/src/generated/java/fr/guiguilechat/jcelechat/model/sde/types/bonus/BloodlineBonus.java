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

public class BloodlineBonus
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
     * Factor to scale mining laser durations by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MiningDurationMultiplier;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    /**
     * Multiplier to adjust the cost of repairs.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double RepairCostMultiplier;
    /**
     * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double SpeedMultiplier;
    public static final BloodlineBonus.MetaGroup METAGROUP = new BloodlineBonus.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  205 :
            {
                return AccuracyMultiplier;
            }
            case  38 :
            {
                return Capacity;
            }
            case  4 :
            {
                return Mass;
            }
            case  203 :
            {
                return MiningDurationMultiplier;
            }
            case  162 :
            {
                return Radius;
            }
            case  187 :
            {
                return RepairCostMultiplier;
            }
            case  204 :
            {
                return SpeedMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<BloodlineBonus> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BloodlineBonus>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/BloodlineBonus.yaml";
        private Map<String, BloodlineBonus> cache = (null);

        @Override
        public IMetaCategory<? super BloodlineBonus> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  190;
        }

        @Override
        public String getName() {
            return "BloodlineBonus";
        }

        @Override
        public synchronized Map<String, BloodlineBonus> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(BloodlineBonus.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, BloodlineBonus> types;
        }
    }
}
