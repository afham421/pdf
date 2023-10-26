package com.generatepdf.pdf.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    private Logger logger = LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdf(){

        logger.info("Create pdf started : ");

        String title = "welcome to pdf";
        String content = "OpenPDF is a Java library for creating and editing PDF files with a LGPL and MPL open source license." +
                " OpenPDF is the LGPL/MPL open source successor of iText, and is based on some forks of iText 4 svn tag." +
                " We welcome contributions from other developers. " +
                "Please feel free to submit pull-requests and bugreports to this GitHub repository.";

        ByteArrayOutputStream output = new ByteArrayOutputStream();

//        Document document = new Document(PageSize.A4.rotate());
        Document document = new Document();

        PdfWriter.getInstance(document,output); // is sy ho ga yeh k document main jo write krin gy woh output main show ho ga

        HeaderFooter footer = new HeaderFooter(true, new Phrase("LCWD"));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);
        document.open(); // document open ho ga is sy

        Image gif = null;
        try {
            gif = Image.getInstance("C:\\Users\\Muhammad Afham\\Downloads\\salam mobile.png");
            gif.setAlignment(Image.RIGHT);
            document.add(gif);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Font titleFont = FontFactory.getFont(FontFactory.defaultEncoding,25);
        Paragraph titleParagraph = new Paragraph(title,titleFont);  // for create paragraph
        titleParagraph.setAlignment(Element.ALIGN_CENTER);

        document.add(titleParagraph);

        Font paraFont = FontFactory.getFont(FontFactory.defaultEncoding,17);
        Paragraph para = new Paragraph(content,paraFont);
//        para.add(new Chunk(gif,0,17));
        para.add(new Chunk("wow this text is added after creating chunk"));
        document.add(para);

        document.close();

        return new ByteArrayInputStream(output.toByteArray());


        //agr method ki yeh return type nhin bnain gy tu agla person read nhin kr saky ga
        //ab is sy yeh ho ga k ab is createPdf() method ko kahin bhi call krin gy tu yeh file read able ho gi
///// yahan per simple output return nhin kr sakty is liye toByteArray() method use krin gy
    }

}
