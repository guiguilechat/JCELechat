package fr.guiguilechat.eveonline.database.apiv2;

import java.util.Date;

import fr.guiguilechat.eveonline.database.apiv2.Account.Character;
import fr.guiguilechat.eveonline.database.apiv2.Char.JobEntry;

public class CheckActivities {

	public static void main(String[] args) {
		Date now = new Date();
		for (String api : args) {
			APIRoot r = new APIRoot(api);
			for (Character c : r.account.characters()) {
				System.out.println("" + c.name);
				for (JobEntry e : r.chars.industryJobs(c.characterID)) {
					if (now.after(e.endDate)) {
						System.out.println("\t" + e.blueprintTypeName);
					}
				}
			}
		}
	}

}
