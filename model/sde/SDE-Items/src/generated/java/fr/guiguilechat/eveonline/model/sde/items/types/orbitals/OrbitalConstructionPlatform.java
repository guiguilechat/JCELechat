
package fr.guiguilechat.eveonline.model.sde.items.types.orbitals;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class OrbitalConstructionPlatform
    extends Orbitals
{

    /**
     * How long it takes to unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double UnanchoringDelay;
    /**
     * How long it takes to bring this object online.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double OnliningDelay;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Uniformity;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DisallowOffensiveModifiers;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(100.0D)
    public double SignatureRadius;
    /**
     * Capacity of material bay
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SpecialMaterialBayCapacity;
    /**
     * The maximum distance at which the object can be used.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxOperationalDistance;
    /**
     * Type of object which this object transforms into.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ConstructionType;
    /**
     * How long it takes to anchor or unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double AnchoringDelay;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxOperationalUsers;
    /**
     * Radar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanRadarStrength;
    /**
     * Ladar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanMagnetometricStrength;
    /**
     * Gravimetric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanGravimetricStrength;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
    public final static String RESOURCE_PATH = "SDE/items/orbitals/OrbitalConstructionPlatform.yaml";
    private static LinkedHashMap<String, OrbitalConstructionPlatform> cache = (null);

    @Override
    public int getGroupId() {
        return  1106;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalConstructionPlatform.class;
    }

    public static LinkedHashMap<String, OrbitalConstructionPlatform> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalConstructionPlatform.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, OrbitalConstructionPlatform> items;

    }

}
