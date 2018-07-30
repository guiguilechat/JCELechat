package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max1YearSKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max30DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max7DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max90DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.PermanentSKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.VolatileSKIN;

public abstract class SuperKerrInducedNanocoatings
    extends Item
{

    @Override
    public int getCategoryId() {
        return  91;
    }

    @Override
    public Class<?> getCategory() {
        return SuperKerrInducedNanocoatings.class;
    }

    public static Map<String, ? extends SuperKerrInducedNanocoatings> loadCategory() {
        return Stream.of(Max1YearSKIN.load(), Max30DaySKIN.load(), Max7DaySKIN.load(), Max90DaySKIN.load(), PermanentSKIN.load(), VolatileSKIN.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
