
package fr.guiguilechat.eveonline.model.sde.items.types.sovereigntystructures;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class InfrastructureHub
    extends SovereigntyStructures
{

    /**
     * How many meters from the standard warp-in distance a planet can be anchored from.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100000.0D)
    public double PlanetAnchorDistance;
    public final static String RESOURCE_PATH = "SDE/items/sovereigntystructures/InfrastructureHub.yaml";
    private static LinkedHashMap<String, InfrastructureHub> cache = (null);

    @Override
    public int getGroupId() {
        return  1012;
    }

    @Override
    public Class<?> getGroup() {
        return InfrastructureHub.class;
    }

    public static LinkedHashMap<String, InfrastructureHub> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfrastructureHub.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, InfrastructureHub> items;

    }

}
