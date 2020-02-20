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
public class prestest {
    private Integer pres_id;
    private Integer test_id;
    
    public prestest(Integer pres_id, Integer test_id)
    {
        super();
        this.pres_id=pres_id;
        this.test_id=test_id;
    }
    
    public Integer getPres_id()
    {
        return pres_id;
    }
    public Integer getTest_id()
    {
        return test_id;
    }
    public void setPres_id(Integer pres_id)
    {
        this.pres_id=pres_id;
    }
     public void setTest_id(Integer test_id)
    {
        this.test_id=test_id;
    }
}
