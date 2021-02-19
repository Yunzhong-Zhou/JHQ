package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/1/28.
 */
public class Fragment1Model_P implements Serializable {

    public Fragment1Model_P(String img1, String img2, String img3) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    /**
     * img1 :
     * img2 :
     * img3 :
     */

    private String img1;
    private String img2;
    private String img3;

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    @Override
    public String toString() {
        return "Fragment1Model_P{" +
                "img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                '}';
    }
}
