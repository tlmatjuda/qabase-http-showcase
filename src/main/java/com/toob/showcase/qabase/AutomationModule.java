package com.toob.showcase.qabase;

import com.toob.qabase.QaBaseCoreModuleFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({QaBaseCoreModuleFactory.class})
public class AutomationModule {}
