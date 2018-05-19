package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class StructureResistanceSwitcherScript
    extends Charge
{
    /**
     * Sets Em damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ArmorEmDamageResonancePostAssignment;
    /**
     * Sets Explosive damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ArmorExplosiveDamageResonancePostAssignment;
    /**
     * Sets Kinetic damage taken by Armor. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ArmorKineticDamageResonancePostAssignment;
    /**
     * Sets Thermal damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ArmorThermalDamageResonancePostAssignment;
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeSize;
    /**
     * Sets Em damage taken by Hull. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonancePostAssignment;
    /**
     * Sets Explosive damage taken by Hull. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonancePostAssignment;
    /**
     * Sets Kinetic damage taken by Hull. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonancePostAssignment;
    /**
     * Sets Em damage taken by Shields. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ShieldEmDamageResonancePostAssignment;
    /**
     * Sets Explosive damage taken by shields. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ShieldExplosiveDamageResonancePostAssignment;
    /**
     * Sets kinetic damage taken by Shields. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ShieldKineticDamageResonancePostAssignment;
    /**
     * Sets Thermal damage taken by Shields. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ShieldThermalDamageResonancePostAssignment;
    /**
     * Sets Thermal damage taken by Hull. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonancePostAssignment;
    public final static String RESOURCE_PATH = "SDE/items/charge/StructureResistanceSwitcherScript.yaml";
    private static LinkedHashMap<String, StructureResistanceSwitcherScript> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2079 :
            {
                return ArmorEmDamageResonancePostAssignment;
            }
            case  2080 :
            {
                return ArmorExplosiveDamageResonancePostAssignment;
            }
            case  2081 :
            {
                return ArmorKineticDamageResonancePostAssignment;
            }
            case  2082 :
            {
                return ArmorThermalDamageResonancePostAssignment;
            }
            case  128 :
            {
                return ChargeSize;
            }
            case  2087 :
            {
                return EmDamageResonancePostAssignment;
            }
            case  2088 :
            {
                return ExplosiveDamageResonancePostAssignment;
            }
            case  2090 :
            {
                return KineticDamageResonancePostAssignment;
            }
            case  2083 :
            {
                return ShieldEmDamageResonancePostAssignment;
            }
            case  2084 :
            {
                return ShieldExplosiveDamageResonancePostAssignment;
            }
            case  2085 :
            {
                return ShieldKineticDamageResonancePostAssignment;
            }
            case  2086 :
            {
                return ShieldThermalDamageResonancePostAssignment;
            }
            case  2089 :
            {
                return ThermalDamageResonancePostAssignment;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1559;
    }

    @Override
    public Class<?> getGroup() {
        return StructureResistanceSwitcherScript.class;
    }

    public static synchronized LinkedHashMap<String, StructureResistanceSwitcherScript> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureResistanceSwitcherScript.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureResistanceSwitcherScript> items;
    }
}
