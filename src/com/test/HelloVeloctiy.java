package com.test;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
public class HelloVeloctiy {  
    public HelloVeloctiy(String templateFile) {  
        try {  
  
        	Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        	Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());	
            Velocity.init();  
  
            /* 
             * Make a context object and populate with the data. This is where 
             * the Velocity engine gets the data to resolve the references (ex. 
             * $list) in the template 
             */  
  
            VelocityContext context = new VelocityContext();  
            context.put("list", getNames());  
  
            /* 
             * get the Template object. This is the parsed version of your 
             * template input file. Note that getTemplate() can throw 
             * ResourceNotFoundException : if it doesn't find the template 
             * ParseErrorException : if there is something wrong with the VTL 
             * Exception : if something else goes wrong (this is generally 
             * indicative of as serious problem...) 
             */  
  
            Template template = null;  
  
            try {  
                template = Velocity.getTemplate(templateFile);  
            } catch (ResourceNotFoundException rnfe) {  
                System.out.println("Example : error : cannot find template "  
                        + templateFile);  
            } catch (ParseErrorException pee) {  
                System.out.println("Example : Syntax error in template "  
                        + templateFile + ":" + pee);  
            }  
  
            /* 
             * Now have the template engine process your template using the data 
             * placed into the context. Think of it as a 'merge' of the template 
             * and the data to produce the output stream. 
             */  
  
            BufferedWriter writer = writer = new BufferedWriter(  
                    new OutputStreamWriter(System.out));  
  
            if (template != null)  
                template.merge(context, writer);  
  
            /* 
             * flush and cleanup 
             */  
  
            writer.flush();  
            writer.close();  
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
     public ArrayList getNames()  
        {  
            ArrayList list = new ArrayList();  
  
            list.add("HelloVelocity 1");  
            list.add("HelloVelocity 2");  
            list.add("HelloVelocity 3");  
            list.add("HelloVelocity 4");  
  
            return list;  
        }  
     public static void main(String[] args)  
        {  
         HelloVeloctiy t = new HelloVeloctiy("example.vm");  
        }  
}  