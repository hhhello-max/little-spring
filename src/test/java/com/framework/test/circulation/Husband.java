package com.framework.test.circulation;

public class Husband {

    private Wife wife;

    public String queryWife(){
        return "Husband.Wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
