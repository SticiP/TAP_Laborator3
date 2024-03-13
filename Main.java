import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Linie 
{
    String text;

    Linie()
    {
        System.out.println("Constructor Linie()");
    }

    String modifyString(String input) {
        String escapedInput = input.replaceAll("([\\\\\\[\\](){}+*?.^$|])", "\\\\$1");
        
        String manipulatedString = escapedInput.replaceAll("\\b(\\w+)\\b", "\\\\b$1\\\\b");
        
        return manipulatedString;
    }

    String modifyString2(String input) {
        StringBuilder modifiedString = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            
            if (isSpecialCharacter(currentChar)) {
                modifiedString.append("\\").append(currentChar);
            }

            if (Character.isLetterOrDigit(currentChar)) {
                int start = i;
                while (i < input.length() && Character.isLetterOrDigit(input.charAt(i))) {
                    i++;
                }
                modifiedString.append("\\b").append(input.substring(start, i)).append("\\b");
                i--; 
            }
            
        }
        
        return modifiedString.toString();
    }

    boolean isSpecialCharacter(char c) {
        return c == '\\' || c == '[' || c == ']' || c == '(' || c == ')' ||
               c == '{' || c == '}' || c == '+' || c == '*' || c == '?' ||
               c == '.' || c == '^' || c == '$' || c == '|' || c == '-';
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
        String regex = "(?<!\\S)" + modifyString2(pattern) + "(?!\\S)";
        System.out.println("regex = " + regex);
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
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

        String regex = "(?<!\\S)" + modifyString2(pattern) + "(?!\\S)";
        System.out.println("regex = " + regex);
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        if (m.find()) {
            System.out.println("Sirul a fost gasit in pagina : " + pattern);
        } else {
            System.out.println("Sirul nu a fost gasit in pagina");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Pagina pagina = new Pagina();

        pagina.setText("Lorem ipsum dolor sit amet. consectetur adipiscing elit.");
        pagina.afisare("Textul este: ");
        pagina.cautare("amet.");

        Linie linie = new Linie("Lorem ipsum.-s dolor sit amet.");
        linie.afisare();
        linie.cautare("ipsum.-s");

    }
}
