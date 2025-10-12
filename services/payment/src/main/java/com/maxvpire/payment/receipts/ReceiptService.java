package com.maxvpire.payment.receipts;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.maxvpire.payment.invoices.Invoices;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    public String generatePdfReceipt(ReceiptRequest request) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("===== RECEIPT ====="));
        document.add(new Paragraph("Invoice No: " + request.getInvoiceId()));
        document.add(new Paragraph("Amount Paid: " + request.getAmount() + "$"));
        document.add(new Paragraph("Payment Method: " + request.getMethod()));
        document.add(new Paragraph("Status: SUCCESSFUL"));
        document.add(new Paragraph("Issued At: " + LocalDateTime.now()));
        document.add(new Paragraph(" "));

        String qrContent = "paymentId:" + request.getPaymentId();
        byte[] qrImageBytes = QRCodeGenerator.generateQRCodeBytes(qrContent, 200, 200);
        Image qrImage = Image.getInstance(qrImageBytes);
        qrImage.setAlignment(Image.ALIGN_CENTER);
        document.add(qrImage);

        document.add(new Paragraph("==================="));
        document.close();

        byte[] pdfBytes = baos.toByteArray();

        String objectName = "receipt-" + request.getPaymentId() + ".pdf";
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(new ByteArrayInputStream(pdfBytes), pdfBytes.length, -1)
                        .contentType("application/pdf")
                        .build()
        );
        return objectName;
    }
}