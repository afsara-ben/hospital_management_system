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
public class prescription {
    private Integer pres_id;
    private String pres_date;
    private String pres_comment;
    private Integer p_id;
    private Integer doctor_id;
    
    public prescription(Integer pres_id, String pres_date, String pres_comment, Integer p_id, Integer doctor_id)
    {
        super();
        this.pres_id=pres_id;
        this.pres_date=pres_date;
        this.pres_comment=pres_comment;
        this.p_id=p_id;
        this.doctor_id=doctor_id;
    }
    public Integer getPres_id()
    {
        return pres_id;
    }
    public String getPres_date()
    {
        return pres_date;
    }
    public String getPres_comment()
    {
        return pres_comment;
    }
    public Integer getP_id()
    {
        return p_id;
    }
    public Integer getDoctor_id()
    {
        return doctor_id;
    }
    public void setPres_id(Integer pres_id)
    {
        this.pres_id=pres_id;
    }
    public void setPres_date(String pres_date)
    {
        this.pres_date=pres_date;
    }
    public void setPres_comment(String pres_comment)
    {
        this.pres_comment=pres_comment;
    }
    public void setP_id(Integer p_id)
    {
        this.p_id=p_id;
    }
    public void setDoctor_id(Integer doctor_id)
    {
        this.doctor_id=doctor_id;
    }
}
