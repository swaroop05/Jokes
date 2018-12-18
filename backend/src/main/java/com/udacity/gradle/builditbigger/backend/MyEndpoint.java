package com.udacity.gradle.builditbigger.backend;

import com.example.android.javajokeslib.JavaJokesSrc;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJokesFromJavaModule")
    public MyBean getJokesFromJavaModule() {
        MyBean response = new MyBean();
        JavaJokesSrc javaJokesSrc = new JavaJokesSrc();
        String joke = javaJokesSrc.getGoodJoke();
        response.setData(joke);

        return response;
    }



}
