package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;

public class Room implements Bookable{

    private int roomID;
    private int bedCount;
    private double price;

    /**
     * Follow this format [floorLevel]-[roomNumber] , uniq
     */
    // private String roomName;

    private int floorLevel;
    private int roomNumber;

    @Override
    public String toString() {
        return this.getRoomName();
    }

    public String getRoomName() {
        return String.format("%d-%d", this.getFloorLevel(), this.getRoomNumber());
    }

    @Override
    public Booking book(User theBooker, LocalDate starDate, Duration bookingDuration) {
        return new Booking(this, theBooker, starDate, bookingDuration);
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    
}
