package build;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Director director;
		Scores scores;
		ArrayList< String> arrayList = new ArrayList<>();
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		arrayList.add("4");
		arrayList.add("5");
		arrayList.add("6");
		
		FighterBulider bully = new BullyBuilder();
		FighterBulider nimble = new NimbleBuilder();
		FighterBulider tank = new TankBuilder();
		
		director = new Director();
		director.setBuilder(bully);
		director.constructScores(arrayList);
		scores = director.getScores();
		System.out.println(scores.getStrength()+"--"+scores.getConstitution()+"--"+scores.getDexterity());
		
		director = new Director();
		director.setBuilder(nimble);
		director.constructScores(arrayList);
		scores = director.getScores();
		System.out.println(scores.getStrength()+"--"+scores.getConstitution()+"--"+scores.getDexterity());
		
		director = new Director();
		director.setBuilder(tank);
		director.constructScores(arrayList);
		scores = director.getScores();
		System.out.println(scores.getStrength()+"--"+scores.getConstitution()+"--"+scores.getDexterity());
	}

}
