package com.grijesh.application.monitor.service.filereader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

/**
 * Created by grijesh.
 */
public class FileReaderImplTest {

    private FileReaderImpl fileReader;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private ClassPathResource classPathResource;

    private InputStream inputStream;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        byte[] data = "test=http://localhost:8080/test".getBytes();
        inputStream = new ByteArrayInputStream(data);
        fileReader = new FileReaderImpl();
        fileReader.setResourceLoader(resourceLoader);
    }

    @Test
    public void shouldReturnMap() throws IOException {
        //Given
        willReturn(classPathResource).given(resourceLoader).getResource(anyString());
        willReturn(inputStream).given(classPathResource).getInputStream();

        Map<String, String> map = fileReader.getPropertiesOf("testing");
        map.forEach((key, value) -> System.out.println(key + "-" + value));
        Assert.assertEquals("Key should be", "test", map.keySet().iterator().next());
        Assert.assertEquals("Value should be", "http://localhost:8080/test", map.get("test"));
    }

}