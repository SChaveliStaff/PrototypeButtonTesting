package PrototypeButtonTesting.PrototypeButtonTesting;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class buttonTesting {

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("In shutdown hook");
				shutdownGPIO();
			}
		}, "Shutdown-thread"));
		TestSwitches();
	}
	private static void TestSwitches()  {
		GpioController gpio = GpioFactory.getInstance();
		//Need fixing
		final GpioPinDigitalInput switch1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,PinPullResistance.PULL_UP);
		final GpioPinDigitalInput switch2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27,PinPullResistance.PULL_UP);
		//Working
		final GpioPinDigitalInput switch3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_22,PinPullResistance.PULL_UP);
		final GpioPinDigitalInput switch4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21,PinPullResistance.PULL_UP);
		// set shutdown state for this input pin
		switch1.setShutdownOptions(true);
		// set shutdown state for this input pin
		switch2.setShutdownOptions(true);
		// set shutdown state for this input pin
		switch3.setShutdownOptions(true);
		// set shutdown state for this input pin
		switch4.setShutdownOptions(true);
		// create and register gpio pin listener
		switch1.addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
			}

		});
		// create and register gpio pin listener
		switch2.addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
			}

		});
		// create and register gpio pin listener
		switch3.addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
			}

		});
		// create and register gpio pin listener
		switch4.addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
			}

		});

		System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

		// keep program running until user aborts (CTRL-C)
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// stop all GPIO activity/threads by shutting down the GPIO controller
		// (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
		// gpio.shutdown();  // <--- implement this method call if you wish to terminate the Pi4J GPIO controller
	}
	public static void shutdownGPIO() {
		GpioController gpio = GpioFactory.getInstance();
		System.out.println("shutting down gpio");
		gpio.shutdown();
	}
}
