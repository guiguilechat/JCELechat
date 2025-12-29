package fr.guiguilechat.jcelechat.model.sde.types.ship;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class CommandDestroyer
    extends Ship
{
    /**
     * reduction in MicroWarp Drive signature
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mwdsignatureradiusbonus;
    /**
     * Tells if this type (ship) can be affected by the Rorqual Invulnerability Module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int affectedbyindustrialinvulnmodule;
    /**
     * Tells if this type (ship) can be placed in the maintenance bay of a capital industrial ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int allowedincapindustrialmaintenancebay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double destroyerrofpenality;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonuscommanddestroyer1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonuscommanddestroyer2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * Capacity of general mining hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int generalminingholdcapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationmed;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullemdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullexplosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullkineticdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullthermaldamageresonance;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int integratedminingscanner;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through an Industrial Jump Conduit.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isindustrialjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through an Industrial Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isindustrialjumpportalpassenger;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lowslots;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int maxdirectionalvelocity;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double mintargetveldmgmultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigslots;
    /**
     * role bonus for command destroyers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscd;
    /**
     * bonus to range of tractor beams
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonustractorbeamrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonustractorbeamvelocity;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusad1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusad2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonuscd1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuscd2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgd1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusgd2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusmd1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusmd2;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusoredestroyer1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusoredestroyer2;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusoredestroyer3;
    /**
     * Precursor Destroyer Skill Attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuspd1;
    /**
     * Precursor Destroyer Skill Attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuspd2;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole2;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipscanresistance;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, MWDSignatureRadiusBonus.INSTANCE, EliteBonusCommandDestroyer1 .INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, EliteBonusCommandDestroyer2 .INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, RoleBonusCD.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, RoleBonusTractorBeamRange.INSTANCE, MaxTargetRange.INSTANCE, RoleBonusTractorBeamVelocity.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, IntegratedMiningScanner.INSTANCE, ShipBonusAD1 .INSTANCE, ShipBonusAD2 .INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, ShipBonusOreDestroyer1 .INSTANCE, CargoScanResistance.INSTANCE, ShipBonusOreDestroyer2 .INSTANCE, ShipBonusOreDestroyer3 .INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, HullEmDamageResonance.INSTANCE, HullExplosiveDamageResonance.INSTANCE, HullKineticDamageResonance.INSTANCE, ScanRadarStrength.INSTANCE, HullThermalDamageResonance.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, DestroyerROFpenality.INSTANCE, ShipBonusMD1 .INSTANCE, PropulsionGraphicID.INSTANCE, ShipBonusCD1 .INSTANCE, ShipBonusCD2 .INSTANCE, ShieldRechargeRate.INSTANCE, ShipBonusGD1 .INSTANCE, CapacitorCapacity.INSTANCE, ShipBonusGD2 .INSTANCE, ShipBonusMD2 .INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, ShipBonusPD1 .INSTANCE, ShipBonusPD2 .INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, ShipBonusRole1 .INSTANCE, ShipBonusRole2 .INSTANCE, IsIndustrialJumpConduitPassenger.INSTANCE, IsIndustrialJumpPortalPassenger.INSTANCE, EnergyWarfareResistance.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final CommandDestroyer.MetaGroup METAGROUP = new CommandDestroyer.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1803 :
            {
                return mwdsignatureradiusbonus;
            }
            case  2464 :
            {
                return affectedbyindustrialinvulnmodule;
            }
            case  1891 :
            {
                return allowedincapindustrialmaintenancebay;
            }
            case  727 :
            {
                return destroyerrofpenality;
            }
            case  2059 :
            {
                return elitebonuscommanddestroyer1;
            }
            case  2060 :
            {
                return elitebonuscommanddestroyer2;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1556 :
            {
                return generalminingholdcapacity;
            }
            case  1259 :
            {
                return heatattenuationhi;
            }
            case  1262 :
            {
                return heatattenuationlow;
            }
            case  1261 :
            {
                return heatattenuationmed;
            }
            case  14 :
            {
                return hislots;
            }
            case  974 :
            {
                return hullemdamageresonance;
            }
            case  975 :
            {
                return hullexplosivedamageresonance;
            }
            case  976 :
            {
                return hullkineticdamageresonance;
            }
            case  977 :
            {
                return hullthermaldamageresonance;
            }
            case  5978 :
            {
                return integratedminingscanner;
            }
            case  3324 :
            {
                return isindustrialjumpconduitpassenger;
            }
            case  3325 :
            {
                return isindustrialjumpportalpassenger;
            }
            case  12 :
            {
                return lowslots;
            }
            case  124 :
            {
                return maincolor;
            }
            case  661 :
            {
                return maxdirectionalvelocity;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  13 :
            {
                return medslots;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  2064 :
            {
                return rolebonuscd;
            }
            case  1355 :
            {
                return rolebonustractorbeamrange;
            }
            case  1357 :
            {
                return rolebonustractorbeamvelocity;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  1887 :
            {
                return shipbonusad1;
            }
            case  1888 :
            {
                return shipbonusad2;
            }
            case  734 :
            {
                return shipbonuscd1;
            }
            case  735 :
            {
                return shipbonuscd2;
            }
            case  738 :
            {
                return shipbonusgd1;
            }
            case  739 :
            {
                return shipbonusgd2;
            }
            case  729 :
            {
                return shipbonusmd1;
            }
            case  740 :
            {
                return shipbonusmd2;
            }
            case  5820 :
            {
                return shipbonusoredestroyer1;
            }
            case  5821 :
            {
                return shipbonusoredestroyer2;
            }
            case  5822 :
            {
                return shipbonusoredestroyer3;
            }
            case  2799 :
            {
                return shipbonuspd1;
            }
            case  2800 :
            {
                return shipbonuspd2;
            }
            case  2298 :
            {
                return shipbonusrole1;
            }
            case  2299 :
            {
                return shipbonusrole2;
            }
            case  511 :
            {
                return shipscanresistance;
            }
            case  1768 :
            {
                return typecolorscheme;
            }
            case  1132 :
            {
                return upgradecapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<CommandDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CommandDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/CommandDestroyer.yaml";
        private Map<Integer, CommandDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super CommandDestroyer> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1534;
        }

        @Override
        public String getName() {
            return "CommandDestroyer";
        }

        @Override
        public synchronized Map<Integer, CommandDestroyer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CommandDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, CommandDestroyer> types;
        }
    }
}
