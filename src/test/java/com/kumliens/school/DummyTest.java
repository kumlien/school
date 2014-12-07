package com.kumliens.school;

import static reactor.event.selector.Selectors.$;

import org.junit.Test;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;

public class DummyTest {
	
	@Test
	public void dummyTest() throws InterruptedException {
		Environment env = new Environment();
		Reactor reactor = Reactors.reactor()
							.env(env)
							.dispatcher("ringBuffer")
							.get();
		String key = "key";
		reactor.<Event<String>>on($(key), event -> System.out.println("hej " + event.getData()));
		
		reactor.notify(key, Event.wrap("Svante1"));
		//reactor.send(key, Event.wrap("Svante2").setReplyTo("test2"));
		System.out.println("ooops");
		Thread.sleep(10);
	}
}
