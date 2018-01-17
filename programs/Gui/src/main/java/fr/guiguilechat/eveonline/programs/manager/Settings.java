package fr.guiguilechat.eveonline.programs.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.programs.manager.settings.ISettings;

public class Settings implements ISettings {

	@Override
	public String getAppName() {
		return "guiguilechat.evemanager";
	}

	public static class TeamDescription {

		public Set<String> members = new HashSet<>();

		public Set<String> systems = new HashSet<>();

		public static class Provision {

			public HashMap<Integer, Integer> lpoffers = new HashMap<>();

			public HashMap<Integer, Integer> blueprints = new HashMap<>();

			public HashMap<Integer, Integer> total = new HashMap<>();
		}

		public Provision provisionMaterials = new Provision();

		public Provision provisionProduct = new Provision();

		public Provision provisionSO = new Provision();

		@Override
		public TeamDescription clone() {
			TeamDescription ret = new TeamDescription();
			ret.members.addAll(members);
			ret.provisionMaterials.lpoffers.putAll(provisionMaterials.lpoffers);
			ret.provisionMaterials.blueprints.putAll(provisionMaterials.blueprints);
			ret.provisionMaterials.total.putAll(provisionMaterials.total);
			ret.provisionProduct.lpoffers.putAll(provisionProduct.lpoffers);
			ret.provisionProduct.blueprints.putAll(provisionProduct.blueprints);
			ret.provisionProduct.total.putAll(provisionProduct.total);
			ret.provisionSO.lpoffers.putAll(provisionSO.lpoffers);
			ret.provisionSO.blueprints.putAll(provisionSO.blueprints);
			ret.provisionSO.total.putAll(provisionSO.total);
			ret.members.addAll(members);
			ret.systems.addAll(systems);
			return ret;
		}
	}

	public static enum ProvisionType {
		/** provision material : those need to be in BO or present in hangar */
		MATERIAL,
		/**
		 * provision product : those need to be in result of industry jobs, or in
		 * hangar
		 */
		PRODUCT,
		/** provision so : those need to be in SO */
		SO
	}

	public LinkedHashMap<Integer, String> apiKeys = new LinkedHashMap<>();
	public LinkedHashMap<String, String> ssoKeys = new LinkedHashMap<>();

	public LinkedHashMap<String, TeamDescription> teams = new LinkedHashMap<>();

	public String focusedTeam = null;

	public boolean hideDebug = true;

	public static class BurnersEvalParams {
		public String region = "TheForge";
		public double sellTax = 1;
		public double brokerFee = 2;
		public int lpQtty = 1000000;

		public double weightConstel = 1;
		public double weightOut = 1;
		public double hubConstelMult = 1;

		public LinkedHashMap<String, MissionStats> missions = new LinkedHashMap<>();

	}

	/**
	 * stats of a burner mission. isk_indexed is the reward for a 1.0 system and 0
	 * skills, same for lp.<br />
	 * align and warp speed are the stats of the ship used. time to kill is the
	 * time between using the gate, and the time to leave the grid(or loot the
	 * wreck)
	 */
	public static class MissionStats {
		public int isk_cstt = 0, isk_indexed = 0, lp = 0, align_time_s = 0, timetokill_s = 0;
		public double warp_speed_aups = 0.0;
		public boolean burner = true;
		public boolean active = true;

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean isActive) {
			active = isActive;
		}

		public int getIsk_cstt() {
			return isk_cstt;
		}

		public void setIsk_cstt(int isk_cstt) {
			this.isk_cstt = isk_cstt;
		}

		public int getIsk_indexed() {
			return isk_indexed;
		}

		public void setIsk_indexed(int isk_indexed) {
			this.isk_indexed = isk_indexed;
		}

		public int getLp() {
			return lp;
		}

		public void setLp(int lp) {
			this.lp = lp;
		}

		public int getAlign_time_s() {
			return align_time_s;
		}

		public void setAlign_time_s(int align_time_s) {
			this.align_time_s = align_time_s;
		}

		public int getTimetokill_s() {
			return timetokill_s;
		}

