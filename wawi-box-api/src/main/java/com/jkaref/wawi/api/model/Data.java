package com.jkaref.wawi.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @SerializedName("Herstellernummer")
    private String herstellernummer;
    @SerializedName("_ID")
    private String id;
    @SerializedName("_Herstellername")
    private String herstellername;
    @SerializedName("Title")
    private String title;
    @SerializedName("_PreisAb_formated")
    private String preisAb_formated;
    @SerializedName("_PreisBis_formated")
    private String preisBis_formated;

    private List<Angebote> angebotes = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHerstellername() {
        return herstellername;
    }

    public void setHerstellername(String herstellername) {
        this.herstellername = herstellername;
    }

    public String getHerstellernummer() {
        return herstellernummer;
    }

    public void setHerstellernummer(String herstellernummer) {
        this.herstellernummer = herstellernummer;
    }

    public String getPreisAb_formated() {
        return preisAb_formated;
    }

    public void setPreisAb_formated(String preisAb_formated) {
        this.preisAb_formated = preisAb_formated;
    }

    public String getPreisBis_foemated() {
        return preisBis_formated;
    }

    public void setPreisBis_foemated(String preisBis_formated) {
        this.preisBis_formated = preisBis_formated;
    }

    public List<Angebote> getAngebotes() {
        return angebotes;
    }

    public void setAngebotes(List<Angebote> angebotes) {
        this.angebotes = angebotes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;

        Data data = (Data) o;

        if (getId() != null ? !getId().equals(data.getId()) : data.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(data.getTitle()) : data.getTitle() != null) return false;
        if (getHerstellername() != null ? !getHerstellername().equals(data.getHerstellername()) : data.getHerstellername() != null)
            return false;
        if (getHerstellernummer() != null ? !getHerstellernummer().equals(data.getHerstellernummer()) : data.getHerstellernummer() != null)
            return false;
        if (getPreisAb_formated() != null ? !getPreisAb_formated().equals(data.getPreisAb_formated()) : data.getPreisAb_formated() != null)
            return false;
        if (getPreisBis_foemated() != null ? !getPreisBis_foemated().equals(data.getPreisBis_foemated()) : data.getPreisBis_foemated() != null)
            return false;
        return getAngebotes() != null ? getAngebotes().equals(data.getAngebotes()) : data.getAngebotes() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getHerstellername() != null ? getHerstellername().hashCode() : 0);
        result = 31 * result + (getHerstellernummer() != null ? getHerstellernummer().hashCode() : 0);
        result = 31 * result + (getPreisAb_formated() != null ? getPreisAb_formated().hashCode() : 0);
        result = 31 * result + (getPreisBis_foemated() != null ? getPreisBis_foemated().hashCode() : 0);
        result = 31 * result + (getAngebotes() != null ? getAngebotes().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Data\n" +
                "id: " + id + '\n' +
                ", title='" + title + '\n' +
                ", herstellername: " + herstellername + '\n' +
                ", herstellernummer: " + herstellernummer + '\n' +
                ", preisAb_formated: " + preisAb_formated + '\n' +
                ", preisBis_foemated: " + preisBis_formated + '\n' +
                ", angebotes: " + angebotes + '\n';
    }
}
