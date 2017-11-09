package security;

/**
 * http://technet.microsoft.com/en-us/library/bb742610.aspx wmic documentation
 *
 * @author hardiku
 */
import java.util.Scanner;

public class GetBiosSerialNumber
{

    public static void main(String[] args) throws Throwable
    {
        // wmic command for diskdrive id: wmic DISKDRIVE GET SerialNumber
        // wmic command for cpu id : wmic cpu get ProcessorId
        //String cmd = "wmic bios get name, serialnumber, version";
        String cmd = "wmic csproduct get name, identifyingnumber, uuid";
        //String cmd = "wmic cpu get name, CurrentClockSpeed, MaxClockSpeed";

        Process process = Runtime.getRuntime().exec(cmd);

        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        while(sc.hasNext())
        {
            System.out.println(sc.next());
        }
    }
}
