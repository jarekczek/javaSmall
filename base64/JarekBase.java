import java.io.*;
import java.util.*;

public class JarekBase
{

  public static void usage()
  {
    System.out.println("uzycie: JarekBase.jar e/d plikWe plikWy");
  }

  public static void main(String args[])
  throws Exception
  {
    JarekBase b = new JarekBase();
    if (args.length < 3) {
      usage();
      System.exit(1);
    }
    if (args[0].equals("e")) {
      b.encode(args[1], args[2]);
    } else if (args[0].equals("d")) {
      b.decode(args[1], args[2]);
    } else {
      usage();
      System.exit(1);
    }
  }

  public void encode(String plikWe, String plikWy)
  throws Exception
  {
    InputStream istr = new FileInputStream(plikWe);
    OutputStream ostr = new FileOutputStream(plikWy);
    OutputStream ostre = Base64.getMimeEncoder().wrap(ostr);
    int bajt, c = 0;
    while (true) {
      bajt = istr.read();
      if (bajt < 0)
        break;
      c++;
      ostre.write(bajt);
    }
    ostre.close();
    System.out.println("bytes: " + c);
  }

  public void decode(String plikWe, String plikWy)
  throws Exception
  {
    InputStream istr = new FileInputStream(plikWe);
    OutputStream ostr = new FileOutputStream(plikWy);
    InputStream istrd = Base64.getMimeDecoder().wrap(istr);
    int bajt, c = 0;
    while (true) {
      bajt = istrd.read();
      if (bajt < 0)
        break;
      c++;
      ostr.write(bajt);
    }
    ostr.close();
    System.out.println("bytes: " + c);
  }
}
