package com.donath.service;

import br.com.moip.API;
import br.com.moip.request.*;
import br.com.moip.resource.Payment;
import com.donath.config.MoipAuth;
import com.donath.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cin_bdonath on 21/03/2017.
 */
@Service
public class MoipService {


    @Autowired
    private MoipAuth moipAuth;

    public Payment createPayment(Order order) {
        API moipAPI = moipAuth.getMoipAPI();
        Payment createdPayment = moipAPI.payment().create(
                new PaymentRequest()
                        .orderId(order.getId())
                        .installmentCount(order.getInstallments())
                        .fundingInstrument(
                                new FundingInstrumentRequest()
                                        .creditCard(
                                                new CreditCardRequest()
                                                        .hash(order.getCcHash())
                                                        .holder(
                                                                new HolderRequest()
                                                                        .fullname("Jose Portador da Silva")
                                                                        .birthdate("1988-10-10")
                                                                        .phone(
                                                                                new PhoneRequest()
                                                                                        .setAreaCode("11")
                                                                                        .setNumber("55667788")
                                                                        )
                                                                        .taxDocument(TaxDocumentRequest.cpf("22222222222"))
                                                        )
                                        )
                        )
        );

        return createdPayment;
    }

    public Order placeMoipOrder(Order order) {
        API moipAPI = moipAuth.getMoipAPI();

        br.com.moip.resource.Order moipOrder = moipAPI.order().create(new OrderRequest()
                .ownId("ABC-123-DEF")
                .addItem(order.getProduct().getDescription(), 1, order.getProduct().getDescription(),
                        order.getFinalPrice().multiply(BigDecimal.valueOf(100)).intValue())
                .customer(new CustomerRequest()
                        .ownId("AEEEEEMMMMPPPP")
                        .fullname("Jose da Silva")
                        .email("josedasilva@email.com")
                        .birthdate(new ApiDateRequest().date(new Date()))
                        .taxDocument(TaxDocumentRequest.cpf("22222222222"))
                        .phone(new PhoneRequest().setAreaCode("11").setNumber("55443322"))
                        .shippingAddressRequest(new ShippingAddressRequest().street("Avenida Faria Lima")
                                .streetNumber("3064")
                                .complement("12 andar")
                                .city("São Paulo")
                                .state("SP")
                                .district("Itaim")
                                .country("BRA")
                                .zipCode("01452-000")
                        )
                )
        );

        order.setId(moipOrder.getId());
        return order;
    }
}
