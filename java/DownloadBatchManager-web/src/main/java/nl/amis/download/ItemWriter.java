/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amis.download;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.naming.InitialContext;

@Named("FileItemWriter")
public class ItemWriter     extends AbstractItemWriter {    

    @Override
    public void open(Serializable checkpoint) throws Exception {
        System.out.println("open item writing stage");
    }

    public void writeItems(List list) throws Exception {
        System.out.println("Write "+list.size()+" file items");
        for (Object obj : list) {
            System.out.println("Write file item "+obj);
        }
    }

}
