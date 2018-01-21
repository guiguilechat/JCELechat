
package fr.guiguilechat.eveonline.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class NPCForwardOperatingBase
    extends Structure
{

    /**
     * The maximum possible target range.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(300000.0D)
    public double MaximumRangeCap;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double NpcStructureStasisWebificationBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double NpcStructureEnergyWarfareBonus;
    /**
     * Number of hours of vulnerability each week required. Applies only to categoryStructure.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double VulnerabilityRequired;
    public final static String RESOURCE_PATH = "SDE/items/structure/NPCForwardOperatingBase.yaml";
    private static LinkedHashMap<String, NPCForwardOperatingBase> cache = (null);

    @Override
    public int getGroupId() {
        return  1924;
    }

    @Override
    public Class<?> getGroup() {
        return NPCForwardOperatingBase.class;
    }

    public static LinkedHashMap<String, NPCForwardOperatingBase> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCForwardOperatingBase.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, NPCForwardOperatingBase> items;

    }

}
