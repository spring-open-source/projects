package com.Maulik;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

    @Component
    public class MyListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    	static int thePort=0;
    	
      @Override
      public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {
          thePort = event.getEmbeddedServletContainer().getPort();
      }
    }