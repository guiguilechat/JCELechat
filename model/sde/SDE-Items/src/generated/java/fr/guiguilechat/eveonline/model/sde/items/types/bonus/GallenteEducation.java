package fr.guiguilechat.eveonline.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class GallenteEducation
    extends Bonus
{
    /**
     * Scales the capacitor need for fitted modules.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeedMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CharismaSkillTrainingTimeMultiplierBonus;
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CpuMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MemorySkillTrainingTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double PerceptionSkillTrainingTimeMultiplierBonus;
    public final static String RESOURCE_PATH = "SDE/items/bonus/GallenteEducation.yaml";
    private static LinkedHashMap<String, GallenteEducation> cache = (null);

    @Override
    public int getGroupId() {
        return  197;
    }

    @Override
    public Class<?> getGroup() {
        return GallenteEducation.class;
    }

    public static synchronized LinkedHashMap<String, GallenteEducation> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GallenteEducation.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GallenteEducation> items;
    }
}
