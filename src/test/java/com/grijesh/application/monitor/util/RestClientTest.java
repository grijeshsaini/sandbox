package com.grijesh.application.monitor.util;

import com.grijesh.application.monitor.model.EnvironmentProperties;
import com.grijesh.application.monitor.service.client.RestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by grijesh.
 */
public class RestClientTest {

    private RestClient restClient;


    @Before
    public void setup() {
        restClient = Mockito.spy(new RestClient());
    }

    @Test
    public void shouldGetEnvProps() throws IOException, URISyntaxException {
        //Given
        String url ="http://localhost:8080/%s/version";
        RestTemplate restTemplate = restClient.getRestTemplate();
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        String body = loadJsonFile();
        mockServer.expect(requestTo("http://localhost:8080/test/version")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));
        willReturn(restTemplate).given(restClient).getRestTemplate();
        restClient.setUrl(url);

        //When
        EnvironmentProperties environmentProperties = restClient.getEnvironmentProperties("test");

        //Then
        Assert.assertEquals("Test expectedName should be","test",environmentProperties.getTest().getExpectedName());

    }

    @Test
    public void shouldGetVersion(){
        //Given
        RestTemplate restTemplate = restClient.getRestTemplate();
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        String body = "1.2.0.SNAPSHOT";
        String expectedUri = "http://localhost:8080/test/version";
        mockServer.expect(requestTo(expectedUri)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(body, MediaType.TEXT_PLAIN));
        willReturn(restTemplate).given(restClient).getRestTemplate();

        //When
        String version = restClient.getVersionFrom(expectedUri);

        //Then
        Assert.assertEquals("Version should be",body,version);

    }

    private String loadJsonFile() throws URISyntaxException, IOException {
        URL url = RestClientTest.class.getResource("/expected/props.json");
        Path path = Paths.get(url.toURI());
        byte[] ba = Files.readAllBytes(path);
        return new String(ba, StandardCharsets.UTF_8);
    }
}