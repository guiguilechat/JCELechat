package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.owner.Character;

public abstract class Owner
    extends Item
{
    public final static Owner.MetaCat METACAT = new Owner.MetaCat();

    @Override
    public int getCategoryId() {
        return  1;
    }

    @Override
    public MetaCategory<Owner> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Owner> loadCategory() {
        return Stream.of(Character.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Owner>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Owner> [] groups = new MetaGroup[] {Character.METAGROUP };

        @Override
        public String getName() {
            return "Owner";
        }

        public Collection<MetaGroup<? extends Owner>> groups() {
            return Arrays.asList(groups);
        }
    }
}
