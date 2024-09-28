package com.devsuperior.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;


@Service
public class PaymentService {
	/*
	@Value("${hr-worker.host}")
	private String workerHost;
	
	//Conexao forçada, hardcoded
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(long workerId, int days) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id","" + workerId);
		
		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		
	}
	*/
	//usando o Feign Client
	@Autowired
	private WorkerFeignClient workerFeingClient;
	public Payment getPayment(long workerId, int days) {
		Worker worker = workerFeingClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}	
}
