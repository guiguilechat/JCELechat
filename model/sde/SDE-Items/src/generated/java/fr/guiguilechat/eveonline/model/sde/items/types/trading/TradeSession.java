package fr.guiguilechat.eveonline.model.sde.items.types.trading;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Trading;
import org.yaml.snakeyaml.Yaml;

public class TradeSession
    extends Trading
{
    public final static String RESOURCE_PATH = "SDE/items/trading/TradeSession.yaml";
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
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TradeSession.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, TradeSession> items;
    }
}
