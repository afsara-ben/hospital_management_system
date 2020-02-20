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
public class department {
    private Integer dept_id;
    private String dept_title;
    private Integer total_patient;
    
    public department(Integer dept_id, String dept_title,Integer total_patient)
    {
        super();
        this.dept_id=dept_id;
        this.dept_title=dept_title;
        this.total_patient=total_patient;
    }
    
    public Integer getDept_id()
    {
        return dept_id;
    }
    
    public String getDept_title()
    {
        return dept_title;
    }
    
    public Integer getTotal_patient()
    {
        return total_patient;
    }
    
    public void setDept_id(Integer dept_id)
    {
        this.dept_id=dept_id;
    }
    
    public void setDept_title(String dept_title)
    {
        this.dept_title=dept_title;
    }
    
    public void setTotal_patient(Integer total_patient)
    {
        this.total_patient=total_patient;
    }
}
