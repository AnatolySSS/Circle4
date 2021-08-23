package com.scorp.circle4;

public class Circle {

    /** field for image of the sharik*/
    private int mImage;

    /** field for price of the sharik*/
    private int mPrice;

    /** field for transparent of the sharik*/
    private boolean isBought;

    /**
     * @param mImage for image of the sharik
     * @param mPrice for price of the sharik
     * @param isBought for being bought or not
     */
    public Circle(int mImage,
                  int mPrice,
                  boolean isBought) {
        this.mImage = mImage;
        this.mPrice = mPrice;
        this.isBought = isBought;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
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
}
