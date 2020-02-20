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
public class test {
    private Integer test_id;
    private String test_name;
    private Integer test_cost;
    public test(Integer test_id, String test_name, Integer test_cost)
    {
        super();
        this.test_id= test_id;
        this.test_name= test_name;
        this.test_cost= test_cost;
    }
    public Integer getTest_id()
    {
        return test_id;
    }
    public String getTest_name()
    {
        return test_name;
    }
    public Integer getTest_cost()
    {
        return test_cost;
    }
    public void setTest_id(Integer test_id)
    {
        this.test_id=test_id;
    }
    public void setTest_name(String test_name)
    {
        this.test_name=test_name;
    }
    public void setTest_cost(Integer test_cost)
    {
        this.test_cost=test_cost;
    }
}
