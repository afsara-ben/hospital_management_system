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
public class seat {
    private Integer seat_id;
    private Integer seat_cost;
    private Integer room_id;
    public seat(Integer seat_id, Integer seat_cost, Integer room_id)
    {
        super();
        this.seat_id= seat_id;
        this.seat_cost= seat_cost;
        this.room_id= room_id;
    }
    public Integer getSeat_id()
    {
        return seat_id;
    }
    public Integer getSeat_cost()
    {
        return seat_cost;
    }
    public Integer getRoom_id()
    {
        return room_id;
    }
    public void setSeat_id(Integer seat_id)
    {
        this.seat_id=seat_id;
    }
    public void setSeat_cost(Integer seat_cost)
    {
        this.seat_cost=seat_cost;
    }
    public void setRoom_id(Integer room_id)
    {
        this.room_id=room_id;
    }
}
