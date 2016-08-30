package com.esb.server.entities.media;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Image extends AFile {

	public String ftpUrl;

}
