package com.esb.sharedlibrary.entities;


import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer extends AQuestionAndAnswer
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	/*
	###############################
	#           Getter            #
	###############################
	*/
	/*
	###############################
	#         Setter              #
	###############################
	*/
	/*
	###############################
	#         Other Methods       #
	###############################
	*/
}