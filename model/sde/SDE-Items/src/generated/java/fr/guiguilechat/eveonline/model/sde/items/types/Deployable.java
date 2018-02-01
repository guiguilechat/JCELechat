package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.EncounterSurveillanceSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileCynoInhibitor;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileDecoyUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileDepot;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileJumpDisruptor;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileMicroJumpUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileScanInhibitor;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileSiphonUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileTractorUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileVault;
import fr.guiguilechat.eveonline.model.sde.items.types.deployable.MobileWarpDisruptor;

public abstract class Deployable
    extends Item
{
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrength;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;

    @Override
    public int getCategoryId() {
        return  22;
    }

    @Override
    public Class<?> getCategory() {
        return Deployable.class;
    }

    public static Map<String, ? extends Deployable> loadCategory() {
        return Stream.of(MobileDecoyUnit.load(), MobileScanInhibitor.load(), MobileSiphonUnit.load(), MobileVault.load(), MobileJumpDisruptor.load(), MobileDepot.load(), EncounterSurveillanceSystem.load(), MobileTractorUnit.load(), MobileMicroJumpUnit.load(), MobileWarpDisruptor.load(), MobileCynoInhibitor.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
