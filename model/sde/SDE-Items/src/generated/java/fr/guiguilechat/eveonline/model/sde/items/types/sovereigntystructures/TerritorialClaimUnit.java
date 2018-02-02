package fr.guiguilechat.eveonline.model.sde.items.types.sovereigntystructures;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class TerritorialClaimUnit
    extends SovereigntyStructures
{
    public final static String RESOURCE_PATH = "SDE/items/sovereigntystructures/TerritorialClaimUnit.yaml";
    private static LinkedHashMap<String, TerritorialClaimUnit> cache = (null);

    @Override
    public int getGroupId() {
        return  1003;
    }

    @Override
    public Class<?> getGroup() {
        return TerritorialClaimUnit.class;
    }

    public static synchronized LinkedHashMap<String, TerritorialClaimUnit> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TerritorialClaimUnit.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, TerritorialClaimUnit> items;
    }
}
