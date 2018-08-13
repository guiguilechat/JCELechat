package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max1YearSKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max30DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max7DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.Max90DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings.PermanentSKIN;

public abstract class SuperKerrInducedNanocoatings
    extends Item
{
    public final static SuperKerrInducedNanocoatings.MetaCat METACAT = new SuperKerrInducedNanocoatings.MetaCat();

    @Override
    public int getCategoryId() {
        return  91;
    }

    @Override
    public MetaCategory<SuperKerrInducedNanocoatings> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends SuperKerrInducedNanocoatings> loadCategory() {
        return Stream.of(Max1YearSKIN.load(), Max30DaySKIN.load(), Max7DaySKIN.load(), Max90DaySKIN.load(), PermanentSKIN.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<SuperKerrInducedNanocoatings>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends SuperKerrInducedNanocoatings> [] groups = new MetaGroup[] {PermanentSKIN.METAGROUP, Max7DaySKIN.METAGROUP, Max30DaySKIN.METAGROUP, Max90DaySKIN.METAGROUP, Max1YearSKIN.METAGROUP };

        @Override
        public String getName() {
            return "SuperKerrInducedNanocoatings";
        }

        public Collection<MetaGroup<? extends SuperKerrInducedNanocoatings>> groups() {
            return Arrays.asList(groups);
        }
    }
}
