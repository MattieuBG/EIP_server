package com.esb.server.entities.media;

import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;

/**
 * Created by alex on 18/06/16. Project name : Eschoolbag
 */
public class Course extends AFile {
	/*
	 * ###############################
	 * #          Attributes         #
	 * ###############################
	 */
	private String html;
	/*
	 * ###############################
	 * #           Getters           #
	 * ###############################
	 */
	public String getHtml() {
		return html;
	}
	/*
	 * ###############################
	 * #          Setters            #
	 * ###############################
	 */
	public void setHtml(final String html) {
		this.html = html;
	}
	/*
	 * ###############################
	 * #       Others Methods        #
	 * ###############################
	 */

    @Override
    public String toString() {
        return "Course{}";
    }
}
