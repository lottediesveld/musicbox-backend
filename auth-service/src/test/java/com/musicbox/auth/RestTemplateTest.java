package com.musicbox.auth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {
    @Before
    public void setUp(){
    }

    @Test
    public void testRestTemplateCallTest() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        // test one of our endpoints
        final String baseUrl =  "http://35.246.102.210/";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class );

        //Verify request is ok
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
