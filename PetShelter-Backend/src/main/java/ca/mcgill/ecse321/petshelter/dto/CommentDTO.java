package ca.mcgill.ecse321.petshelter.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;

public class CommentDTO {
	
	@NotNull(message = "Text cannot be empty.")
	@NotEmpty(message = "Text cannot be empty.")
	private String text;
	
	@NotNull(message = "Username cannot be empty.")
	@NotEmpty(message = "Username cannot be empty.")
	private String username;
	
	private Date datePosted;
	
	private Time time;
	
	public CommentDTO() {
	}
	
	public CommentDTO(@NotNull @NotEmpty String username,@NotNull @NotEmpty String text, Date datePosted, Time time) {
		this.text = text;
		this.username = username;
		this.datePosted = datePosted;
		this.time = time;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the datePosted
	 */
	public Date getDatePosted() {
		return datePosted;
	}

	/**
	 * @param datePosted the datePosted to set
	 */
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}
}