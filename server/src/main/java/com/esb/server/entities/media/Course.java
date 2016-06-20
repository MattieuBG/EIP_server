package com.esb.server.entities.media;

/**
 * Created by alex on 18/06/16.
 * Project name : Eschoolbag
 */
public class Course extends AFile {
    /*
	###############################
	#         Attributes          #
	###############################
	*/
    private String html;
	/*
	###############################
	#           Getter            #
	###############################
	*/
    public String getHtml() {
        return html;
    }
	/*
	###############################
	#         Setter              #
	###############################
	*/
    public void setHtml(String html) {
        this.html = html;
    }
	/*
	###############################
	#         Other Methods       #
	###############################
	*/
    public String toString()
    {
        String attributes;

        attributes = "[id : "+this.getId()+
                ", name : "+this.getName()+
                ", html : "+this.getHtml()+
                ", description : "+this.getDescription()+
                ", create : "+this.getCreationDate()+
                ", lastUpdate : "+this.getModifiedDate()+
                ", deleted : "+this.getDeletedDate()+
                "]";
        return attributes;
    }
}
