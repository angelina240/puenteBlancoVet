package com.puenteblanco.pb.controller.Report;

import com.puenteblanco.pb.services.report.OrderReceiptReport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/orders")
public class OrderReceiptController {

    private final OrderReceiptReport orderReceiptReport;

    public OrderReceiptController(OrderReceiptReport orderReceiptReport) {
        this.orderReceiptReport = orderReceiptReport;
    }

    @GetMapping("/{id}/receipt")
    public ResponseEntity<byte[]> generarComprobante(@PathVariable Long id) {
        byte[] pdf = orderReceiptReport.generateReceipt(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recibo-pedido-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
