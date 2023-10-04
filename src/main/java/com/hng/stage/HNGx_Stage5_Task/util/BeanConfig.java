package com.hng.stage.HNGx_Stage5_Task.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
//@Configuration
public class BeanConfig {
    @Value("${OPENAI_API_KEY}")
    public String apiKey;
}
