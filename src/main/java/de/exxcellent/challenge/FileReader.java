package de.exxcellent.challenge;

import tech.tablesaw.api.Table;
import java.io.IOException;
import java.net.URL;

/**
 Interface to make file import modular. There is the possibility to implement another class to
 handle JSON files that implements this Interface.

 The Tablesaw package can import different files (JSON, Excel, HTML, RDBMS with JDBC). Therefore future changes
 in import formats can be handled easily by adding new classes that implement this interface. The advantage of using
 tablesaw is that the standard return of a Table object.
 */
public interface FileReader  {
    /**
     * @param filePath URL to the file that should be read
     * @return Returns a Table Object for further processing
     * @throws IOException Throws Excpetion if file cannot be read or accessed
     */
    Table readFile(URL filePath) throws IOException;
}
