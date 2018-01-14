
package fr.guiguilechat.eveonline.model.sde.compiled.items.sovereigntystructures;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class TerritorialClaimUnit
    extends SovereigntyStructures
{

    public final static String RESOURCE_PATH = "SDE/sovereigntystructures/TerritorialClaimUnit.yaml";
    private static LinkedHashMap<String, TerritorialClaimUnit> cache = (null);

    @Override
    public int getGroupId() {
        return  1003;
    }

    @Override
    public Class<?> getGroup() {
        return TerritorialClaimUnit.class;
    }

    public static LinkedHashMap<String, TerritorialClaimUnit> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TerritorialClaimUnit> items;

    }

}
