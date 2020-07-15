package com.tlm.faelecEntities.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class Utils {
	
	public static StreamedContent convertBytesToStreamedContentImg(byte[] bts, String formato)
	{
    	if(bts != null){
    		InputStream is = new ByteArrayInputStream(bts);
    	    StreamedContent image = new DefaultStreamedContent (is, "image/"+formato,Math.random()+".png");
    	    return image; 
    	}else{
        	return null;
    	}		
	}    

}
