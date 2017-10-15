import java.io.*;
import java.util.*;

public class JarekBase
{
  public static final int BUF_SIZE = 1000;

  public static void usage()
  {
    System.out.println("uzycie: JarekBase.jar e/d plikWe");
  }

  public static void main(String args[])
  throws Exception
  {
    JarekBase b = new JarekBase();
    if (args.length < 2) {
      usage();
      System.exit(1);
    }
    System.out.println("start");
    if (args[0].equals("e")) {
      String plikWy = args[1].replaceFirst("\\.[^.]+$", ".log");
      System.out.println("plik wy: " + plikWy);
      if (plikWy.equals(args[1]))
        plikWy += "_enc";
      b.encode(args[1], plikWy);
    } else if (args[0].equals("d")) {
      b.decode(args[1]);
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
    ostr.write("1.1\r\n".getBytes());
    File f = new File(plikWe);
    String name = f.getName();
    ostr.write(Base64.getEncoder().encode((name).getBytes()));
    ostr.write("\r\n".getBytes());
    ostr.flush();
    OutputStream ostre = Base64.getMimeEncoder().wrap(ostr);
    int cAll = 0;
    byte[] buf = new byte[BUF_SIZE];
    while (true) {
      int cRead = istr.read(buf);
      if (cRead < 0)
        break;
      cAll += cRead;
      ostre.write(buf, 0, cRead);
    }
    ostre.close();
    System.out.println("bytes: " + cAll);
  }

  public void decode(String plikWe)
  throws Exception
  {
    InputStream istr = new FileInputStream(plikWe);
    System.out.println("wersja: " + readLine(istr));
    String plikWyNazwa = new String(Base64.getDecoder().decode(readLine(istr)));
    File plikWy = new File(new File(plikWe).getParent(), plikWyNazwa);
    System.out.println("plik wy: " + plikWy);
    if (plikWe.equals(plikWy))
      throw new IllegalArgumentException();

    OutputStream ostr = new FileOutputStream(plikWy);
    InputStream istrd = Base64.getMimeDecoder().wrap(istr);
    byte[] buf = new byte[BUF_SIZE];
    int cAll = 0;
    while (true) {
      int cRead = istrd.read(buf);
      if (cRead < 0)
        break;
      cAll += cRead;
      ostr.write(buf, 0, cRead);
    }
    ostr.close();
    System.out.println("bytes: " + cAll);
  }

  private String readLine(InputStream istr) throws IOException {
    StringBuffer sb = new StringBuffer();
    while (true) {
      int ch = istr.read();
      if (ch < 0 || ch == '\n')
        break;
      if (ch != '\r')
        sb.append((char)ch);
    }
    return sb.toString();
  }
}
