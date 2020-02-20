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
public class doctor {
    private  String em_firstname;
    private  String em_lastname;
    private  String em_desig;
    private  String em_dob;
    private  String joining_date;
    private  Integer salary;
    private  String em_add;
    private  Integer em_contact;
  //  private  String em_email;
    private  Integer job_id;
    private  Integer dept_id;
    private  String qualification;
    private  Integer em_id;
 /*   private  Integer employee_id;
    private  Integer d_status;
    private  Integer d_experience;*/
  
    public doctor(Integer em_id,String em_firstname,String em_lastname,String em_desig,String em_dob,String joining_date,Integer salary,String em_add,Integer em_contact,Integer job_id,Integer dept_id,String qualification)
    {
        super();
        this.em_id=em_id;
        this.em_firstname=em_firstname;
        this.em_lastname=em_lastname;
        this.em_desig=em_desig;
        this.em_dob=em_dob;
        this.joining_date=joining_date;
        this.salary=salary;
        this.em_add=em_add;
        this.em_contact=em_contact;
       // this.em_email=em_email;
        this.job_id=job_id;
        this.dept_id=dept_id;
        this.qualification=qualification;
     /*   this.employee_id=employee_id;
        this.d_status= d_status;
        this.d_experience= d_experience;*/
    }
    public Integer getEm_id()
    {
        return em_id;
    }
    public String getEm_firstname()
    {
        return em_firstname;
    }
    public String getEm_lastname()
    {
        return em_lastname;
    }
    public String getEm_desig()
    {
        return em_desig;
    }
    public String getEm_dob()
    {
        return em_dob;
    }
    public String getJoining_date()
    {
        return joining_date;
    }
    public Integer getSalary()
    {
        return salary;
    }
    public String getEm_add()
    {
        return em_add;
    }
    public Integer getEm_contact()
    {
        return em_contact;
    }
    public Integer getJob_id()
    {
        return job_id;
    }
    public Integer getDept_id()
    {
        return dept_id;
    }
    public String getQualification()
    {
        return qualification;
    }
 /*   public Integer getEmployee_id()
    {
        return employee_id;
    }
    public Integer getD_status()
    {
        return d_status;
    }
    public Integer getD_experience()
    {
        return d_experience;
    }*/
    public void setEm_id(Integer em_id)
    {
        this.em_id=em_id;
    }
    public void setEm_firstname(String em_firstname)
    {
        this.em_firstname=em_firstname;
    }
     public void setEm_lastname(String em_lastname)
    {
        this.em_lastname=em_lastname;
    }
      public void setEm_designame(String em_desig)
    {
        this.em_desig=em_desig;
    }
     public void setEm_dob(String em_dob)
    {
        this.em_dob=em_dob;
    }
     public void setJoining_date(String joining_date)
    {
        this.joining_date=joining_date;
    }
     public void setSalary(Integer salary)
    {
        this.salary=salary;
    }
     public void setEm_add(String em_add)
    {
        this.em_add=em_add;
    }
     public void setEm_contact(Integer em_contact)
    {
        this.em_contact=em_contact;
    }
     public void setJob_id(Integer job_id)
    {
        this.job_id=job_id;
    }
     public void setDept_id(Integer dept_id)
    {
        this.dept_id=dept_id;
    }
     public void setQualification(String qualification)
    {
        this.qualification=qualification;
    }
  /*    public void setEmployee_id(Integer employee_id)
    {
        this.employee_id=employee_id;
    }
      public void setD_status(Integer d_status)
    {
        this.d_status=d_status;
    }
       public void setD_experience(Integer d_experience)
    {
        this.d_experience=d_experience;
    }*/
}
