package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class InvalidTypeChar {

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("target/invalidChars.txt"));
		int nb = 0;
		for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
			var t = e.getValue();
			Set<String> invalidNameLanguages = new HashSet<>();
			for (Entry<String, String> e2 : t.name.entrySet()) {
				if (e2.getValue().contains("�")) {
					invalidNameLanguages.add(e2.getKey());
				}
			}
			Set<String> invalidDescLanguages = new HashSet<>();
			for (Entry<String, String> e2 : t.description.entrySet()) {
				if (e2.getValue().contains("�")) {
					invalidDescLanguages.add(e2.getKey());
				}
			}
			if (!invalidNameLanguages.isEmpty() || !invalidDescLanguages.isEmpty()) {
				nb++;
				bw.append(e.getKey() + (invalidNameLanguages.isEmpty() ? ""
						: " names="
						+ invalidNameLanguages)
						+ (invalidDescLanguages.isEmpty() ? "" : " descriptions=" + invalidDescLanguages));
				bw.newLine();
			}
		}
		bw.close();
		System.out.println("done with "+nb+" error types");
	}

}
