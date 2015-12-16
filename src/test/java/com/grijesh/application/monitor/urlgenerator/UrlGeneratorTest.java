package com.grijesh.application.monitor.urlgenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by grijesh.
 */
public class UrlGeneratorTest {

    private UrlGenerator urlGenerator;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ClassPathResource classPathResource;

    private InputStream inputStream;

    @Before
    public void setup() {
        initMocks(this);
        byte[] data = "app1".getBytes();
        inputStream = new ByteArrayInputStream(data);
        urlGenerator = new UrlGenerator();
        urlGenerator.setResourceLoader(resourceLoader);
    }

    @Test
    public void shouldReturnTheListOfApplicationNames() throws IOException {
        //Given
        //I have urlGenerator instance
        willReturn(classPathResource).given(resourceLoader).getResource(anyString());
        willReturn(inputStream).given(classPathResource).getInputStream();

        //When
        List<String> applications = urlGenerator.getApplicationNames();

        //Then
        assertTrue("Application should be greater than 0", (0 < applications.size()));
        assertEquals("First app should be","app1",applications.get(0));

    }

}