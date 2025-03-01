
package jpa.projectresearch.API;
import jakarta.servlet.http.HttpServletRequest;
import jpa.projectresearch.Dto.PaymentDto;
import jpa.projectresearch.Dto.TransactionDto;
import jpa.projectresearch.Payment.ConfigPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private HttpServletRequest request;
    @GetMapping("/create-payment/{amounts}")
    public ResponseEntity<?> createPayment( @PathVariable double amounts  ) throws UnsupportedEncodingException {
        long amount = (long) (amounts * 100 * 25000); // Nhân 100 và ép kiểu long

        String vnp_TxnRef = ConfigPayment.getRandomNumber(13);
        String vnp_TmnCode = ConfigPayment.vnp_TmnCode;
        String vnp_IpAddr = ConfigPayment.getIpAddress(request); // Lấy IP của khách hàng

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", ConfigPayment.vnp_Version);
        vnp_Params.put("vnp_Command", ConfigPayment.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        // BankCode là tham số tùy chọn, chỉ bắt buộc khi muốn chỉ định ngân hàng
        // vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", ConfigPayment.vnp_ReturnUrl); // Bỏ comment
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr); // Bỏ comment
        vnp_Params.put("vnp_OrderType", "other"); // Bổ sung OrderType

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();

        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigPayment.hmacSHA512(ConfigPayment.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigPayment.vnp_PayUrl + "?" + queryUrl;

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setStatus("Ok");
        paymentDto.setMessage("Successfully created payment");
        paymentDto.setURL(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);
    }


    @GetMapping("/info_payment")
    public ResponseEntity<?> infoPayment(
            @RequestParam(value = "vnp_Amount") String amount,
            @RequestParam(value = "vnp_BankCode") String bankCode,
            @RequestParam(value = "vnp_OrderInfo") String orderInfo,
            @RequestParam(value = "vnp_ResponseCode") String responseCode) {

        TransactionDto transactionDto = new TransactionDto();
        if ("00".equals(responseCode)) {
            transactionDto.setData("Ok");
            transactionDto.setMessage("Successfully payment");
            transactionDto.setStatus("Ok");
        } else {
            transactionDto.setData("Error");
            transactionDto.setMessage("Failed payment");
            transactionDto.setStatus("Error");
        }

        return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
    }
}

