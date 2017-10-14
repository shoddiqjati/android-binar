package com.example.shoddiq.binarandoridassessmenttest.network;

import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EndPointApi {
    @GET("stuff.json")
    Observable<HashMap<String, Stuff>> getAllStuff();
}
