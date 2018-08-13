package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class WorldSpace
    extends Item
{
    public final static WorldSpace.MetaCat METACAT = new WorldSpace.MetaCat();

    @Override
    public int getCategoryId() {
        return  26;
    }

    @Override
    public MetaCategory<WorldSpace> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends WorldSpace> loadCategory() {
        return Stream.of(fr.guiguilechat.jcelechat.model.sde.items.types.worldspace.WorldSpace.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<WorldSpace>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends WorldSpace> [] groups = new MetaGroup[] {fr.guiguilechat.jcelechat.model.sde.items.types.worldspace.WorldSpace.METAGROUP };

        @Override
        public String getName() {
            return "WorldSpace";
        }

        public Collection<MetaGroup<? extends WorldSpace>> groups() {
            return Arrays.asList(groups);
        }
    }
}
