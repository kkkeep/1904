package com.jy.data.entity;

import java.util.ArrayList;

/*
 * created by Cherry on 2019-11-22
 **/
public class ArticleData {

    private int curPage;
    private int offset;
    private int pageCount;
    private int size;
    private int total;

    private ArrayList<Article> datas;



    public static class Article{

        private int id;

        private String title;


    }




}

