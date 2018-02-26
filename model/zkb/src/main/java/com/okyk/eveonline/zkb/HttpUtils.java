package com.okyk.eveonline.zkb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * connect to an url and retrieve the result.
     *
     * @param url
     *          the url to fetch
     * @param method
     *          the method to connect. must be POST or GET
     * @param properties
     *          the properties to transmit in the header
     * @param transmit
     *          the data to transmit during the query
     * @return the line returned by the server as a response. null if there was an
     *         issue
     */
    public static String connect(String url, String method, Map<String, String> properties, String transmit) {
        try {
            URL target = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) target.openConnection();
            con.setRequestMethod(method);
            if (properties != null) {
                for (Map.Entry<String, String> e : properties.entrySet()) {
                    con.setRequestProperty(e.getKey(), e.getValue());
                }
            }
            con.setDoOutput(true);
            if (transmit != null) {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(transmit.getBytes(StandardCharsets.UTF_8));
                wr.flush();
                wr.close();
            }
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                StringBuilder sb = new StringBuilder(method).append(url).append(" ").append(responseCode);
                if (responseCode != 500) {
                    sb.append(" error : ");
                    new BufferedReader(new InputStreamReader(con.getErrorStream())).lines().forEach(sb::append);
                }
                System.err.println(sb.toString());
                return null;
            } else {
                return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            }
        } catch (Exception e) {
            logger.debug("while getting " + url, e);
            return null;
        }
    }

    /**
     * get an url, using your authorization
     *
     * @param url
     *          the url to fetch
     * @return the line returned by the server as a response. null if there was an
     *         issue
     */
    public static String connectGet(String url, boolean connected) {
        return connect(url, "GET", Collections.emptyMap(), null);
    }

    /**
     * post an url, using your authorization
     *
     * @param url
     *          the url to fetch
     * @param contentType
     *          the type of the data transmitted
     * @param transmit
     *          the data to transmit during the query
     * @return the line returned by the server as a response. null if there was an
     *         issue
     */
    public static String connectPost(String url, String contentType, String transmit, boolean connected) {
        HashMap<String, String> props = new HashMap<>();
        props.put("Content-Type", contentType);
        return connect(url, "POST", props, transmit);
    }

    ObjectWriter ow = new ObjectMapper().writer();

    public String connectPost(String url, Map<String, String> transmit, boolean connected) {
        try {
            return connectPost(url, "application/json", ow.writeValueAsString(transmit), connected);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException("catch this", e);
        }
    }

}
