package com.framework.test.circulation;

public class Wife {

    private Husband husband;

    private IMother mother;

    public String queryHusband(){
        return "wife is querying "+mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
