
package com.jrondina.jamesrondina.cardcounter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("svg")
    @Expose
    private String svg;
    @SerializedName("png")
    @Expose
    private String png;

    /**
     * 
     * @return
     *     The svg
     */
    public String getSvg() {
        return svg;
    }

    /**
     * 
     * @param svg
     *     The svg
     */
    public void setSvg(String svg) {
        this.svg = svg;
    }

    /**
     * 
     * @return
     *     The png
     */
    public String getPng() {
        return png;
    }

    /**
     * 
     * @param png
     *     The png
     */
    public void setPng(String png) {
        this.png = png;
    }

}
