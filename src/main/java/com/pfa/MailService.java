package com.pfa;

public interface MailService {
public void send(String toAdress,String fromAddress,String subject ,String content) throws Exception;
}
