package fr.guiguilechat.jcelechat.model.sde.types.drone;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Drone;
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
    @DefaultDoubleValue(0.0)
    public double MaxRange;
    /**
     * The range at which this thing does it thing.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OrbitRange;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill4;
    /**
     * Required skill level for skill 4
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill4Level;
    public static final SalvageDrone.MetaGroup METAGROUP = new SalvageDrone.MetaGroup();

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
            case  1285 :
            {
                return RequiredSkill4;
            }
            case  1286 :
            {
                return RequiredSkill4Level;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<SalvageDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SalvageDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/drone/SalvageDrone.yaml";
        private Map<String, SalvageDrone> cache = (null);

        @Override
        public IMetaCategory<? super SalvageDrone> category() {
            return Drone.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1159;
        }

        @Override
        public String getName() {
            return "SalvageDrone";
        }

        @Override
        public synchronized Map<String, SalvageDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SalvageDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SalvageDrone> types;
        }
    }
}
