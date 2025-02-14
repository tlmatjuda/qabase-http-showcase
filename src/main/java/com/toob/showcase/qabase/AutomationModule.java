package com.toob.showcase.qabase;

import com.toob.qabase.core.CoreComponentFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CoreComponentFactory.class})
public class AutomationModule {}