		public void setTimetokill_s(int timetokill_s) {
			this.timetokill_s = timetokill_s;
		}

		public double getWarp_speed_aups() {
			return warp_speed_aups;
		}

		public void setWarp_speed_aups(double warp_speed_aups) {
			this.warp_speed_aups = warp_speed_aups;
		}

		public boolean isBurner() {
			return burner;
		}

		public void setBurner(boolean isBurner) {
			burner = isBurner;
		}

		/**
		 *
		 * @param au
		 * @return travel time in seconds, rounded up
		 */
		public int systemTravel(double au) {
			return (int) Math.ceil(align_time_s+(
					warp_speed_aups<6
					?(98.4+au)/warp_speed_aups
							:12.36+(25.73+au-warp_speed_aups/2)/warp_speed_aups));
		}
	}

	public static class InventionParams {
		public String marketRegion = "TheForge";
		public String characterSkills = null;
		public double copyIndex = 4;
		public double copyTax = 0;
		public double inventIndex = 4;
		public double inventTax = 0;
		public double manufactureIndex = 4;
		public double manufactureTax = 0;
		public double sellTax = 1;
		public double brokerFee = 2;
		public String copystruct, inventstruct, manufstruct;
		/**
		 * reduction over the gain in a cycle we accept to calculate the number of
		 * cycles
		 */
		public double maxCycleReduction = 1;
		public double minActionHours = 0;

		public String getMarketRegion() {
			return marketRegion;
		}

		public void setMarketRegion(String marketRegion) {
			this.marketRegion = marketRegion;
		}

		public String getCharacterSkills() {
			return characterSkills;
		}

		public void setCharacterSkills(String characterSkills) {
			this.characterSkills = characterSkills;
		}

		public double getCopyIndex() {
			return copyIndex;
		}

		public void setCopyIndex(double copyIndex) {
			this.copyIndex = copyIndex;
		}

		public double getCopyTax() {
			return copyTax;
		}

		public void setCopyTax(double copyTax) {
			this.copyTax = copyTax;
		}

		public double getInventIndex() {
			return inventIndex;
		}

		public void setInventIndex(double inventIndex) {
			this.inventIndex = inventIndex;
		}

		public double getInventTax() {
			return inventTax;
		}

		public void setInventTax(double inventTax) {
			this.inventTax = inventTax;
		}

		public double getManufactureIndex() {
			return manufactureIndex;
		}

		public void setManufactureIndex(double manufactureIndex) {
			this.manufactureIndex = manufactureIndex;
		}

		public double getManufactureTax() {
			return manufactureTax;
		}

		public void setManufactureTax(double manufactureTax) {
			this.manufactureTax = manufactureTax;
		}

		public double getSellTax() {
			return sellTax;
		}

		public void setSellTax(double sellTax) {
			this.sellTax = sellTax;
		}

		public double getBrokerFee() {
			return brokerFee;
		}

		public void setBrokerFee(double brokerFee) {
			this.brokerFee = brokerFee;
		}

		public String getCopystruct() {
			return copystruct;
		}

		public void setCopystruct(String copystruct) {
			this.copystruct = copystruct;
		}

		public String getInventstruct() {
			return inventstruct;
		}

		public void setInventstruct(String inventstruct) {
			this.inventstruct = inventstruct;
		}

		public String getManufstruct() {
			return manufstruct;
		}

		public void setManufstruct(String manufstruct) {
			this.manufstruct = manufstruct;
		}

		public double getMaxCycleReduction() {
			return maxCycleReduction;
		}

		public void setMaxCycleReduction(double maxCycleLoss) {
			maxCycleReduction = maxCycleLoss;
		}

		public double getMinActionHours() {
			return minActionHours;
		}

		public void setMinActionHours(double minHours) {
			minActionHours = minHours;
		}
	}

	public InventionParams invention = new InventionParams();

	public BurnersEvalParams burners = new BurnersEvalParams();

	@Override
	public Representer makeYamlRepresenter() {
		Representer ret = ISettings.super.makeYamlRepresenter();
		ret.getPropertyUtils().setSkipMissingProperties(true);
		return ret;
	}

}