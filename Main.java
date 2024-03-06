import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Linie 
{
    String text;

    Linie()
    {
        System.out.println("Constructor Linie()");
    }

    Linie(String text) 
    {
        this.text = text;
        System.out.println("Constructor Linie(String text), text = " + text);
    }

    void setText(String text)
    {
        this.text = text;
        System.out.println("Linie.setText(String text), text = " + text);
    }

    void setText(String text1, String text2)
    {
        this.text = text1 + text2;
        System.out.println("Linie.setText(String text1, String text2), text = " + text);
    }

    void afisare() 
    {
        System.out.println("Text din linie: \n" + text);
    }

    void afisare(String prefix) 
    {
        System.out.println(prefix + text);
    }

    void cautare(String pattern) 
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        if (m.find()) {
            System.out.println("Sirul a fost gasit in linie : " + pattern);
        } else {
            System.out.println("Sirul nu a fost gasit in linie");
        }
    }
}

class Pagina extends Linie {
    
    Pagina() {
        System.out.println("Constructor Pagina()");
    }

    Pagina(String text) {
        super(text);
        System.out.println("Constructor Pagina(String text), text = " + text);
    }

    @Override
    void setText(String text) {
        this.text = text;
        System.out.println("Pagina.setText(String text), text = " + text);
    }

    @Override
    void afisare() {
        System.out.println("Text din pagină: \n" + text);
    }

    @Override
    void cautare(String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        if (m.find()) {
            System.out.println("Sirul a fost gasit in pagina : " + pattern);
        } else {
            System.out.println("Sirul nu a fost gasit in pagina");
        }
    }

    // Alte metode suprascrise sau supraincarcate
}

public class Main {
    public static void main(String[] args) {
        Pagina pagina = new Pagina();

        pagina.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        pagina.afisare("Textul este: ");
        pagina.cautare("amet");

        Linie linie = new Linie("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        linie.afisare();
        linie.cautare("amet");


    }
}
