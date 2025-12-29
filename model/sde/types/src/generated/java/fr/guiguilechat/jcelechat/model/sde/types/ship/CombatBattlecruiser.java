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

public class CombatBattlecruiser
    extends Ship
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(30000)
    public int covertopsandreconopscloakmoduledelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
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
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through a BlackOps Jump Conduit
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a BlackOps Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpportalpassenger;
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
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double rolebonuscbc;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscbc2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscommandburstaoerange;
    /**
     * Bonus to drone damage, HP and mining yield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiedronebonus;
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
    @DefaultRealValue(0.0)
    public double shipbonusabc1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusabc2;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusabc3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonuscbc1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonuscbc2;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonuscbc3;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuscbc4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgbc1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgbc2;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusgbc3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusmbc1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusmbc2;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusmbc3;
    /**
     * Precursor Battlecruiser Skill Attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuspbc1;
    /**
     * Precursor Battlecruiser Skill Attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuspbc2;
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
     * Fixed Role Bonus on a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole7;
    /**
     * Second Stock Bonus on Pirate Faction Ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole8;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonuswarpspeed;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CovertOpsAndReconOpsCloakModuleDelay.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, ShipBonusRole7 .INSTANCE, ShipBonusABC1 .INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, RookieDroneBonus.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, ShipBonusCBC4 .INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, ShipBonusABC3 .INSTANCE, ShipBonusMBC3 .INSTANCE, WarpSpeedMultiplier.INSTANCE, ShipBonusABC2 .INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, ShipBonusRole8 .INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, ShipBonusCBC3 .INSTANCE, RoleBonusCBC2 .INSTANCE, RequiredSkill1 .INSTANCE, ShipBonusGBC3 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, ShipRoleBonusWarpSpeed.INSTANCE, ShipBonusCBC1 .INSTANCE, TypeColorScheme.INSTANCE, ShipBonusCBC2 .INSTANCE, ShipBonusGBC2 .INSTANCE, HeatAttenuationHi.INSTANCE, ShipBonusGBC1 .INSTANCE, ShipBonusMBC1 .INSTANCE, HeatAttenuationMed.INSTANCE, ShipBonusMBC2 .INSTANCE, HeatAttenuationLow.INSTANCE, ShipBonusPBC1 .INSTANCE, ShipBonusPBC2 .INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsBlackOpsJumpPortalPassenger.INSTANCE, ShipBonusRole1 .INSTANCE, IsBlackOpsJumpConduitPassenger.INSTANCE, RoleBonusCBC.INSTANCE, ShipBonusRole2 .INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final CombatBattlecruiser.MetaGroup METAGROUP = new CombatBattlecruiser.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1034 :
            {
                return covertopsandreconopscloakmoduledelay;
            }
            case  1555 :
            {
                return fwlpkill;
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
            case  3322 :
            {
                return isblackopsjumpconduitpassenger;
            }
            case  3320 :
            {
                return isblackopsjumpportalpassenger;
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
            case  1692 :
            {
                return metagroupid;
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
            case  2043 :
            {
                return rolebonuscbc;
            }
            case  5045 :
            {
                return rolebonuscbc2;
            }
            case  2574 :
            {
                return rolebonuscommandburstaoerange;
            }
            case  1831 :
            {
                return rookiedronebonus;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  795 :
            {
                return shipbonusabc1;
            }
            case  1889 :
            {
                return shipbonusabc2;
            }
            case  5205 :
            {
                return shipbonusabc3;
            }
            case  743 :
            {
                return shipbonuscbc1;
            }
            case  745 :
            {
                return shipbonuscbc2;
            }
            case  5044 :
            {
                return shipbonuscbc3;
            }
            case  5956 :
            {
                return shipbonuscbc4;
            }
            case  747 :
            {
                return shipbonusgbc1;
            }
            case  746 :
            {
                return shipbonusgbc2;
            }
            case  5046 :
            {
                return shipbonusgbc3;
            }
            case  748 :
            {
                return shipbonusmbc1;
            }
            case  749 :
            {
                return shipbonusmbc2;
            }
            case  5207 :
            {
                return shipbonusmbc3;
            }
            case  2801 :
            {
                return shipbonuspbc1;
            }
            case  2802 :
            {
                return shipbonuspbc2;
            }
            case  2298 :
            {
                return shipbonusrole1;
            }
            case  2299 :
            {
                return shipbonusrole2;
            }
            case  793 :
            {
                return shipbonusrole7;
            }
            case  1688 :
            {
                return shipbonusrole8;
            }
            case  2789 :
            {
                return shiprolebonuswarpspeed;
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
    public IMetaGroup<CombatBattlecruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CombatBattlecruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/CombatBattlecruiser.yaml";
        private Map<Integer, CombatBattlecruiser> cache = (null);

        @Override
        public IMetaCategory<? super CombatBattlecruiser> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  419;
        }

        @Override
        public String getName() {
            return "CombatBattlecruiser";
        }

        @Override
        public synchronized Map<Integer, CombatBattlecruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CombatBattlecruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CombatBattlecruiser> types;
        }
    }
}
