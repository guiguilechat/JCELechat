package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.trading.TradeSession;

public abstract class Trading
    extends Item
{

    @Override
    public int getCategoryId() {
        return  10;
    }

    @Override
    public Class<?> getCategory() {
        return Trading.class;
    }

    public static Map<String, ? extends Trading> loadCategory() {
        return Stream.of(TradeSession.load(), fr.guiguilechat.eveonline.model.sde.items.types.trading.Trading.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
