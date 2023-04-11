package GUI.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private int number = 0;

    private String filePath = "QRCodeData//code" + number + ".png";

    private String charSet = "UTF-8";

    private List<File> QRCodes = new ArrayList<>();

    public void generateQrCode(String data, String path, String charSet, Map map, int h, int w) throws WriterException, IOException
    {
        File file = new File(filePath);
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charSet), charSet), BarcodeFormat.QR_CODE,w,h);
        MatrixToImageWriter.writeToFile(matrix,path.substring(path.indexOf(".")+1),file);
        QRCodes.add(file);
        number++;
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

    public List<File> getQRCodes() {
        return QRCodes;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public String getCharSet() {
        return charSet;
    }
}
