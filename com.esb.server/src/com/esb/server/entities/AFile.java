package com.esb.server.entities;

import java.util.Date;

public abstract class AFile {
	protected Long id;
	protected String name;
	protected String path;
	protected String description;
	protected Date creationdate;
	protected Date lastmodified;
}
