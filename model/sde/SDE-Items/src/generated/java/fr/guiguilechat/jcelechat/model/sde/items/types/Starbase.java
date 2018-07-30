package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.AssemblyArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.CompressionArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ControlTower;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.CorporateHangarArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.CynosuralGeneratorArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.CynosuralSystemJammer;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ElectronicWarfareBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.EnergyNeutralizingBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ForceFieldArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.JumpPortalArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.Laboratory;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileHybridSentry;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileLaserSentry;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileMissileSentry;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobilePowerCore;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileProjectileSentry;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileReactor;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileShieldGenerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MobileStorage;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.MoonMining;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.PersonalHangar;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ReprocessingArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ScannerArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.SensorDampeningBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ShieldHardeningArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.ShipMaintenanceArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.Silo;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.StasisWebificationBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.StealthEmitterArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.StructureRepairArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.TargetPaintingBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.TrackingArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.starbase.WarpScramblingBattery;

public abstract class Starbase
    extends Item
{
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
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * How long it takes to bring this object online.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int OnliningDelay;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * How long it takes to unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int UnanchoringDelay;

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  556 :
            {
                return AnchoringDelay;
            }
            case  872 :
            {
                return DisallowOffensiveModifiers;
            }
            case  9 :
            {
                return Hp;
            }
            case  677 :
            {
                return OnliningDelay;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  676 :
            {
                return UnanchoringDelay;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getCategoryId() {
        return  23;
    }

    @Override
    public Class<?> getCategory() {
        return Starbase.class;
    }

    public static Map<String, ? extends Starbase> loadCategory() {
        return Stream.of(AssemblyArray.load(), CompressionArray.load(), ControlTower.load(), CorporateHangarArray.load(), CynosuralGeneratorArray.load(), CynosuralSystemJammer.load(), ElectronicWarfareBattery.load(), EnergyNeutralizingBattery.load(), ForceFieldArray.load(), JumpPortalArray.load(), Laboratory.load(), MobileHybridSentry.load(), MobileLaserSentry.load(), MobileMissileSentry.load(), MobilePowerCore.load(), MobileProjectileSentry.load(), MobileReactor.load(), MobileShieldGenerator.load(), MobileStorage.load(), MoonMining.load(), PersonalHangar.load(), ReprocessingArray.load(), ScannerArray.load(), SensorDampeningBattery.load(), ShieldHardeningArray.load(), ShipMaintenanceArray.load(), Silo.load(), StasisWebificationBattery.load(), StealthEmitterArray.load(), StructureRepairArray.load(), TargetPaintingBattery.load(), TrackingArray.load(), WarpScramblingBattery.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
