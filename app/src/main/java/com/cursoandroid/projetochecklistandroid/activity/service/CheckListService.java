package com.cursoandroid.projetochecklistandroid.activity.service;


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

    @GET("checklists/")
    Observable<List<CheckList>> mostraTodosCheckLists();

    @PUT("checklists/{id}")
    Observable<CheckList> atualizaCheckList(@Path("id") int id,
                                            @Body CheckList checkList);

    @POST("checklists/")
    Observable<String> cadastraNovoCheckList(@Body String checkList);

    @DELETE("checklists/remove/{id}")
    Observable<Void> removeCheckList(@Path(value = "id") Integer id);

}
