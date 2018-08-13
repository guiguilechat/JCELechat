package fr.guiguilechat.jcelechat.model.sde.items.types.drone;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Drone;
import org.yaml.snakeyaml.Yaml;

public class SalvageDrone
    extends Drone
{
    /**
     * Bonus to chance of opening a container.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AccessDifficultyBonus;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double DamageMultiplier;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * The range at which this thing does it thing.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OrbitRange;
    public final static SalvageDrone.MetaGroup METAGROUP = new SalvageDrone.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/drone/SalvageDrone.yaml";
    private static Map<String, SalvageDrone> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  902 :
            {
                return AccessDifficultyBonus;
            }
            case  64 :
            {
                return DamageMultiplier;
            }
            case  73 :
            {
                return Duration;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  157 :
            {
                return OrbitRange;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1159;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SalvageDrone> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SalvageDrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SalvageDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SalvageDrone> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SalvageDrone>
    {

        @Override
        public MetaCategory<? super SalvageDrone> category() {
            return Drone.METACAT;
        }

        @Override
        public String getName() {
            return "SalvageDrone";
        }

        @Override
        public Collection<SalvageDrone> items() {
            return (load().values());
        }
    }
}
