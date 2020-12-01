package com.bravo.bravobest.binterface;


public interface ESUserService<ESUser> {

    String query();

    String save();

    String update();

    String delete();

    String bulk();

    String queryWithPage();

    String search();
}
