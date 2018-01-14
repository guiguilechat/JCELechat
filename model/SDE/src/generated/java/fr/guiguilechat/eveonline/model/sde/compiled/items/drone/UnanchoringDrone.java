
package fr.guiguilechat.eveonline.model.sde.compiled.items.drone;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Drone;
import org.yaml.snakeyaml.Yaml;

public class UnanchoringDrone
    extends Drone
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
     * Maximum range at which the scanner can lock a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double MaxTargetRange;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2;
    /**
     * The range at which this thing does it thing.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double OrbitRange;
    public final static String RESOURCE_PATH = "SDE/drone/UnanchoringDrone.yaml";
    private static LinkedHashMap<String, UnanchoringDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  470;
    }

    @Override
    public Class<?> getGroup() {
        return UnanchoringDrone.class;
    }

    public static LinkedHashMap<String, UnanchoringDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, UnanchoringDrone> items;

    }

}
