package test;

import java.io.PrintWriter;
import java.io.StringWriter;
/** Simple Debug stuff for writing debug output.
    This has debug output constants hardcoded to enable "dead code removing" for
    the final version without any logging. See at.workforce.util.test.Debug.
    @author Peter Kofler
*/
public final class D extends Object {

  /** Do logging. For production this must be false. */
  public static final boolean bug = false;

  /** Write the given object name and text to the output.
      @param obj the object to print before the call.
      @param text the text to log. */
  public static final void log(Object obj, String text) {
     String name = obj.getClass().getName();
     name = name.substring(name.lastIndexOf('.')+1,name.length());
     write(name+": "+text);
  }

  /** Write the given exception text to the output.
      @param e the exception to log. */
  public static final void log(Throwable e) {
    log(null,e);
  }

  /** Write the given exception text to the output.
      @param text the text to log.
      @param e the exception to log. */
  public static final void log(String text, Throwable e) {
     StringWriter w = new StringWriter();
     e.printStackTrace(new PrintWriter(w));
     if (text==null) log(w.toString());
     else log(text+'\n'+w.toString());
  }

  /** Write the given text to the output.
      @param text the text to log. */
  public static final void log(String text) {
     write(text);
  }

 // ----------------------------------------------------------------------------
 // ---                      private                                         ---
 // ----------------------------------------------------------------------------

   /** Log Thread infos. */
  private static final boolean LOG_THREAD = false,
  /** Log time infos. */
                               LOG_TIME = false;

  /** The time we started this one. */
  private static final long START_TIME = (LOG_TIME)?System.currentTimeMillis():0;

  /** Log the thread and thread-group. */
  private static final String threadName() {
    Thread t;
    return (t=Thread.currentThread()).getName()+'('+t.getThreadGroup().getName()+')';
  }

  /** Log the time. */
  private static final String timeName() {
    return Long.toString(System.currentTimeMillis()-START_TIME);
  }

  /** Write the string to standard out. This is the basic logging method.
      This is the <b>only</b> method which writes to std-out.
      @param text the text to log. */
  private static final void write(String text) {
    if (LOG_TIME && LOG_THREAD) {
      System.out.println(timeName()+' '+threadName()+':'+text);
    } else if (LOG_TIME) {
      System.out.println(timeName()+':'+text);
    } else if (LOG_THREAD) {
      System.out.println(threadName()+':'+text);
    } else {
      System.out.println(text);
    }
  }

}

