package fr.guiguilechat.jcelechat.model.sde.items.types.sovereigntystructures;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class InfrastructureHub
    extends SovereigntyStructures
{
    /**
     * How many meters from the standard warp-in distance a planet can be anchored from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100000)
    public int PlanetAnchorDistance;
    public final static String RESOURCE_PATH = "SDE/items/sovereigntystructures/InfrastructureHub.yaml";
    private static LinkedHashMap<String, InfrastructureHub> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  865 :
            {
                return PlanetAnchorDistance;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1012;
    }

    @Override
    public Class<?> getGroup() {
        return InfrastructureHub.class;
    }

    public static synchronized LinkedHashMap<String, InfrastructureHub> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfrastructureHub.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfrastructureHub> items;
    }
}
