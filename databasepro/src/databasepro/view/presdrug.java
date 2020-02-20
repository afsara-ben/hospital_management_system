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
public class presdrug {
    private Integer pres_id;
    private Integer drug_id;
    private String time_of_intake;
    
    public presdrug(Integer pres_id, Integer drug_id, String time_of_intake)
    {
        super();
        this.pres_id=pres_id;
        this.drug_id=drug_id;
        this.time_of_intake=time_of_intake;
    }
    
    public Integer getPres_id()
    {
        return pres_id;
    }
    public Integer getDrug_id()
    {
        return drug_id;
    }
    public String getTime_of_intake()
    {
        return time_of_intake;
    }
    public void setPres_id(Integer pres_id)
    {
        this.pres_id=pres_id;
    }
     public void setDrug_id(Integer drug_id)
    {
        this.drug_id=drug_id;
    }
      public void setTime_of_intake(String time_of_intake)
    {
        this.time_of_intake=time_of_intake;
    }
}
