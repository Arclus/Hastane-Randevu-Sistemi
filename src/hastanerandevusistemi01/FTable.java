package hastanerandevusistemi01;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arclus
 */

public class FTable
{
   
    private final SimpleStringProperty tc;
    private final SimpleStringProperty randevuID;
    private final SimpleStringProperty doktorID;
    private final SimpleStringProperty hastane;
    private final SimpleStringProperty klinik;
    private final SimpleStringProperty hekim;
    private final SimpleStringProperty tarih;
    private final SimpleStringProperty saat;

    public FTable(String tc,String doktorID, String ranID, String hast, String klin, String hek, String tar, String sa)
    {
        this.tc = new SimpleStringProperty(tc);
        this.randevuID = new SimpleStringProperty(ranID);
        this.doktorID = new SimpleStringProperty(doktorID);
        this.hastane = new SimpleStringProperty(hast);
        this.klinik = new SimpleStringProperty(klin);
        this.hekim = new SimpleStringProperty(hek);
        this.tarih = new SimpleStringProperty(tar);
        this.saat = new SimpleStringProperty(sa);

    }
    
    public String getTc()
    {
        return tc.get();
    }

    public void setTc(String tc)
    {
        this.tc.set(tc);
    }

    public String getRandevuID()
    {
        return randevuID.get();
    }

    public void setRandevuID(String randevuID)
    {
        this.randevuID.set(randevuID);
    }

    public String getDoktorID()
    {
        return doktorID.get();
    }

    public void setDoktorID(String doktorıd)
    {
        this.doktorID.set(doktorıd);
    }

    public String getHastane()
    {
        return hastane.get();
    }

    public void setHastane(String hastane)
    {
        this.hastane.set(hastane);
    }

    public String getKlinik()
    {
        return klinik.get();
    }

    public void setKlinik(String klinik)
    {
        this.klinik.set(klinik);
    }

    public String getHekim()
    {
        return hekim.get();
    }

    public void setHekim(String hekim)
    {
        this.hekim.set(hekim);
    }

    public String getTarih()
    {
        return tarih.get();
    }

    public void setTARİH(String tarih)
    {
        this.tarih.set(tarih);
    }

    public String getSaat()
    {
        return saat.get();
    }

    public void setSaat(String saat)
    {
        this.saat.set(saat);
    }
}
