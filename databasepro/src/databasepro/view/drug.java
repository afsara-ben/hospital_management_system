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
public class drug {
    private Integer drug_id;
    private String drug_name;
    private String generic_name;
    private String mft_date;
    private String exp_date;
    private String state;
    private String method_of_intake;
    private Integer max_brought;
    private String pharma_name;
    private Integer drug_cost;
    
    public drug(Integer drug_id, String drug_name, String generic_name, String mft_date, String exp_date, String state, String method_of_intake, Integer max_brought, String pharma_name, Integer drug_cost)
    {
        super();
        this.drug_id=drug_id;
        this.drug_name=drug_name;
        this.generic_name=generic_name;
        this.mft_date=mft_date;
        this.exp_date=exp_date;
        this.state=state;
        this.method_of_intake=method_of_intake;
        this.pharma_name=pharma_name;
        this.max_brought=max_brought;
        this.drug_cost=drug_cost;
    }
    public Integer getDrug_id()
    {
        return drug_id;
    }
    public String getDrug_name()
    {
        return drug_name;
    }
    public String getGeneric_name()
    {
        return generic_name;
    }
    public String getMft_date()
    {
        return mft_date;
    }
    public String getExp_date()
    {
        return exp_date;
    }
    public String getState()
    {
        return state;
    }
    public String getMethod_of_intake()
    {
        return method_of_intake;
    }
    public String getPharma_name()
    {
        return pharma_name;
    }
    public Integer getMax_brought()
    {
        return max_brought;
    }
    public Integer getDrug_cost()
    {
        return drug_cost;
    }
    public void setDrug_id(Integer drug_id)
    {
        this.drug_id= drug_id;
    }
    public void setDrug_name(String drug_name)
    {
        this.drug_name= drug_name;
    }
    public void setGeneric_name(String generic_name)
    {
        this.generic_name= generic_name;
    }
    public void setMft_date(String mft_date)
    {
        this.mft_date= mft_date;
    }
    public void setExp_date(String exp_date)
    {
        this.exp_date= exp_date;
    }
    public void setState(String state)
    {
        this.state= state;
    }
    public void setMethod_of_intake(String method_of_intake)
    {
        this.method_of_intake= method_of_intake;
    }
    public void setPharma_name(String pharma_name)
    {
        this.pharma_name= pharma_name;
    }
    public void setMax_brought(Integer max_brought)
    {
        this.max_brought= max_brought;
    }
    public void setDrug_cost(Integer drug_cost)
    {
        this.drug_cost= drug_cost;
    }
}
