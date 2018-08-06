package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class ShieldHardeningArray
    extends Starbase
{
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Multiplier to the EM damage resonance of something.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonanceMultiplier;
    /**
     * Multiplier to the explosive damage resistance of something.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonanceMultiplier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * Multiplier to the kinetic damage resonance of something.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonanceMultiplier;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * Multipler to adjust the thermal damage resonance of an object.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonanceMultiplier;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    public final static String RESOURCE_PATH = "SDE/items/starbase/ShieldHardeningArray.yaml";
    private static LinkedHashMap<String, ShieldHardeningArray> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  50 :
            {
                return Cpu;
            }
            case  133 :
            {
                return EmDamageResonanceMultiplier;
            }
            case  132 :
            {
                return ExplosiveDamageResonanceMultiplier;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  131 :
            {
                return KineticDamageResonanceMultiplier;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  130 :
            {
                return ThermalDamageResonanceMultiplier;
            }
            case  136 :
            {
                return Uniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  444;
    }

    @Override
    public Class<?> getGroup() {
        return ShieldHardeningArray.class;
    }

    public static synchronized LinkedHashMap<String, ShieldHardeningArray> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ShieldHardeningArray.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ShieldHardeningArray> items;
    }
}
