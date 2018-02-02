package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.AssemblyArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.CompressionArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ControlTower;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.CorporateHangarArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.CynosuralGeneratorArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.CynosuralSystemJammer;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ElectronicWarfareBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.EnergyNeutralizingBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ForceFieldArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.JumpPortalArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.Laboratory;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileHybridSentry;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileLaserSentry;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileMissileSentry;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobilePowerCore;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileProjectileSentry;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileReactor;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileShieldGenerator;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MobileStorage;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.MoonMining;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.PersonalHangar;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ReprocessingArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ScannerArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.SensorDampeningBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ShieldHardeningArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.ShipMaintenanceArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.Silo;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.StasisWebificationBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.StealthEmitterArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.StructureRepairArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.TargetPaintingBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.TrackingArray;
import fr.guiguilechat.eveonline.model.sde.items.types.starbase.WarpScramblingBattery;

public abstract class Starbase
    extends Item
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * How long it takes to anchor or unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int AnchoringDelay;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowOffensiveModifiers;
    /**
     * How long it takes to unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int UnanchoringDelay;
    /**
     * How long it takes to bring this object online.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int OnliningDelay;

    @Override
    public int getCategoryId() {
        return  23;
    }

    @Override
    public Class<?> getCategory() {
        return Starbase.class;
    }

    public static Map<String, ? extends Starbase> loadCategory() {
        return Stream.of(PersonalHangar.load(), ShieldHardeningArray.load(), AssemblyArray.load(), CynosuralSystemJammer.load(), MobilePowerCore.load(), MobileShieldGenerator.load(), StealthEmitterArray.load(), MobileLaserSentry.load(), MobileProjectileSentry.load(), ReprocessingArray.load(), Silo.load(), CorporateHangarArray.load(), StructureRepairArray.load(), JumpPortalArray.load(), TargetPaintingBattery.load(), MobileMissileSentry.load(), MoonMining.load(), ShipMaintenanceArray.load(), MobileHybridSentry.load(), TrackingArray.load(), ScannerArray.load(), SensorDampeningBattery.load(), ForceFieldArray.load(), MobileStorage.load(), Laboratory.load(), WarpScramblingBattery.load(), ControlTower.load(), CompressionArray.load(), CynosuralGeneratorArray.load(), ElectronicWarfareBattery.load(), MobileReactor.load(), StasisWebificationBattery.load(), EnergyNeutralizingBattery.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
