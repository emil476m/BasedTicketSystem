package GUI.Util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeGenerator {

    private Map<EncodeHintType,ErrorCorrectionLevel> map;

    private String path = "QRCodeData//code.png";

    private String charSet = "UTF-8";

    public void generateQrCode(String data, String path, String charSet, Map map, int h, int w) throws WriterException, IOException
    {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charSet), charSet), BarcodeFormat.QR_CODE,w,h);
        MatrixToImageWriter.writeToFile(matrix,path.substring(path.indexOf(".")+1),new File(path));
    }


    public void createMap()
    {
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
        map = hashMap;
    }

    public Map<EncodeHintType, ErrorCorrectionLevel> getMap()
    {
        return map;
    }

    public String getPath() {
        return path;
    }

    public String getCharSet() {
        return charSet;
    }
}
