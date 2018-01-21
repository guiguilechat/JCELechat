
package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class StructureECMScript
    extends Charge
{

    /**
     * The size of the charges that can fit in the turret/whatever.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ChargeSize;
    /**
     * Bonus to Gravimetric Strength bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ScanGravimetricStrengthBonusBonus;
    /**
     * One of the groups of launcher this charge can be loaded into.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double LauncherGroup;
    /**
     * Bonus to Lader Strength bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ScanLadarStrengthBonusBonus;
    /**
     * Bonus to Magnetometric Strength bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ScanMagnetometricStrengthBonusBonus;
    /**
     * Bonus to Radar Strength bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ScanRadarStrengthBonusBonus;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double StructureItemVisualFlag;
    public final static String RESOURCE_PATH = "SDE/items/charge/StructureECMScript.yaml";
    private static LinkedHashMap<String, StructureECMScript> cache = (null);

    @Override
    public int getGroupId() {
        return  1549;
    }

    @Override
    public Class<?> getGroup() {
        return StructureECMScript.class;
    }

    public static LinkedHashMap<String, StructureECMScript> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureECMScript.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StructureECMScript> items;

    }

}
