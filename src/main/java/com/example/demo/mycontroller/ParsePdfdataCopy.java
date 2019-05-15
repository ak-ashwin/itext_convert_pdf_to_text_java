//package com.example.demo.mycontroller;
//
//
//import com.example.demo.UserCreateRequest;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.parser.PdfTextExtractor;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.tomcat.util.codec.binary.StringUtils;
//import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@RestController
//public class ParsePdfdataCopy {
//
//    @RequestMapping("/")
//    public String index() throws IOException {
//
//        //     * The original PDF that will be parsed.
//        String SRC = "/Users/apple/Downloads/hdfc_2/hdfc_2.pdf";
//        //     * The resulting text file.
//        String DEST = "/Users/apple/Downloads/result_pdf.txt";
//
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//
//        PdfReader reader = new PdfReader(SRC);
//        FileOutputStream fos = new FileOutputStream(DEST);
//        int number_of_pages = reader.getNumberOfPages();
//        for (int page = 1; page <= number_of_pages; page++) {
//            fos.write(PdfTextExtractor.getTextFromPage(reader, page).getBytes("UTF-8"));
//        }
//        fos.flush();
//        fos.close();
//
//        return "done";
//    }
//
//
//    @RequestMapping(value = "/parsepdf", method = RequestMethod.POST)
//    public String parsepdf(@RequestBody UserCreateRequest data) throws IOException {
//
//        String file_data = data.getFile_data();
//        return parsepdf(decode(file_data));
//    }
//
//    public String decode(String s) {
//        return StringUtils.newStringUtf8(Base64.decodeBase64(s));
//    }
//    public String encode(String s) {
//        return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
//    }
//
//    public String parsepdf(String src_data) throws IOException {
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        PdfReader reader = new PdfReader(src_data);
//        int number_of_pages = reader.getNumberOfPages();
//        for (int page = 1; page <= number_of_pages; page++) {
//            stream.write(PdfTextExtractor.getTextFromPage(reader, page).getBytes("UTF-8"));
//        }
//
//        String finalString = new String(stream.toByteArray());
////        System.out.println(finalString);
//        return encode(finalString);
//    }
//}
