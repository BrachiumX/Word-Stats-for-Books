/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.brachium.bookwordstatistics;

import java.io.File;

/**
 *
 * @author burak
 */
public class BookWordStatistics {

    public static void main(String[] args) {
        
        if(args[0].equals("textfile")){
            TextFile.run(new File(args[1]),new File(args[2]));
        }

        if(args[0].equals("sql")){
            Sql.run(new File (args[1]),args[2],args[3],args[4],args[5]);
        }
    }
}
