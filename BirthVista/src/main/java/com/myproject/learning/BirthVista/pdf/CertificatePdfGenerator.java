package com.myproject.learning.BirthVista.pdf;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.LineSeparator;

import com.myproject.learning.BirthVista.model.Application;

@Component
public class CertificatePdfGenerator {
	
	public byte[] generatePdfView1(Application application) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    PdfWriter writer = new PdfWriter(out);
	    PdfDocument pdf = new PdfDocument(writer);
	    Document document = new Document(pdf, PageSize.A4);

	    // Fonts
	    PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	    PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	    PdfFont italicFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);

	    // Logo
	    ImageData imageData = ImageDataFactory.create(getClass().getClassLoader().getResource("static/birthlogo.png"));
	    Image logo = new Image(imageData);
	    logo.scaleToFit(100, 100);
	    logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    document.add(logo);

	    // Title
	    Paragraph title = new Paragraph("Birth Certificate")
	            .setFont(boldFont)
	            .setFontSize(26)
	            .setFontColor(ColorConstants.BLUE)
	            .setTextAlignment(TextAlignment.CENTER);
	    document.add(title);

	    document.add(new Paragraph("\n"));

	    // Intro Paragraph
	    String intro = "This is to certify that the following child was born and registered as per the details provided below:";
	    document.add(new Paragraph(intro)
	            .setFont(regularFont)
	            .setFontSize(13)
	            .setTextAlignment(TextAlignment.JUSTIFIED));

	    document.add(new Paragraph("\n"));

	    // Table
	    Table table = new Table(UnitValue.createPercentArray(new float[]{3, 7}))
	            .useAllAvailableWidth();

	    table.addCell(createHeaderCell("Full Name", boldFont));
	    table.addCell(createValueCell(application.getFullName(), boldFont, ColorConstants.DARK_GRAY));

	    table.addCell(createHeaderCell("Gender", boldFont));
	    table.addCell(createValueCell(application.getGender(), regularFont));

	    table.addCell(createHeaderCell("Date of Birth", boldFont));
	    table.addCell(createValueCell(application.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), regularFont));

	    table.addCell(createHeaderCell("Place of Birth", boldFont));
	    table.addCell(createValueCell(application.getPlaceOfBirth(), regularFont));

	    table.addCell(createHeaderCell("Father's Name", boldFont));
	    table.addCell(createValueCell(application.getFatherName(), regularFont));

	    table.addCell(createHeaderCell("Permanent Address", boldFont));
	    table.addCell(createValueCell(application.getPermanentAddress(), regularFont));

	    table.addCell(createHeaderCell("Application Date", boldFont));
	    table.addCell(createValueCell(application.getApplyDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), regularFont));

	    table.addCell(createHeaderCell("Mobile Number", boldFont));
	    table.addCell(createValueCell(application.getMobileNumber(), regularFont));

	    table.addCell(createHeaderCell("Email Address", boldFont));
	    table.addCell(createValueCell(application.getApplicantEmail(), regularFont));

	    document.add(table);

	    document.add(new Paragraph("\n"));

	    // Metadata
	    Paragraph date = new Paragraph("Certificate Generation Date: " + application.getUpdationDate())
	            .setFont(italicFont)
	            .setFontSize(12)
	            .setFontColor(ColorConstants.GRAY)
	            .setTextAlignment(TextAlignment.RIGHT);
	    document.add(date);

	    // Signature
	    Paragraph sign = new Paragraph("__________________________\nRegistrar Signature")
	            .setFont(italicFont)
	            .setFontSize(12)
	            .setFontColor(ColorConstants.GRAY)
	            .setTextAlignment(TextAlignment.RIGHT);
	    document.add(sign);

	    document.close();
	    return out.toByteArray();
	}
	private Cell createHeaderCell(String text, PdfFont font) {
	    return new Cell().add(new Paragraph(text).setFont(font).setFontSize(13).setBold())
	                     .setBackgroundColor(ColorConstants.LIGHT_GRAY)
	                     .setPadding(5);
	}

	private Cell createValueCell(String text, PdfFont font) {
	    return new Cell().add(new Paragraph(text).setFont(font).setFontSize(13))
	                     .setPadding(5);
	}

	private Cell createValueCell(String text, PdfFont font, com.itextpdf.kernel.colors.Color color) {
	    return new Cell().add(new Paragraph(text).setFont(font).setFontSize(13).setFontColor(color))
	                     .setPadding(5);
	}

	public byte[] generatePdfView2(Application application) throws Exception {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    PdfWriter writer = new PdfWriter(out);
	    PdfDocument pdf = new PdfDocument(writer);
	    Document document = new Document(pdf, PageSize.A4);

	    // Load image
	    ImageData imageData = ImageDataFactory.create(getClass().getClassLoader().getResource("static/birthlogo.png"));
	    Image logo = new Image(imageData).scaleAbsolute(100, 100);
	    logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    document.add(logo);
	    document.add(new Paragraph("\n"));

	    // Title
	    PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	    Paragraph title = new Paragraph("Birth Certificate")
	            .setFont(titleFont)
	            .setFontSize(28)
	            .setFontColor(ColorConstants.BLUE)
	            .setTextAlignment(TextAlignment.CENTER);
	    document.add(title);

	    
	    SolidLine solidLine = new SolidLine(3f); 
	    LineSeparator line = new LineSeparator(solidLine);
	    document.add(line);
	   
	    PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	    PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	    PdfFont italicFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);

	    // Paragraph Construction
	    Paragraph paragraph = new Paragraph()
	            .setTextAlignment(TextAlignment.JUSTIFIED)
	            .setMarginTop(10)
	            .setMarginBottom(10);

	    paragraph.add(new Text("This is to certify that ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getFullName()).setFont(boldFont).setFontSize(15).setFontColor(ColorConstants.DARK_GRAY));
	    paragraph.add(new Text(", a ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getGender().toLowerCase()).setFont(boldFont).setFontSize(13));
	    paragraph.add(new Text(" child, was born on ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))).setFont(boldFont).setFontSize(13));
	    paragraph.add(new Text(" in ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getPlaceOfBirth()).setFont(boldFont).setFontSize(13));
	    paragraph.add(new Text(". His father is ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getFatherName()).setFont(boldFont).setFontSize(13));
	    paragraph.add(new Text(", and their permanent address is ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getPermanentAddress()).setFont(boldFont).setFontSize(13));
	    paragraph.add(new Text(". The application for the birth certificate was submitted on ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getApplyDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))).setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(". For any further communication, the registered mobile number is ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getMobileNumber()).setFont(boldFont).setFontSize(13));
	    paragraph.add(new Text(", and the email address is ").setFont(regularFont).setFontSize(13));
	    paragraph.add(new Text(application.getApplicantEmail() + ".").setFont(boldFont).setFontSize(13));

	    document.add(paragraph);
	    document.add(new Paragraph("\n\n"));

	    // Metadata
	    Paragraph datePara = new Paragraph("Certificate Generation Date: " + application.getUpdationDate())
	            .setFont(italicFont)
	            .setFontSize(12)
	            .setFontColor(ColorConstants.GRAY)
	            .setTextAlignment(TextAlignment.RIGHT);
	    document.add(datePara);

	    // Signature
	    Paragraph sign = new Paragraph("__________________________\nRegistrar Signature")
	            .setFont(italicFont)
	            .setFontSize(12)
	            .setFontColor(ColorConstants.GRAY)
	            .setTextAlignment(TextAlignment.RIGHT);
	    document.add(sign);

	    document.close();
	    return out.toByteArray();
	}
	
	public byte[] generatePdfView3(Application application) throws Exception {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    PdfWriter writer = new PdfWriter(out);
	    PdfDocument pdf = new PdfDocument(writer);
	    Document document = new Document(pdf, PageSize.A4);

        // Set font
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

        // Logo (optional)
        ImageData imageData = ImageDataFactory.create(getClass().getClassLoader().getResource("static/birthlogo.png"));
	    Image logo = new Image(imageData).scaleAbsolute(100, 100);
	  
        logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(logo);

        // Title
        Paragraph title = new Paragraph("BIRTH CERTIFICATE")
                .setFont(boldFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);
        document.add(title);

        // Line separator
        SolidLine line = new SolidLine(1f);
        line.setColor(ColorConstants.BLACK);
        document.add(new LineSeparator(line));

        // Subtitle
        document.add(new Paragraph("\n\nThis is to certify that the following details are recorded as per government records.")
                .setFont(font)
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(5)
                .setMarginBottom(20));

        // Certificate Info Table
        float[] colWidths = {150F, 300F};
        Table table = new Table(UnitValue.createPointArray(colWidths));
        table.setWidth(UnitValue.createPercentValue(70)).setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addCell(getCell("Child's Full Name", font));
        table.addCell(getCell(application.getFullName(), boldFont));

        table.addCell(getCell("Date of Birth", font));
        table.addCell(getCell(application.getDateOfBirth().toString(), font));

        table.addCell(getCell("Place of Birth", font));
        table.addCell(getCell(application.getPlaceOfBirth(), font));

        table.addCell(getCell("Father's Name", font));
        table.addCell(getCell(application.getFatherName(), font));

        table.addCell(getCell("Mobile Number", font));
        table.addCell(getCell(application.getMobileNumber(), font));

        table.addCell(getCell("Permanent Address", font));
        table.addCell(getCell(application.getPermanentAddress(), font));

        document.add(table.setMarginTop(10));

        // Signature Section
        document.add(new Paragraph("\n\n"));
        Table signatureTable = new Table(UnitValue.createPercentArray(new float[]{1, 1}))
                .useAllAvailableWidth();

        signatureTable.addCell(new Cell()
                .add(new Paragraph("Issued Date:\n"+application.getUpdationDate().toString()))
                .setBorder(Border.NO_BORDER)
                .setFont(font));

        signatureTable.addCell(new Cell()
                .add(new Paragraph("\nRegistrar Signature"))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER)
                .setFont(font));

        document.add(signatureTable);


	    document.close();
	    return out.toByteArray();
	}
	private static Cell getCell(String text, PdfFont font) {
        return new Cell().add(new Paragraph(text))
                .setFont(font)
                .setFontSize(11)
                .setBorder(Border.NO_BORDER)
                .setPaddingBottom(5);
    }

}
