package com.scorp.circle4;

import com.scorp.circle4.data.CircleContract;

public class Circle {

    /** field for image of the sharik*/
    private String imgName;

    /** field for price of the sharik*/
    private int mPrice;

    /** field for transparent of the sharik*/
    private boolean isBought;

    /** field for image of the sharik*/
    private byte[] mImage;

    /**
     * @param mPrice for price of the sharik
     * @param isBought for being bought or not
     * @param mImage for image of the sharik
     */
    public Circle(String imgName,
                  int mPrice,
                  int isBought,
                  byte[] mImage) {
        this.imgName = imgName;
        this.mPrice = mPrice;
        switch (isBought) {
            case CircleContract.CircleEntry.ISBOUGHT_NO:
                this.isBought = false;
                break;
            case CircleContract.CircleEntry.ISBOUGHT_YES:
                this.isBought = true;
                break;
        }
        this.mImage = mImage;
    }

    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public boolean isBought() {
        return isBought;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
