package com.jkaref.wawi.api.model;

import com.google.gson.annotations.SerializedName;

public class Angebote {

    @SerializedName("Preis_einzeln_formatted")
    private String preis_einzel_formatted;
    @SerializedName("Lieferant_Name")
    private String anbieter;


    public String getPreis_einzel_formatted() {
        return preis_einzel_formatted;
    }

    public void setPreis_einzel_formatted(String preis_einzel_formatted) {
        this.preis_einzel_formatted = preis_einzel_formatted;
    }

    public String getAnbieter() {
        return anbieter;
    }

    public Angebote setAnbieter(String anbieter) {
        this.anbieter = anbieter;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angebote)) return false;

        Angebote angebote = (Angebote) o;

        return getPreis_einzel_formatted() != null ? getPreis_einzel_formatted().equals(angebote.getPreis_einzel_formatted()) : angebote.getPreis_einzel_formatted() == null;
    }

    @Override
    public int hashCode() {
        int result = getAnbieter() != null ? getAnbieter().hashCode() : 0;
        result = 31 * result + (getPreis_einzel_formatted() != null ? getPreis_einzel_formatted().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Angebote:\n" +
                "preis_einzel_formatted: " + preis_einzel_formatted + '\n'+
                "anbieter: " + anbieter + '\n';
    }
}
