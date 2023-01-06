package com.example.pruebagit.model;

public class Club
{
    private int id;
    private String name;

    public Club(int id, String club)
    {
        this.id = id;
        this.name = club;
    }

    public Club() { }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String club)
    {
        this.name = club;
    }

    @Override
    public String toString() {
        return "Club [id=" + id + ", name=" + name + "]";
    }
}
