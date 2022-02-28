package com.cursoandroid.projetochecklistandroid.retrofit.service;


import com.cursoandroid.projetochecklistandroid.model.CheckList;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface CheckListService {

    // http://localhost:8080/checklists

    @GET("checklists/")
    Observable<List<CheckList>> mostraTodosCheckLists();

    @PUT("checklists/{id}")
    Observable<CheckList> verificaCheckList(@Path("id") int id,
                                            @Body CheckList checkList);

    @POST("checklists/")
    Observable<CheckList> cadastraNovoCheckList(@Body CheckList checkList);

    @DELETE("checklists/remove/{id}")
    Observable<CheckList> removeCheckList(@Path("id") int id);

}
