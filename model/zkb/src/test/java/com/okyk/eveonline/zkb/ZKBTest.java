package com.okyk.eveonline.zkb;

import com.okyk.eveonline.zkb.dto.KBObject;
import com.okyk.eveonline.zkb.dto.KBStats;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ZKBTest {

    @Test
    public void parseZKBResponseTest() throws URISyntaxException, IOException {
            final String json = Files.readAllLines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("zkb_km_response.json").toURI())).stream().collect(Collectors.joining());
            final KBObject[] characterKB = ZKB.parseZKBResponse(json);
            Assert.assertEquals(characterKB.length, 200);
    }

    @Test
    public void testLoadStats() throws Exception {
        final String json = Files.readAllLines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("zkb_stat_response.json").toURI())).stream().collect(Collectors.joining());
        final KBStats kbStats = ZKB.parseStatResponse(json);
        Assert.assertEquals(kbStats.info.name, "Torin Fellborn");
    }

    @Test
    public void testNetworkLoadStats() throws Exception {
        final KBStats kbStats = ZKB.loadStats(93288602L);
        Assert.assertEquals(kbStats.info.name, "Torin Fellborn");
    }



}