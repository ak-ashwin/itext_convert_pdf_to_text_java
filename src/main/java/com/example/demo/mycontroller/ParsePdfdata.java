package com.example.demo.mycontroller;


import com.example.demo.UserCreateRequest;
import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

@RestController
public class ParsePdfdata {

    @RequestMapping(value = "/parsepdf", method = RequestMethod.POST)
    public String parsepdf(@RequestBody UserCreateRequest data) throws Exception {

        String file_data = data.getFile_data();
        return parsepdf(file_data);
    }

    public String decode(String s) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(s));
    }

    public String encode(String s) {
        return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
    }

    public String parsepdf(String src_data) throws Exception {

        FileOutputStream fos2 = new FileOutputStream("src.pdf");
        fos2.write(Base64.decodeBase64(src_data));
        fos2.close();

        String DEST = "result.txt";
        File file = new File(DEST);

        PdfReader reader = new PdfReader("src.pdf");
        FileOutputStream fos = new FileOutputStream(DEST);
        int number_of_pages = reader.getNumberOfPages();
        for (int page = 1; page <= number_of_pages; page++) {
            fos.write(PdfTextExtractor.getTextFromPage(reader, page).getBytes("UTF-8"));
        }
        fos.flush();
        fos.close();

        HashMap<String,String> map = new HashMap();
        map.put("file_data",encode_file(DEST));
        map.put("name","12");

        //convert holder object to JSONObject directly and return as string as follows
        return new Gson().toJson(map);

    }

    private static String encode_file(String sourceFile) throws Exception {

        byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(sourceFile));
        return new String(base64EncodedData);
    }

    public static byte[] loadFileAsBytesArray(String fileName) throws Exception {

        File file = new File(fileName);
        int length = (int) file.length();
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        reader.close();
        return bytes;

    }


}
