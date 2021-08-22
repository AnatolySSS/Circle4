package com.scorp.circle4;

public class Choice {

    /** field for image of the sharik*/
    private int mImage;

    /** field for price of the sharik*/
    private int mPrice;

    /**
     *
     * @param mImage for image of the sharik
     * @param mPrice for price of the sharik
     */
    public Choice(int mImage,
                    int mPrice) {
        this.mImage = mImage;
        this.mPrice = mPrice;
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
}
