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
public class job {
    private Integer job_id;
    private String job_title;
    
    public job(Integer job_id, String job_title)
    {
        super();
        this.job_id=job_id;
        this.job_title=job_title;
    }
    public Integer getJob_id()
    {
        return job_id;
    }
    public String getJob_title()
    {
        return job_title;
    }
    public void setJob_id(Integer job_id)
    {
        this.job_id=job_id;
    }
    public void setJob_title(String job_title)
    {
        this.job_title=job_title;
    }
}
