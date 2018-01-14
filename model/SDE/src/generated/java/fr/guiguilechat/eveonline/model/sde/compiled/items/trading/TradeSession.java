
package fr.guiguilechat.eveonline.model.sde.compiled.items.trading;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Trading;
import org.yaml.snakeyaml.Yaml;

public class TradeSession
    extends Trading
{

    public final static String RESOURCE_PATH = "SDE/trading/TradeSession.yaml";
    private static LinkedHashMap<String, TradeSession> cache = (null);

    @Override
    public int getGroupId() {
        return  95;
    }

    @Override
    public Class<?> getGroup() {
        return TradeSession.class;
    }

    public static LinkedHashMap<String, TradeSession> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TradeSession> items;

    }

}
