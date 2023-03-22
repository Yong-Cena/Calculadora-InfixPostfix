/**
 *  Represent wrong situations:
 *     - a Null Pointer,
 *     - an Empty Collection.
 */

package Stacks;

public class ADTsException  extends RuntimeException {
    /** Sets up this exception with an appropriate message.
     */
    public ADTsException (String collection) {
        super ("> > > ADTsException:  " + collection);
  }
}
