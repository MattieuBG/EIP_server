package com.esb.sharedlibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mongodb.morphia.annotations.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audio extends AFile
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
