package com.andrewYurkevich.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Андрей on 13.06.2017.
 */


@Entity
@Table(name="car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="NAME")
    @NotNull
    @Size(min=2, max=30)
    private String name;

    @Column(name="SPEED")
    @Max(value = 500)
    private int speed;

    @Column(name="VOLUME")
    private double volume;

    @Column(name="TYPE")
    @Size(max=30)
    private String type;



    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (speed != car.speed) return false;
        if (Double.compare(car.volume, volume) != 0) return false;
        if (name != null ? !name.equals(car.name) : car.name != null) return false;
        return type != null ? type.equals(car.type) : car.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + speed;
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                ", volume=" + volume +
                ", type='" + type + '\'' +
                '}';
    }
}
