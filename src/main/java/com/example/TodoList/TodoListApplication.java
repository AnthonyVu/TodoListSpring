package com.example.TodoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication {

	/*
	todo
	when user checks otp
	filter checks header for otp and user
	if otp is there, find user and set enabled to true
	end chain
	create end point that sends code / get_code
		user must provide their username, password not required
		find username from userdetailsservice and send text to user
		if user is enabled, skip entire process
		otherwise
		must add phone number to appuser model
		and repository for codes
			id and code should both be unique
			time_created and time_to_remove
			remove code after some 15 minutes?
		add code to temporory database
	create filter to check for username and code / check_code
		if user is enabled, skip entire process
		otherwise
		check if code is contained in database
		if it is, remove it from the database and enable user
		if not, throw an error
	 */
	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}
