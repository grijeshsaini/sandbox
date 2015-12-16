package com.grijesh.application.monitor.urlgenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.List;

/**
 * Created by grijesh.
 */
public class UrlGeneratorTest {

    private UrlGenerator urlGenerator;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ClassPathResource classPathResource;

    @Mock
    private BufferedReader bufferedReader;

    @Mock
    private FileInputStream inputStream;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        urlGenerator = new UrlGenerator();
        urlGenerator.setResourceLoader(resourceLoader);
    }

    @Test
    public void shouldReturnTheListOfApplicationNames() throws IOException {
        //Given
        //I have urlGenerator instance
        BDDMockito.willReturn(classPathResource).given(resourceLoader).getResource(org.mockito.Matchers.anyString());
        BDDMockito.willReturn(inputStream).given(classPathResource).getInputStream();
        Mockito.when(new BufferedReader(new InputStreamReader(Matchers.any()))).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn("first line").thenReturn("second line");

        //When
        List<String> applications = urlGenerator.getApplicationNames();

        //Then
        Assert.assertTrue("Application should be greater than 0", (0 < applications.size()));
    }

}