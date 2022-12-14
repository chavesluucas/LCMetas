package tech.lucaschaves.lcmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.repositories.SaleRepository;

//Service que peguei pronto no github do evento, o service é para os sms enviado pelo Twilio

@Service
public class SmsService {
	
	@Autowired
	private SaleRepository repository;

	//As váriaves são parametros que estão no application.properties, dados sensiveis que será passado pelo Twilio
	//o @Value vai buscar na variavel de ambiente oq foi especificado no parametro
	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long saleId) {
		
		//Buscando um sale pelo id e o get é para pegar ele, não só encontrar
		Sale sale = repository.findById(saleId).get();
		
		//Fazendo uma string onde vamos pegar o mes e contacater com uma barra e o ano, "05/2022" por exemplo
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		
		String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
			    + " com um total de R$ " + String.format("%.0f", sale.getAmount());
		
		//podemos verificar que utilizamos todas as variaveis
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid() + "Deu boa ein!");
	}
}
