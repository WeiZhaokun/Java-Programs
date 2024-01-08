package Booking;

import java.sql.Date;

public class Booking {
    private int bookingID;
    private int customerID;
    private int flightID;
    private int seatID;
    private String status; // Assuming status could be "Booked", "Cancelled", etc.
    private Date bookingDate; // Assuming there is a booking date

    // Constructor
    public Booking(int bookingID, int customerID, int flightID, int seatID, String status, Date bookingDate) {
        this.setBookingID(bookingID);
        this.setCustomerID(customerID);
        this.setFlightID(flightID);
        this.setSeatID(seatID);
        this.setStatus(status);
        this.setBookingDate(bookingDate);
    }

	/**
	 * @return the bookingID
	 */
	public int getBookingID() {
		return bookingID;
	}

	/**
	 * @param bookingID the bookingID to set
	 */
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the flightID
	 */
	public int getFlightID() {
		return flightID;
	}

	/**
	 * @param flightID the flightID to set
	 */
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	/**
	 * @return the seatID
	 */
	public int getSeatID() {
		return seatID;
	}

	/**
	 * @param seatID the seatID to set
	 */
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}

	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

  

   
}
