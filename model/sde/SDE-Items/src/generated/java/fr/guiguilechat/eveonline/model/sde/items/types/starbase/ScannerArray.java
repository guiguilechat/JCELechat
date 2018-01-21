
package fr.guiguilechat.eveonline.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class ScannerArray
    extends Starbase
{

    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Uniformity;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Duration;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ArmorUniformity;
    /**
     * Radar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanRadarStrength;
    /**
     * Ladar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanMagnetometricStrength;
    /**
     * Gravimetric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanGravimetricStrength;
    /**
     * Minimum scanning error
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MinScanDeviation;
    /**
     * Maximum scan error
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double MaxScanDeviation;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double IncapacitationRatio;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * Whether the structure requires the onliner's alliance to hold sovereignty in the system for it to be onlineable.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnliningRequiresSovereigntyLevel;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldUniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * How many structures in this group can be anchored for the same alliance per solar system.  0 means there is no limit.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PosAnchoredPerSolarSystemAmount;
    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Cpu;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Effective range of scanner in multiples of AUs
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int ScanRange;
    public final static String RESOURCE_PATH = "SDE/items/starbase/ScannerArray.yaml";
    private static LinkedHashMap<String, ScannerArray> cache = (null);

    @Override
    public int getGroupId() {
        return  709;
    }

    @Override
    public Class<?> getGroup() {
        return ScannerArray.class;
    }

    public static LinkedHashMap<String, ScannerArray> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ScannerArray.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ScannerArray> items;

    }

}
