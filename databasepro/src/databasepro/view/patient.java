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
public class patient {
    private Integer p_id;
    private String p_firstname;
    private String p_lastname;
    private String p_gender;
    private String p_dob;
    private String p_bloodgrp;
    private String p_add;
    private Integer p_contact;
    private String p_email;
    
    public patient(Integer p_id,String p_firstname,String p_lastname,String p_gender,String p_dob,String p_bloodgrp,String p_add,Integer p_contact,String p_email)
    {
        super();
        this.p_id=p_id;
        this.p_firstname=p_firstname;
        this.p_lastname=p_lastname;
        this.p_gender=p_gender;
        this.p_dob=p_dob;
        this.p_bloodgrp=p_bloodgrp;
        this.p_add=p_add;
        this.p_contact=p_contact;
        this.p_email=p_email;
    }
    public Integer getP_id()
    {
        return p_id;
    }
    public String getP_firstname()
    {
        return p_firstname;
    }
    public String getP_lastname()
    {
        return p_lastname;
    }
    public String getP_gender()
    {
        return p_gender;
    }
    public String getP_dob()
    {
        return p_dob;
    }
    public String getP_bloodgrp()
    {
        return p_bloodgrp;
    }
    public String getP_add()
    {
        return p_add;
    }
    public Integer getP_contact()
    {
        return p_contact;
    }
    public String getP_email()
    {
        return p_email;
    }
    public void setP_id(Integer p_id)
    {
        this.p_id=p_id;
    }
    public void setP_firstname(String p_firstname)
    {
        this.p_firstname=p_firstname;
    }
    public void setP_lastname(String p_lastname)
    {
        this.p_lastname=p_lastname;
    }
    public void setP_gender(String p_gender)
    {
        this.p_gender=p_gender;
    }
    public void setP_dob(String p_dob)
    {
        this.p_dob=p_dob;
    }
    public void setP_bloodgrp(String p_bloodgrp)
    {
        this.p_bloodgrp=p_bloodgrp;
    }
    public void setP_add(String p_add)
    {
        this.p_add=p_add;
    }
    public void setP_contact(Integer p_contact)
    {
        this.p_contact=p_contact;
    }
    public void setP_email(String p_email)
    {
        this.p_email=p_email;
    }
}
