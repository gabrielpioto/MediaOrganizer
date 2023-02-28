package testejava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {

	public static void main(String[] args) throws Exception {

		
		//System.out.println(divide(2, 1));
		printTest();
		

	}
	
	public static void printTest(){
		Object[] numeros = {2,3,5};
		Object[] nomes = {new Nome("primeiro","ultimo"),new Nome("gabriel","pioto"), numeros};
		String s = toString(nomes);
		System.out.println(String.valueOf(nomes));
		
		
		
		
	}
	
	public static String toString(Object o){
		if(o.getClass().isArray()){
			String s = "[";
			for(Object obj : (Object[])o){
				s+=toString(obj)+", ";
			}
			return s+"]";
		} else {
			return o.toString();
		}		
		
	}
	
	
	
	public static <T> T unmarshal(Class<T> docClass, Reader reader)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(docClass);
		Unmarshaller u = jc.createUnmarshaller();
		return (T) u.unmarshal(reader);
//		JAXBElement<T> doc = (JAXBElement<T>) u.unmarshal(reader);
//		return doc.getValue();
	}

	private static void teste2(String... strings) {
		String[] vetor = prepend(strings, "gabriel", "pioto");
		for (String s : vetor) {
			System.out.println(s);
		}
	}

	private static void teste() throws Exception {
		URL url = new URL(
				"https://api-v2launch.trakt.tv/search?id_type=imdb&id=tt0848228");
		URLConnection yc = url.openConnection();
		yc.addRequestProperty("Content-Type", "application/json");
		yc.addRequestProperty("trakt-api-version", "2");
		yc.addRequestProperty("trakt-api-key",
				"2f32cbb0d5cb03c6e9b216b45aa2025373df4ff691de2d6a62191711fac438f9");

		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}

	// @SuppressWarnings("unchecked")
	@SafeVarargs
	private static <T> T[] prepend(T[] target, T... s) {
		T[] aux = Arrays.copyOf(s, s.length + target.length);
		System.arraycopy(target, 0, aux, s.length, target.length);
		return aux;
	}

	public static enum E {
		A,
		B,
		C;
	}

}
