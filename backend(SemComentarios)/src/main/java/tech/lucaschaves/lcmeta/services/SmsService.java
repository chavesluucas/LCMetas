package tech.lucaschaves.lcmeta.services;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.repositories.SaleRepository;

@Service
public class SmsService {
	
	@Autowired
	private SaleRepository repository;

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long saleId) {
		
		Sale sale = repository.findById(saleId).get();
		
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		
		String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
			    + " com um total de R$ " + new DecimalFormat("#,##0.00").format(sale.getAmount());
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}
