package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.decryptors.DecryptorsAmarr;
import fr.guiguilechat.eveonline.model.sde.items.types.decryptors.DecryptorsCaldari;
import fr.guiguilechat.eveonline.model.sde.items.types.decryptors.DecryptorsGallente;
import fr.guiguilechat.eveonline.model.sde.items.types.decryptors.DecryptorsHybrid;
import fr.guiguilechat.eveonline.model.sde.items.types.decryptors.DecryptorsMinmatar;
import fr.guiguilechat.eveonline.model.sde.items.types.decryptors.GenericDecryptor;

public abstract class Decryptors
    extends Item
{
    /**
     * Modifies the max runs in a blueprint created through invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionMaxRunModifier;
    /**
     * Modifies base chance of successful invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionPropabilityMultiplier;
    /**
     * Modifies the mineral efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionMEModifier;
    /**
     * Modifies the time efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionTEModifier;

    @Override
    public int getCategoryId() {
        return  35;
    }

    @Override
    public Class<?> getCategory() {
        return Decryptors.class;
    }

    public static Map<String, ? extends Decryptors> loadCategory() {
        return Stream.of(DecryptorsGallente.load(), DecryptorsHybrid.load(), DecryptorsAmarr.load(), DecryptorsMinmatar.load(), DecryptorsCaldari.load(), GenericDecryptor.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
