package business_layer;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateMutants {

  private static final Logger LOGGER = Logger.getLogger(GenerateMutants.class.getName());

  private static String inputString;

  // It sets the value for inputString used in a class’s programs
  public GenerateMutants(String inputString) {
    this.inputString = inputString;
  }

  public String implementGenerateMutants() {

    // Create a regular expression that matches the characters you want to replace
    Pattern pattern = Pattern.compile("[(ے|ی|ھ|ہ|و|ں|ن|م|ل|گ|ک|ق|ف|غ|ع|ظ|ط|ض|ص|ش|س|ژ|ز|ڑ|ر|ذ|ڈ|د|خ|ح|چ|ج|ث|ٹ|ت|پ|ب|آ|ا|ء)]");
    Matcher matcher = pattern.matcher(inputString);

    // Use a Set to store the unique mutants
    Set < String > mutants = new HashSet < > ();

    // Loop through all the matches and replace them with the corresponding character
    while (matcher.find()) {
      String character = matcher.group();
      // Use a regular expression to match the character and replace it with the corresponding character

      // Call the generateMutants method to generate mutants and add them to the Set
      generateMutants(inputString, mutants);
    }

    // Print the unique mutants
    for (String mutant: mutants) {
      System.out.println(mutant);
    }

    // Convert the mutants set to a string and return it
    return mutants.toString();
  }

  // This method generates mutants of a given input string and adds them to a given set
  public static void generateMutants(String inputString, Set < String > mutants) {
    // Generate mutants and add them to the Set
    mutants.add(inputString.replaceAll("ق", "ک"));
    mutants.add(inputString.replaceAll("ک", "ق"));

    mutants.add(inputString.replaceAll("ص", "ث"));
    mutants.add(inputString.replaceAll("ش", "ث"));
    mutants.add(inputString.replaceAll("س", "ث"));
    mutants.add(inputString.replaceAll("ث", "ص"));
    mutants.add(inputString.replaceAll("ش", "ص"));
    mutants.add(inputString.replaceAll("س", "ص"));
    mutants.add(inputString.replaceAll("ث", "ش"));
    mutants.add(inputString.replaceAll("ص", "ش"));
    mutants.add(inputString.replaceAll("س", "ش"));
    mutants.add(inputString.replaceAll("ث", "س"));
    mutants.add(inputString.replaceAll("ص", "س"));
    mutants.add(inputString.replaceAll("ش", "س"));

    mutants.add(inputString.replaceAll("ظ", "ض"));
    mutants.add(inputString.replaceAll("ذ", "ض"));
    mutants.add(inputString.replaceAll("ز", "ض"));
    mutants.add(inputString.replaceAll("ض", "ظ"));
    mutants.add(inputString.replaceAll("ذ", "ظ"));
    mutants.add(inputString.replaceAll("ز", "ظ"));
    mutants.add(inputString.replaceAll("ض", "ذ"));
    mutants.add(inputString.replaceAll("ظ", "ذ"));
    mutants.add(inputString.replaceAll("ز", "ذ"));
    mutants.add(inputString.replaceAll("ض", "ز"));
    mutants.add(inputString.replaceAll("ظ", "ز"));
    mutants.add(inputString.replaceAll("ذ", "ز"));

    mutants.add(inputString.replaceAll("د", "ڈ"));
    mutants.add(inputString.replaceAll("ڈ", "د"));

    mutants.add(inputString.replaceAll("ٹ", "ط"));
    mutants.add(inputString.replaceAll("ت", "ط"));
    mutants.add(inputString.replaceAll("ط", "ٹ"));
    mutants.add(inputString.replaceAll("ت", "ٹ"));
    mutants.add(inputString.replaceAll("ط", "ت"));
    mutants.add(inputString.replaceAll("ٹ", "ت"));

    mutants.add(inputString.replaceAll("ہ", "ھ"));
    mutants.add(inputString.replaceAll("ح", "ھ"));
    mutants.add(inputString.replaceAll("ھ", "ہ"));
    mutants.add(inputString.replaceAll("ح", "ہ"));
    mutants.add(inputString.replaceAll("ھ", "ح"));
    mutants.add(inputString.replaceAll("ہ", "ح"));

    mutants.add(inputString.replaceAll("ن", "ں"));
    mutants.add(inputString.replaceAll("ں", "ن"));

    mutants.add(inputString.replaceAll("ر", "ڑ"));
    mutants.add(inputString.replaceAll("ڑ", "ر"));

    mutants.add(inputString.replaceAll("ع", "ا"));
    mutants.add(inputString.replaceAll("ا", "ع"));

    mutants.add(inputString.replaceAll("خا", "کھا"));
    mutants.add(inputString.replaceAll("تھ", "ٹھ"));
    mutants.add(inputString.replaceAll("ٹھ", "تھ"));
    mutants.add(inputString.replaceAll("دھ", "ڈھ"));
    mutants.add(inputString.replaceAll("ڈھ", "دھ"));

    // Log the number of mutants generated
    LOGGER.info("Number of mutants generated: " + mutants.size());

  }
}