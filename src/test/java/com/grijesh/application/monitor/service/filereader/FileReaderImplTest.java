package com.grijesh.application.monitor.service.filereader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by grijesh.
 */
public class FileReaderImplTest {

    private FileReader fileReader;

    @Before
    public void setup(){
        fileReader = new FileReaderImpl();
    }

    @Test
    public void shouldReturnMap(){
        Map<String,String> map = fileReader.getPropertiesOf("testing");
        map.forEach((key,value) -> System.out.println(key+"-"+value));
        Assert.assertEquals("Key should be","test",map.keySet().iterator().next());
        Assert.assertEquals("Value should be","http://localhost:8080/test",map.get("test"));
    }

}