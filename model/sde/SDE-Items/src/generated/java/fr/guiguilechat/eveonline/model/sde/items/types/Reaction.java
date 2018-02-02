package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.ComplexBiochemicalReactions;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.ComplexReactions;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.EnslavementPrograms;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.FreedomPrograms;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.HybridReactions;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.SimpleBiochemicalReactions;
import fr.guiguilechat.eveonline.model.sde.items.types.reaction.SimpleReaction;

public abstract class Reaction
    extends Item
{

    @Override
    public int getCategoryId() {
        return  24;
    }

    @Override
    public Class<?> getCategory() {
        return Reaction.class;
    }

    public static Map<String, ? extends Reaction> loadCategory() {
        return Stream.of(ComplexBiochemicalReactions.load(), FreedomPrograms.load(), SimpleReaction.load(), EnslavementPrograms.load(), SimpleBiochemicalReactions.load(), ComplexReactions.load(), HybridReactions.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
