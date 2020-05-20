package controller;

import java.util.concurrent.Semaphore;

import view.Bilheteria;

public class Cliente extends Thread {

	int idCli;
	boolean conectado = false;
	Semaphore semaforo;

	public Cliente(int idCli, Semaphore semaforo) {
		this.idCli = idCli;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {

		login();
		if (conectado) {
			compra();
		}
	}

	public void login() {

		int tempLogin = (int) ((Math.random() * 1950) + 50);
		try {
			Thread.sleep(tempLogin);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (tempLogin >= 1000) {
			System.out.println("Tempo expirado para o cliente " + idCli);
			conectado = true;
		} else {
			System.out.println("o Cliente " + idCli + "acessou");
		}
	}

	public void compra() {

		int tempCompra = (int) ((Math.random() * 2000) + 1000);
		try {
			Thread.sleep(tempCompra);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (tempCompra >= 2500) {
			System.out.println("O Tempo da sessão para o cliente " + idCli + " expirou");
		} else {
			try {

				semaforo.acquire();
				validarCompra();

			}

			catch (InterruptedException e) {
				e.printStackTrace();
			} finally {

				semaforo.release();

			}
		}

	}

	public void validarCompra() {

		int valCompra = (int) ((Math.random() * 4) + 1);
		if (Bilheteria.ingressoTot > 0) {

			if ((Bilheteria.ingressoTot - valCompra) < 0) {

				System.out.println("Voce tentou comprar " + valCompra + " ingressos. Quantidade de ingresso indisponiveis para essa compra, quantidade atual é "
						+ Bilheteria.ingressoTot);
			} else {

				Bilheteria.ingressoTot -= valCompra;
				System.out.println(
						"Cliente " + idCli + " efetuou a compra de " + valCompra + " e resta " + Bilheteria.ingressoTot);
			}

		} else {

			System.out.println("ingresso esgotado");

		}
	}

}
