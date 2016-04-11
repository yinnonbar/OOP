package oop.ex6.filescript.order;

import java.util.Comparator;
import java.io.File;
/**
 * Order is an interface for all the orders that are in use in this project.
 * Giving the template for all of the possible used orders.
 * This interface extends the java's comparator and will determine between
 * two files (as possible values are  1 if the first file bigger, 0 if equal 
 * and -1 else.
 * @author yinnonbar
 *
 */
public interface Order extends Comparator<File>{

}
