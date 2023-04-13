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

    private String filePath;

    private String charSet = "UTF-8";

    private List<File> QRCodes = new ArrayList<>();

    /**
     * creates a qrcode in a image file
     * @param data
     * @param path
     * @param charSet
     * @param map
     * @param h
     * @param w
     * @throws WriterException
     * @throws IOException
     */
    public void generateQrCode(String data, String charSet, Map map, int h, int w, int amount) throws WriterException, IOException
    {
        for(int i = 1; i <=amount; i++)
        {
        filePath = "QRCodeData//code" + number + ".png";
        File file = new File(filePath);
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charSet), charSet), BarcodeFormat.QR_CODE,w,h);
        MatrixToImageWriter.writeToFile(matrix,filePath.substring(filePath.indexOf(".")+1),file);
        QRCodes.add(file);
        number++;
        }
    }


    /**
     * creates the error correction map for the qrcodes
     */
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
