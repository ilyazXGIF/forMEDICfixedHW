import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static  int randomHero;
    public static int[] heroesHealth = {270, 260, 250, 255};
    public static int[] heroesDamage = {10, 15, 20, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseDefence();
        hillHero();
        bossHits();
        heroesHit();
        printStatistics();

    }
    public static void hillHero() {
        Random random1 = new Random();
        int randomHeroes =random1.nextInt(3);
        int randomHill = random1.nextInt(50)+50;
        for (int i = 0; i < heroesHealth.length; i++) {
             if (i==3){
                    continue;
                }
             if (heroesHealth[3] <= 0){
                 break;
            }
             if (heroesHealth[i] > 100){
                 continue;
             }
                else if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    heroesHealth[randomHeroes] = heroesHealth[randomHeroes] + randomHill;
            }

            System.out.println("Medic healed " + randomHill + "xp:" + heroesAttackType[randomHeroes]);
            break;
        }

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            /*if (bossHealth <= 0) {
                break;
            }*/


            int damage = heroesDamage[i];
            if (heroesAttackType[i] == bossDefence) {
                Random random = new Random();
                //int coeff = random.nextInt(10) + 2; // 2,3,4,5,6,7,8,9,10,11
                int coeff = random.nextInt(11); // 0,1,2,3,4,5,6,7,8,9,10
                damage = heroesDamage[i] * coeff;
                System.out.println("Critical damage: " + heroesDamage[i]
                        + " * " + coeff + " = " + damage);
            }
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + round_number + "  _________________");
        System.out.println("Boss health: " + bossHealth + "; damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + "; damage: " + heroesDamage[i]);
        }
    }
}
