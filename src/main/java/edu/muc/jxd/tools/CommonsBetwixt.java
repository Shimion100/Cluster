package edu.muc.jxd.tools;

import edu.muc.jxd.item.ImageItemXml;
import edu.muc.jxd.item.ImageItemXmlElement;
import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.beans.IntrospectionException;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by gwd on 9/17/2016.
 */
public class CommonsBetwixt {
    private static Logger logger=Logger.getLogger(CommonsBetwixt.class.getName());

    /**
     * To save a object to file a xml stander file
     * @param t
     * @param objecTname
     * @param filename
     * @param <T>
     * @return
     */
    public <T> boolean persistObjectToFile(T t,String objecTname,String filename){

        FileOutputStream out= null;
        try {
            out = new FileOutputStream(new File(PathKit.getRootClassPath()+"/"+filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("File not found!"+PathKit.getRootClassPath()+"/"+filename);
            return  false;
        }

        BeanWriter writer = new BeanWriter(out);
        writer.setEndTagForEmptyElement(true);

        try {
            if(objecTname==null)
                objecTname=filename;
            writer.write(objecTname,t);
            writer.flush();
            writer.close();
            logger.debug("a object has to save to a file named "+filename);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Apache Common Betwixt Error.");
            return  false;
        } catch (SAXException e) {
            e.printStackTrace();
            logger.error("Apache Common Betwixt Error.");
            return  false;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            logger.error("Apache Common Betwixt Error.");
            return  false;
        }
        return true;
    }

    public <T> boolean persistObjectToFile(T t){

        String filename="betwixt.xml";
        FileOutputStream out= null;

        try {
            out = new FileOutputStream(new File(PathKit.getRootClassPath()+"/"+filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("File not found!"+PathKit.getRootClassPath()+"/"+filename);
            return  false;
        }

        BeanWriter writer = new BeanWriter(out);
        writer.setEndTagForEmptyElement(true);

        try {

            writer.write(BeanWriter.class.getName(),t);
            writer.flush();
            writer.close();
            logger.debug("a object has to save to a file named "+filename);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Apache Common Betwixt Error.");
            return  false;
        } catch (SAXException e) {
            e.printStackTrace();
            logger.error("Apache Common Betwixt Error.");
            return  false;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            logger.error("Apache Common Betwixt Error.");
            return  false;
        }

        return true;
    }

    /**
     * a method to save a object to string to print
     * @param t
     * @param objectName
     * @param <T>
     * @return
     */
    public <T> String persistObjectToString(T t,String objectName){

        StringWriter stringWriter=new StringWriter();
        BeanWriter beanWriter=new BeanWriter(stringWriter);
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(false);
        beanWriter.enablePrettyPrint();

        // If the base element is not passed in, Betwixt will guess
        // But let's write example bean as base element 'person'
        try {

            beanWriter.write(objectName,t);
            beanWriter.flush();
            beanWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }


        // Write to System.out
        // (We could have used the empty constructor for BeanWriter
        // but this way is more instructive)
        logger.debug(stringWriter.toString());

        // Betwixt writes fragments not documents so does not automatically close
        // writers or streams.
        // This example will do no more writing so close the writer now.
        return stringWriter.toString();
    }

}
