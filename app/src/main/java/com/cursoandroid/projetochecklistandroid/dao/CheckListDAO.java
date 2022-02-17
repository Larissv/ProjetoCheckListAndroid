package com.cursoandroid.projetochecklistandroid.dao;

import com.cursoandroid.projetochecklistandroid.model.CheckList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckListDAO {

    private final static ArrayList<CheckList> checkLists = new ArrayList<>();

    public List<CheckList> getTodosCheckLists() {
        return (List<CheckList>) checkLists.clone();
    }

    public void cadastraFormularioCheckList(CheckList... checkLists) {
        CheckListDAO.checkLists.addAll(Arrays.asList(checkLists));
    }
}
