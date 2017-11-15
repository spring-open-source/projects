
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * cactoos api demo
 *
 * @author hardiku
 */
public class Encrypted4 implements Encrypted
{

    private final IoCheckedScalar<String> text;

    public Encrypted4(InputStream stream)
    {
        this(() ->
        {
            ByteArrayOutputStream baos
                    = new ByteArrayOutputStream();
            while(true)
            {
                int one = stream.read();
                if(one < 0)
                {
                    break;
                }
                baos.write(one);
            }
            return new String(baos.toByteArray());
        }
        );
    }

    public Encrypted4(String txt)
    {
        this(() -> txt);
    }

    public Encrypted4(Scalar<String> source)
    {
        this.text = new IoCheckedScalar<>(source);
    }

    @Override
    public String asString() throws IOException
    {
        final byte[] in = this.text.value().getBytes();
        final byte[] out = new byte[in.length];
        for(int i = 0; i < in.length; ++i)
        {
            out[i] = (byte) (in[i] + 1);
        }
        return new String(out);
    }
