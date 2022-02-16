package com.cursoandroid.projetochecklistandroid.retrofit;


import com.cursoandroid.projetochecklistandroid.model.CheckList;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface CheckListService {

    // http://localhost:8080/checklists

    @GET("checklists/")
    Observable<List<CheckList>> mostraTodosCheckLists();

    @POST("checklists/")
    Observable<CheckList> cadastraNovoCheckList(@Body CheckList checkList);
}
