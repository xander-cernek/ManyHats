package manyHats.util;

public class MathUtility {

  /**
   * @param min the lower bound (inclusive)
   * @param max the upper bound (not inclusive)
   * @return a random number between min and max
   */
  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
