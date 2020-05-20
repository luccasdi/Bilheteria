package view;

import java.util.concurrent.Semaphore;
import controller.Cliente;

public class Bilheteria {

	public static Semaphore sem;
	public static int ingressoTot = 100;

	public static void main(String[] args) {

		sem = new Semaphore(1);
		for (int i = 0; i < 300; i++) {
			Thread th = new Cliente(i, sem);
			th.start();

		}

	}

}
