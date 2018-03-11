package hastanerandevusistemi01;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Arclus
 */

public class DBIslemleri
{
    Connection cn;
    static String time;
    static String SCI;
    static String kID;
    static String SHI;
    static String hT;
    static String sDID;
    static final ObservableList hS = FXCollections.observableArrayList();
    static final ObservableList cOptions = FXCollections.observableArrayList();
    static final ObservableList dOptions = FXCollections.observableArrayList();
    static final ObservableList hOptions = FXCollections.observableArrayList();
    static final ObservableList kOptions = FXCollections.observableArrayList();
    static final ObservableList dOOptions = FXCollections.observableArrayList();
    
    
    public Statement baglan()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrandevu", "username", "password");
            
            return cn.createStatement();
        }
        
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, "Not Connected");
            return null;
        }
    }
    
    public static String sorgulaR(String tcNo)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        rSorgula.data2.clear();
        
        try
        {
            String sql= "SELECT doktor.DoktorID, randevu.RandevuID, randevu.TCNo, doktor.DoktorAdi, newhastane.HastaneAdi, klinik.KlinikAdi,klinik.KlinikID, randevu.RandevuTarihi, randevu.RandevuSaati FROM randevu INNER JOIN doktor on doktor.DoktorID = randevu.DoktorID INNER JOIN klinik on klinik.KlinikID = doktor.KlinikID INNER JOIN newhastane on newhastane.HastaneID = klinik.HastaneID WHERE TCNo = '" + tcNo + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {            
                while (rss.next())
                {
                    rSorgula.data2.add(new FTable(
                        rss.getString("TCNo"),
                        rss.getString("DoktorID"),
                        rss.getString("RandevuID"),
                        rss.getString("HastaneAdi"),
                        rss.getString("KlinikAdi"),
                        rss.getString("DoktorAdi"),
                        rss.getString("RandevuTarihi"),
                        rss.getString("RandevuSaati")
                    ));
                }

                rSorgula.table2.setItems(rSorgula.data2);
                
                connection.close();
                rss.close();
            }
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static String updateR(String rID, String nRD, String nRS)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "UPDATE randevu SET RandevuTarihi = '" + nRD + "', RandevuSaati = '" + nRS +"' WHERE RandevuID = '" + rID + "'";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String addNewR(String dID, String rDate, String rH)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "INSERT INTO hizmet(DoktorID,HizmetTarih,HizmetSaat) VALUES(" + " '" + dID + "', " + " '" + rDate + "', " + " '" + rH + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static void deleteRandevu(String rID)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "DELETE FROM randevu WHERE RandevuID = '" + rID + "'";
            
            connection.executeUpdate(sql);
            connection.close();
        }
        catch (SQLException | HeadlessException a)
        {
           System.out.println(a);
        }
    }
    
    public static String getR()
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        rEdit.data.clear();
        
        try
        {
            String sql= "SELECT doktor.DoktorID, randevu.RandevuID, randevu.TCNo, doktor.DoktorAdi, newhastane.HastaneAdi, klinik.KlinikAdi,klinik.KlinikID, randevu.RandevuTarihi, randevu.RandevuSaati FROM randevu INNER JOIN doktor on doktor.DoktorID = randevu.DoktorID INNER JOIN klinik on klinik.KlinikID = doktor.KlinikID INNER JOIN newhastane on newhastane.HastaneID = klinik.HastaneID WHERE TCNo = '" + HastaneRandevuSistemi01.tcNo + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {            
                
                while (rss.next())
                {
                    rEdit.data.add(new FTable(
                        rss.getString("TCNo"),
                        rss.getString("DoktorID"),
                        rss.getString("RandevuID"),
                        rss.getString("HastaneAdi"),
                        rss.getString("KlinikAdi"),
                        rss.getString("DoktorAdi"),
                        rss.getString("RandevuTarihi"),
                        rss.getString("RandevuSaati")
                    ));
                }

                rEdit.table.setItems(rEdit.data);
                
                connection.close();
                rss.close();
            }
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static void deleteH(String hT, String hS)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "DELETE FROM hizmet WHERE HizmetSaat = '" + hS + "' and HizmetTarih = '" + hT + "'";
            
            connection.executeUpdate(sql);
            connection.close();
        }
        catch (SQLException | HeadlessException a)
        {
           System.out.println(a);
        }
    }
    
    public static void sDoctorID(String selectedDoctorName)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT DoktorID FROM doktor WHERE DoktorAdi = '" + selectedDoctorName + "'";
            
            try (ResultSet rss = connection.executeQuery(sql)) 
            {
                while(rss.next())
                {
                    sDID = rss.getString("DoktorID");
                }
                
                connection.close();
                rss.close();
            }
        }
        catch (SQLException | HeadlessException a)
        {
            
        }
    }
        
    public static String addR(String tcNo, String dID, String rDate, String rH)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "INSERT INTO randevu(TCNo,DoktorID,RandevuTarihi,RandevuSaati) VALUES(" + " '" + tcNo + "', " + " '" + dID + "', " + " '" + rDate + "', " + " '" + rH + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getB(String date, String dName)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        hS.clear();
        
        try
        {
            String sql= "SELECT HizmetSaat FROM doktor INNER JOIN hizmet on doktor.DoktorID = hizmet.DoktorID WHERE DoktorAdi = '" + dName + "' and HizmetTarih = '" + date + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {
                    hS.add(rss.getString("HizmetSaat"));     
                }
                
                connection.close();
                rss.close();
            }
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static void sHospitalID(String selectedHospitalName)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
               
        try
        {
            String sql= "SELECT HastaneID FROM newhastane WHERE HastaneAdi = '" + selectedHospitalName + "'";
            
            try (ResultSet rss = connection.executeQuery(sql)) 
            {
                while(rss.next())
                {
                    SHI = rss.getString("HastaneID");
                }
                
                connection.close();
                rss.close();
            }
        }
        catch (SQLException | HeadlessException a)
        {
            
        }
    }
    
    public static String getDoctor(String selectedKID)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();

        try
        {
            String sql= "SELECT DoktorAdi FROM doktor WHERE KlinikID = '" + selectedKID + "'";
            ResultSet rss = connection.executeQuery(sql);
            
            dOOptions.clear();
            
            while(rss.next())
            {
                dOOptions.add(rss.getString("DoktorAdi"));
            }
            connection.close();
            rss.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static void getKID(String kAdi, String hID)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT KlinikID FROM klinik WHERE KlinikAdi = '" + kAdi + "' and HastaneID = '" + hID + "'";
            
            try (ResultSet rss = connection.executeQuery(sql)) 
            {
                while(rss.next())
                {
                    kID = rss.getString("KlinikID");
                }
                
                connection.close();
                rss.close();
            }
        }
        catch (SQLException | HeadlessException a)
        {
            
        }
    }
    
    public static String getK()
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT KlinikAdi FROM klinik WHERE HastaneID = '" + SHI + "'";
            
            
            try (ResultSet rss = connection.executeQuery(sql))
            {
                kOptions.clear();
                
                while(rss.next())
                {
                    kOptions.add(rss.getString("KlinikAdi"));

                }
                
                connection.close();
                rss.close();
            }
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static void sCityIndex(String selectedCityName)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT * FROM iller WHERE SehirAdi = '" + selectedCityName + "'";
            
            try (ResultSet rss = connection.executeQuery(sql)) 
            {
                while(rss.next())
                {
                    SCI = rss.getString("SehirID");
                }
                
                connection.close();
                rss.close();
            }
        }
        catch (SQLException | HeadlessException a)
        {
            
        }
    }

    public static String getHospital(String selectedCityName, String selectedDistrictName)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT HastaneAdi FROM newhastane WHERE SehirAdi = '" + selectedCityName + "' and IlceAdi = '" + selectedDistrictName + "'";

            try (ResultSet rss = connection.executeQuery(sql))
            {
                hOptions.clear();
                
                while(rss.next())
                {
                    hOptions.add(rss.getString("HastaneAdi"));
                }
                
                connection.close();
            }
            
             return "1";    
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static String getDistrict()
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();

        try
        {
            String sql= "SELECT IlceAdi FROM ilceler WHERE SehirID = '" + SCI + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {
                dOptions.clear();
                
                while(rss.next())
                {
                    dOptions.add(rss.getString("IlceAdi"));        
                }
            }
            
            connection.close();
            //rss.close();
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static String getCity()
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT SehirAdi FROM iller";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {
                cOptions.clear();
                
                while(rss.next())
                {
                    cOptions.add(rss.getString("SehirAdi"));
                }
                
                connection.close();
                rss.close();
            }
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return a.toString();
        }
    }
    
    public static String addRegister(String tcNo, String name, String sName, String pW, String tNo, String bL, String bD, String eMail, String sT, String sN, String gender)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "INSERT INTO kullanici(TCNo,KullaniciAdi,KullaniciSoyadi,KullaniciSifre,KullaniciTelNo,KullaniciMail,KullaniciDYeri,KullaniciDTarihi,KullaniciSigorta,KullaniciSNo,KullaniciCinsiyet) VALUES(" + " '" + tcNo + "', " + " '" + name + "', " + " '" + sName + "', " + " '" + pW + "'," + " '" + tNo + "'," + " '" + bL + "'," + " '" + bD + "'," + " '" + eMail + "'," + " '" + sT + "'," + " '" + sN + "'," + " '" + gender + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
   
    public static String loginCheck(String tcNo, String pW)
    {
        DBIslemleri vt = new DBIslemleri();
        Statement connection = vt.baglan();
        
        try
        {
            String sql= "SELECT TCNo,KullaniciSifre FROM kullanici WHERE TCNo = '" + tcNo + "' && KullaniciSifre = '" + pW + "'";
            ResultSet rss = connection.executeQuery(sql);
            
            if (rss.next())
            {
                rss.close();
                connection.close();
                
                return "1";
            }

            else
            {
                rss.close();
                connection.close();
                
                return "0";
            }
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
}