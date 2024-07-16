package com.example.firebaselogin;

public class User {
    private String detailOne, detailTwo, detailThree;

    public User(String detailOne, String detailTwo, String detailThree) {
        this.detailOne = detailOne;
        this.detailTwo = detailTwo;
        this.detailThree = detailThree;
    }

    public String getDetailOne() {
        return detailOne;
    }

    public void setDetailOne(String detailOne) {
        this.detailOne = detailOne;
    }

    public String getDetailTwo() {
        return detailTwo;
    }

    public void setDetailTwo(String detailTwo) {
        this.detailTwo = detailTwo;
    }

    public String getDetailThree() {
        return detailThree;
    }

    public void setDetailThree(String detailThree) {
        this.detailThree = detailThree;
    }
}
