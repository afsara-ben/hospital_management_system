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
public class payment {
    private Integer pay_id;
    private Integer cost_drug;
    private Integer cost_test;
    private Integer cost_seat;
    private String drug_status;
    private String test_status;
    private String seat_status;
    private Integer p_id;
    private Integer accountant_id;
    private Integer total;
    
    public payment(Integer pay_id, Integer cost_drug, Integer cost_test, Integer cost_seat, String drug_status, String test_status, String seat_status, Integer p_id, Integer accountant_id, Integer total)
    {
        super();
    this.pay_id= pay_id;
    this.cost_drug= cost_drug;
    this.cost_test= cost_test;
    this.cost_seat= cost_seat;
    this.drug_status= drug_status;
    this.test_status= test_status;
    this.seat_status= seat_status;
    this.p_id= p_id;
    this.accountant_id= accountant_id;
    this.total=total;
    }
    public Integer getPay_id()
    {
        return pay_id;
    }
    public Integer getCost_drug()
    {
        return cost_drug;
    }
    public Integer getCost_test()
    {
        return cost_test;
    }
    public Integer getCost_seat()
    {
        return cost_seat;
    }
    public String getDrug_status()
    {
        return drug_status;
    }
    public String getTest_status()
    {
        return test_status;
    }
    public String getSeat_status()
    {
        return seat_status;
    }
    public Integer getP_id()
    {
        return p_id;
    }
    public Integer getAccountant_id()
    {
        return accountant_id;
    }
    public Integer getTotal()
    {
        return total;
    }
    public void setPay_id(Integer pay_id)
    {
        this.pay_id= pay_id;
    }
    public void setCost_drug(Integer cost_drug)
    {
        this.cost_drug= cost_drug;
    }
    public void setCost_test(Integer cost_test)
    {
        this.cost_test= cost_test;
    }
    public void setCost_seat(Integer cost_seat)
    {
        this.cost_seat= cost_seat;
    }
    public void setDrug_status(String drug_status)
    {
        this.drug_status= drug_status;
    }
    public void setTest_status(String test_status)
    {
        this.test_status= test_status;
    }
    public void setSeat_status(String seat_status)
    {
        this.seat_status= seat_status;
    }
    public void setP_id(Integer p_id)
    {
        this.p_id= p_id;
    }
    public void setAccountant_id(Integer accountant_id)
    {
        this.accountant_id= accountant_id;
    }
    public void setTotal(Integer total)
    {
        this.total= total;
    }
}
