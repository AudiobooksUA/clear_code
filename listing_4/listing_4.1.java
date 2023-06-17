//Utility method that returns when this.closed is true. Trows an exception
// if timeout is reached.
public synchronized void waitForClose(final long timeoutMillis)
throws Exception
{
  if(!closed)
    {
      wait(timeoutMillis);
      if(!closed)
        throws new Exception("MockResponseSender could not be closed");
    }

}
