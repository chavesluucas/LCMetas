package tech.lucaschaves.lcmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.services.SaleService;
import tech.lucaschaves.lcmeta.services.SmsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	SaleService service;

	@Autowired
	SmsService sms;

	@GetMapping
	public ResponseEntity<Page<Sale>> getAll(@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable pageable) {
		Page<Sale> listSale = service.findAll(minDate, maxDate, pageable);

		return ResponseEntity.ok().body(listSale);
	}

	@GetMapping("/{id}/notification")
	public void notifySms(@PathVariable Long id) {
		sms.sendSms(id);
	}

}
