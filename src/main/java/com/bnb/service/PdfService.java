package com.bnb.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;
@Service
public class PdfService {
    public static ByteArrayInputStream customerPDFReport() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();
            Font font1 = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Booking Invoice",font1);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            document.add(new Paragraph("\n"));


            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Customer Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);


            document.add(new Paragraph("\n"));

            // Add Status (Pending)
            Paragraph statusParagraph = new Paragraph("BOOKING STATUS -PENDING", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            statusParagraph.setAlignment(Element.ALIGN_CENTER);

            document.add(statusParagraph);

// Add Customer Information
            document.add(new Paragraph("Bridgette Hammes", FontFactory.getFont(FontFactory.COURIER, 10)));
            document.add(new Paragraph("email -bridgette@example.com"));
            document.add(new Paragraph("address -59512 Anderson Loop\nArchibaldhaven, 06299\nUnited Kingdom"));


            document.add(new Paragraph("\n"));
// Add Booking Details Table
            float[] columnWidths = {1, 3}; // Define column widths
            PdfPTable detailsTable = new PdfPTable(columnWidths);
            detailsTable.setWidthPercentage(100);
            Font font2 = FontFactory.getFont(FontFactory.COURIER);

            Phrase phrase = new Phrase("writer", font2);
            detailsTable.addCell(new PdfPCell(phrase));
            detailsTable.addCell(new PdfPCell(new Phrase("Booking ID:", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            detailsTable.addCell(new PdfPCell(new Phrase("CFPP-290317")));
            detailsTable.addCell(new PdfPCell(new Phrase("Created:", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            detailsTable.addCell(new PdfPCell(new Phrase("March 29, 2017")));
            detailsTable.addCell(new PdfPCell(new Phrase("Booking Date:", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            detailsTable.addCell(new PdfPCell(new Phrase("December 24, 2017")));
            detailsTable.addCell(new PdfPCell(new Phrase("Total (GBP):", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            detailsTable.addCell(new PdfPCell(new Phrase("£92.02")));
            document.add(detailsTable);

// Add Line Break
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("\n"));

// Add Itemized Booking List
            float[] itemColumnWidths = {4, 1, 1}; // Define column widths
            PdfPTable itemTable = new PdfPTable(itemColumnWidths);
            itemTable.setWidthPercentage(100);

            itemTable.addCell(new PdfPCell(new Phrase("Item", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            itemTable.addCell(new PdfPCell(new Phrase("Rate", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            itemTable.addCell(new PdfPCell(new Phrase("Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));

            itemTable.addCell(new PdfPCell(new Phrase("Walking Tour with a Taste of London")));
            itemTable.addCell(new PdfPCell(new Phrase("Adults: 1 @ £27.00")));
            itemTable.addCell(new PdfPCell(new Phrase("£27.00")));

            itemTable.addCell(new PdfPCell(new Phrase("The London Experience Hat (2)")));
            itemTable.addCell(new PdfPCell(new Phrase("2 x £15.00")));
            itemTable.addCell(new PdfPCell(new Phrase("£30.00")));

            itemTable.addCell(new PdfPCell(new Phrase("The London Experience Mini-Bus")));
            itemTable.addCell(new PdfPCell(new Phrase("")));
            itemTable.addCell(new PdfPCell(new Phrase("£25.00")));

            itemTable.addCell(new PdfPCell(new Phrase("Security Code: ABC-123", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            itemTable.addCell(new PdfPCell(new Phrase("")));
            itemTable.addCell(new PdfPCell(new Phrase("")));

            document.add(itemTable);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close(); // Ensure the document is closed
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}