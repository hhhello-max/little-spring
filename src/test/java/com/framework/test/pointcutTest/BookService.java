package com.framework.test.pointcutTest;

public class BookService implements IBookService{

    private String overview;

    @Override
    public void bookmark(String word) {
        System.out.println("我是一本书,书名是："+word+" 简介："+overview);
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
