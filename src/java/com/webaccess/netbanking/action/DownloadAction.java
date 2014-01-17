/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

/**
 *
 * @author Administrator
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
 
public class DownloadAction extends ActionSupport{
 
	private InputStream fileInputStream;
 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	public String execute() throws Exception {
	    fileInputStream = new FileInputStream(new File("C:\\FirstPdf.pdf"));
            System.out.print("hi jay");
	    return SUCCESS;
	}
}