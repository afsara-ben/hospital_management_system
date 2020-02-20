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
public class pharmacompany {
    private Integer pharma_id;
    private String pharma_name;
    private Integer pharma_contact;
    
    public pharmacompany(Integer pharma_id, String pharma_name, Integer pharma_contact)
    {
        super();
        this.pharma_id= pharma_id;
        this.pharma_name= pharma_name;
        this.pharma_contact=pharma_contact;
    }
    public Integer getPharma_id()
    {
        return pharma_id;
    }
     public String getPharma_name()
    {
        return pharma_name;
    }
    public Integer getPharma_contact()
    {
        return pharma_contact;
    }
    public void setPharma_id(Integer pharma_id)
    {
        this.pharma_id= pharma_id;
    }
    public void setPharma_name(String pharma_name)
    {
        this.pharma_name= pharma_name;
    }
    public void setPharma_contact(Integer pharma_contact)
    {
        this.pharma_contact= pharma_contact;
    }
}
