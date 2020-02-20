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
public class employee {

    private Integer em_id;
    private String em_firstname;
    private String em_lastname;
    private String occupation;

    public employee(Integer em_id, String em_firstname, String em_lastname, String occupation) {
        super();
        this.em_id = em_id;
        this.em_firstname = em_firstname;
        this.em_lastname = em_lastname;
        this.occupation = occupation;
    }

    public Integer getEm_id() {
        return em_id;
    }

    public String getEm_firstname() {
        return em_firstname;
    }

    public String getEm_lastname() {
        return em_lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setEm_id(Integer em_id) {
        this.em_id = em_id;
    }

    public void setEm_firstname(String em_firstname) {
        this.em_firstname = em_firstname;
    }

    public void setEm_lastname(String em_lastname) {
        this.em_lastname = em_lastname;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
