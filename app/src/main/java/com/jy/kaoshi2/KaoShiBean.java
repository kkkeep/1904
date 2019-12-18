package com.jy.kaoshi2;

import java.util.ArrayList;

/*
 * created by Cherry on 2019-12-18
 **/
public class KaoShiBean {


    public int code;
    public String message;

    public DataBean data;






    public class DataBean{

        private int start;

        private ArrayList<Article> article_list;


        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public ArrayList<Article> getArticleList() {
            return article_list;
        }

        public void setArticleList(ArrayList<Article> articleList) {
            this.article_list = articleList;
        }
    }



    public class Article{

        private String theme;

        private String link;

        private int time;

        private boolean isSelect;


        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

}
