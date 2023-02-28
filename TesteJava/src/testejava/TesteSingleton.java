package testejava;

class TesteSingleton {

	public static void main(String[] args) {
		Thread t1 = new MyThread(1);
		Thread t2 = new MyThread(2);

		t1.start();
		t2.start();

		System.out.println("retomando execucao na thread main...");
		Singleton.getInstance().imprimeValor(0);
	}

	static class Singleton {

		private int valor;
		private static Singleton singleton;

		private Singleton() {
			this.valor = 0;
		}

		public static synchronized Singleton getInstance() {
			if (singleton == null)
				singleton = new Singleton();
			return singleton;
		}

		public synchronized void incrementaValor() {
			this.valor++;
		}

		public void imprimeValor(int id) {
			System.out.println("Thread " + id + " Valor: " + valor);
		}

	}

	static class MyThread extends Thread {

		private int id;
		private int contador;

		public MyThread(int id) {
			this.id = id;
		}

		public void run() {
			Singleton s = Singleton.getInstance();
			s.incrementaValor();
			s.imprimeValor(id);
			contador++;
		}

	}

}