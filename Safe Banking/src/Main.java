
public class Main {
	
    public static void main(String[] args) {
    	
        ContBancar cont = new ContBancar("RO123456789", "RON", 1000, 500);

        try {
        	
            cont.alimenteazaCont(100, "USD");
        } catch (ContBancar.ValutaGresitaException e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        cont.blocheazaCont();
        
        try {
        	
            cont.retrageBani(200, "RON");
        } catch (ContBancar.ContBlocatException e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        cont.deblocheazaCont();

        try {
            cont.retrageBani(1200, "RON");
        } catch (ContBancar.FonduriInsuficienteException e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (ContBancar.DepasireLimitaException e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        System.out.println("Soldul final este: " + cont.getSold());
    }
}
