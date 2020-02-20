/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

/**
 *
 * @author user
 */
public class admission {
    private Integer ad_id;
    private String ad_date;
    private String ad_time;
    private String date_discharge;
    private Integer seat_id;
    private Integer p_id;
    private Integer doctor_id;
    
    public admission(Integer ad_id, String ad_date, String ad_time, String date_discharge,Integer seat_id, Integer p_id, Integer doctor_id )
    {
        super();
        this.ad_id=ad_id;
        this.ad_date=ad_date;
        this.ad_time=ad_time;
        this.date_discharge=date_discharge;
        this.seat_id=seat_id;
        this.p_id=p_id;
        this.doctor_id=doctor_id;
    }
    
    public Integer getAd_id()
    {
        return ad_id;
    }
    public String getAd_date()
    {
        return ad_date;
    }
    public String getAd_time()
    {
        return ad_time;
    }
    public String getDate_discharge()
    {
        return date_discharge;
    }
    public Integer getSeat_id()
    {
        return seat_id;
    }
    public Integer getP_id()
    {
        return p_id;
    }
    public Integer getDoctor_id()
    {
        return doctor_id;
    }
     public void setAd_id(Integer ad_id)
    {
        this.ad_id=ad_id;
    }
    public void setAd_date(String ad_date)
    {
         this.ad_date=ad_date;
    }
    public void setAd_time(String ad_time)
    {
        this.ad_time=ad_time;
    }
    public void setDate_discharge(String date_discharge)
    {
        this.date_discharge= date_discharge;
    }
    public void setSeat_id(Integer seat_id)
    {
        this.seat_id= seat_id;
    }
    public void setP_id(Integer p_id)
    {
        this.p_id= p_id;
    }
    public void setDoctor_id(Integer doctor_id)
    {
        this.doctor_id= doctor_id;
    }
}
