package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.reaction.FreedomPrograms;

public abstract class Reaction
    extends Item
{
    public final static Reaction.MetaCat METACAT = new Reaction.MetaCat();

    @Override
    public int getCategoryId() {
        return  24;
    }

    @Override
    public MetaCategory<Reaction> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Reaction> loadCategory() {
        return Stream.of(FreedomPrograms.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Reaction>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Reaction> [] groups = new MetaGroup[] {FreedomPrograms.METAGROUP };

        @Override
        public String getName() {
            return "Reaction";
        }

        public Collection<MetaGroup<? extends Reaction>> groups() {
            return Arrays.asList(groups);
        }
    }
}
