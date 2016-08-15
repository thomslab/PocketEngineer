package com.thomslab.proengineer;

/**
 * Created by mitohida on 8/15/2016.
 */

public class MenuRowItem {

    private String List_Title;
    private int Image_Title;

    public MenuRowItem(int Image_Title, String List_Title) {

        this.Image_Title = Image_Title;
        this.List_Title = List_Title;
    }

    public int getImage_Title(){

        return Image_Title;
    }

    public String getList_Title(){
        return List_Title;
    }

    public void setImage_Title(int Image_Title){
        this.Image_Title = Image_Title;
    }

    public void setList_Title(String List_Title){
        this.List_Title = List_Title;
    }
}
