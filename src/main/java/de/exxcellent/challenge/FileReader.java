package de.exxcellent.challenge;

import tech.tablesaw.api.Table;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
Interface to make file import modular. There is the possibility to implement another class to
handle JSON files that implements this Interface.
 */
public interface FileReader  {
    Table readFile() throws FileNotFoundException, IOException;
}
