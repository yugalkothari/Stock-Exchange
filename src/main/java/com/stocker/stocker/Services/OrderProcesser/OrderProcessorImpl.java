package com.stocker.stocker.Services.OrderProcesser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.stocker.stocker.Repositories.*;
import com.stocker.stocker.Models.*;

import java.io.Serializable;


@Service
public class OrderProcessorImpl implements Serializable {



    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void Processor() {

    }



}
