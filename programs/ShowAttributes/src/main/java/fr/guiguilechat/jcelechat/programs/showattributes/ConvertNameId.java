package fr.guiguilechat.jcelechat.programs.showattributes;

import java.io.IOException;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;

public class ConvertNameId {

	public static void main(String[] args) throws IOException {
		String in = "125mm Light Carbine Repeating Cannon I\n" + "125mm Light Prototype Automatic Cannon\n"
				+ "18th Tier Overseer's Personal Effects\n" + "1st Tier Overseer's Personal Effects\n"
				+ "200mm Carbide Railgun I\n" + "200mm Light Carbine Repeating Cannon I\n"
				+ "220mm Medium Carbine Repeating Cannon I\n" + "250mm 'Scout' Accelerator Cannon\n"
				+ "250mm Carbide Railgun I\n" + "250mm Light Carbine Howitzer I\n" + "250mm Light Gallium Cannon\n"
				+ "280mm Gallium Cannon\n" + "2nd Tier Overseer's Personal Effects\n" + "3rd Tier Overseer's Personal Effects\n"
				+ "425mm Medium Gallium Machine Gun\n" + "425mm Medium Prototype Automatic Cannon\n"
				+ "4th Tier Overseer's Personal Effects\n" + "5th Tier Overseer's Personal Effects\n"
				+ "650mm Medium Carbine Howitzer I\n" + "650mm Medium Gallium Cannon\n" + "75mm Carbide Railgun I\n"
				+ "7th Tier Overseer's Personal Effects\n" + "8th Tier Overseer's Personal Effects\n"
				+ "Advanced 'Limos' Heavy Missile Bay I\n" + "AE-K Compact Drone Damage Amplifier\n"
				+ "Ancient Coordinates Database\n" + "Arbalest Compact Light Missile Launcher\n" + "Auxiliary Parts\n"
				+ "Compact EM Shield Amplifier\n" + "Compact Explosive Shield Hardener\n" + "Compact Kinetic Shield Amplifier\n"
				+ "Compact Kinetic Shield Hardener\n" + "Compact Multispectrum Shield Hardener\n"
				+ "Copasetic Compact Shield Boost Amplifier\n" + "Counterbalanced Compact Gyrostabilizer\n"
				+ "Covert Research Tools\n" + "Cynabal Blueprint\n" + "Domination EM Shield Hardener\n"
				+ "Domination Kinetic Armor Hardener\n" + "Domination Tracking Enhancer\n" + "Domination Warp Scrambler\n"
				+ "Dread Guristas Warp Scrambler\n" + "Dual 150mm Carbide Railgun I\n" + "Dual 150mm Compressed Coil Gun I\n"
				+ "Dual 150mm Prototype Gauss Gun\n" + "Dual 180mm Carbine Repeating Cannon I\n" + "Electronic Engineering\n"
				+ "Enduring Kinetic Shield Hardener\n" + "Faint Epsilon Scoped Warp Scrambler\n"
				+ "Fourier Compact Tracking Enhancer\n" + "Gardan's Private Key\n" + "Gistii A-Type Small Shield Booster\n"
				+ "Gistum B-Type Medium Shield Booster\n" + "Gistum B-Type Thermal Shield Amplifier\n"
				+ "Gistum C-Type 10MN Afterburner\n" + "Gistum C-Type 50MN Microwarpdrive\n"
				+ "Gistum C-Type EM Shield Amplifier\n" + "Gistum C-Type Explosive Shield Amplifier\n"
				+ "Gistum C-Type Kinetic Shield Amplifier\n" + "Gistum C-Type Medium Remote Shield Booster\n"
				+ "Gistum C-Type Medium Shield Booster\n" + "Gistum C-Type Thermal Shield Amplifier\n" + "Graviton Physics\n"
				+ "J5 Enduring Warp Disruptor\n" + "J5b Enduring Warp Scrambler\n" + "Large C5-L Compact Shield Booster\n"
				+ "Medium I-a Enduring Armor Repairer\n" + "Medium Rudimentary Concussion Bomb I\n" + "Metal Scraps\n"
				+ "Mid-grade Ascendancy Alpha Blueprint\n" + "Mid-grade Ascendancy Epsilon Blueprint\n"
				+ "Neural Network Analyzer\n" + "Nuclear Physics\n" + "Peace' Large Remote Armor Repairer Blueprint\n"
				+ "Pithum B-Type Multispectrum Shield Hardener\n" + "Pithum C-Type EM Shield Amplifier\n"
				+ "Pithum C-Type Explosive Shield Amplifier\n" + "Pithum C-Type Kinetic Shield Amplifier\n"
				+ "Pithum C-Type Medium Remote Shield Booster\n" + "Pithum C-Type Medium Shield Booster\n"
				+ "Pithum C-Type Multispectrum Shield Hardener\n" + "Pithum C-Type Thermal Shield Amplifier\n"
				+ "Plasma Physics\n" + "Polarized Heavy Assault Missile Launcher Blueprint\n"
				+ "Polarized Heavy Neutron Blaster Blueprint\n" + "Polarized Mega Pulse Laser Blueprint\n"
				+ "R.A.M.- Armor/Hull Tech\n" + "R.A.M.- Electronics\n" + "R.A.M.- Energy Tech\n" + "R.Db - Core Complexion\n"
				+ "Shattered Villard Wheel\n" + "Sleeper Drone AI Nexus\n" + "Small Clarity Ward Enduring Shield Booster\n"
				+ "Stalwart Restrained Shield Boost Amplifier\n" + "Talocan Ignition Device\n" + "Talocan Mechanical Gears\n"
				+ "TE-2100 Ample Light Missile Launcher\n" + "Type-D Restrained Inertial Stabilizers\n"
				+ "Type-D Restrained Nanofiber Structure\n" + "Type-D Restrained Overdrive Injector\n"
				+ "Wild' Miner I Blueprint";
		for (String s : in.split("\n")) {
			EveType t = TypeIndex.getType(s);
			if (t != null) {
				System.out.println(t.name + "\t" + t.id);
			} else {
				System.out.println(s + "\t0");
			}
		}
	}

}
