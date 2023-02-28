package testejava;

import javax.xml.bind.annotation.XmlElement;

public class Nome {
	@XmlElement
	private String primeiro;
	@XmlElement
	private String ultimo;

	public String getPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(String primeiro) {
		this.primeiro = primeiro;
	}

	public String getUltimo() {
		return ultimo;
	}

	public void setUltimo(String ultimo) {
		this.ultimo = ultimo;
	}

	public Nome(String primeiro, String ultimo) {
		super();
		this.primeiro = primeiro;
		this.ultimo = ultimo;
	}

	@Override
	public String toString() {
		return "Nome [primeiro=" + primeiro + ", ultimo=" + ultimo + "]";
	}

}
