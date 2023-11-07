package facade_design_pattern;

import business_layer.GenerateMutants;

public class MutantGenerator {
  private GenerateMutants generator;

  public MutantGenerator(String inputString) {
    generator = new GenerateMutants(inputString);
  }

  public String generateMutants() {
    String mutantsString = generator.implementGenerateMutants();
    String mutants = mutantsString;
    // Parse the mutants from the string and add them to the set
    return mutants;
  }
}