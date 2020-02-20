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
public class appointment {
    private Integer apt_id;
    private String apt_date;
    private String apt_time;
    private Integer p_id;
    private Integer doctor_id;
    
    public appointment(Integer apt_id, String apt_date, String apt_time, Integer p_id, Integer doctor_id )
    {
        super();
        this.apt_id=apt_id;
        this.apt_date=apt_date;
        this.apt_time=apt_time;
        this.p_id=p_id;
        this.doctor_id=doctor_id;
    }
    
    public Integer getApt_id()
    {
        return apt_id;
    }
    public String getApt_date()
    {
        return apt_date;
    }
    public String getApt_time()
    {
        return apt_time;
    }
    public Integer getP_id()
    {
        return p_id;
    }
    public Integer getDoctor_id()
    {
        return doctor_id;
    }
     public void setApt_id(Integer apt_id)
    {
        this.apt_id=apt_id;
    }
    public void setApt_date(String apt_date)
    {
         this.apt_date=apt_date;
    }
    public void setApt_time(String apt_time)
    {
        this.apt_time=apt_time;
    }
    public void setP_id(Integer p_id)
    {
        this.p_id= p_id;
    }
    public void getDoctor_id(Integer doctor_id)
    {
        this.doctor_id= doctor_id;
    }
}
