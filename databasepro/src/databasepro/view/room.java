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
public class room {
    private Integer room_id;
    private Integer room_cap;
    private Integer floor;
    private Integer dept_id;
    
    public room(Integer room_id, Integer room_cap, Integer floor, Integer dept_id)
    {
        super();
        this.room_id= room_id;
        this.room_cap= room_cap;
        this.floor= floor;
        this.dept_id= dept_id;
    }
    public Integer getRoom_id()
    {
        return room_id;
    }
    public Integer getRoom_cap()
    {
        return room_cap;
    }
    public Integer getFloor()
    {
        return floor;
    }
    public Integer getDept_id()
    {
        return dept_id;
    }
    public void setRoom_id(Integer room_id)
    {
        this.room_id= room_id;
    }
    public void setRoom_cap(Integer room_cap)
    {
        this.room_cap= room_cap;
    }
    public void setFloor(Integer floor)
    {
        this.floor= floor;
    }
    public void setDept_id(Integer dept_id)
    {
        this.dept_id= dept_id;
    }
}
