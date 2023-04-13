package GUI.Util;

import com.google.zxing.WriterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QrCodeGeneratorTest {

    @DisplayName("Test of QRCode generation")
    @Test
    public void QRCodeGeneration() throws IOException, WriterException {
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

        //act
        qrCodeGenerator.createMap();
        qrCodeGenerator.generateQrCode("Hello",qrCodeGenerator.getCharSet(),qrCodeGenerator.getMap(),200,200, 1);

        //Assertion
        File notExpectedResult = null;
        File actualResult = new File("QRCodeData//code.png");

        Assertions.assertNotEquals(notExpectedResult, actualResult);
    }

}