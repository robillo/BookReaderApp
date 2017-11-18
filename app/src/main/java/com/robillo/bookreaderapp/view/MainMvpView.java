package com.robillo.bookreaderapp.view;

import com.robillo.bookreaderapp.retrofit.model.Content;

import java.util.List;

/**
 * Created by robinkamboj on 15/11/17.
 */

public interface MainMvpView {

    void setUp();

    void getContent();

    void setFragmentsForContents(List<Content> contents);

    void hideShowCustomizeLayout();

    void increaseTextSize();

    void decreaseTextSize();

    void changeFont();

    void changeTheme();

    void changeLineSpacing();

    void changeContentPadding();

    void refreshFragments();

}
